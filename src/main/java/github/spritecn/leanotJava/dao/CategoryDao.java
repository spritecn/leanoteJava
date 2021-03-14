package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.dao.utils.SqlGenerator;
import github.spritecn.leanotJava.model.CategoryModel;
import github.spritecn.leanotJava.model.TokenModel;

import java.util.List;

public class CategoryDao extends BaseDao implements BaseDaoInterface<CategoryModel> {
    private final String TABLE_NAME = "leanote_category";

    @Override
    public CategoryModel getById(String id) {
        String sql = SqlGenerator.genDefaultSelectByIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(CategoryModel.class);
    }

    @Override
    public CategoryModel getByUId(String uId) {
        String sql = SqlGenerator.genDefaultSelectByUIdSql(TokenModel.class,TABLE_NAME);
        return conn.createQuery(sql).addParameter("uId",uId).executeAndFetchFirst(CategoryModel.class);
    }

    @Override
    public List<CategoryModel> ListAll() {
        return conn.createQuery(SqlGenerator.genDefaultListAllSql(TokenModel.class,TABLE_NAME)).executeAndFetch(CategoryModel.class);
    }

    @Override
    public int updateById(CategoryModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }

    public int updateByUId(CategoryModel model) {
        return conn.createQuery(SqlGenerator.genDefaultUpdateByUIdSql(model,TABLE_NAME)).executeUpdate().getResult();
    }

    @Override
    public Long insertAndReturnId(CategoryModel model) {
        Object key =  conn.createQuery(SqlGenerator.genDefaultInsertByModelSql(model,TABLE_NAME)).executeUpdate().getKey();
        return Long.valueOf(key.toString());
    }
}
