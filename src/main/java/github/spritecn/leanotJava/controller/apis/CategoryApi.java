package github.spritecn.leanotJava.controller.apis;


import github.spritecn.leanotJava.constant.CategoryTypeEnum;
import github.spritecn.leanotJava.service.CategoryService;
import spark.Route;

import java.util.ArrayList;


public class CategoryApi {
    private CategoryService categoryService = new CategoryService();

    public static Route addNotebook;
    public static Route deleteNotebook;
    public static Route getSyncNotebooks;
    public static Route getSyncTags;
    //TODO addTag
    //public static Route addTag;

    public CategoryApi(){
        addNotebook = (req,res) ->{
            String title = req.queryParams("title");
            String parentId = req.queryParams("parentNotebookId");
            String userId = req.attribute("userId");
            return categoryService.addCategory(title,parentId,userId, CategoryTypeEnum.NoteBook);
        };

        deleteNotebook = (req, res) ->{
            String notebookId = req.queryParams("notebookId");
            return categoryService.delNotebook(notebookId);
        };

        getSyncNotebooks = (req,res) ->{
            Integer afterUsn = Integer.parseInt(req.queryParams("afterUsn"));
            String userId = req.attribute("userId");
            return new ArrayList<>();
            //return categoryService.getSyncNotebooks(userId,afterUsn);
        };

        getSyncTags = (req,res) ->{
            Integer afterUsn = Integer.parseInt(req.queryParams("afterUsn"));
            String userId = req.attribute("userId");
            return new ArrayList<>();
            //return categoryService.getSyncNotebooks(userId,afterUsn);
        };
    }
}
