package com.example.jokingApp.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by idea-pc on 2016/4/11.
 */
public class ToastHelper {

    private Context mContext;

    @Inject
    public ToastHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void showToast(int resId) {
        Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
