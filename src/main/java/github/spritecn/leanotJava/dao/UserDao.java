package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.UserModel;

import java.util.List;

public class UserDao extends BaseDao implements BaseInterface<UserModel>{
    private final String TABLE_NAME = "leanote_user";
    @Override
    public UserModel getById(Long id) {
        String sql = SqlGenerator.genDefaultSelectByIdSql(UserModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(UserModel.class);
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
}
