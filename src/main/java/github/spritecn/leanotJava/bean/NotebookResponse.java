package github.spritecn.leanotJava.bean;


import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class NotebookResponse extends BaseResponse {
    private  String NotebookId;
    private  String TagId;
    private  String UserId;
    private  String Title;
    private  String UrlTitle;
    private  Boolean IsBlog;
    private  Integer Usn;
    private  Boolean IsDeleted;
    private  Boolean IsTrash;
    private  String UpdatedTime;
    private  String CreatedTime;
    private  String PublicTime;

}
