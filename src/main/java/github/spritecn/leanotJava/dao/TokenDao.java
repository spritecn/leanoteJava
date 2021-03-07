package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.TokenModel;

import java.util.List;

public class TokenDao extends BaseDao implements BaseInterface<TokenModel> {
    private final String TABLE_NAME = "leanote_token";
    @Override
    public TokenModel getById(Long id) {
        String sql = SqlGenerator.genDefaultSelectByIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(TokenModel.class);
    }

    public TokenModel getByToken(String token) {
        String sql = SqlGenerator.genDefaultSelectSqlWithWhere(TokenModel.class,TABLE_NAME);
        sql += "token = :token";
        return conn.createQuery(sql).addParameter("token",token).executeAndFetchFirst(TokenModel.class);
    }

    @Override
    public List<TokenModel> ListAll() {
        return conn.createQuery(SqlGenerator.genDefaultListAllSql(TokenModel.class,TABLE_NAME)).executeAndFetch(TokenModel.class);
    }

    @Override
    public int updateById(TokenModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }

    @Override
    public Long insertAndReturnId(TokenModel model) {
        Object key =  conn.createQuery(SqlGenerator.genDefaultInsertByModelSql(model,TABLE_NAME)).executeUpdate().getKey();
        return Long.valueOf(key.toString());
    }
}
