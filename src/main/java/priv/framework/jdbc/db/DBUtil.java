package priv.framework.jdbc.db;

/**
 * Created by easom on 2017/9/8.
 */

import org.sql2o.Sql2o;
import priv.framework.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    //这里从配置文件中拿
    private static final String URL;//="jdbc:mysql://127.0.0.1:3306/blog_demo?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String USER;//="root";
    private static final String PASSWORD;//="583376938";
    private static final String DRIVER;



    private static Sql2o sql2o;

    //conn这样写是不是有问题？
    private static Connection conn=null;

    static {

        Properties conf = PropsUtil.loadProps("smart.properties");
        DRIVER  = conf.getProperty("smart.framework.jdbc.driver");
        URL     = conf.getProperty("smart.framework.jdbc.url");
        USER    = conf.getProperty("smart.framework.jdbc.username");
        PASSWORD= conf.getProperty("smart.framework.jdbc.password");

        try {
            Class.forName(DRIVER);
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static{
        sql2o = new Sql2o(URL, USER, PASSWORD);
    }

    public static Connection getConnection(){
        return conn;
    }
    public static Sql2o getSql2o() {
        return sql2o;
    }
}