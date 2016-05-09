package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class JoyInfo {


    /**
     * allNum : 30225
     * allPages : 1512
     * contentlist : [{"ct":"2016-05-08 12:00:23.345","id":"572eb9d76e36de3b89e6182b","img":"http://img6.hao123
     * .com/data/3_fb407c9a899957e5a9e7e36f73e0d061_430","title":"伦家的车牌够霸气吧，我就想问还有谁","type":2},{"ct":"2016-05-08
     * 12:00:23.312","id":"572eb9d76e36de3b89e6182a","img":"http://img2.hao123
     * .com/data/3_1b2c76c8b7b9d50ee06fd168889fa4f4_430","title":"我勒个去，发现个无头鬼","type":2},{"ct":"2016-05-08 12:00:23
     * .311","id":"572eb9d76e36de3b89e61829","img":"http://img4.hao123
     * .com/data/3_2c091575c7289782d28e03aabd5aab10_430","title":"人家结婚都是闹伴娘，这哥们是不是脑子有shi","type":2},{"ct":"2016-05-08
     * 12:00:23.311","id":"572eb9d76e36de3b89e61828","img":"http://img6.hao123
     * .com/data/3_1eb3541db990671266082ab77f219104_0","title":"这饭吃的多淡定从容啊","type":2},{"ct":"2016-05-08 12:00:23
     * .311","id":"572eb9d76e36de3b89e61827","img":"http://img4.hao123
     * .com/data/3_bad196498604cffb4a00806da839a4f4_430","title":"莫名其妙被监考老师请出了考场","type":2},{"ct":"2016-05-08
     * 12:00:23.311","id":"572eb9d76e36de3b89e61826","img":"http://img.hao123
     * .com/data/3_317802189567ce0e7782f755f24ca202_430","title":"老妈，你不买我只能睡","type":2},{"ct":"2016-05-08 11:30:43
     * .530","id":"572eb2e36e36de3b89e60b23","img":"http://img3.hao123
     * .com/data/3_b25f2f584fdcf1ef34dd6e8960f3a4b5_0","title":"蜘蛛等待的女人","type":2},{"ct":"2016-05-08 11:30:43.526",
     * "id":"572eb2e36e36de3b89e60b22","img":"http://img4.hao123.com/data/3_8cbc6878c5a6c0eec69af057f96999b8_430",
     * "title":"最浪漫的事就是每天陪你们一起泡泡脚","type":2},{"ct":"2016-05-08 02:30:51.048","id":"572e345b6e36de3b89e41769",
     * "img":"http://img3.hao123.com/data/3_aae2733eba991ce047ed4109d0d52951_430","title":"那个，请问有手纸吗？","type":2},
     * {"ct":"2016-05-08 02:00:50.754","id":"572e2d526e36de3b89e3e655","img":"http://img0.hao123
     * .com/data/3_6d2a0860ca36d187590bea98dd0d3bac_430","title":"老婆大人我错了","type":2},{"ct":"2016-05-08 02:00:25.541",
     * "id":"572e2d396e36de3b89e3e049","img":"http://img.hao123.com/data/3_51a542d25d93542064fc4b98c5d9ad47_0",
     * "title":"看着办，人家就缺个男票","type":2},{"ct":"2016-05-08 02:00:25.541","id":"572e2d396e36de3b89e3e048",
     * "img":"http://img5.hao123.com/data/3_f190728efed290218d610b4f0adeb650_430","title":"阝可扌立其斤力口","type":2},
     * {"ct":"2016-05-08 02:00:25.541","id":"572e2d396e36de3b89e3e047","img":"http://img6.hao123
     * .com/data/3_39073be6f8e9814b318c2b967368a6d0_430","title":"小提琴难以承受你的梦想啊","type":2},{"ct":"2016-05-08 02:00:25
     * .541","id":"572e2d396e36de3b89e3e046","img":"http://img3.hao123
     * .com/data/3_c347dff83df6bcc0515d0da4602d9c2f_430","title":"还能不能愉快的吃个水果了？","type":2},{"ct":"2016-05-08 02:00:25
     * .541","id":"572e2d396e36de3b89e3e045","img":"http://img0.hao123
     * .com/data/3_677b7c5c9ad687da7faac7f150619c23_430","title":"生无可恋！！！","type":2},{"ct":"2016-05-07 23:31:07.920",
     * "id":"572e0a3b6e36de3b89e39924","img":"http://img0.hao123.com/data/3_f3dd763ff649977ae57355aa3dd132c0_430",
     * "title":"每一个追风少年的青春都是不甘寂寞的","type":2},{"ct":"2016-05-07 22:30:43.598","id":"572dfc136e36de3b89e36b2c",
     * "img":"http://img4.hao123.com/data/3_122737553d5f1a555a3e89b60a30ec2c_0","title":"哥们你跟我撞衫了","type":2},
     * {"ct":"2016-05-07 22:00:27.820","id":"572df4fb6e36de3b89e34ce1","img":"http://img1.hao123
     * .com/data/3_ebea0066f727c91e4a91827850cb6e14_0","title":"网友一个月不倒烟头的成果。。。。也是宅出一定境界了。。。。。。","type":2},
     * {"ct":"2016-05-07 22:00:27.818","id":"572df4fb6e36de3b89e34ce0","img":"http://img1.hao123
     * .com/data/3_642d39342627b84c4e0860340ad964a7_430","title":"是时候展现真正的技术了","type":2},{"ct":"2016-05-07 22:00:27
     * .817","id":"572df4fb6e36de3b89e34cdf","img":"http://img0.hao123
     * .com/data/3_40d364ff893451bd56a4ed37a5c953d7_430","title":"一些不法分子在光天化日下溜门撬锁，气焰十分猖獗。","type":2}]
     * currentPage : 1
     * maxResult : 20
     * ret_code : 0
     */

    private ShowapiResBodyBean showapi_res_body;

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int currentPage;
        private int maxResult;
        /**
         * ct : 2016-05-08 12:00:23.345
         * id : 572eb9d76e36de3b89e6182b
         * img : http://img6.hao123.com/data/3_fb407c9a899957e5a9e7e36f73e0d061_430
         * title : 伦家的车牌够霸气吧，我就想问还有谁
         * type : 2
         */

        private List<ContentlistBean> contentlist;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }

        public static class ContentlistBean {
            private String ct;
            private String id;
            private String img;
            private String title;

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
