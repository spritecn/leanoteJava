package github.spritecn.leanotJava.service;

import github.spritecn.leanotJava.bean.BaseResponse;
import github.spritecn.leanotJava.bean.BaseResponseInterface;
import github.spritecn.leanotJava.bean.UserResponse;
import github.spritecn.leanotJava.dao.TokenDao;
import github.spritecn.leanotJava.dao.UserDao;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;
import github.spritecn.leanotJava.util.IdGenerator;
import github.spritecn.leanotJava.util.Md5Util;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
    private static final UserDao userDao = new UserDao();
    private static final TokenDao tokenDao = new TokenDao();
    private static UserService userService = new UserService();
    private UserService(){}


    public static UserService newInstance(){
        return userService;
    }

    public BaseResponse login(String email, String pwd){
        //校验用户
        UserModel userModel = userDao.getByEmail(email);
        if(Objects.isNull(userModel)){
            return BaseResponse.failResponse("user not found");
        }
        //校验密码
        if(!Objects.equals(userModel.getPwd(), Md5Util.md5(pwd))){
            return BaseResponse.failResponse("user or password error");
        }

        //生成token
        String token = IdGenerator.genId();
        TokenModel tokenModel = TokenModel.build(token,userModel.getUId());
        tokenDao.insertAndReturnId(tokenModel);

        //构造返回数据
        UserResponse result = new UserResponse();
        result.setUserId(userModel.getUId());
        result.setEmail(userModel.getEmail());
        //用户名没设置用email
        result.setUsername(Objects.nonNull(result.getUsername())?result.getUsername() :result.getEmail());
        result.setToken(token);
        result.setOk(true);
        return result;
    }

    public TokenModel getUserByTOken(String token){
        return tokenDao.getByToken(token);
    }


    public synchronized Integer getUsn(String userId){
       return userDao.getUsn(userId);
    }




}
