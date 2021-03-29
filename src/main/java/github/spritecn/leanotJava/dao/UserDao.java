package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;
import github.spritecn.leanotJava.util.DbConnectionFactory;
import github.spritecn.leanotJava.util.TimeUtil;
import org.sql2o.Connection;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserDao extends BaseDao implements BaseDaoInterface<UserModel> {
    private final String TABLE_NAME = "leanote_user";

    @Override
    public UserModel getById(String id) {
        String sql = SqlGenerator.genDefaultSelectByUIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(UserModel.class);
    }

    @Override
    public UserModel getByUId(String uId) {
        String sql = SqlGenerator.genDefaultSelectByUIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("uId",uId).executeAndFetchFirst(UserModel.class);
    }

    public UserModel getByEmail(String email) {
        String sql = SqlGenerator.genDefaultSelectSqlWithWhere(UserModel.class,TABLE_NAME);
        sql += "email = :email";
        return conn.createQuery(sql).addParameter("email",email).executeAndFetchFirst(UserModel.class);
    }

    @Override
    public List<UserModel> ListAll() {
        return conn.createQuery(SqlGenerator.genDefaultListAllSql(UserModel.class,TABLE_NAME)).executeAndFetch(UserModel.class);
    }

    @Override
    public int updateById(UserModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }

    @Override
    public Long insertAndReturnId(UserModel model) {
        Object key =  conn.createQuery(SqlGenerator.genDefaultInsertByModelSql(model,TABLE_NAME)).executeUpdate().getKey();
        return Long.valueOf(key.toString());
    }

    public Integer getUsn(String uId) {
        //用事务
        Connection   transactionConn = DbConnectionFactory.getTransactionConnection();
        String selectSql = SqlGenerator.genDefaultSelectByUIdSql(TokenModel.class,TABLE_NAME);
        UserModel userModel = transactionConn.createQuery(selectSql).addParameter("uId",uId).executeAndFetchFirst(UserModel.class);
        Integer newUsn = Objects.nonNull(userModel.getLastUsn())?userModel.getLastUsn() + 1:1;
        UserModel updateModel = new UserModel();
        updateModel.setId(userModel.getId());
        updateModel.setLastUsn(newUsn);
        transactionConn.createQuery(SqlGenerator.genDefaultUpdateByIdSql(updateModel,TABLE_NAME)).executeUpdate().getResult();
        transactionConn.commit(true);
        return newUsn;
    }

    public void updateTokenSync(String token,Integer usn){
        TokenModel model = new TokenModel();
        model.setUId(token);
        model.setLastSyncUsn(usn);
        model.setLastSyncTime(TimeUtil.genTimeStampSecond());
        conn.createQuery(SqlGenerator.genDefaultUpdateByUIdSql(model,TABLE_NAME)).executeUpdate().getResult();

    }
}
