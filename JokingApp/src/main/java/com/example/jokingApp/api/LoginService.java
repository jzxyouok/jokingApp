package com.example.jokingApp.api;

import com.example.jokingApp.bean.LoginData;
import com.example.jokingApp.bean.UserData;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by idea-pc on 2016/4/13.
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("user/loginUsernameEmail")
    Observable<LoginData> login(@FieldMap Map<String, String> params, @Query("client") String client);


    @FormUrlEncoded
    @POST("user/page")
    Observable<UserData> getUserInfo(@FieldMap Map<String, String> params, @Query("client") String client);
}
