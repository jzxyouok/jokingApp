package com.example.jokingApp.injector.component;

import android.content.Context;

import com.example.jokingApp.*;
import com.example.jokingApp.api.LoginApi;
import com.example.jokingApp.injector.moduel.ApiModule;
import com.example.jokingApp.injector.moduel.ApplicationModule;
import com.example.jokingApp.injector.moduel.HelperModule;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.ToastHelper;
import com.example.jokingApp.utils.helper.CacheHelper;
import com.example.jokingApp.utils.helper.NetWorkHelper;
import com.example.jokingApp.utils.helper.RequestHelper;
import com.example.jokingApp.utils.helper.SecurityHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/11.
 */
@Singleton
@Component(modules = {ApplicationModule.class, HelperModule.class, ApiModule.class} )
public interface ApplicationComponent {
    Context getContext();


    NetWorkHelper getNetWorkHelper();

    SecurityHelper getSecurityHelper();

    UserStorage getUserStorage();


    ToastHelper getToastHelper();


    SettingPrefHelper getSettingPrefHelper();

    RequestHelper getRequestHelper();

    CacheHelper getCacheHelper();

    RxBus  getRxBus();

    LoginApi  getLoginApi();


    void inject(BaseApplication mApplication);
}
