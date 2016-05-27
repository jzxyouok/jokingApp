package com.example.jokingApp.api;

import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.bean.JoyInfo;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.bean.TerroeInfo;
import com.example.jokingApp.bean.TerrorDeatail;
import com.example.jokingApp.bean.WeiXinInfo;

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
    //获取趣图图片
    @GET("341-2/")
    Call<JoyInfo> getJoyInfo(@Query("showapi_appid") String appid,@Query("showapi_sign") String sign,
                                   @Query("page")String page,@Query("maxResult")String maxResult
                                   );
    //http://api.huceo.com/social/?key=14e59ebf7e28a04aac0d1a1a885bacce&page=1&num=20
    //获取新闻
    @GET("social/")
    Call<FantasticInfo> getFantastic(@Query("key") String key, @Query("page") String page,
                                     @Query("num")String num
    );

    //获取奇闻
    @GET("wxnew/")
    Call<WeiXinInfo> getWeiXin(@Query("key") String key, @Query("num") String num ,
                                  @Query("page")String page
    );
    //获取恐怖故事列表
    @GET("955-1/")
    Call<TerroeInfo> getTerror(@Query("showapi_appid") String appId, @Query("showapi_sign") String sign ,
                               @Query("page")String page,@Query("type") String type
    );

    //获取恐怖故事详情
    @GET("213-4/")
    Observable<TerrorDeatail> getTerrorDetail(@Query("showapi_appid") String appId, @Query("showapi_sign") String sign ,
                                              @Query("id")String id
    );

}

