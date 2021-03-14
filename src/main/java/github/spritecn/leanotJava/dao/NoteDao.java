package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.NoteModel;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;

import java.util.List;

public class NoteDao extends BaseDao implements BaseDaoInterface<NoteModel> {
    private final String TABLE_NAME = "leanote_note";

    @Override
    public NoteModel getById(String id) {
        String sql = SqlGenerator.genDefaultSelectByIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(NoteModel.class);
    }

    @Override
    public NoteModel getByUId(String uId) {
        String sql = SqlGenerator.genDefaultSelectByUIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("uId",uId).executeAndFetchFirst(NoteModel.class);
    }

    @Override
    public List<NoteModel> ListAll() {
        return conn.createQuery(SqlGenerator.genDefaultListAllSql(UserModel.class,TABLE_NAME)).executeAndFetch(NoteModel.class);
    }

    @Override
    public int updateById(NoteModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }


    public int updateByUId(NoteModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByUIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }

    @Override
    public Long insertAndReturnId(NoteModel model) {
        Object key =  conn.createQuery(SqlGenerator.genDefaultInsertByModelSql(model,TABLE_NAME)).executeUpdate().getKey();
        return Long.valueOf(key.toString());
    }
}
