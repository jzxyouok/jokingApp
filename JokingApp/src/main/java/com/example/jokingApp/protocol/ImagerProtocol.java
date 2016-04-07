package com.example.jokingApp.protocol;

import com.example.jokingApp.bean.ImageInfo;
import com.google.gson.Gson;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class ImagerProtocol extends  BaseProtocol {
    @Override
    public String getKey() {
        return "imagefragment";
    }

    @Override
    public Object parseJson(String json) {
        Gson gson = new Gson();
        ImageInfo imageInfo = gson.fromJson(json, ImageInfo.class);
        return imageInfo;
    }
}
