package github.spritecn.leanotJava.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class TimeUtil {
    private static final String UTC_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    //获取秒级时间戳
    public static  long genTimeStampSecond(){
        return System.currentTimeMillis()/1000;
    }

    public static  long genTimeStampSecond(Date date){
        return date.getTime()/1000;
    }

    //获取utc时间字串
    public static String getUtcTimeStr(Long timeStampSecond) {
        Date date = new Date(timeStampSecond*1000);
        SimpleDateFormat df = new SimpleDateFormat(UTC_TIME_PATTERN);
        return df.format(date);
    }

    //获取utc时间字串
    public static String getUtcTimeStr(Date date) {
        date = Objects.nonNull(date)?date:new Date();
        SimpleDateFormat df = new SimpleDateFormat(UTC_TIME_PATTERN);
        return df.format(date);
    }

    //获取utc时间字串
    public static Date getUtcDate(String  utcTimeStr) {
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(UTC_TIME_PATTERN);
        df.setTimeZone(UTC_TIME_ZONE);
        try {
            date = df.parse(utcTimeStr);
        }catch (Exception e){
            //pass
        }
        return date;
    }
}
