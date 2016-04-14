package com.example.jokingApp.injector.moduel;

import com.example.jokingApp.api.LoginApi;
import com.example.jokingApp.utils.helper.RequestHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by idea-pc on 2016/4/13.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    public LoginApi provideLoginApi(RequestHelper mRequestHelper, SettingPrefHelper mSettingPrefHelper, UserStorage mUserStorage){
        return   new LoginApi( mRequestHelper,  mSettingPrefHelper,  mUserStorage);
    }


}
