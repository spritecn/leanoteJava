package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.model.BaseModel;

import java.util.List;

public interface BaseDaoInterface<T extends BaseModel> {
    T getById(String  id);
    T getByUId(String  uId);
    List<T> ListAll();
    int updateById(T model);
    Long insertAndReturnId(T model);
}
