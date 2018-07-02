package xyz.yisheng.blog.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.framework.mvc.annotation.Controller;
import priv.framework.proxy.Aspect;
import priv.framework.proxy.AspectProxy;

import java.lang.reflect.Method;


@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    public void before(Class<?> cls, Method method,Object[] params)throws Throwable{
        LOGGER.info("-------begin---------");
        LOGGER.info(String.format("class:  %s",cls.getName()));
        LOGGER.info(String.format("method: %s",method.getName()));
        begin=System.currentTimeMillis();
    }

    public void after(Class<?>cls,Method method,Object[]params,Object result)throws Throwable{
        LOGGER.info(String.format("time: %dms",System.currentTimeMillis()-begin));
        LOGGER.info("-------end------------");
    }
}
