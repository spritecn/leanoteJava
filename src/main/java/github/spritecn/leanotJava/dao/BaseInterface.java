package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.model.BaseModel;

import java.util.List;

public interface BaseInterface<T extends BaseModel> {
    T getById(Long id);
    List<T> ListAll();
    int updateById(T model);
    Long insertAndReturnId(T model);
}
