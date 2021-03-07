package github.spritecn.leanotJava.util;

public class TimeUtil {
    //获取秒级时间戳
    public static  long genTimeStampSecond(){
        return System.currentTimeMillis()/1000;
    }
}
