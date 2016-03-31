package com.example.jokingApp.protocol;

import android.os.SystemClock;

import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.http.HttpHelper;
import com.example.jokingApp.utils.FileUtils;
import com.lidroid.xutils.util.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

/**
 * 加载本地数据
 * 请求服务器数据
 * 数据保存到本地
 * 过期删除数据
 * Created by idea-pc on 2016/3/21.
 */
public abstract class BaseProtocol<T> {
    private String mJson;

    public T load(int index) {
        SystemClock.sleep(500);
        // 首先加载服务器数据
        String json = loadLocal(index);
        if (json == null) {
            // 请求服务器拿到数据
            json = loadServer(index);
            if (json != null) {
                saveLocal(json, index);
            }
        }
        if (json != null) {
            return parseJson(json);
        } else {
            return null;
        }
    }
    private String loadServer(final int index) {
        HttpHelper.HttpResult httpResult = HttpHelper.get(GlobalConstant.SERVER_URL + getKey() + index+".json");
        if (httpResult != null) {
            String json = httpResult.getString();
            return json;
        } else {
            return null;
        }
    }

    private void saveLocal(String json, int index) {
        BufferedWriter bw = null;
        try {
            File dir = FileUtils.getCacheDir();
            // 获取路径
            File file = new File(dir, getKey() + index); // /mnt/sdcard/jokingApp/cache/joke0
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            //写入了文件过期的时间  即100秒
            bw.write(System.currentTimeMillis() + 1000 * 100 + "");
            bw.newLine();//这里是换行
            bw.write(json);//  写入json 数据
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关流 节省资源
            IOUtils.closeQuietly(bw);
        }
    }

    private String loadLocal(int index) {
        File dir = FileUtils.getCacheDir();//
        File file = new File(dir, getKey() + index);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //读取json文件 首行的时间
            long outOfDate = Long.parseLong(br.readLine());
            if (System.currentTimeMillis() > outOfDate) {
                //如果文件过期了 那么要删除文件
                clearDir(file);
                return null;
            } else {
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null) {
                    sw.write(str);
                }
                return sw.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract String getKey();


    private static void clearDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                clearDir(f);
                f.delete();
            }
        }
        file.delete();
    }

    public abstract T parseJson(String json);
}
