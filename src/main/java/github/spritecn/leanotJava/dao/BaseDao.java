package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.util.DbConnectionFactory;
import org.sql2o.Connection;

import java.util.Objects;

public class BaseDao {
    Connection conn = null;
    public BaseDao(){
        this.conn = DbConnectionFactory.getConnection();
    }

    //sql2o 的连接默认自动关闭的，理论上不用调
    private void closeConnection(){
        try {
            if (Objects.nonNull(conn)) {
                conn.close();
            }
        }catch (Exception e){
            //pass
        }
    }

}
