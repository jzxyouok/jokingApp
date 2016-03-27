package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class HomeInfo {


    private List<String> picture;
    private List<String> viewpager;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<String> getViewpager() {
        return viewpager;
    }

    public void setViewpager(List<String> viewpager) {
        this.viewpager = viewpager;
    }
}
