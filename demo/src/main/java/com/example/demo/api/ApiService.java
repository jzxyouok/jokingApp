package com.example.demo.api;

import com.example.demo.bean.JokeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * http://121.42.200.58/1.txt
 * Created by idea-pc on 2016/4/5.
 */
public interface ApiService {
    @GET("joke0.json")
    Observable<JokeInfo> getApiService();
}
