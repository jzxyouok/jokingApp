package com.example.jokingApp.injector.moduel;

import android.app.Activity;

import com.example.jokingApp.injector.PerActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by idea-pc on 2016/4/10.
 */
@Module
public class ActivityModule {
    Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity=activity;
    }
    @PerActivity
    @Provides// 注明该方法是用来提供依赖对象的特殊方法
    public Activity provideActivity(){
        return  mActivity;
    }

}
