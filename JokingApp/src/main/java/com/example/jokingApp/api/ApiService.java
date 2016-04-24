package com.example.jokingApp.api;

import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.bean.MeizhiInfo;

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


    //请求获取 美女图片
    //示例 http://gank.io/api/data/福利/10/1
    @GET("福利/10/{Pager}")
    Call<MeizhiInfo> getMeiZhi(@Path("Pager")int pager);

    @GET("福利/10/{Pager}")
    Observable<MeizhiInfo> getMeiZhiByRxjava(@Path("Pager")String string);

}
