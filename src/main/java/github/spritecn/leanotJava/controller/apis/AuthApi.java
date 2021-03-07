package github.spritecn.leanotJava.controller.apis;

import github.spritecn.leanotJava.service.UserService;
import org.apache.commons.lang3.StringUtils;
import spark.QueryParamsMap;
import spark.Route;


import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

public class AuthApi {
    UserService userService =new UserService();
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
}
