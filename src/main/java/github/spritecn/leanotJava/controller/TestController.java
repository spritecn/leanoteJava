package github.spritecn.leanotJava.controller;


import github.spritecn.leanotJava.util.GsonUtil;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class TestController extends BaseController{
    public TestController(){
        get("/hello", (request, response) -> {
            Map<String,String> map = new HashMap<>();
            map.put("hello","world");
            return GsonUtil.gson.toJson(map);
        });

    }
}
