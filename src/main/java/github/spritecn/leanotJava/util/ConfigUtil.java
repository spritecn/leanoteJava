package github.spritecn.leanotJava.util;

import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {
    public static Map<String,String> getConfig(String[] args){
        Map<String,String> configMap = new HashMap<>();
        if(args.length > 0){
            configMap.put("dbPath", args[0]);
            configMap.put("dbUser",  args[3]);
            configMap.put("dbPass",  args[4]);
        }
        return configMap;
    }
}
