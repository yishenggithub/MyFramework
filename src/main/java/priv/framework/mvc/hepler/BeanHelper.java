package priv.framework.mvc.hepler;

import priv.framework.kit.ReflectionKit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet=ClassHelper.getBeanClassSet();

        for(Class<?>beanClass:beanClassSet){
            Object obj= ReflectionKit.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }
    //获取Bean映射
    public static Map<Class<?>,Object>getBeanMap(){return BEAN_MAP;}
    //获取Bean实例
    public static <T> T getBean(Class<T>cls){
        if(!BEAN_MAP.containsKey(cls)){

            return (T)ReflectionKit.newInstance(cls);
            //throw new RuntimeException("can not get bean by class: "+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

    //设置Bean实例 //AOP框架
    public static void setBean(Class<?>cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}
