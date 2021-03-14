package github.spritecn.leanotJava.controller.apis;

import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.service.UserService;
import org.apache.commons.lang3.StringUtils;
import spark.QueryParamsMap;
import spark.Route;


import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static spark.Spark.*;

public class AuthApi {
    UserService userService = UserService.newInstance();
    private final String FIRST_USER_TOKEN_PREFIX = "user";
    public static Route login;

    public AuthApi(){
        login = (req,res) ->{
            String email = req.queryParams("email");
            String pwd = req.queryParams("pwd");
            //密码最少6位
            if(StringUtils.isAnyBlank(email,pwd) || pwd.length()<6){
                halt(400);
            }
            return userService.login(email,pwd);
        };
    }

    //根据token返回userId,检查失败返回null
    public String getUserIdByToken(String token){
        boolean firstUserFlag = isFirstUser(token);
        if(firstUserFlag) return token;
        TokenModel tokenModel = userService.getUserByTOken(token);
        if(Objects.isNull(tokenModel)) return null;
        return tokenModel.getUserId();
    }

    boolean isFirstUser(String token){
        return token.startsWith(FIRST_USER_TOKEN_PREFIX)
                && StringUtils.isNumeric(token.substring(FIRST_USER_TOKEN_PREFIX.length()));
    }
}
