package priv.framework.mvc;


import priv.framework.kit.ReflectionKit;
import priv.framework.mvc.bean.ClassAndAction;
import priv.framework.mvc.bean.MethodAndPath;
import priv.framework.mvc.route.Route;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easom on 2017/11/14.
 */
public class LambdaHandler {
    private static  final Logger LOGGER= LoggerFactory.getLogger(LambdaHandler.class);
    ServletContext servletContext;

    public LambdaHandler(ServletContext servletContext) {
        this.servletContext=servletContext;
    }

    void handle(HttpServletRequest request,HttpServletResponse response){
        //获取请求方法
        String requestMethod=request.getMethod().toLowerCase();
        //获取请求路径
        String requestPath=request.getRequestURI();
        LOGGER.info("requestMethod:=>{};requestPath:=>{}",new Object[]{requestMethod,requestPath});
        //   ClassAndAction classAndAction= Route.getHandler(requestMethod,requestPath);
        MethodAndPath methodAndPath = new MethodAndPath(requestMethod, requestPath);

        Map<MethodAndPath, ClassAndAction> ACTION_MAP = Route.getActionMap();
        ClassAndAction classAndAction=Route.getLambdaActionMap().get(methodAndPath);

        if(classAndAction!=null){
            //反射调用方法
            //调用Action方法
            Method actionMethod=classAndAction.getActionMethod();
//          Object routeHandler=ReflectionKit.newInstance(classAndAction.getControllerClass());
            Class ControllerClass =classAndAction.getControllerClass();
            try {
                Constructor constructor= ControllerClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                RouteHandler ControllerObject=(RouteHandler) constructor.newInstance();
                //RouteHandler的构造函数是private不能实例
                // Object routeHandler=ReflectionKit.newInstance(RouteHandler.class);
                Method method=ControllerObject.getClass().getMethod("handle",new Class[]{HttpServletRequest.class, HttpServletResponse.class});
                ReflectionKit.invokeMethod(ControllerObject,method,request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}