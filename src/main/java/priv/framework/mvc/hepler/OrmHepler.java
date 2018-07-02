package priv.framework.mvc.hepler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.framework.jdbc.Annotation.Table;
import priv.framework.jdbc.Kit.GetType;
import priv.framework.jdbc.db.DBUtil;
import priv.framework.mvc.DispatcherHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class OrmHepler {
    private static  final Logger LOGGER= LoggerFactory.getLogger(OrmHepler.class);
    private static final Set<Class<?>> CLASS_SET=ClassHelper.getClassSet();
    //这种方式是项目启动初始化创建，但也需要调用的方式创建
    static {
        //先返回某目录下的全部的类，在查看这些类是否有table注解。
        //如果有的话就根据类来创建表，查看表是否已经存在。
        //到时候可以从ClassHelper中获取table注解的类。
        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Table.class)){
                Connection conn= DBUtil.getConnection();
                try {
                    Statement stmt = conn.createStatement();
                    //查看table是否存在
                    ResultSet resultSet=conn.getMetaData().getTables(null,null,cls.getSimpleName(),null);
                    if(resultSet.next()){
                        LOGGER.info(cls.getSimpleName()+"表已存在");
                    }else {
                        LOGGER.info(cls.getSimpleName()+"表不存在，创建...");
                        //获取属性字段名和值
                        Field[] fields= cls.getDeclaredFields();
                        //todo 获取设置主键，以及简化
                        StringBuilder sql=new StringBuilder("CREATE TABLE "+cls.getSimpleName()+" (");
                        for(int i=0;i<fields.length;i++){
                            Field f=fields[i];
                            f.setAccessible(true);
                            sql.append(f.getName()+" ");
                            String type= GetType.getDefaultType(f.getType());
                            sql.append(type);
                            //最后一个","不用插入
                            if(i!=fields.length-1)
                                sql.append(",");
                        }

                        Table annotation = cls.getAnnotation(Table.class);
                        //如果注解上有pk值
                        if(annotation.pk()!=null){
                            sql.append(",PRIMARY KEY ("+annotation.pk()+")");
                        }
                        sql.append(");");
                        //想要创建表的话，还要获取对象中的数据。也许需要用一个框架来简化简化。
                        if(stmt.executeUpdate(sql.toString())==0){
                            LOGGER.info("create table success!");
                        }
                        else LOGGER.info("create table fail!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
