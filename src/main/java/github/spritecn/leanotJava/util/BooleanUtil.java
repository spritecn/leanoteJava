package github.spritecn.leanotJava.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BooleanUtil {
    private static final String TRUE_LOW = "true";
    private static final String TRUE_SUP = "TRUE";
    private static final String FALSE_LOW = "false";
    private static final String FALSE_SUP = "FALSE";
    private static final List<String> ALLOW_COVERT_STRING = Arrays.asList(TRUE_LOW,TRUE_SUP,FALSE_LOW,FALSE_SUP);

    public static Boolean covertString2Boolean(String str){
        if(Objects.isNull(str) || !ALLOW_COVERT_STRING.contains(str)){
            return null;
        }
        return Objects.equals(str, TRUE_LOW) || Objects.equals(str, TRUE_SUP);
    }

    public static  boolean isTrue(Boolean obj){
        return Objects.nonNull(obj) && obj;
    }
}
