package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.model.TestModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TestDao extends BaseDao  implements BaseInterface<TestModel> {
    @Override
    public TestModel getById(Long id) {
        String sql= "select id,createdTm,name from test where id = :id";
        return conn.createQuery(sql).addParameter("id",id).executeAndFetchFirst(TestModel.class);
    }

    @Override
    public List<TestModel> ListAll() {
        String sql= "select id,createdTm,name from test";
        return conn.createQuery(sql).executeAndFetch(TestModel.class);
    }

    @Override
    public int updateById(TestModel testModel) {
        String sql = "update test set name = :name where id = :id";
        return conn.createQuery(sql)
                .addParameter("id",testModel.getId())
                .addParameter("name",testModel.getName())
                .executeUpdate().getResult();
    }

    @Override
    public Long insertAndReturnId(TestModel model) {
        if(Objects.isNull(model.getCreatedTm())){
            model.setCreatedTm(new Date().getTime());
        }
        String sql = "insert into test (createdTm,name ) values ( :createdTm,:name )";
        Object key = conn.createQuery(sql,true).bind(model).executeUpdate().getKey();
        return Long.valueOf(key.toString());
    }
}
