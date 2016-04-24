package com.example.jokingApp.bean;

import java.util.List;

/**
 * 从干货网拿的数据 主要用来展示图片
 * Created by idea-pc on 2016/4/16.
 */
public class MeizhiInfo {


    /**
     * error : false
     * results : [{"_id":"57105d6e67765974fbfcf8f4","createdAt":"2016-04-15T11:18:06.529Z","desc":"4.15",
     * "publishedAt":"2016-04-15T13:04:43.738Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg
     * .cn/large/7a8aed7bjw1f2x7vxkj0uj20d10mi42r.jpg","used":true,"who":"张涵宇"},{"_id":"570eff956776590f57c4e0f9",
     * "createdAt":"2016-04-14T10:25:25.991Z","desc":"4.14","publishedAt":"2016-04-14T11:43:31.514Z",
     * "source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f2w0qujoecj20f00kzjtt.jpg",
     * "used":true,"who":"张涵宇"},{"_id":"570dc90b6776590f5a0ecbb6","createdAt":"2016-04-13T12:20:27.707Z","desc":"4
     * .13","publishedAt":"2016-04-13T13:49:02.190Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg
     * .cn/large/610dc034jw1f2uyg3nvq7j20gy0p6myx.jpg","used":true,"who":"代码家"},{"_id":"570c5e906776590f62db7b9b",
     * "createdAt":"2016-04-12T10:33:52.575Z","desc":"4.12","publishedAt":"2016-04-12T11:47:37.342Z",
     * "source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f2tpr3im0mj20f00l6q4o.jpg",
     * "used":true,"who":"张涵宇"},{"_id":"570b1c5a6776590f62db7b8e","createdAt":"2016-04-11T11:39:06.138Z","desc":"4
     * .11","publishedAt":"2016-04-11T12:37:49.993Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg
     * .cn/large/7a8aed7bjw1f2sm0ns82hj20f00l8tb9.jpg","used":true,"who":"张涵宇"},{"_id":"5707051467765950c3190163",
     * "createdAt":"2016-04-08T09:10:44.529Z","desc":"4.8","publishedAt":"2016-04-08T12:18:10.29Z","source":"chrome",
     * "type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2p0v9vwr5j20b70gswfi.jpg","used":true,"who":"张涵宇"},
     * {"_id":"5705c962677659132abfddcd","createdAt":"2016-04-07T10:43:46.879Z","desc":"4.7",
     * "publishedAt":"2016-04-07T11:43:11.427Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg
     * .cn/large/7a8aed7bjw1f2nxxvgz7xj20hs0qognd.jpg","used":true,"who":"张涵宇"},{"_id":"570480c46776591325d463ff",
     * "createdAt":"2016-04-06T11:21:40.621Z","desc":"4.6","publishedAt":"2016-04-06T12:06:32.601Z",
     * "source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f2mteyftqqj20jg0siq6g.jpg",
     * "used":true,"who":"张涵宇"},{"_id":"570317ed677659634149029a","createdAt":"2016-04-05T09:42:05.911Z","desc":"4
     * .5","publishedAt":"2016-04-05T10:45:46.487Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg
     * .cn/large/7a8aed7bjw1f2lkx2lhgfj20f00f0dhm.jpg","used":true,"who":"张涵宇"},{"_id":"56fddfcd67765933d8be9193",
     * "createdAt":"2016-04-01T10:41:17.615Z","desc":"4.1","publishedAt":"2016-04-01T11:17:05.676Z",
     * "source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f2h04lir85j20fa0mx784.jpg",
     * "used":true,"who":"张涵宇"}]
     */

    private boolean error;
    /**
     * _id : 57105d6e67765974fbfcf8f4
     * createdAt : 2016-04-15T11:18:06.529Z
     * desc : 4.15
     * publishedAt : 2016-04-15T13:04:43.738Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/7a8aed7bjw1f2x7vxkj0uj20d10mi42r.jpg
     * used : true
     * who : 张涵宇
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
