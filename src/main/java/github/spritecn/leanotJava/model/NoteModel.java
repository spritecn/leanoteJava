package github.spritecn.leanotJava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
public class NoteModel extends BaseModel {
    String type;
    String title;
    String notebookId;
    String content;
    Boolean isMarkdown;
    String tags;
    String files;
    Boolean isBlog;
    Boolean isTrash;
    Boolean isDeleted;
    Integer usn;
    Long publicTime;
}
