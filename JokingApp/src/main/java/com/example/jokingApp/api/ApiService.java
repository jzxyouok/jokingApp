package com.example.jokingApp.api;

import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.bean.JokeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by idea-pc on 2016/4/5.
 */
public interface ApiService {
    @GET("{key}/{key}{index}.json")
    Call<String> getResult(@Path("key") String key,@Path("index") int index);


    @GET("{key}/{key}{index}.json")
    Observable<JokeInfo> getJoke(@Path("key") String key,@Path("index") int index);


}
