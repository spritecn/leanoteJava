package github.spritecn.leanotJava.controller.apis;

import github.spritecn.leanotJava.service.UserService;
import spark.Route;

public class UserApi {
    UserService userService = UserService.newInstance();
    public static Route syncState;
    public UserApi(){
        /*
        - GET //api/user/getSyncState?token=6046093246874c0ce8000488&
        - 返回 {"LastSyncTime":1615769369,"LastSyncUsn":144}
         */
        syncState = (req,res) ->{
            String token = req.queryParams("token");
            return userService.getSyncState(token);
        };
    }
}
