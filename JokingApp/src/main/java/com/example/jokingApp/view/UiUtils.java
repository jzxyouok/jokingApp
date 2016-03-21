package com.example.jokingApp.view;

import android.content.Context;
import android.content.res.Resources;

import com.example.jokingApp.BaseApplication;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class UiUtils {
    public static Context getContext(){
        return BaseApplication.getApplication();
    }
    public static Resources getResource() {
        return BaseApplication.getApplication().getResources();
    }
    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** px转换dip */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    /**
     * 把Runnable 方法提交到主线程运行
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if(android.os.Process.myTid()==BaseApplication.getMainTid()){
            runnable.run();
        }else{
            //获取handler
            BaseApplication.getHandler().post(runnable);
        }
    }
}
