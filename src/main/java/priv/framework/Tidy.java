package priv.framework;

import priv.framework.mvc.RouteHandler;
import priv.framework.mvc.TestHandler;
import priv.framework.mvc.bean.ClassAndAction;
import priv.framework.mvc.bean.MethodAndPath;
import priv.framework.mvc.bean.Param;
import priv.framework.mvc.route.Route;
import priv.framework.server.JettyServer;
import priv.framework.util.ArrayUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by easom on 2017/11/13.
 */
public final class Tidy {
    private Route router=new Route();

    public static Tidy me(){
        return new Tidy();
    }

    public Tidy get(String path, RouteHandler handler){
        this.router.addRoute(path, handler, "get");
        return this;
    }

    public void start() throws Exception {
        JettyServer jettyServer=new JettyServer();
        jettyServer.start();
    }

    //从main方法添加路由
    public Tidy addAction(String path, Class controller, String methodName) {
        if (path.matches("\\w+:/\\w*")) {
            String[] array = path.split(":");
            if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                //获取请求方法和请求路径
                String requestMethod = array[0];
                String requestPath = array[1];

                try {
                    Method method = controller.getMethod(methodName, Param.class);
                    MethodAndPath methodAndPath = new MethodAndPath(requestMethod, requestPath);
                    ClassAndAction classAndAction = new ClassAndAction(controller, method);
                    //初始化ActionMap
                    Route.addRoute(methodAndPath, classAndAction);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }
}
