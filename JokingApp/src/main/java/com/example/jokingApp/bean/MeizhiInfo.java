package com.example.jokingApp.bean;

import java.util.List;

/**
 * 从干货网拿的数据 主要用来展示图片
 * Created by idea-pc on 2016/4/16.
 */
public class MeizhiInfo {

    /**
     * imageurl : image/img/1.jpg
     */

    private List<ImageBean> image;

    public List<ImageBean> getImage() {
        return image;
    }

    public void setImage(List<ImageBean> image) {
        this.image = image;
    }

    public static class ImageBean {
        private String imageurl;

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }
}
