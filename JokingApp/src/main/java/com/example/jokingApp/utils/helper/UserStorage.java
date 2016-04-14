package com.example.jokingApp.utils.helper;

import android.content.Context;


import com.example.jokingApp.db.User;

/**
 *
 * 用户信息的仓库
 * Created by idea-pc on 2016/4/11.
 */

public class UserStorage {


    private SettingPrefHelper mSettingPrefHelper;
    private Context mContext;


    public UserStorage(SettingPrefHelper mSettingPrefHelper, Context mContext) {
        this.mSettingPrefHelper = mSettingPrefHelper;
        this.mContext = mContext;
    }


    private String cookie;
    private String token;

    private User user;

    public User getUser() {
        return user;
    }


    public void login(User user) {
        this.user = user;
        mSettingPrefHelper.setLoginUid(user.getUid());
    }


    public void logout() {
        if (user.getUid().equals(mSettingPrefHelper.getLoginUid())) {
            mSettingPrefHelper.setLoginUid("");
        }
        user = null;
        cookie = "";
        token = "";
    }


    public boolean isLogin() {
        return user != null && mSettingPrefHelper.getLoginUid().equals(user.getUid());
    }

    public String getToken() {
        if (!isLogin()) {
            return token;
        }
        return user.getToken();
    }

    public String getUid() {
        if (!isLogin()) {
            return "";
        }
        return user.getUid();
    }


    public String getCookie() {
        if (isLogin()) {
            return user.getCookie();
        }
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
