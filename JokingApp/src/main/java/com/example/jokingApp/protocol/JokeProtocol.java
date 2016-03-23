package com.example.jokingApp.protocol;

import com.example.jokingApp.bean.JokeInfo;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class JokeProtocol extends BaseProtocol {
    @Override
    public String getKey() {
        return "joke";
    }

    @Override
    public List<JokeInfo.JokeBean> parseJson(String json) {
        Gson gson = new Gson();
        JokeInfo jokeInfo = gson.fromJson(json, JokeInfo.class);
        List<JokeInfo.JokeBean> joke = jokeInfo.getJoke();
        return joke;
    }
}
