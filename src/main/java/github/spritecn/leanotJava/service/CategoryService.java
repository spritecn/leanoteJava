package github.spritecn.leanotJava.service;

import github.spritecn.leanotJava.bean.BaseResponse;
import github.spritecn.leanotJava.bean.NotebookResponse;
import github.spritecn.leanotJava.constant.CategoryTypeEnum;
import github.spritecn.leanotJava.dao.CategoryDao;
import github.spritecn.leanotJava.model.CategoryModel;
import github.spritecn.leanotJava.util.IdGenerator;
import github.spritecn.leanotJava.util.TimeUtil;

import java.util.Date;
import java.util.Objects;

public class CategoryService {
    private static final CategoryDao categoryDao = new CategoryDao();
    private final UserService userService =  UserService.newInstance();

    public BaseResponse addCategory(String title, String parentId, String userId,CategoryTypeEnum type){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setTitle(title);
        categoryModel.setParentId(parentId);
        categoryModel.setType(type);
        String uId = IdGenerator.genId();
        Date now = new Date();
        categoryModel.setCreatedTime(TimeUtil.genTimeStampSecond(now));
        categoryModel.setUId(uId);
        Integer  usn = userService.getUsn(userId);
        categoryModel.setUsn(usn);
        categoryDao.insertAndReturnId(categoryModel);
        NotebookResponse response = new NotebookResponse();
        response.setOk(true);
        response.setCreatedTime(TimeUtil.getUtcTimeStr(now));
        if(Objects.equals(type,CategoryTypeEnum.NoteBook)) {
            response.setNotebookId(uId);
        }else {
            response.setTagId(uId);
        }
        response.setTitle(title);
        response.setUsn(usn);
        return response;
    }

    public BaseResponse delNotebook(String notebookId){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setUId(notebookId);
        categoryModel.setIsDeleted(true);
        categoryDao.updateByUId(categoryModel);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        return baseResponse;
    }

}
