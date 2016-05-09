package com.example.jokingApp.global;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class GlobalConstant {
    //服务器地址
    public static final String SERVER_URL = "http://121.42.200.58/";
    public  static final Object tag =new Object();
    public static String Cookie = "";

    public static final String URL1 = "http://mvvideo1.meitudata.com/571044204d8495965.mp4";
    public static final String URL2 = "http://mvvideo1.meitudata.com/570f3483246dd4632.mp4";
    public static final String URL3 = "http://mvvideo1.meitudata.com/570dd31826775833.mp4";
    public static final String URL4 = "http://mvvideo2.meitudata.com/5711ea08a84774444.mp4";
    public static final String URL5 = "http://mvvideo1.meitudata.com/56e7de76537033445.mp4";
    public static final String URL6 = "http://mvvideo1.meitudata.com/5711edbfeb02c5682.mp4";
    public static final String URL7 = "http://mvvideo1.meitudata.com/5709fa3298cd28822.mp4";
    public static final String URL8 = "http://mvvideo2.meitudata.com/57086303a50344428.mp4";
    public static final String URL9 = "http://mvvideo2.meitudata.com/570dca38eb5766862.mp4";
    public static final String URL10 = "http://mvvideo1.meitudata.com/5711e159c2b664561.mp4";
    public static final String URL11 = "http://mvvideo2.meitudata.com/57119ed9158d11341.mp4";

    public static String getData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(URL1);
        strings.add(URL2);
        strings.add(URL3);
        strings.add(URL4);
        strings.add(URL5);
        strings.add(URL6);
        strings.add(URL7);
        strings.add(URL8);
        strings.add(URL9);
        strings.add(URL10);
        strings.add(URL11);
        int position = new Random().nextInt(11);
        return  strings.get(position);
    }
}
