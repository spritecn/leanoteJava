package github.spritecn.leanotJava.controller;

import github.spritecn.leanotJava.controller.apis.AuthApi;

import github.spritecn.leanotJava.controller.apis.CategoryApi;
import github.spritecn.leanotJava.controller.apis.NoteApi;
import github.spritecn.leanotJava.controller.apis.UserApi;
import github.spritecn.leanotJava.util.JsonTransformer;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static spark.Spark.*;

public class ApiController {

    public ApiController() {
        AuthApi authApi = new AuthApi();
        new CategoryApi();
        new NoteApi();
        new UserApi();
        path("/api",
            () -> {
                before("/*", (req, res) -> {
                    //token是在url后面扔
                    String token = req.queryParams("token");
                    //api 全局校验token
                    if (StringUtils.isBlank(token) || token.length() < 5) halt(400);
                    String userId = authApi.getUserIdByToken(token);
                    if(Objects.isNull(userId))  halt(400);
                    //将usrId写入请求中
                    req.attribute("userId",userId);
                });
                after("/*", (req, res) -> {
                    //APi全局返回 json
                    res.type("application/json; charset=utf-8");
                });

                //授权api
                path("/auth", () -> {
                    post("/login", AuthApi.login,new JsonTransformer());  //登录
                });

                //note
                path("/note", () -> {
                    post("/addNote", NoteApi.addNote,new JsonTransformer());  //新增分类
                    post("/updateNote", NoteApi.updateNote,new JsonTransformer());  //新增分类
                    post("/deleteNote", NoteApi.deleteNote,new JsonTransformer());  //新增分类
                });

                //TODO:syncAPI
                /*
                path("/user", () -> {
                    post("/getSyncState", UserApi.getSyncState,new JsonTransformer());
                });
                 */


                //noteBook
                path("/notebook", () -> {
                    post("/addNotebook", CategoryApi.addNotebook,new JsonTransformer());  //新增分类
                    post("/deleteNotebook", CategoryApi.deleteNotebook,new JsonTransformer());  //新增分类
                });

                /* TODO：tag待补
                /tag
                path("/tag", () -> {
                    post("/addTag", CategoryApi.addTag,new JsonTransformer());  //新增TAG
                });
                 */

            }
        );
    }
}