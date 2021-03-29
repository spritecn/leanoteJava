package github.spritecn.leanotJava.util;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Map;

public class DbConnectionFactory {

    private static Sql2o sql2o;

    public static void init(Map<String,String> configMap) {
        String dbPath = configMap.get("dbPath");
        //sqlite support
        //only support mysql postgresql
        sql2o = new Sql2o(dbPath, configMap.get("dbUser"), configMap.get("dbPass"));
        sql2o.open().close();

    }

    public static Connection getConnection() {
        return sql2o.open();
    }

    public static Connection getTransactionConnection() {
        return sql2o.beginTransaction(java.sql.Connection.TRANSACTION_SERIALIZABLE);
    }


}
