package com.example.jokingApp.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * 获取bitmapUtils
 * Created by idea-pc on 2016/3/23.
 */
public class BitmapHelper {
    private BitmapHelper() {
    }

    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(UiUtils.getContext(), FileUtils.getCacheDir().getAbsolutePath(), 0.3f);
        }
        return bitmapUtils;
    }
}
