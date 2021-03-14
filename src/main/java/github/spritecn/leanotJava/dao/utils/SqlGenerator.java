package github.spritecn.leanotJava.dao.utils;

import com.google.gson.reflect.TypeToken;
import github.spritecn.leanotJava.model.BaseModel;
import github.spritecn.leanotJava.util.GsonUtil;
import github.spritecn.leanotJava.util.TimeUtil;
import spark.utils.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

//根据model生成默认sql
public class SqlGenerator {
    /**
     * @param tClass  Model
     * @param tableName 表名
     * @param <T> Model
     * @return 返回默认的SelectById语句的sql
     */
    public static  <T extends BaseModel> String genDefaultSelectByIdSql(Class<T> tClass, String tableName){
        return genDefaultSelectSql(tClass,tableName) + " where id= :id";
    }

    public static  <T extends BaseModel> String genDefaultSelectByUIdSql(Class<T> tClass, String tableName){
        return genDefaultSelectSql(tClass,tableName) + " where uId= :uId";
    }

    public static  <T extends BaseModel> String genDefaultListAllSql(Class<T> tClass, String tableName){
        return genDefaultSelectSql(tClass,tableName);
    }

    public static  <T extends BaseModel> String genDefaultSelectSql(Class<T> tClass, String tableName){
        String columnsStr = getColumnsStr( tClass);
        return  "select " + columnsStr +
                " " +
                "from " +
                tableName;
    }

    public static  <T extends BaseModel> String genDefaultSelectSqlWithWhere(Class<T> tClass, String tableName){
        String columnsStr = getColumnsStr( tClass);
        return  "select " + columnsStr +
                " " +
                "from " +
                tableName + " where ";
    }


    public static  <T extends BaseModel> String genDefaultUpdateByIdSql(T model,String tableName){
        if(Objects.isNull(model.getId())){
            throw new Error("id must not be null");
        }
        return genDefaultUpdateSqlWithWhere(model,tableName) + "id = " + model.getId().toString();
    }

    public static  <T extends BaseModel> String genDefaultUpdateByUIdSql(T model,String tableName){
        if(Objects.isNull(model.getId())){
            throw new Error("id must not be null");
        }
        return genDefaultUpdateSqlWithWhere(model,tableName) + "uId = " + model.getUId().toString();
    }

    //根据model生成insert sql
    //        //INSERT INTO "main"."leanote_user" ("id", "UserId", "Email", "Pwd", "Username", "CreatedTime", "UpdatedTime", "IsDeleted", "LastUsn", "ROWID") VALUES (1, '40C35EBECAB5C', 'admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', NULL, 1615033835, NULL, 0, NULL, 1);
    public static  <T extends BaseModel> String genDefaultInsertByModelSql(T model,String tableName){
        //插入时间
        if(Objects.isNull(model.getCreatedTime())){
            model.setCreatedTime(TimeUtil.genTimeStampSecond());
        }
        String jsonStr = GsonUtil.gson.toJson(model);
        Map<String,Object> jsonMap = GsonUtil.gson.fromJson(jsonStr, new TypeToken<LinkedHashMap<String,Object>>(){}.getType());
        List<String> columns = new ArrayList<>(); //要更新的列
        List<String> values= new ArrayList<>();
        jsonMap.entrySet().forEach(x-> {
                    //id字段忽略，走自动生成
                    if (Objects.equals(x.getKey(), "id")) return;
                    if (Objects.isNull(x.getValue())) return; //
                    columns.add(x.getKey());
                    values.add(genValueString(x.getValue()));
                }
        );
        String keyStr = columns.stream().map(x-> "\"" + x + "\"").collect(Collectors.joining(","));
        String valueStr = String.join(",",values);
        return "insert into " + tableName + "(" + keyStr +") " + "values (" + valueStr + ")";
    }

    //update tableName set a = "a",b = "b" where
    public static  <T extends BaseModel> String genDefaultUpdateSqlWithWhere(T model,String tableName){
        String jsonStr = GsonUtil.gson.toJson(model);
        Map<String,Object> jsonMap = GsonUtil.gson.fromJson(jsonStr, new TypeToken<LinkedHashMap<String,Object>>(){}.getType());
        String keyEqualsValueStr = jsonMap.entrySet().stream()
                .filter(x->Objects.nonNull(x.getValue())) //去除null列
                .filter(x->!Objects.equals(x.getKey(),"id"))//去除id列
                .map(x->x.getKey() + "=" + genValueString(x.getValue())) //生成 key = value,语句
                .collect(Collectors.joining(","));
        if(StringUtils.isBlank(keyEqualsValueStr)){
            throw new Error("model all Null s");
        }
        return  "update " + tableName + " set " + keyEqualsValueStr + " where ";
    }

    private static String genValueString(Object o){
        if(o == null){
            return  "null";
        }
        if(o instanceof Boolean){
            return (Boolean) o ?"1":"0";
        }
        if(o instanceof BigDecimal){
            return ((BigDecimal) o).toPlainString();
        }
        //由于JSON会将所有的Number类转为double，所以需要先转long再获取
        return  (o instanceof Number)?String.valueOf (((Number) o).longValue()):"'" + o.toString() + "'";
    }

    private static  <T extends BaseModel> String getColumnsStr(Class<T> tClass){
        T t = null;
        try {

            t = tClass.newInstance();
        }catch (Exception e){
            //pass
        }
        String jsonStr = GsonUtil.gsonWithNull.toJson(t);
        Map<String,Object> jsonMap = GsonUtil.gsonWithNull.fromJson(jsonStr, new TypeToken<LinkedHashMap<String,Object>>(){}.getType());

        return String.join(",",jsonMap.keySet());
    }








}
