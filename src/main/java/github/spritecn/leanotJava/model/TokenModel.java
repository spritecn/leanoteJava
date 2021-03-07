package github.spritecn.leanotJava.model;

import github.spritecn.leanotJava.util.TimeUtil;
import lombok.Data;

@Data
public class TokenModel extends BaseModel{
    private String token;
    private String userId;
    private Integer lastSyncUsn;
    private Long lastSyncTime;

    public static TokenModel build(String token,String userId){
        TokenModel tokenModel = new TokenModel();
        tokenModel.setToken(token);
        tokenModel.setUserId(userId);
        tokenModel.setCreatedTime(TimeUtil.genTimeStampSecond());
        return tokenModel;
    }
}
