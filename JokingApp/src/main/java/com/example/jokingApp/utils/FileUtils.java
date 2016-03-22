package com.example.jokingApp.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class FileUtils {
    public static final String CACHE = "cache";
    public static final String ROOT = "JokingAPP";
    public static final String IMAGE = "image";

    public static File getDir(String str) {
        StringBuilder path = new StringBuilder();
        //首先要拿到路径
        if (isSDAcailable()) {//SD卡如果可用的话
            path.append(Environment.getExternalStorageDirectory()
                    .getAbsolutePath());
            path.append(File.separator);
            path.append(ROOT);
            path.append(File.separator);
            path.append(str);
        } else {//SD卡如果不可用的话
            String cacheDir = UiUtils.getContext().getCacheDir().getAbsolutePath();
            path.append(cacheDir);// data.data.com.example.jokingApp
            path.append(File.separator);
            path.append(str);
        }
        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            boolean mkdirs = file.mkdirs();// 创建文件夹 ,返回值为true  即创建成功
            System.out.println("mkdirs"+mkdirs);
        }
        return file;
    }
    /**判断SD卡是否可用*/
    private static boolean isSDAcailable() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**获取json数据的路径*/
    public static File getCacheDir() {
        return getDir(CACHE);
    }
    /**获取图片的缓存路径*/
    public static File getIconDir() {
        return getDir(IMAGE);
    }

}
