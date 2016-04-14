package com.example.jokingApp.utils.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对信息  进行MD5加密
 * Created by idea-pc on 2016/4/11.
 */
public class SecurityHelper {

    //对文件加密进行加密
    public String getMd5ByteByFile(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length;
            while ((length = in.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            //32位加密
            return bytesToHexString(md.digest());
        } catch (Exception e) {
            return "";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用MD5算法对传入的key进行加密并返回。
     */
    public String getMD5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }
    //Integer.toHexString 无符号整数基数为16的整数参数的字符串
    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
