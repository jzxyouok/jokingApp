package com.example.jokingApp.bean;

import java.util.List;

/**
 * 分区页面的信息
 * Created by idea-pc on 2016/3/24.
 */
public class PartitionInfo {

    /**
     * iconUrl : image/ic_category_01.png
     * name : 音乐
     */

    private List<PictureBean> picture;

    public List<PictureBean> getPicture() {
        return picture;
    }

    public void setPicture(List<PictureBean> picture) {
        this.picture = picture;
    }

    public static class PictureBean {
        private String iconUrl;
        private String name;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
