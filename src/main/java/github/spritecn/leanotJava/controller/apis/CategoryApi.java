package github.spritecn.leanotJava.controller.apis;


import github.spritecn.leanotJava.constant.CategoryTypeEnum;
import github.spritecn.leanotJava.service.CategoryService;
import spark.Route;


public class CategoryApi {
    private CategoryService categoryService = new CategoryService();

    public static Route addNotebook;
    public static Route deleteNotebook;
    public static Route addTag;
    //标签没啥好删的
    //public static Route delTag;


    public CategoryApi(){
        addNotebook = (req,res) ->{
            String title = req.queryParams("title");
            String parentId = req.queryParams("parentNotebookId");
            String userId = req.attribute("userId");
            return categoryService.addCategory(title,parentId,userId, CategoryTypeEnum.NoteBook);
        };

        deleteNotebook = (req, res) ->{
            String notebookId = req.queryParams("notebookId");
            String usn = req.queryParams("notebookId");
            return categoryService.delNotebook(notebookId);
        };
    }
}
