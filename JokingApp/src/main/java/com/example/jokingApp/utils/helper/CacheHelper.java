package com.example.jokingApp.utils.helper;

import android.content.Context;
import android.os.Environment;

import com.example.jokingApp.utils.FileUtils;
import com.example.jokingApp.utils.UiUtils;

import java.io.File;

/**
 *
 * 缓存帮助类
 *
 * Created by idea-pc on 2016/4/12.
 */
public class CacheHelper {

    private Context mContext;
    public CacheHelper(Context context  ) {
        this.mContext = context;
    }

    /**
     * 计算总的缓存大小
     * 包括 SD卡 和手机缓存
     * @return
     */
    public String getCacheSize() {
        // 计算缓存大小
        long fileSize = 0;
        String cacheSize = "0KB";
        //手机file路径
        File filesDir = mContext.getFilesDir();
        //手机cache路径
        File cacheDir = mContext.getCacheDir();
        //系统默认的SD卡路径 没存储过数据 则为null
        File ExternalCacheDir = mContext.getExternalCacheDir();
        //自己设置的SD卡路径
        File cacheDir1 = FileUtils.getCacheDir();

        //遍历文件夹 计算大小
        fileSize += FileUtils.getDirSize(filesDir);
        fileSize += FileUtils.getDirSize(cacheDir);
        fileSize += FileUtils.getDirSize(ExternalCacheDir);
        fileSize += FileUtils.getDirSize(cacheDir1);

        if (fileSize > 0)
            cacheSize = FileUtils.formatFileSize(fileSize);
        return cacheSize;
    }
    public void cleanApplicationCache() {
        cleanInternalCache();//删除手机 cache文件夹下的文件
        cleanExternalCache();//删除手机  SD默认文件夹的文件 没设置过的则为null
        cleanFiles();   //删除手机 files文件夹下的文件
        //删除自定义文件夹下的文件
        //cleanCustomCache()
    }
    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context
     */
    public void cleanInternalCache() {
        deleteFilesByDirectory(UiUtils.getContext().getCacheDir());
    }
    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(UiUtils.getContext().getExternalCacheDir());
        }
    }
    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath
     */
    public void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isDirectory()) {
                    deleteFilesByDirectory(item);
                }
                item.delete();
            }
            directory.delete();
        }
    }
    /**
     * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context
     */
    public void cleanFiles() {
        deleteFilesByDirectory(UiUtils.getContext().getFilesDir());
    }

}
