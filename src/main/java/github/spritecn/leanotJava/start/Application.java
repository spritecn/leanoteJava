package github.spritecn.leanotJava.start;

import github.spritecn.leanotJava.controller.TestController;
import github.spritecn.leanotJava.util.ConfigUtil;
import github.spritecn.leanotJava.util.DbConnectionFactory;

import java.util.Map;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) throws Exception{
        Map<String,String> configMap = ConfigUtil.getConfig(args);
        DbConnectionFactory.init(configMap);
        //暂时不需要静态目录
        //staticFiles.location("/public");
        port(8880);

        new TestController();
    }
}
