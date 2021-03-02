package github.spritecn.leanotJava.start;

import github.spritecn.leanotJava.controller.TestController;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(8880);
        new TestController();
    }
}
