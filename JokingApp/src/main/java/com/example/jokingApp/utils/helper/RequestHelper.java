package com.example.jokingApp.utils.helper;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求  帮助类
 * Created by idea-pc on 2016/4/11.
 */
public class RequestHelper {

    private SecurityHelper mSecurityHelper;
    private Context mContext;
    private UserStorage mUserStorage;
    private SettingPrefHelper mSettingPrefHelper;

    public RequestHelper(SecurityHelper mSecurityHelper, Context mContext, UserStorage mUserStorage,
                         SettingPrefHelper mSettingPrefHelper) {
        this.mSecurityHelper = mSecurityHelper;
        this.mContext = mContext;
        this.mUserStorage = mUserStorage;
        this.mSettingPrefHelper = mSettingPrefHelper;

    }


    public Map<String, String> getHttpRequestMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("client", getDeviceId());   //客户端 android? ios
        map.put("night", mSettingPrefHelper.getNightModel() ? "1" : "0");//切换模式
        if (mUserStorage.isLogin()) {
            try {
                //将token  用utf_8格式编码
                map.put("token", URLEncoder.encode(mUserStorage.getToken(), "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    //获取Android设备的ID
    public String getAndroidId() {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //获取设备ID
    public String getDeviceId() {
        String deviceId;
        //需要添加权限
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getDeviceId() == null) {
            //没有的话获取 android ID  在设备首次启动时，系统会随机生成一个64位的数字
            deviceId = getAndroidId();
        } else {
            //设备如果有通话功能的话 可以获取到
            deviceId = tm.getDeviceId();
        }
        return deviceId;
    }
    //
    //获取签名信息
    //将所有请求参数按key在字典中的顺序排列
    //按照排序顺序依次将key、value值拼接起来
   // 最后拼接HUPU_SALT_AKJfoiwer394Jeiow4u309，取拼接的值的md5
    public String getRequestSign(Map<String, String> map) {
        ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs) {
                return lhs.getKey().compareTo(rhs.getKey());
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i = i + 1) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            Map.Entry<String, String> map1 = list.get(i);
            builder.append(map1.getKey()).append("=").append(map1.getValue());
        }
        builder.append("HUPU_SALT_AKJfoiwer394Jeiow4u309");
        return mSecurityHelper.getMD5(builder.toString());
    }

}