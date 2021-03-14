package github.spritecn.leanotJava.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IdGenerator {
    private static  Random random;
    private static final int FIRST_LOW_CASE_ASCII = 97;
    private static final int SIZE = 12;
    private static final int HASH_START = 10_0000;
    private static final int HASH_END = 100_0000;

    //生成12位id  策略：1位字母 + 随机打乱(7位秒时间chuo数字去掉第一位和后面2位 + 4位字母),保证100秒后无重复
    //时间chuo升位是300年后的事，理论上升了也能正常生成，只是数字比字母多一位
    //测试200万用时3秒，无重复,500万用时11s,2000万测试3将无重复
    public static  String genId(){
        if (random == null)  random =  new Random();
        long hash =  TimeUtil.genTimeStampSecond()/100;
        String hashStr = String.valueOf(hash).substring(1);
        //第一个字符
        char fistChar =  (char)(FIRST_LOW_CASE_ASCII + random.nextInt(26));
        List<Character> characterList = IntStream.range(1,SIZE - hashStr.length())
                .mapToObj(x-> (char) (FIRST_LOW_CASE_ASCII + random.nextInt(26))).collect(Collectors.toList());
        characterList.addAll(hashStr.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        //随机后面字符
        Collections.shuffle(characterList);
        //第一个必须是字每
        characterList.add(0,fistChar);
        StringBuilder sb = new StringBuilder();
        characterList.forEach(sb::append);
        return sb.toString();
    }

}
