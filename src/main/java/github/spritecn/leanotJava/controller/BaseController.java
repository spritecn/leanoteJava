package github.spritecn.leanotJava.controller;
import github.spritecn.leanotJava.util.GsonUtil;

import static spark.Spark.*;

public class BaseController {
    BaseController() {
        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });
    }
}
