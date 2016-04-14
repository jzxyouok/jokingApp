package com.example.jokingApp.injector.moduel;

import android.content.Context;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.ToastHelper;
import com.example.jokingApp.utils.helper.CacheHelper;
import com.example.jokingApp.utils.helper.NetWorkHelper;
import com.example.jokingApp.utils.helper.RequestHelper;
import com.example.jokingApp.utils.helper.SecurityHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by idea-pc on 2016/4/13.
 */
@Module
public class HelperModule {

    @Provides
    @Singleton
    SecurityHelper provideSecurityHelper() {
        return new SecurityHelper();
    }


    @Provides
    @Singleton
    NetWorkHelper provideNetWorkHelper(Context mContext) {
        return new NetWorkHelper(mContext);
    }


    @Provides
    @Singleton
    RequestHelper provideRequestUtil(SecurityHelper securityHelper, Context context, UserStorage mUserStorage, SettingPrefHelper mSettingPrefHelper) {
        return new RequestHelper(securityHelper, context, mUserStorage, mSettingPrefHelper);
    }


    @Provides
    @Singleton
    SettingPrefHelper provideSettingPrefHelper(Context context) {
        return new SettingPrefHelper(context);
    }



    @Provides
    @Singleton
    ToastHelper provideToastHelper(Context mContext) {
        return new ToastHelper(mContext);
    }

    @Provides
    @Singleton
    CacheHelper provideCacheHelper(Context mContext) {
        return new CacheHelper(mContext);
    }

   @Provides
    @Singleton
   RxBus provideRxBus( ) {
        return new RxBus();
    }

}
