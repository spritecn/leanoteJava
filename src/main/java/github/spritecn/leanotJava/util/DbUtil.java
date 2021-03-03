package github.spritecn.leanotJava.util;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DbUtil {
    public static String[] args;
    private static String dbHost;
    private static String dbPort;
    private static String dbUser;
    private static String dbPass;
    private static String dbName;

    private static Sql2o sql2o;


    public static void init() {
        //TODO:连接初始化
        if (args.length != 0) {
            dbHost = args[0];
            dbPort = args[1];
            dbName = args[2];
            dbUser = args[3];
            dbPass = args[4];
        }
        sql2o = new Sql2o("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPass);
    }

    private Connection getConnection() {
        return sql2o.open();
    }

    private Connection getTransactionConnection() {
        return sql2o.beginTransaction();
    }


}
