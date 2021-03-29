package github.spritecn.leanotJava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class NoteModel extends BaseModel {
    //名称
    String title;
    String userId;
    String notebookId;
    String content;
    Boolean isMarkdown;
    String tags;
    String files;
    Boolean isBlog;
    Boolean isTrash;
    Integer usn;
    Long publicTime;
}
