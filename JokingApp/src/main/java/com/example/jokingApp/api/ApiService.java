package com.example.jokingApp.api;

import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.bean.JoyInfo;
import com.example.jokingApp.bean.MeizhiInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by idea-pc on 2016/4/5.
 */
public interface ApiService {
    @GET("{key}/{key}{index}.json")
    Call<String> getResult(@Path("key") String key,@Path("index") int index);


    @GET("{key}/{key}{index}.json")
    Observable<JokeInfo> getJoke(@Path("key") String key,@Path("index") int index);


    @GET("image.json")
    Call<MeizhiInfo> getMeiZhi();

    //获取视频封面
    @GET("福利/10/{Pager}")
    Observable<MeizhiInfo> getMeiZhiByRxjava(@Path("Pager")String string);
    //获取逗比图片
    @GET("341-2/")
    Call<JoyInfo> getJoyInfo(@Query("showapi_appid") String appid,@Query("showapi_sign") String sign,
                                   @Query("page")String page,@Query("maxResult")String maxResult
                                   );
    //获取奇闻异事
    @GET("231-1/")
    Call<FantasticInfo> getFantastic(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign,
                                     @Query("page")String page, @Query("num")String num
    );


}
