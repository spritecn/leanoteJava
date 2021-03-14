package github.spritecn.leanotJava.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

//table name leanoteUser

@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends BaseModel {
    private String  email;
    private String pwd;
    private String username;
    private Integer lastUsn;

}
