package github.spritecn.leanotJava.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseResponse implements BaseResponseInterface, Serializable {
    private static final long serialVersionUID = 5180362947L;
    private Boolean Ok;
    private String Msg;
    private static final String DEFAULT_MSG= "........................";
    public static BaseResponse failResponse(String msg){
        BaseResponse response = new BaseResponse();
        response.setOk(Boolean.FALSE);
        msg = Objects.nonNull(msg)?msg:DEFAULT_MSG;
        response.setMsg(msg);
        return response;
    }
}
