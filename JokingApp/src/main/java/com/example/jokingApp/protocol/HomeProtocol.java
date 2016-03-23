package com.example.jokingApp.protocol;

import com.example.jokingApp.bean.HomeInfo;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class HomeProtocol extends  BaseProtocol {
    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public Object parseJson(String json) {
        Gson gson = new Gson();
        HomeInfo homeInfo = gson.fromJson(json, HomeInfo.class);
        List<String> picture = homeInfo.getPicture();
        return picture;
    }
}
