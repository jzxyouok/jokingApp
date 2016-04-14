package com.example.jokingApp.api;

import com.example.jokingApp.bean.LoginData;
import com.example.jokingApp.bean.UserData;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.helper.RequestHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by idea-pc on 2016/4/13.
 */
public class LoginApi {


    private LoginService mGameService;
    private RequestHelper mRequestHelper;
    private SettingPrefHelper mSettingPrefHelper;
    private UserStorage mUserStorage;
    private final LoginService mLoginService;

    public LoginApi(RequestHelper mRequestHelper, SettingPrefHelper mSettingPrefHelper, UserStorage mUserStorage) {
        this.mRequestHelper = mRequestHelper;
        this.mSettingPrefHelper = mSettingPrefHelper;
        this.mUserStorage = mUserStorage;
        //
        mLoginService = RetrofitUtils.createApiToString(LoginService.class);
    }

    /**
     *用户执行登录操作
     * 携带数据  客户端/用户名/密码/签名信息/
     * @param userName
     * @param passWord
     * @return
     */
    public Observable<LoginData> login(String userName, String passWord) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("client", mRequestHelper.getDeviceId());
        params.put("username", userName);
        params.put("password", passWord);
        String sign = mRequestHelper.getRequestSign(params);
        params.put("sign", sign);
        return mGameService.login(params, mRequestHelper.getDeviceId()).subscribeOn(Schedulers.io());
    }


    public Observable<UserData> getUserInfo(String uid) {
        Map<String, String> params = mRequestHelper.getHttpRequestMap();
        params.put("puid", uid);
        String sign = mRequestHelper.getRequestSign(params);
        params.put("sign", sign);
        return mGameService.getUserInfo(params, mRequestHelper.getDeviceId()).subscribeOn(Schedulers.io());
    }

}
