package com.example.jokingApp.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.jokingApp.BaseApplication;


/**
 *
 * Created by idea-pc on 2016/3/20.
 */
public class UiUtils {

    public static Resources getResource() {
        return BaseApplication.getApplication().getResources();
    }

   /** 获取上下文对象 */
    public static Context getContext() {
        return BaseApplication.getApplication();
    }

    /** dip转换px*/
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** px转换为dp*/
    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    /** 提交到主线程运行 */
    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if(android.os.Process.myTid()==BaseApplication.getMainTid()){
            runnable.run();
        }else{
            //获取handler
            BaseApplication.getHandler().post(runnable);
        }
    }
    /**延迟执行任务 */
    public static void postDelay(Runnable runnable,long time){
        BaseApplication.getHandler().postDelayed(runnable,time);
    }


}
