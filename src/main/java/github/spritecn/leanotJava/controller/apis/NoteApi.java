package github.spritecn.leanotJava.controller.apis;

import github.spritecn.leanotJava.model.NoteModel;
import github.spritecn.leanotJava.service.NoteService;
import github.spritecn.leanotJava.service.UserService;
import github.spritecn.leanotJava.util.BooleanUtil;
import org.apache.commons.lang3.StringUtils;
import spark.Request;
import spark.Route;


public class NoteApi {
    private NoteService noteService = new NoteService();
    public static Route addNote;
    public static Route updateNote;
    public static Route deleteNote;
    public static Route getSyncNote;


    public NoteApi(){
        addNote = (req,res) ->{
            NoteModel noteModel = req2NoteModel(req);
            return noteService.addNote(noteModel);
        };
        getSyncNote = (req,res) ->{
            Integer afterUsn = Integer.parseInt(req.queryParams("afterUsn"));
            String userId = req.attribute("userId");
            return noteService.getSyncNotes(userId,afterUsn);
        };
    }

    NoteModel req2NoteModel(Request req){
        NoteModel noteModel = new NoteModel();
        noteModel.setTitle(req.queryParams("Title"));
        noteModel.setUserId(req.attribute("userId"));
        noteModel.setNotebookId(req.queryParams("NotebookId"));
        noteModel.setContent(req.queryParams("Content"));
        noteModel.setIsMarkdown(BooleanUtil.covertString2Boolean(req.queryParams("isMarkdown")));
        noteModel.setTags(req.queryParams("Tags"));
        noteModel.setIsBlog(BooleanUtil.covertString2Boolean(req.queryParams("IsBlog")));
        noteModel.setIsTrash(BooleanUtil.covertString2Boolean(req.queryParams("IsTrash")));
        noteModel.setFiles(req.queryParams("Files"));

        //noteModel.setUsn(Integer.valueOf(req.queryParams("Usn")));
        return noteModel;
    }


}
