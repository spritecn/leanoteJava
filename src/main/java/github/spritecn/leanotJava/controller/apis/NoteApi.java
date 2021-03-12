package github.spritecn.leanotJava.controller.apis;

import github.spritecn.leanotJava.service.UserService;
import org.apache.commons.lang3.StringUtils;
import spark.Route;


public class NoteApi {
    UserService userService =new UserService();
    public static Route addNote;

    public NoteApi(){
        addNote = (req,res) ->{
            String title = req.queryParams("Title");
            String notebookId = req.queryParams("NotebookId");
            String content = req.queryParams("Content");
            String IsMarkdown = req.queryParams("isMarkdown");
            String tags = req.queryParams("Tags");
            String isBlog = req.queryParams("IsBlog");
            String updatedTime = req.queryParams("UpdatedTime");
            String createdTime = req.queryParams("CreatedTime");
            String isTrash = req.queryParams("IsTrash");
            String isDeleted = req.queryParams("IsDeleted");
            String usn = req.queryParams("Usn");
            //"Files":[{"FileId":"6049778146874c0ce8000518","LocalFileId":"60497733e4857224cd000000","Type":"png","Title":"test","HasBody":true,"IsAttach":true}]
            String files = req.queryParams("Files");
            return null;
        };
    }


}
