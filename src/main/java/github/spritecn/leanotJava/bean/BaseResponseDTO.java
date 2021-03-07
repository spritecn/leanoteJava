package github.spritecn.leanotJava.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseResponseDTO implements BaseResponse, Serializable {
    private static final long serialVersionUID = 5180362947L;
    private Boolean Ok;
    private String Msg;
    private static final String DEFAULT_MSG= "........................";
    public static BaseResponseDTO failResponse(String msg){
        BaseResponseDTO response = new BaseResponseDTO();
        response.setOk(Boolean.FALSE);
        msg = Objects.nonNull(msg)?msg:DEFAULT_MSG;
        response.setMsg(msg);
        return response;
    }
}
