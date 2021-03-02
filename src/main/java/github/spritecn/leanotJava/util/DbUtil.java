package github.spritecn.leanotJava.util;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DbUtil {
    private Sql2o sql2o;

    void init(){
        //TODO:连接初始化

    }
    private Connection getConnection(){
        return sql2o.open();
    }

    private Connection getTransactionConnection(){
        return sql2o.beginTransaction();
    }
}
