package com.example.jokingApp.db;

/**
 * 用户存入数据库的信息
 * Created by idea-pc on 2016/4/11.
 */
public class User {


    private Long id;
    private String userName;
    private String uid;
    private String token;
    private String icon;
    private Integer sex;
    private String cookie;
    private String registerTime;
    private String location;
    private String school;
    private String threadUrl;
    private String postUrl;
    private String nickNameUrl;
    public User() {
    }
    public User(Long id, String userName, String uid, String token, String icon, Integer sex, String cookie, String
            registerTime, String location, String school, String threadUrl, String postUrl, String nickNameUrl) {
        this.id = id;
        this.userName = userName;
        this.uid = uid;
        this.token = token;
        this.icon = icon;
        this.sex = sex;
        this.cookie = cookie;
        this.registerTime = registerTime;
        this.location = location;
        this.school = school;
        this.threadUrl = threadUrl;
        this.postUrl = postUrl;
        this.nickNameUrl = nickNameUrl;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getThreadUrl() {
        return threadUrl;
    }

    public void setThreadUrl(String threadUrl) {
        this.threadUrl = threadUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }
}
