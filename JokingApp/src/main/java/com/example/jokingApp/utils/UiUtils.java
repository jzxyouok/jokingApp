package com.example.jokingApp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;

import com.example.jokingApp.BaseApplication;


/**
 *对一些常用的工具类的封装
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
    /**取消任务*/
    public static void cancel(Runnable auToRunTask) {
        BaseApplication.getHandler().removeCallbacks(auToRunTask);
    }
    /**获取屏幕的高度*/
    public static final int getHeightInPx( ) {
        final int height = getResource().getDisplayMetrics().heightPixels;
        return height;
    }

    /**获取屏幕的宽度 */
    public static final int getWidthInPx() {
        final int width = getResource().getDisplayMetrics().widthPixels;
        return width;
    }







}
