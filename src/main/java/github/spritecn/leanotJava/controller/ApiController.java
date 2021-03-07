package github.spritecn.leanotJava.controller;

import github.spritecn.leanotJava.controller.apis.AuthApi;

import github.spritecn.leanotJava.util.JsonTransformer;
import org.apache.commons.lang3.StringUtils;

import static spark.Spark.*;

public class ApiController {

    public ApiController() {
        new AuthApi();
        path("/api",
            () -> {
                before("/*", (req, res) -> {
                    //token是在url后面扔
                    String token = req.queryParams("token");
                    //api 全局校验token
                    if (StringUtils.isBlank(token) || token.length() < 5) halt(400);
                });
                after("/*", (req, res) -> {
                    //APi全局返回 json
                    res.type("application/json; charset=utf-8");
                });

                //授权api
                path("/auth", () -> {
                    post("/login", AuthApi.login,new JsonTransformer());  //登录
                });
            }
        );
    }
}