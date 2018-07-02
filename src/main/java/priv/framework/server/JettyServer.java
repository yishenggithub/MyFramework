package priv.framework.server;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import priv.framework.context.WebContextListener;
import priv.framework.handler.JspSetting;
import priv.framework.mvc.DispatcherServlet;
/**
 * Created by easom on 2017/11/10.
 */

public class JettyServer {
    private int port =9000;
    private Server server;
    private WebAppContext webAppContext;
    public void start() throws Exception {
        this.server=new Server(port);
        this.webAppContext = new WebAppContext();
        this.webAppContext.setContextPath("/");
        //这个一定要设置吗，有什么影响
        this.webAppContext.setResourceBase("./src/main/webapp");
        //这个怎么用？
        HttpConfiguration http_config = new HttpConfiguration();
        //为什么要用holder呢？
        ServletHolder servletHolder = new ServletHolder(DispatcherServlet.class);
        //初始化交给监听器吗？
        this.webAppContext.addEventListener(new WebContextListener());
        this.webAppContext.addServlet(servletHolder,"/");
        this.webAppContext.addServlet(DefaultServlet.class,"*.html");
        this.webAppContext.addServlet(DefaultServlet.class,"/assets/*");
        this.webAppContext.addServlet(DefaultServlet.class,"/ckeditor/");
        this.webAppContext.addServlet(DefaultServlet.class,"*.js");
        //todo jsp设置
        webAppContext= JspSetting.WebAppSetting(webAppContext);
        server=JspSetting.ServerSetting(server);

        this.server.setHandler(webAppContext);
        this.server.start();
    }
}
