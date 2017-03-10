package com.example.yangbang.miowner.entity;

import java.util.List;

/**
 * 监理报告实体类 节点验收
 *
 * @FileName: com.example.yangbang.miowner.entity.Report.java
 * @author: Yangbang
 * @date: 2015-12-21 11:12
 */
public class Report {


    /**
     * title : 验收单
     * title_id : 1
     * imgs : [{"id":"44197","small":"/images/thumb_imgs/20160624/small14667395103297.jpg","normal":"/images/thumb_imgs/20160624/normal14667395103297.jpg"}]
     * addtime : 2016-06-24 11:38:31
     */

    private String title;
    private String title_id;
    private String addtime;
    /**
     * id : 44197
     * small : /images/thumb_imgs/20160624/small14667395103297.jpg
     * normal : /images/thumb_imgs/20160624/normal14667395103297.jpg
     */

    private List<ImgsBean> imgs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public static class ImgsBean {
        private String id;
        private String small;
        private String normal;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }
    }
}
