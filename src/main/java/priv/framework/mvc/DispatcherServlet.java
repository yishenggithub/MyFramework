package priv.framework.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by easom on 2017/11/14.
 */
public class DispatcherServlet extends HttpServlet {
    private DispatcherHandler dispatcherHandler;
    private LambdaHandler lambdaHandler;
    public void init(ServletConfig config)throws ServletException {
        //dispatcherHandeler中需要的配置？
        this.dispatcherHandler =new DispatcherHandler(config.getServletContext());
        this.lambdaHandler     =new LambdaHandler    (config.getServletContext());
        //初始化相关Helper类 ClassHelper.class, BeanHelper.class,IocHelper.class,ControllerHelper.class
        HelperLoader.init();
    }
    protected void  service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this. dispatcherHandler .handle(request, response);
        this. lambdaHandler     .handle(request, response);
    }
}
