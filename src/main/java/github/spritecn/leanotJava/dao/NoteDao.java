package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.NoteModel;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static github.spritecn.leanotJava.constant.Constant.SYNC_MAX_SIZE;

@Slf4j
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


    public List<NoteModel> ListByUserIdAfterUsn(String userId,Integer afterUsn) {
        NoteModel noteModel = new NoteModel();
        noteModel.setUserId(userId);
        String condition = "usn > " + afterUsn.toString();
        String sqlStr = SqlGenerator.genListByModelAndExtendConditionAndLimit(noteModel,TABLE_NAME,condition,SYNC_MAX_SIZE,null);
        log.info(sqlStr);
        return conn
                .createQuery(sqlStr)
                .executeAndFetch(NoteModel.class);
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
