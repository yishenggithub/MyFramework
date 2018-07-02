package priv.framework.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by easom on 2017/11/14.
 */
public class ReflectionKit {
    private static  final Logger LOGGER= LoggerFactory.getLogger(ReflectionKit.class);

    //创建实例
    public static  Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
//            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    //调用方法
    public  static Object invokeMethod(Object obj, Method method,Object...args){
        Object result;
        method.setAccessible(true);
        try{
            result=method.invoke(obj,args);
        }catch (Exception e){
            LOGGER.error("invoke method failure",e);
            throw  new RuntimeException(e);
        }
        return result;
    }

    //设置成员变量的值
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        }catch (IllegalAccessException e){
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
