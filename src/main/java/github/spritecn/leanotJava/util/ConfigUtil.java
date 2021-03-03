package github.spritecn.leanotJava.util;

import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {
    public static Map<String,String> getConfig(String[] args) throws Exception {
        Map<String,String> configMap = new HashMap<>();
        if(args.length == 3){
            configMap.put("dbPath", args[0]);
            configMap.put("dbUser",  args[1]);
            configMap.put("dbPass",  args[2]);
        }else if (args.length == 1) {
            configMap.put("dbPath", args[0]);
        }else {
            throw  new  Exception("args error");
        }
        return configMap;
    }
}
