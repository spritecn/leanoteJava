package github.spritecn.leanotJava.model;


import lombok.Data;

//table name leanoteUser
@Data
public class UserModel extends BaseModel {
    private String userId;
    private String  email;
    private String pwd;
    private String username;
    private Integer lastUsn;

}
