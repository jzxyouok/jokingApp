package com.example.jokingApp.utils.helper;

import android.content.Context;
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

    //获取图片保存路径
    public String getPicSavePath() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString("PicSavePath", "gzsll");
    }
    //设置图片保存路径
    public void setPicSavePath(String path) {
        PrefUtils.setString(context,"PicSavePath", path);
    }

    //获取字体默认大小
    private int getTextSizePref() {
        return Integer.parseInt(PrefUtils.getString(context,"pTextSize", "3"));
    }

    //获取夜间模式
    public boolean getNightModel() {
        return PrefUtils.getBoolean(context,"pNightMode", false);
    }
    //设置夜间模式
    public void setNightModel(boolean nightModel) {
        PrefUtils.setBoolean(context,"pNightMode", nightModel);
    }

    //设置加载图片
    public boolean getLoadPic() {
        return PrefUtils.getBoolean(context,"pLoadPic", true);
    }
    //是否接受通知
    public boolean getNotification() {
        return PrefUtils.getBoolean(context,"pNotification", true);
    }
    //默认加载图片
    public boolean getLoadOriginPic() {
        return PrefUtils.getBoolean(context,"pLoadOriginPic", false);
    }

    //应用是否自动更新
    public boolean getAutoUpdate() {
        return PrefUtils.getBoolean(context,"pAutoUpdate", true);
    }

    public boolean getSingleLine() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("pSingleLine", false);
    }

    //获取手势返回的方向
    public int getSwipeBackEdgeMode() {
        return Integer.parseInt(PrefUtils.getString(context,"pSwipeBackEdgeMode", "0"));
    }


    public String getLoginUid() {
        return   PrefUtils.getString(context,"loginUid","");
    }

    //获取uid
    public void setLoginUid(String uid) {
        PrefUtils.setString(context,"loginUid",uid);
    }
}
