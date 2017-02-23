package com.fanqu.main.model;

import com.fanqu.framework.model.BaseData;
import com.fanqu.framework.model.ShareBean;

import java.util.List;

/**
 * 解析主页实体类
 */

public class HomePageEntity extends BaseData{


    /**
     * Data : {"adv":[{"id":"1","province_id":"0","city_id":"0","category":null,"title":"儿时的味道","intro":"细品香醇时光细品香醇时","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-29_586469070acdd.jpg","picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_5824366146a7e.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17","sort":"1","end_time":"0","status":"1","create_time":"1478155357","modify_time":"1478156653"},{"id":"2","province_id":"0","city_id":"0","category":null,"title":"澳洲龙虾主题餐馆","intro":"得50元优惠礼包","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-29_58646992033d4.jpg","picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_58243530ad802.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=4","sort":"2","end_time":"0","status":"1","create_time":"1478156804","modify_time":"0"},{"id":"4","province_id":"0","city_id":"0","category":null,"title":"元气早餐","intro":"\n带你走进这些温柔的店里，完美的一天才要刚刚开始","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635d8c120dd.png","picture":"http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635dfa75e60.png","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17","sort":"3","end_time":"0","status":"1","create_time":"1478766047","modify_time":"1482908045"},{"id":"5","province_id":"0","city_id":"0","category":null,"title":"暖暖火锅季","intro":"寒秋的美食慰藉","thumb":null,"picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_582432677fa85.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=7","sort":"4","end_time":"0","status":"1","create_time":"1478767218","modify_time":"0"}],"slider":[{"id":"72","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Kitchen&a=detail&id=39","picture":"http://o8uz2td92.bkt.clouddn.com/2016-12-21_585a23b117d41.png","status":"1","end_time":"0","city_id":"0","sort":"2"}],"topic":[{"id":"13","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-24_585de89373369.jpg","up_time":"1484202508"}],"share":{"title":"蹭范趣o","desc":"饿了别叫么，就来蹭范趣o","link":"","cover":"http://o8uz2td92.bkt.clouddn.com/2016-12-23_585c865841c86.png"}}
     */

    private DataBean Data;

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public DataBean getData() {
        return Data;
    }

    public static class DataBean {
        /**
         * adv : [{"id":"1","province_id":"0","city_id":"0","category":null,"title":"儿时的味道","intro":"细品香醇时光细品香醇时","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-29_586469070acdd.jpg","picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_5824366146a7e.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17","sort":"1","end_time":"0","status":"1","create_time":"1478155357","modify_time":"1478156653"},{"id":"2","province_id":"0","city_id":"0","category":null,"title":"澳洲龙虾主题餐馆","intro":"得50元优惠礼包","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-29_58646992033d4.jpg","picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_58243530ad802.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=4","sort":"2","end_time":"0","status":"1","create_time":"1478156804","modify_time":"0"},{"id":"4","province_id":"0","city_id":"0","category":null,"title":"元气早餐","intro":"\n带你走进这些温柔的店里，完美的一天才要刚刚开始","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635d8c120dd.png","picture":"http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635dfa75e60.png","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17","sort":"3","end_time":"0","status":"1","create_time":"1478766047","modify_time":"1482908045"},{"id":"5","province_id":"0","city_id":"0","category":null,"title":"暖暖火锅季","intro":"寒秋的美食慰藉","thumb":null,"picture":"http://o8uz2td92.bkt.clouddn.com/2016-11-10_582432677fa85.jpg","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=7","sort":"4","end_time":"0","status":"1","create_time":"1478767218","modify_time":"0"}]
         * slider : [{"id":"72","link":"http://test.cengfan7.cn/wap.php?m=Wap&c=Kitchen&a=detail&id=39","picture":"http://o8uz2td92.bkt.clouddn.com/2016-12-21_585a23b117d41.png","status":"1","end_time":"0","city_id":"0","sort":"2"}]
         * topic : [{"id":"13","thumb":"http://o8uz2td92.bkt.clouddn.com/2016-12-24_585de89373369.jpg","up_time":"1484202508"}]
         * share : {"title":"蹭范趣o","desc":"饿了别叫么，就来蹭范趣o","link":"","cover":"http://o8uz2td92.bkt.clouddn.com/2016-12-23_585c865841c86.png"}
         */

        private ShareBean share;
        private List<AdvBean> adv;
        private List<SliderBean> slider;
        private List<TopicBean> topic;

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public void setAdv(List<AdvBean> adv) {
            this.adv = adv;
        }

        public void setSlider(List<SliderBean> slider) {
            this.slider = slider;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public ShareBean getShare() {
            return share;
        }

        public List<AdvBean> getAdv() {
            return adv;
        }

        public List<SliderBean> getSlider() {
            return slider;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }



        public static class AdvBean {
            /**
             * id : 1
             * province_id : 0
             * city_id : 0
             * category : null
             * title : 儿时的味道
             * intro : 细品香醇时光细品香醇时
             * thumb : http://o8uz2td92.bkt.clouddn.com/2016-12-29_586469070acdd.jpg
             * picture : http://o8uz2td92.bkt.clouddn.com/2016-11-10_5824366146a7e.jpg
             * link : http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17
             * sort : 1
             * end_time : 0
             * status : 1
             * create_time : 1478155357
             * modify_time : 1478156653
             */

            private String id;
            private String province_id;
            private String city_id;
            private Object category;
            private String title;
            private String intro;
            private String thumb;
            private String picture;
            private String link;
            private String sort;
            private String end_time;
            private String status;
            private String create_time;
            private String modify_time;

            public void setId(String id) {
                this.id = id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public void setCategory(Object category) {
                this.category = category;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setModify_time(String modify_time) {
                this.modify_time = modify_time;
            }

            public String getId() {
                return id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public Object getCategory() {
                return category;
            }

            public String getTitle() {
                return title;
            }

            public String getIntro() {
                return intro;
            }

            public String getThumb() {
                return thumb;
            }

            public String getPicture() {
                return picture;
            }

            public String getLink() {
                return link;
            }

            public String getSort() {
                return sort;
            }

            public String getEnd_time() {
                return end_time;
            }

            public String getStatus() {
                return status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getModify_time() {
                return modify_time;
            }
        }

        public static class SliderBean {
            /**
             * id : 72
             * link : http://test.cengfan7.cn/wap.php?m=Wap&c=Kitchen&a=detail&id=39
             * picture : http://o8uz2td92.bkt.clouddn.com/2016-12-21_585a23b117d41.png
             * status : 1
             * end_time : 0
             * city_id : 0
             * sort : 2
             */

            private String id;
            private String link;
            private String picture;
            private String status;
            private String end_time;
            private String city_id;
            private String sort;

            public void setId(String id) {
                this.id = id;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getId() {
                return id;
            }

            public String getLink() {
                return link;
            }

            public String getPicture() {
                return picture;
            }

            public String getStatus() {
                return status;
            }

            public String getEnd_time() {
                return end_time;
            }

            public String getCity_id() {
                return city_id;
            }

            public String getSort() {
                return sort;
            }
        }

        public static class TopicBean {
            /**
             * id : 13
             * thumb : http://o8uz2td92.bkt.clouddn.com/2016-12-24_585de89373369.jpg
             * up_time : 1484202508
             */

            private String id;
            private String thumb;
            private String up_time;

            public void setId(String id) {
                this.id = id;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public void setUp_time(String up_time) {
                this.up_time = up_time;
            }

            public String getId() {
                return id;
            }

            public String getThumb() {
                return thumb;
            }

            public String getUp_time() {
                return up_time;
            }
        }
    }
}
