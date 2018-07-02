package priv.framework.mvc.bean;

import java.lang.reflect.Method;

/**
 * Created by easom on 2017/11/14.
 */
public class ClassAndAction {
    //Controller类
    private Class<?>controllerClass;
    //Action方法
    private Method actionMethod;

    public ClassAndAction(Class<?>controllerClass,Method actionMethod){
        this.controllerClass=controllerClass;
        this.actionMethod=actionMethod;
    }
    public Class<?>getControllerClass(){
        return controllerClass;
    }
    public Method getActionMethod(){
        return actionMethod;
    }
}
