package com.example.jokingApp;

import android.app.Application;
import android.os.Handler;

/**
 * 代表当前的程序
 * Created by idea-pc on 2016/3/17.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int mainTid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainTid = android.os.Process.myTid();
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });

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
}
