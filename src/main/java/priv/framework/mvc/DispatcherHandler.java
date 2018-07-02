package priv.framework.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.framework.kit.ReflectionKit;
import priv.framework.mvc.bean.*;
import priv.framework.mvc.hepler.BeanHelper;
import priv.framework.mvc.hepler.ConfigHelper;
import priv.framework.mvc.hepler.ControllerHelper;
import priv.framework.mvc.route.Route;
import priv.framework.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DispatcherHandler {

    private static  final Logger LOGGER= LoggerFactory.getLogger(DispatcherHandler.class);
    ServletContext servletContext;

    public DispatcherHandler(ServletContext servletContext) {
        this.servletContext=servletContext;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        //获取请求方式和请求路径
        String requestMethod=request.getMethod().toLowerCase();
        //String requestPath=request.getPathInfo();
        String requestPath=request.getRequestURI();
        //获取Action处理器，一个方法和请求类型对应一个Handler中的controller类和action方法，存储在 ControllerHelper的map中
        //应该是在HelperLoader.init();中，ControllerHelper已经完成了映射储存。
        LOGGER.info("requestMethod:=>{};requestPath:=>{}",new Object[]{requestMethod,requestPath});

        ClassAndAction classAndAction= Route.getHandler(requestMethod,requestPath);

        if(classAndAction!=null){

            //获取Controller类以其Bean实例
            // 用于下面反射调用controller中的action方法
            Class<?>controllerClass= classAndAction.getControllerClass();
            //为什么要从BeanHelper中获取
            Object controllerBean = BeanHelper    .getBean(controllerClass);
           // Object controllerBean= ReflectionKit.newInstance(controllerClass);

            //创建请求参数对象，用于存放body里的映射关系，也可以存放response
            Map<String,Object> paramMap=new HashMap<String,Object>();

            String body= CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));//将请求体转为字符串

            if(StringUtil.isNotEmpty(body)) {
                // String[]params=StringUtil.splitString(body,"&");
                if (request.getContentType() != null) { //判断是不是json
                    String[] contentType = request.getContentType().split(";");
                    if (contentType[0].equals("application/json")) {
                        paramMap = JsonUtil.toMap(body);
                        paramMap.put("jsonBody",body);
                    }
                } else {
                    String[] params = body.split("&");
                    if (ArrayUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            //    String[]array=StringUtil.splitString(param,"=");
                            String[] array = param.split("=");
                            if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                String paramName = array[0];
                                String paramValue = array[1];
                                paramMap.put(paramName, paramValue);
                            }

                        }
                    }
                }
            }
            //调换一下顺序
            //将HttpServletResponse response放入到param，以便调用
            paramMap.put("response",response);
            //将HttpServletResponse response放入到param，以便调用
            paramMap.put("request",request);

            //这样子把参数放到一个paramMap中，相比直接用request有什么好处？
            Enumeration<String> paramNames=request.getParameterNames();
            while (paramNames.hasMoreElements()){
                String  paramName=paramNames.nextElement();
                String  paramValue=request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }

            Param param=new Param(paramMap);
            //调用param中的get方法就不要每次转化类型
            //调用Action方法
            Method actionMethod=classAndAction.getActionMethod();

            //这一行是重点。反射调用controller中的方法
            Object result= ReflectionKit.invokeMethod(controllerBean,actionMethod,param);

            //处理Action方法返回值
            if(result instanceof View){
                //返回JSP页面
                View view=(View)result;
                String path=view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        response.sendRedirect(request.getContextPath()+path);
                    }else{
                        Map<String,Object>model=view.getModel();
                        for(Map.Entry<String,Object>entry:model.entrySet()){
                            request.setAttribute(entry.getKey(),entry.getValue());
                        }
                        //关于jetty处理jsp
                        request.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(request,response);
                        //request.getRequestDispatcher("/view/"+path).forward(request,response);
                    }
                }
            }else if (result instanceof Data){
                //返回JSON数据
                Data data=(Data)result;
                Object model=data.getModel();
                if (model!=null){
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer=response.getWriter();
                    String json=JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }

        }
    }
}
