package github.spritecn.leanotJava.service;

import github.spritecn.leanotJava.bean.BaseResponse;
import github.spritecn.leanotJava.bean.BaseResponseInterface;
import github.spritecn.leanotJava.bean.SyncStateResponse;
import github.spritecn.leanotJava.bean.UserResponse;
import github.spritecn.leanotJava.dao.TokenDao;
import github.spritecn.leanotJava.dao.UserDao;
import github.spritecn.leanotJava.model.TokenModel;
import github.spritecn.leanotJava.model.UserModel;
import github.spritecn.leanotJava.util.IdGenerator;
import github.spritecn.leanotJava.util.Md5Util;
import github.spritecn.leanotJava.util.TimeUtil;

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

    public SyncStateResponse getSyncState(String token){
        TokenModel tokenModel = getUserByToken(token);
        Long timeStamp = TimeUtil.genTimeStampSecond();
        SyncStateResponse response = new SyncStateResponse();
        response.setLastSyncTime(timeStamp);
        response.setLastSyncUsn(tokenModel.getLastSyncUsn());

        tokenModel.setLastSyncTime(timeStamp);
        tokenModel.setUpdatedTime(timeStamp);
        tokenModel.setLastSyncUsn(null);
        tokenDao.updateById(tokenModel);
        return response;
    }


    public TokenModel getUserByToken(String token){
        return tokenDao.getByToken(token);
    }

    //更新token的同步时间和usn
    public synchronized void updateTokenSync(String token,Integer usn){
        userDao.updateTokenSync(token,usn);
    }

    //获取usn
    public synchronized Integer getUsn(String userId){
       return userDao.getUsn(userId);
    }




}
