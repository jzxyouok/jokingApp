package com.example.jokingApp.bean;

import java.util.List;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class FantasticInfo {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-11 12:27","title":"湖南一男护工殴打女精神病患者 多人被问责(图)","description":"网易社会",
     * "picUrl":"http://s.cimg.163.com/photo/0001/2016-05-10/t_BMNG084T00AP0001.jpg.119x83.jpg","url":"http://news
     * .163.com/16/0511/12/BMPLGSRP00014AEE.html#f=slist"},{"ctime":"2016-05-11 13:05",
     * "title":"陕西一\u201c副站长\u201d多年吃空饷，坐牢每月还领5200元工资","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/7/78/7894B8269EF1DA4F4996BCA2D3E0419C.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/13/BMPNKSQ100014AED.html#f=slist"},{"ctime":"2016-05-11 13:06","title":"男子持刀杀3人被击毙:醉酒后误以为自家电被掐",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/D/DB/DBA52389FFEE6D3A7BBF307FCE286006.jpg.119x83
     * .jpg","url":"http://news.163.com/16/0511/13/BMPNMU9Q00011229.html#f=slist"},{"ctime":"2016-05-11 13:26",
     * "title":"北京二年级小学生写信给水务局:我家门前河好臭","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/7/7A/7A6FBDA795D93BE4655EFE7D1ADBE174.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/13/BMPORNS80001124J.html#f=slist"},{"ctime":"2016-05-11 11:21","title":"研究驳斥打屁股管教孩子：越打越容易精神病",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/C/C5/C53D9451632E44DFD38DDDD1FEDBAFC2.jpg.119x83
     * .jpg","url":"http://news.163.com/16/0511/11/BMPHMNJS00014AEE.html#f=slist"},{"ctime":"2016-05-11 11:34",
     * "title":"婆婆与儿媳共侍丈夫 为争侍寝权叫来警察","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/7/7E/7E2ECD1D6E725AD2731D5F1735F14BBB.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/11/BMPIFONS00011229.html#f=slist"},{"ctime":"2016-05-11 12:07","title":"大学生掏鸟案被告父亲自首称行贿办案人员",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/9/97/97790AA2EE6045F0EBE26CC1BBD15BED.jpg.119x83
     * .jpg","url":"http://news.163.com/16/0511/12/BMPKBSG700011229.html#f=slist"},{"ctime":"2016-05-11 10:35",
     * "title":"中国夫妇赴美遭美海关中文调侃：\u201c老牛吃嫩草\u201d(图)","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/C/C5/C53D9451632E44DFD38DDDD1FEDBAFC2.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/10/BMPF3Q1E00014AEE.html#f=slist"},{"ctime":"2016-05-11 10:40","title":"河南洛阳高中女生教室内被劫持 嫌疑人已被刑拘",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/7/7E/7E2ECD1D6E725AD2731D5F1735F14BBB.jpg.119x83
     * .jpg","url":"http://news.163.com/16/0511/10/BMPFD0OU00011229.html#f=slist"},{"ctime":"2016-05-11 10:53",
     * "title":"日本新型色狼定义:嗅气味属性骚扰 遭男性不满","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/9/97/97790AA2EE6045F0EBE26CC1BBD15BED.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/10/BMPG41BN00014JB6.html#f=slist"},{"ctime":"2016-05-11 09:30","title":"北大培文杯头奖作品确为抄袭 获奖者奖项被取消",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/cnews/2016/5/11/20160511084822c9e5c.jpg.119x83.jpg",
     * "url":"http://news.163.com/16/0511/09/BMPBB4E000014SEH.html#f=slist"},{"ctime":"2016-05-11 09:44",
     * "title":"雷洋家属已联系第三方鉴定机构 将二次沟通检方","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/cnews/2016/5/11/20160511085537c2788_550.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/09/BMPC53720001124J.html#f=slist"},{"ctime":"2016-05-11 09:49","title":"男子为减压砸车16辆被捕 称喜欢赔钱(图)",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/0/0D/0D680CE777B89799E34A92B4AB6824CD.png.119x83
     * .jpg","url":"http://news.163.com/16/0511/09/BMPCFBNP00014AEE.html#f=slist"},{"ctime":"2016-05-11 10:04",
     * "title":"司机失误公交车门夹乘客 女乘客:你敢夹当官的","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/C/C5/C53D9451632E44DFD38DDDD1FEDBAFC2.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/10/BMPD9GTM00011229.html#f=slist"},{"ctime":"2016-05-11 08:37","title":"广西警方确认一条鲸鲨被制成鱼肥售卖 2
     * .5元1斤","description":"网易社会","picUrl":"http://s.cimg.163.com/catchpic/B/B0/B075909220ECB3EDF1F98C064626AAB2.jpg
     * .119x83.jpg","url":"http://news.163.com/16/0511/08/BMP8ANFH00011229.html#f=slist"},{"ctime":"2016-05-11
     * 08:40","title":"因打牌出牌太慢 男子遭牌友持刀当街砍成重伤","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/cnews/2016/5/11/2016051108251440a40.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/08/BMP8GMH400011229.html#f=slist"},{"ctime":"2016-05-11 08:43","title":"福州广场舞大妈为挂包 大树遭钉刑(图)",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/cnews/2016/5/11/2016051108362350b85.jpg.119x83.jpg",
     * "url":"http://news.163.com/16/0511/08/BMP8M8GU00014AEE.html#f=slist"},{"ctime":"2016-05-11 08:59",
     * "title":"南京一医院给患者输氧4044小时 院方:无严重后果","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/cnews/2016/5/11/20160511084822c9e5c.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/08/BMP9IJE400011229.html#f=slist"},{"ctime":"2016-05-11 09:11","title":"新婚之夜女方因生理期拒同房 夫妻上法庭闹离婚",
     * "description":"网易社会","picUrl":"http://s.cimg.163.com/cnews/2016/5/11/20160511085537c2788_550.jpg.119x83.jpg",
     * "url":"http://news.163.com/16/0511/09/BMPA9EAS00011229.html#f=slist"},{"ctime":"2016-05-11 07:31",
     * "title":"不爱红妆爱道装 四川90后女大学生放弃高薪职业出家当","description":"网易社会","picUrl":"http://s.cimg.163
     * .com/catchpic/D/D2/D2E24506EE5207BFAF6A7251A3C58877.jpg.119x83.jpg","url":"http://news.163
     * .com/16/0511/07/BMP4IEG400014AED.html#f=slist"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-05-11 12:27
     * title : 湖南一男护工殴打女精神病患者 多人被问责(图)
     * description : 网易社会
     * picUrl : http://s.cimg.163.com/photo/0001/2016-05-10/t_BMNG084T00AP0001.jpg.119x83.jpg
     * url : http://news.163.com/16/0511/12/BMPLGSRP00014AEE.html#f=slist
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
