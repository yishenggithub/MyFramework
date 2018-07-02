package priv.framework.mvc.hepler;

import priv.framework.mvc.annotation.Action;
import priv.framework.mvc.bean.ClassAndAction;
import priv.framework.mvc.bean.MethodAndPath;
import priv.framework.mvc.route.Route;
import priv.framework.util.ArrayUtil;
import priv.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {
    static {
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet=ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?>controllerClass:controllerClassSet){
                Method[]methods=controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    for(Method method:methods){
                        if(method.isAnnotationPresent(Action.class)){
                            //从Action注解中获取URL映射规则
                            Action action=method.getAnnotation(Action.class);
                            String mapping=action.value();
                            //验证url映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[]array=mapping.split(":");
                                if(ArrayUtil.isNotEmpty(array)&&array.length==2){
                                    //获取请求方法和请求路径
                                    String requestMethod=array[0];
                                    String requestPath=array[1];
                                    MethodAndPath methodAndPath=new MethodAndPath(requestMethod,requestPath);
                                    ClassAndAction classAndAction=new ClassAndAction(controllerClass,method);
                                    //初始化ActionMap
                                    // ACTION_MAP.put(methodAndPath,classAndAction);
                                    Route.addRoute(methodAndPath,classAndAction);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
