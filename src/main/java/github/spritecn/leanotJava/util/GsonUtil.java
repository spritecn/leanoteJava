package github.spritecn.leanotJava.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class GsonUtil {
    public static final Gson gsonWithNull = new GsonBuilder()
		.serializeNulls()
		.create();
	public static final Gson gson = new GsonBuilder().create();
	public static String toJson(Object o){
		return gson.toJson(o);
	}
}
