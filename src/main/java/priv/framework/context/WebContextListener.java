package priv.framework.context;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by easom on 2017/11/12.
 */
public class WebContextListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
  //    ServletContext servletContext = servletContextEvent.getServletContext();
        LOGGER.info("在webcontext监听器中初始化");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    //todo 自定义的吗
    }
}
