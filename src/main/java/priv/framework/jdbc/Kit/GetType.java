package priv.framework.jdbc.Kit;

/**
 * Created by easom on 2017/10/30.
 */
//用来获取数据默认的数据类型

public class GetType {
    static public String getDefaultType(Class type){
        String TypeString=null;
        if (type.getName().equals("int")||type.getName().equals("java.lang.Integer"))
            TypeString="int";
        else if(type.getName().equals("java.lang.String"))
            TypeString="varchar(10)";
        return TypeString;
    }
}
