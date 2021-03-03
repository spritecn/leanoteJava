package github.spritecn.leanotJava.start;

import github.spritecn.leanotJava.controller.TestController;
import github.spritecn.leanotJava.util.DbUtil;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Application {
    public static void main(String[] args) {
        DbUtil.args = args;
        DbUtil.init();
        staticFiles.location("/public");
        port(8880);
        System.out.println(args[0]);
        System.out.println("11");
        new TestController();
    }
}
