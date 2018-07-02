package priv.framework.jdbc.Kit;


import priv.framework.jdbc.Annotation.Table;

/**
 * Created by easom on 2017/11/2.
 */
public class GetPk {
    static public String getPk(Class<?> entityClass){
        Table table=entityClass.getAnnotation(Table.class);
        if (null != table) {
            return table.pk().toLowerCase();
        }
        return "id";
    }
}
