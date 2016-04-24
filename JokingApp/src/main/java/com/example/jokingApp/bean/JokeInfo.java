package com.example.jokingApp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


/**
 * 笑话页面的信息
 * Created by idea-pc on 2016/3/22.
 */
public class JokeInfo  {


    /**
     * 例子
     * des : 早上八点我还在睡觉，老妈打开房间门：“太阳晒屁股啦！”。多亏她的提醒，我给屁股抹了点防晒霜。
     * id : 1525490
     * imageurl : image/1.jpg
     * name : 笑话0
     * url : url/0.html
     */

    private List<JokeBean> joke;

    public List<JokeBean> getJoke() {
        return joke;
    }

    public void setJoke(List<JokeBean> joke) {
        this.joke = joke;
    }

    public static class JokeBean  implements Parcelable {
        private String des;
        private String id;
        private String imageurl;
        private String name;
        private String url;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.des);
            dest.writeString(this.id);
            dest.writeString(this.imageurl);
            dest.writeString(this.name);
            dest.writeString(this.url);
        }

        public JokeBean() {
        }

        protected JokeBean(Parcel in) {
            this.des = in.readString();
            this.id = in.readString();
            this.imageurl = in.readString();
            this.name = in.readString();
            this.url = in.readString();
        }

        public static final Parcelable.Creator<JokeBean> CREATOR = new Parcelable.Creator<JokeBean>() {
            @Override
            public JokeBean createFromParcel(Parcel source) {
                return new JokeBean(source);
            }

            @Override
            public JokeBean[] newArray(int size) {
                return new JokeBean[size];
            }
        };
    }
}
