package com.example.jokingApp.utils;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * 对retrofit进行了简单的封装
 * 对请求过程进行封装
 * 单例设计模式 DCL
 * Created by  ;
 */
public class RetrofitUtils {
    public static String BASE_URL = "http://121.42.200.58/";
    private static volatile Retrofit singleton;
    private static volatile Retrofit single;
    private static volatile Retrofit sing;

    /**
     * 将请求的数据转换为String
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createApiToString(Class<T> clazz) {
        //DCL  单例
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    Retrofit.Builder retrofit = new Retrofit.Builder();
                    retrofit.baseUrl(BASE_URL)//注意此处,设置服务器的地址
                            // .addConverterFactory(GsonConverterFactory.create())//用于Json数据的转换,非必须
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());//用于返回Rxjava调用,非必须
                    singleton = retrofit.build();
                }
            }
        }
        return singleton.create(clazz);
    }

    /**
     * 将请求的数据 直接转换为Gson
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createApiToGson(Class<T> clazz) {
        //DCL  单例
        if (single == null) {
            synchronized (RetrofitUtils.class) {
                if (single == null) {
                    Retrofit.Builder retrofit = new Retrofit.Builder();
                    retrofit.baseUrl(BASE_URL)//注意此处,设置服务器的地址
                            .addConverterFactory(GsonConverterFactory.create())//用于Json数据的转换,非必须
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());//用于返回Rxjava调用,非必须
                    single = retrofit.build();
                }
            }
        }
        return single.create(clazz);
    }

    public static <T> T createApiToGson(String url, Class<T> clazz) {
        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.baseUrl(url)//注意此处,设置服务器的地址
                .addConverterFactory(GsonConverterFactory.create())//用于Json数据的转换,非必须
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());//用于返回Rxjava调用,非必须
        return retrofit.build().create(clazz);
    }
}
