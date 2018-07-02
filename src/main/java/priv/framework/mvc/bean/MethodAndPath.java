package priv.framework.mvc.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by easom on 2017/11/14.
 */
public class MethodAndPath {
    //请求方法
    private String requestMethod;
    //请求路径
    private String requestPath;

    public MethodAndPath(String requestMethod,String requestPath){
        this.requestMethod=requestMethod;
        this.requestPath=requestPath;
    }
    public MethodAndPath(){

    }

    public String getRequestMethod(){
        return requestMethod;
    }
    public String getRequestPath(){
        return requestPath;
    }


    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this,obj);
    }

}
