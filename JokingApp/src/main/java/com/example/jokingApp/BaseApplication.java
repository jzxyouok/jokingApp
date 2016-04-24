package com.example.jokingApp;

import android.app.Application;
import android.os.Handler;

import com.example.jokingApp.injector.component.ApplicationComponent;
import com.example.jokingApp.injector.component.DaggerApplicationComponent;
import com.example.jokingApp.injector.moduel.ApplicationModule;

/**
 * 代表当前的程序
 * Created by idea-pc on 2016/3/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int mainTid;
    private static Handler handler;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainTid = android.os.Process.myTid();
        handler = new Handler();
        initComponent();
    }
    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static Handler getHandler() {
        return handler;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
