package github.spritecn.leanotJava.service;

import github.spritecn.leanotJava.bean.BaseResponseDTO;
import github.spritecn.leanotJava.bean.BaseResponse;
import github.spritecn.leanotJava.bean.LoginSuccessDTO;
import github.spritecn.leanotJava.dao.TokenDao;
import github.spritecn.leanotJava.dao.UserDao;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;
import github.spritecn.leanotJava.util.IdGenerator;
import github.spritecn.leanotJava.util.Md5Util;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class UserService {
    private static  final String USER = "user";
    private static final UserDao userDao = new UserDao();
    private static final TokenDao tokenDao = new TokenDao();

    public  BaseResponse login(String email, String pwd){
        //校验用户
        UserModel userModel = userDao.getByEmail(email);
        if(Objects.isNull(userModel)){
            return BaseResponseDTO.failResponse("user not found");
        }
        //校验密码
        if(!Objects.equals(userModel.getPwd(), Md5Util.md5(pwd))){
            return BaseResponseDTO.failResponse("user or password error");
        }

        //生成token
        String token = IdGenerator.genId();
        TokenModel tokenModel = TokenModel.build(token,userModel.getUserId());
        tokenDao.insertAndReturnId(tokenModel);

        //构造返回数据
        LoginSuccessDTO result = new LoginSuccessDTO();
        result.setUserId(userModel.getUserId());
        result.setEmail(userModel.getEmail());
        //用户名没设置用email
        result.setUsername(Objects.nonNull(result.getUsername())?result.getUsername() :result.getEmail());
        result.setToken(token);
        result.setOk(true);
        return result;
    }




    //根据token判断 是不是第一次登录
    static boolean checkFirstLogin(String token){
        if(!StringUtils.startsWith(token,"user")) return false;
        return StringUtils.isNumeric(token.substring(USER.length()));
    }

}
