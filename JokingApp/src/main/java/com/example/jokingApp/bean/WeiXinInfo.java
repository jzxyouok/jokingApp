package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/5/10.
 */
public class WeiXinInfo {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-10","title":"现在的COSER真是越来越敬业了！","description":"娱乐二人转","picUrl":"http://zxpic
     * .gtimg.com/infonew/0/wechat_pics_-5194019.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA4NzQ3MTUxMQ==&idx=6&mid=2651288044&sn=7090fe8cc1ab29a0af779e0a8f72e928"},
     * {"ctime":"2016-05-10","title":"这些男人的龙抓手简直无敌了！！！","description":"娱乐二人转","picUrl":"http://t1.qpic
     * .cn/mblogpic/48da2c609d0a7f9d083e/2000","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA4NzQ3MTUxMQ==&idx=3&mid=2651288044&sn=f700a29e4a14608212308fd1437b9f13"},
     * {"ctime":"2016-05-10","title":"小妹纸接客，结局亮了！","description":"娱乐二人转","picUrl":"http://t1.qpic
     * .cn/mblogpic/48da2c609d0a7f9d083e/2000","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA4NzQ3MTUxMQ==&idx=8&mid=2651288044&sn=ae32c20793ed0c4efd6e17cc35e10d8e"},
     * {"ctime":"2016-05-10","title":"老妹活好，别人媳妇活更好！","description":"娱乐二人转","picUrl":"http://t1.qpic
     * .cn/mblogpic/f01a972dbcc1060fd456/2000","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA4NzQ3MTUxMQ==&idx=7&mid=2651288044&sn=ce0d4a742f3323406608a7d952ca83cb"},
     * {"ctime":"2016-05-10","title":"美国良心P2P公司：因2笔交易不符合投资者\u201c标准\u201d，LC创始人主动辞职","description":"亿欧网",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5191906.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA5NTI1MDEyNA==&idx=3&mid=2652531774&sn=63f0404ea5e6ea21947de2ecb646f6a5"},
     * {"ctime":"2016-05-10","title":"独家！长江上的\u201c滴滴打船\u201d完成3000万元A轮融资！","description":"亿欧网",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5191905.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA5NTI1MDEyNA==&idx=6&mid=2652531774&sn=4ffa42c5471975db5e5118e309e05f30"},
     * {"ctime":"2016-05-10","title":"美团10号员工沈鹏创业，\u201c水滴互助社群\u201d敲定天使投资5000万","description":"亿欧网",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5191917.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA5NTI1MDEyNA==&idx=1&mid=2652531774&sn=3ee3b55b23cbb51aca596d28262f45be"},
     * {"ctime":"2016-05-10","title":"明后天，创投圈的半边天将齐聚北京，共谋大事！就等你来！附攻略指南","description":"创业邦杂志","picUrl":"http://zxpic
     * .gtimg.com/infonew/0/wechat_pics_-5192046.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5OTAzMjc4MA==&idx=1&mid=2650047071&sn=a1c423c0fe843b7f86a87befc6681f98"},
     * {"ctime":"2016-05-10","title":"退学，离家出走，卖房创业，在他即将烧完最后一笔钱时，获250万元融资","description":"创业邦杂志",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5192043.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5OTAzMjc4MA==&idx=5&mid=2650047071&sn=62fdbe3b9a3c46698063c098120a007b"},
     * {"ctime":"2016-05-10","title":"野心不限于出行领域，Uber中国强势杀入本地生活，绝不放过每个乘车时间","description":"创业邦杂志",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5192044.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5OTAzMjc4MA==&idx=3&mid=2650047071&sn=3db431a02d18be8d27f5c191a72dd035"},
     * {"ctime":"2016-05-10","title":"离开北京，从二线城市起家，仅用60万启动资金，苦干4年，他做了3万租户的二房东！","description":"创业邦杂志",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5192042.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5OTAzMjc4MA==&idx=4&mid=2650047071&sn=8a2f4204f49552ad0eae5f558cc4c314"},
     * {"ctime":"2016-05-10","title":"【深度】未来5年互联网行业大趋势，不看会后悔！","description":"互联网风云榜","picUrl":"http://zxpic.gtimg
     * .com/infonew/0/wechat_pics_-5003439.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA3NDY2NzAyMw==&idx=4&mid=2650868463&sn=c5d95a655a35cf9c9e20085ffd983a41"},
     * {"ctime":"2016-05-10","title":"否认成立\u201c打乐办\u201d，华为EMT高管在说谎？！施压供应链报复性挖乐视员工","description":"互联网风云榜",
     * "picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5192064.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA3NDY2NzAyMw==&idx=2&mid=2650868463&sn=77bb59a822f3e885ab74a5a73a5e74b3"},
     * {"ctime":"2016-05-10","title":"老婆出轨5年，搞了250次，聊天记录曝光","description":"每日爆笑GIF动态图","picUrl":"http://zxpic.gtimg
     * .com/infonew/0/wechat_pics_-5192068.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA3NzY2MTAzNQ==&idx=3&mid=2650142511&sn=7295da67e00ac9a30e0d2da970599480"},
     * {"ctime":"2016-05-10","title":"美团外卖帮助黑餐馆上线有身份证就能开店","description":"互联网风云榜","picUrl":"http://zxpic.gtimg
     * .com/infonew/0/wechat_pics_-5192066.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzA3NDY2NzAyMw==&idx=1&mid=2650868463&sn=f35e6bd1fa80c2a46a90b9e310e8825e"},
     * {"ctime":"2016-05-10","title":"从电影学习谈判技巧！回顾各种不同情境的谈判电影","description":"电影集结号","picUrl":"http://zxpic.gtimg
     * .com/infonew/0/wechat_pics_-5192109.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5NDU2MjQ4NA==&idx=2&mid=2657095815&sn=36614260345e99edcd47bfa9ec64339f"},
     * {"ctime":"2016-05-10","title":"俄罗斯电影，画风总能让你大吃一惊","description":"电影集结号","picUrl":"http://zxpic.gtimg
     * .com/infonew/0/wechat_pics_-4480654.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5NDU2MjQ4NA==&idx=3&mid=2657095815&sn=ecf2232405a75d5262acb1d52b75cdac"},
     * {"ctime":"2016-05-10","title":"青春一去不复返？论\u201c再见\u201d十九岁的可能性","description":"电影集结号","picUrl":"http://zxpic
     * .gtimg.com/infonew/0/wechat_pics_-5192104.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5NDU2MjQ4NA==&idx=1&mid=2657095815&sn=32eec6f5ab2fc843814c477dabdf5575"},
     * {"ctime":"2016-05-10","title":"刘涛因为高智商安迪圈粉这个情商超高的总攻人生比安迪更震撼","description":"头号电影院","picUrl":"http://zxpic
     * .gtimg.com/infonew/0/wechat_pics_-5192184.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MzI3ODAxNTM1Mg==&idx=1&mid=2649879842&sn=70f012a14a1038482447285c0b1f2346"},
     * {"ctime":"2016-05-10","title":"手游充分使用IP的三要素：解读《山口山战记》68%高次留之迷","description":"威锋网","picUrl":"http://zxpic
     * .gtimg.com/infonew/0/wechat_pics_-5192369.jpg/640","url":"http://mp.weixin.qq
     * .com/s?__biz=MjM5NzUyMDM0MA==&idx=2&mid=2650713035&sn=2fc657536db4f0e0825c351af53ccb4f"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-05-10
     * title : 现在的COSER真是越来越敬业了！
     * description : 娱乐二人转
     * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-5194019.jpg/640
     * url : http://mp.weixin.qq.com/s?__biz=MzA4NzQ3MTUxMQ==&idx=6&mid=2651288044&sn=7090fe8cc1ab29a0af779e0a8f72e928
     */

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
