package github.spritecn.leanotJava.bean;

import lombok.Data;

@Data
public class UserResponse extends BaseResponse {
        private String Token;
        private String UserId;
        private String Email;
        private String Username;
}
