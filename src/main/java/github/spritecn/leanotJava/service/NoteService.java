package github.spritecn.leanotJava.service;

import github.spritecn.leanotJava.bean.BaseResponse;
import github.spritecn.leanotJava.bean.NoteResponse;
import github.spritecn.leanotJava.bean.NotebookResponse;
import github.spritecn.leanotJava.constant.CategoryTypeEnum;
import github.spritecn.leanotJava.dao.CategoryDao;
import github.spritecn.leanotJava.dao.NoteDao;
import github.spritecn.leanotJava.model.CategoryModel;
import github.spritecn.leanotJava.model.NoteModel;
import github.spritecn.leanotJava.util.IdGenerator;
import github.spritecn.leanotJava.util.TimeUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class NoteService {
    private static final NoteDao noteDao = new NoteDao();
    private final UserService userService =  UserService.newInstance();

    public BaseResponse addNote(NoteModel noteModel){
        //生成id
        String uId = IdGenerator.genId();
        Date now = new Date();
        noteModel.setCreatedTime(TimeUtil.genTimeStampSecond(now));
        noteModel.setUId(uId);
        //获取usn
        Integer  usn = userService.getUsn(noteModel.getUId());
        noteModel.setUsn(usn);
        if(noteModel.getIsBlog()){
            noteModel.setPublicTime(noteModel.getCreatedTime());
        }
        //TODO:附件文件处理
        noteDao.insertAndReturnId(noteModel);
        return noteModel2NotebookResponse(noteModel);
    }

    NoteResponse noteModel2NotebookResponse(NoteModel noteModel){
        NoteResponse response = new NoteResponse();
        response.setUsn(noteModel.getUsn());
        response.setTags(Objects.nonNull(noteModel.getTags())?noteModel.getTags().split(","): ArrayUtils.EMPTY_STRING_ARRAY);
        response.setTitle(noteModel.getTitle());
        response.setNotebookId(noteModel.getNotebookId());
        response.setCreatedTime(TimeUtil.getUtcTimeStr(noteModel.getCreatedTime()));
        response.setUpdatedTime(Objects.nonNull(noteModel.getUpdatedTime())?TimeUtil.getUtcTimeStr(noteModel.getUpdatedTime()):response.getCreatedTime());
        response.setOk(true);
        response.setIsBlog(noteModel.getIsBlog());
        response.setIsTrash(noteModel.getIsTrash());
        response.setIsDeleted(noteModel.getIsDeleted());
        response.setUrlTitle(response.getTitle());
        response.setPublicTime(Objects.nonNull(noteModel.getPublicTime())?TimeUtil.getUtcTimeStr(noteModel.getPublicTime()):null);
        return response;
    }

}
