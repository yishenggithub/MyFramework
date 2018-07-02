package priv.framework.jdbc;

import priv.framework.jdbc.Kit.GetPk;
import priv.framework.jdbc.db.DBUtil;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by easom on 2017/10/21.
 */
public class ActiveRecord {

    private String tableName;
    private Class entityClass;
    private Object entityObject;
    Sql2o sql2o= DBUtil.getSql2o();
    Connection conn =sql2o.open();
    private Map<String,Object> fieldMap=new HashMap<String, Object>();

    public  void save(Object entityClass)  {
        Field[] fields=entityClass.getClass().getDeclaredFields();
        StringBuilder name=new StringBuilder("(");
        StringBuilder value=new StringBuilder("(");

        for(int i=0;i<fields.length;i++){
           fields[i].setAccessible(true);
            if(i!=0) {
                name.append(","); value.append(",");
            }
            name.append(fields[i].getName());
            value.append(":"+fields[i].getName());
        }
        name.append(")"); value.append(")");
        String sql2 = "insert into " +entityClass.getClass().getSimpleName()+name +
                      " values "+value;
        conn.createQuery(sql2).bind(entityClass).executeUpdate();
    }

    public void delete(String field,Object value){
        String sql="delete from "+entityClass.getSimpleName()+" where "+field+"="+value.toString();
        conn.createQuery(sql).executeUpdate();

    }
    //todo 有一部分代码和下面重复
    public ActiveRecord update(String field,Object value)  {

        String sql="update "+tableName+" set "+field+" = '"+value.toString()+"' where ";
        StringBuilder sbQ=new StringBuilder(sql);

        Field[] fields= entityClass.getDeclaredFields();
        try {
        for(int i=0,j=0;i<fields.length;i++){
            Field f=fields[i];
            f.setAccessible(true);
            Object o= null;
            o = f.get(entityObject);
            //Object o=f.get(entityObject) 后无法正常使用
            if(o!=null){

             if(f.get(entityObject).toString()!=null && !f.get(entityObject).toString().equals("0")){
                 if (j!=0/*&&i!=fields.length-1*/){
                     sbQ.append(" and ");
                 }
                 sbQ.append(f.getName()+"='"+o.toString()+"'");
                //不能用i来判断，i是总数和插入的数目无关。第一个and和最后一个不用插入。如何知道是最后一个？
                j++;
             }
            }
        }
        } catch (IllegalAccessException e) {e.printStackTrace();}

        conn.createQuery(sbQ.toString()).executeUpdate();
        return this;
    }
    //使用默认主键更新
    public ActiveRecord update(Object entityObject){
        String pk= GetPk.getPk(entityObject.getClass());
        this.find(entityObject);
        String sql="update "+tableName+" set ";  //+field+" = '"+value.toString()+"' where ";
        StringBuilder sql2=new StringBuilder(sql);
        int i = 0;
        //todo 遍历map，构建SQL语句，获取主键的指，根据名。
        for (Map.Entry<String,Object> entry  :  fieldMap.entrySet()) {
            if(i != 0){sql2.append(",");}
            sql2.append(entry.getKey()+"='"+entry.getValue()+"'");
            i++;
        }

        // 将属性的首字符大写，方便构造get，set方法
       // String value=fieldMap.get(pk);
        sql2.append(" where "+pk+"="+fieldMap.get(pk));
        conn.createQuery(sql2.toString()).executeUpdate();
        return this;
    }

    //使用class查找表的名字
    public ActiveRecord find(Class entityClass){
        tableName=entityClass.getSimpleName();
        this.entityClass=entityClass;
        return this;
    }

    //过滤掉没有值的属性，把有值得放到map中
    public ActiveRecord find(Object entityObject){
        tableName=entityObject.getClass().getSimpleName();
        entityClass=entityObject.getClass();
        this.entityObject=entityObject;

        Field[] fields= entityClass.getDeclaredFields();
        try {
            for(int i=0;i<fields.length;i++){
            Field f=fields[i];
            f.setAccessible(true);
            //Object o=f.get(entityObject) 后无法正常使用
            Object  o = f.get(entityObject);
            if(o!=null){
                if(f.get(entityObject).toString()!=null && !f.get(entityObject).toString().equals("0")){

                        fieldMap.put(f.getName(),f.get(entityObject).toString());
                    }
                }
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }
    //2017/11/2
    //使用主键来查询
    public <T>T get(T entityObject,Object id){
        String pk= GetPk.getPk(entityObject.getClass()) ;
        String sql= "select * from " + entityObject.getClass().getSimpleName() + " where " + pk + " = :p1";

        Query query=conn.createQuery(sql).withParams(id);
        T t= (T) query.executeAndFetchFirst(entityObject.getClass());
        return t;
    }

    //2018/5/6
    //使用实体来查询
    public  <T>List<T> get(T entityObject){
        String pk= GetPk.getPk(entityObject.getClass());
        this.find(entityObject);
        String sql="select * from "+tableName+" where ";  //+field+" = '"+value.toString()+"' where ";
        StringBuilder sql2=new StringBuilder(sql);
        int i = 0;
        for (Map.Entry<String,Object> entry  :  fieldMap.entrySet()) {
            if(i != 0){sql2.append(",");}
            sql2.append(entry.getKey()+"='"+entry.getValue()+"'");
            i++;
        }

        Query query=conn.createQuery(sql2.toString());
        List<T> list= (List<T>) query.executeAndFetch(entityObject.getClass());
        return list;
    }

    //  List<Students> students3=activeRecord.getAll(students);
    public <T>List<T> getAll(T entityObject){
        String sql="select * from " + entityObject.getClass().getSimpleName();
        List<T> list = (List<T>) conn.createQuery(sql).executeAndFetch(entityObject.getClass());
        return list;
    }

}
