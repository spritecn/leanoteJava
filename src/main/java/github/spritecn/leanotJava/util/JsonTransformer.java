package github.spritecn.leanotJava.util;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object model) throws Exception {
        return GsonUtil.toJson(model);
    }
}
