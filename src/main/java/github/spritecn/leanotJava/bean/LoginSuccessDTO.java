package github.spritecn.leanotJava.bean;

import lombok.Data;

@Data
public class LoginSuccessDTO extends BaseResponseDTO{
        private String Token;
        private String UserId;
        private String Email;
        private String Username;
}
