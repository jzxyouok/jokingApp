package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/22.
 */
public class JokeInfo {

    /**
     * des : 描述
     * id : 1525490
     * name : 笑话
     */

    private List<JokeBean> joke;

    public List<JokeBean> getJoke() {
        return joke;
    }

    public void setJoke(List<JokeBean> joke) {
        this.joke = joke;
    }

    public static class JokeBean {
        @Override
        public String toString() {
            return "JokeBean{" +
                    "des='" + des + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        private String des;
        private int id;
        private String name;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
