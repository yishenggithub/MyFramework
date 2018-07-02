package priv.framework.mvc.hepler;

import priv.framework.mvc.annotation.Controller;
import priv.framework.mvc.annotation.Service;
import priv.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class ClassHelper {
    //定义类集合（用于存放所加载的类）
    private static final Set<Class<?>> CLASS_SET;

    static {
        //String basePackage = ConfigHelper.getAppBasePackage();
        //todo 这里要从配置文件中取
        String basePackage= "xyz.yisheng";
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }
    // 获取应用包名下的所有类
    public static Set<Class<?>> getClassSet(){return CLASS_SET;}
    //获取应用包名下所有的Service类
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?>cls:CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    //获取应用包名下所有的Controller类
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?>cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    //获取应用包名下所有的Bean类（包括：Service,Controller）
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>>beanClassSet=new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    //加载AOP框架//获取应用包名下某父类或接口的所有子类（或实现类）
    public  static Set<Class<?>>getClassSetBySuper(Class<?>superClass){
        Set<Class<?>>classSet=new HashSet<Class<?>>();
        for(Class<?>cls:CLASS_SET){
            if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    //获取应用包名下带有某注解的所有类
    public static  Set<Class<?>>getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet =new HashSet<Class<?>>();
        for(Class<?>cls:CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}