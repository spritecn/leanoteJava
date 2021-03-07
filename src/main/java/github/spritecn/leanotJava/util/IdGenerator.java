package github.spritecn.leanotJava.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IdGenerator {
    private static  Random random;
    private static final int FIRST_LOW_CASE_ASCII = 97;
    private static final int SIZE = 12;

    //生成12位id  策略：1位字母 + 随机打乱(uuid hashCode + x位字母)
    //测试200万用时5秒，无重复,500万用时15s,3000万测试时在2000以上重复3次
    public static  String genId(){
        if (random == null)  random =  new Random();
        int hash = Double.valueOf(random.nextDouble()).hashCode();
        String hashStr = String.valueOf(Math.abs(hash));
        //第一个字符
        char fistChar =  (char)(FIRST_LOW_CASE_ASCII + random.nextInt(26));
        List<Character> characterList = IntStream.range(1,SIZE - hashStr.length())
                .mapToObj(x-> (char) (FIRST_LOW_CASE_ASCII + random.nextInt(26))).collect(Collectors.toList());
        characterList.addAll(hashStr.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        //随机后面字符
        Collections.shuffle(characterList);
        //第一个必须是字每
        characterList.add(0,fistChar);
        return characterList.stream().map(String::valueOf).collect(Collectors.joining());
    }

}
