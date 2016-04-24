package com.example.jokingApp.utils.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.PrefUtils;

/**
 *
 * 这个类来 保存用户偏好设置
 * Created by idea-pc on 2016/4/11.
 */
public class SettingPrefHelper {
    private Context context;

    public SettingPrefHelper(Context context) {
        this.context = context;
    }


    //设置图片保存路径
    public void setPicSavePath(String path) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("PicSavePath", path).apply();

    }

    //获取字体默认大小
    private int getTextSizePref() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return Integer.parseInt(prefs.getString("pTextSize", "3"));
    }

    //获取夜间模式
    public boolean getNightModel() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pNightMode", false);
    }
    //设置夜间模式
    public void setNightModel(boolean nightModel) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("pNightMode", nightModel).apply();
    }

    //设置加载图片
    public boolean getLoadPic() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pLoadPic", true);
    }
    //是否接受通知
    public boolean getNotification() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pNotification", true);
    }
    //默认加载图片
    public boolean getLoadOriginPic() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pLoadOriginPic", false);
    }

    //应用是否自动更新
    public boolean getAutoUpdate() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pLoadOriginPic", false);
    }

    public boolean getSingleLine() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pSingleLine", false);
    }

    //获取手势返回的方向
    public int getSwipeBackEdgeMode() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return Integer.parseInt(prefs.getString("pSwipeBackEdgeMode", "0"));
    }
    //获取手势返回的方向
    public void getSwipeBackEdgeMode(int  model) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("pSwipeBackEdgeMode", String.valueOf(model)).apply();
    }



    public String getLoginUid() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return   prefs.getString("loginUid","");
    }

    //获取uid
    public void setLoginUid(String uid) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("loginUid", uid).apply();
    }

}
