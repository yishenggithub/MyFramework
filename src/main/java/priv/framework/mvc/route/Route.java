package priv.framework.mvc.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.framework.mvc.RouteHandler;
import priv.framework.mvc.TestHandler;
import priv.framework.mvc.bean.ClassAndAction;
import priv.framework.mvc.bean.MethodAndPath;
import priv.framework.util.ClassUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by easom on 2017/11/14.
 */
public class  Route {
    private static final Logger LOGGER= LoggerFactory.getLogger(Route.class);
    private static final Map<MethodAndPath, ClassAndAction> LAMBDA_ACTION_MAP = new HashMap<MethodAndPath, ClassAndAction>();
    private static final Map<MethodAndPath, ClassAndAction> ACTION_MAP = new HashMap<MethodAndPath, ClassAndAction>();

    public  static void addRoute(String path, RouteHandler handler, String get) {
        MethodAndPath methodAndPath = new MethodAndPath(get, path);
        ClassAndAction classAndAction = null;
        try {
            classAndAction = new ClassAndAction(handler.getClass(), handler.getClass().getMethod("handle", new Class[]{HttpServletRequest.class, HttpServletResponse.class}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        LAMBDA_ACTION_MAP.put(methodAndPath, classAndAction);
        LOGGER.info("addRoute:"+LAMBDA_ACTION_MAP.get(methodAndPath).getControllerClass().getSimpleName());
    }
    //由controllerHepler调用,添加注解方式的映射
    public static void addRoute( MethodAndPath methodAndPath,ClassAndAction classAndAction){
        ACTION_MAP.put(methodAndPath, classAndAction);
        LOGGER.info("addRoute:"+ACTION_MAP.get(methodAndPath).getControllerClass().getSimpleName());
    }

    public static ClassAndAction getLambdaHandler(String requestMethod,String requestPath){
        MethodAndPath methodAndPath=new MethodAndPath(requestMethod,requestPath);
        return LAMBDA_ACTION_MAP.get(methodAndPath);
    }
    public static ClassAndAction getHandler(String requestMethod,String requestPath){
        MethodAndPath methodAndPath=new MethodAndPath(requestMethod,requestPath);
        return ACTION_MAP.get(methodAndPath);
    }

    public static Map<MethodAndPath, ClassAndAction> getLambdaActionMap() {
        return LAMBDA_ACTION_MAP;
    }

    public static Map<MethodAndPath, ClassAndAction> getActionMap(){
        return ACTION_MAP;
    }
}

