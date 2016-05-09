package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class FantasticInfo {
    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"code":200,"msg":"success","newslist":[{"ctime":"2016-05-03 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/65/19216.jpg","title":"罗马尼亚古城堡 吸血鬼品牌","url":"http://www.lieqi
     * .com/read/7/19216/"},{"ctime":"2016-04-29 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/64/19169.jpg","title":"香港万佛寺：沿途五百罗汉表情恐怖","url":"http://www.lieqi.com/read/7/19169/"},
     * {"ctime":"2016-04-29 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19171
     * .jpg","title":"美国西部风景：凄凉阴森的废弃鬼镇","url":"http://www.lieqi.com/read/7/19171/"},{"ctime":"2016-04-28 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19115.jpg",
     * "title":"神秘幽灵船：各地被困搁浅海上的船","url":"http://www.lieqi.com/read/7/19115/"},{"ctime":"2016-04-27 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19058.jpg",
     * "title":"揭秘：恐龙在68万年前的行踪故事","url":"http://www.lieqi.com/read/7/19058/"},{"ctime":"2016-04-26 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/18998.jpg","title":"慎入！驴友误入妲己墓穴
     * 拍到灵异深洞女尸","url":"http://www.lieqi.com/read/7/18998/"},{"ctime":"2016-04-26 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/64/19000.jpg","title":"惊悚鬼屋：情妇火烧城堡化为厉鬼","url":"http://www
     * .lieqi.com/read/7/19000/"},{"ctime":"2016-04-23 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/64/18921.jpg","title":"日本海啸过后突现惊人一幕：慎入！","url":"http://www.lieqi.com/read/7/18921/"},
     * {"ctime":"2016-04-25 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/18962
     * .jpg","title":"男子娶女尸完婚：吓坏所有人","url":"http://www.lieqi.com/read/7/18962/"},{"ctime":"2016-04-21 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/63/18871.jpg","title":"男童深夜对着空气聊天
     * 爸妈听到诡异声吓坏了","url":"http://www.lieqi.com/read/7/18871/"},{"ctime":"2016-04-20 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/63/18834.jpg","title":"探秘：人死后去阴间要经历的七大关","url":"http://www
     * .lieqi.com/read/7/18834/"},{"ctime":"2016-04-04 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/62/18410.jpg","title":"夫妻自拍照藏恐怖秘密：把网友们吓坏了","url":"http://www.lieqi.com/read/7/18410/"},
     * {"ctime":"2016-04-08 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18547
     * .jpg","title":"林正英逝世是跟拍鬼片有关系吗？林正英死亡真相","url":"http://www.lieqi.com/read/7/18547/"},{"ctime":"2016-03-30
     * 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18360.jpg",
     * "title":"揭鬼看到人而人看不到鬼之谜","url":"http://www.lieqi.com/read/7/18360/"},{"ctime":"2016-03-29 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18332.jpg",
     * "title":"男子买房发现神秘物品，第二天立马搬家","url":"http://www.lieqi.com/read/7/18332/"},{"ctime":"2016-03-24 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/61/18222.jpg","title":"坟墓上有这个后代会越过越穷",
     * "url":"http://www.lieqi.com/read/7/18222/"},{"ctime":"2016-03-24 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/61/18225.jpg","title":"小心鬼上身！女生夜间十大禁忌","url":"http://www
     * .lieqi.com/read/7/18225/"},{"ctime":"2016-03-17 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/61/18081.jpg","title":"震惊！16岁孕妇下葬后离奇复活","url":"http://www.lieqi.com/read/7/18081/"},
     * {"ctime":"2016-03-11 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/60/17984
     * .jpg","title":"惊悚骇人！一场吓死25人的诡异出殡","url":"http://www.lieqi.com/read/6/17984/"},{"ctime":"2016-03-09 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/60/17916.jpg","title":"老妇太平间里复活
     * 挣脱装尸袋吓坏守尸人","url":"http://www.lieqi.com/read/6/17916/"}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-03 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/65/19216.jpg","title":"罗马尼亚古城堡 吸血鬼品牌","url":"http://www.lieqi.com/read/7/19216/"},
     * {"ctime":"2016-04-29 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19169
     * .jpg","title":"香港万佛寺：沿途五百罗汉表情恐怖","url":"http://www.lieqi.com/read/7/19169/"},{"ctime":"2016-04-29 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19171.jpg",
     * "title":"美国西部风景：凄凉阴森的废弃鬼镇","url":"http://www.lieqi.com/read/7/19171/"},{"ctime":"2016-04-28 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19115.jpg",
     * "title":"神秘幽灵船：各地被困搁浅海上的船","url":"http://www.lieqi.com/read/7/19115/"},{"ctime":"2016-04-27 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/19058.jpg",
     * "title":"揭秘：恐龙在68万年前的行踪故事","url":"http://www.lieqi.com/read/7/19058/"},{"ctime":"2016-04-26 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/18998.jpg","title":"慎入！驴友误入妲己墓穴
     * 拍到灵异深洞女尸","url":"http://www.lieqi.com/read/7/18998/"},{"ctime":"2016-04-26 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/64/19000.jpg","title":"惊悚鬼屋：情妇火烧城堡化为厉鬼","url":"http://www
     * .lieqi.com/read/7/19000/"},{"ctime":"2016-04-23 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/64/18921.jpg","title":"日本海啸过后突现惊人一幕：慎入！","url":"http://www.lieqi.com/read/7/18921/"},
     * {"ctime":"2016-04-25 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/64/18962
     * .jpg","title":"男子娶女尸完婚：吓坏所有人","url":"http://www.lieqi.com/read/7/18962/"},{"ctime":"2016-04-21 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/63/18871.jpg","title":"男童深夜对着空气聊天
     * 爸妈听到诡异声吓坏了","url":"http://www.lieqi.com/read/7/18871/"},{"ctime":"2016-04-20 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/63/18834.jpg","title":"探秘：人死后去阴间要经历的七大关","url":"http://www
     * .lieqi.com/read/7/18834/"},{"ctime":"2016-04-04 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/62/18410.jpg","title":"夫妻自拍照藏恐怖秘密：把网友们吓坏了","url":"http://www.lieqi.com/read/7/18410/"},
     * {"ctime":"2016-04-08 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18547
     * .jpg","title":"林正英逝世是跟拍鬼片有关系吗？林正英死亡真相","url":"http://www.lieqi.com/read/7/18547/"},{"ctime":"2016-03-30
     * 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18360.jpg",
     * "title":"揭鬼看到人而人看不到鬼之谜","url":"http://www.lieqi.com/read/7/18360/"},{"ctime":"2016-03-29 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/62/18332.jpg",
     * "title":"男子买房发现神秘物品，第二天立马搬家","url":"http://www.lieqi.com/read/7/18332/"},{"ctime":"2016-03-24 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/61/18222.jpg","title":"坟墓上有这个后代会越过越穷",
     * "url":"http://www.lieqi.com/read/7/18222/"},{"ctime":"2016-03-24 00:00","description":"猎奇奇闻",
     * "picUrl":"http://img521.lieqi.com/upload/picture/61/18225.jpg","title":"小心鬼上身！女生夜间十大禁忌","url":"http://www
     * .lieqi.com/read/7/18225/"},{"ctime":"2016-03-17 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi
     * .com/upload/picture/61/18081.jpg","title":"震惊！16岁孕妇下葬后离奇复活","url":"http://www.lieqi.com/read/7/18081/"},
     * {"ctime":"2016-03-11 00:00","description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/60/17984
     * .jpg","title":"惊悚骇人！一场吓死25人的诡异出殡","url":"http://www.lieqi.com/read/6/17984/"},{"ctime":"2016-03-09 00:00",
     * "description":"猎奇奇闻","picUrl":"http://img521.lieqi.com/upload/picture/60/17916.jpg","title":"老妇太平间里复活
     * 挣脱装尸袋吓坏守尸人","url":"http://www.lieqi.com/read/6/17916/"}]
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ctime : 2016-05-03 00:00
         * description : 猎奇奇闻
         * picUrl : http://img521.lieqi.com/upload/picture/65/19216.jpg
         * title : 罗马尼亚古城堡 吸血鬼品牌
         * url : http://www.lieqi.com/read/7/19216/
         */

        private List<NewslistBean> newslist;

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            private String ctime;
            private String description;
            private String picUrl;
            private String title;
            private String url;

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
