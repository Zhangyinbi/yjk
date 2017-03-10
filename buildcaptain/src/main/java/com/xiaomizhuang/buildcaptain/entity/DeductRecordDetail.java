package com.xiaomizhuang.buildcaptain.entity;

import java.util.List;

/**
 * 扣款记录详情实体类
 *
 * @FileName: com.xiaomizhuang.buildcaptain.entity.DeductRecordDetail.java
 * @author: Yangbang
 * @date: 2016-01-21 12:14
 */
public class DeductRecordDetail {

    /**
     * id : 5001
     * content : 不过关个
     * material_info : 哈哈哈哈
     * projectphase : 交底/拆旧
     * amerce_id : 0
     * amerce_money : 200
     * imgs : [{"id":"42740","small":"/images/thumb_imgs/20151228/smallorigin14512926075729.jpg","normal":"/images/thumb_imgs/20151228/normalorigin14512926075729.jpg","addtime":"2015-12-28 16:50:07"}]
     * userimgs : [{"id":"42741","small":"/images/thumb_imgs/20151228/smallorigin14512926071473.jpg","normal":"/images/thumb_imgs/20151228/normalorigin14512926071473.jpg","addtime":"2015-12-28 16:50:07"}]
     * addtime : 2015年12月28日
     * baoming_id : 8797
     * username : 王颖
     * address : 徐汇区湖南路121弄407室
     * amerceItem : null
     */

    private String id;
    private String content;
    private String material_info;
    private String projectphase;
    private String amerce_id;
    private String amerce_money;
    private String addtime;
    private String baoming_id;
    private String username;
    private String address;
    private String amerceItem;
    /**
     * id : 42740
     * small : /images/thumb_imgs/20151228/smallorigin14512926075729.jpg
     * normal : /images/thumb_imgs/20151228/normalorigin14512926075729.jpg
     * addtime : 2015-12-28 16:50:07
     */

    private List<ImgsEntity> imgs;
    /**
     * id : 42741
     * small : /images/thumb_imgs/20151228/smallorigin14512926071473.jpg
     * normal : /images/thumb_imgs/20151228/normalorigin14512926071473.jpg
     * addtime : 2015-12-28 16:50:07
     */

    private List<UserimgsEntity> userimgs;

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMaterial_info(String material_info) {
        this.material_info = material_info;
    }

    public void setProjectphase(String projectphase) {
        this.projectphase = projectphase;
    }

    public void setAmerce_id(String amerce_id) {
        this.amerce_id = amerce_id;
    }

    public void setAmerce_money(String amerce_money) {
        this.amerce_money = amerce_money;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmerceItem(String amerceItem) {
        this.amerceItem = amerceItem;
    }

    public void setImgs(List<ImgsEntity> imgs) {
        this.imgs = imgs;
    }

    public void setUserimgs(List<UserimgsEntity> userimgs) {
        this.userimgs = userimgs;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getMaterial_info() {
        return material_info;
    }

    public String getProjectphase() {
        return projectphase;
    }

    public String getAmerce_id() {
        return amerce_id;
    }

    public String getAmerce_money() {
        return amerce_money;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getAmerceItem() {
        return amerceItem;
    }

    public List<ImgsEntity> getImgs() {
        return imgs;
    }

    public List<UserimgsEntity> getUserimgs() {
        return userimgs;
    }

    public static class ImgsEntity {
        private String id;
        private String small;
        private String normal;
        private String addtime;

        public void setId(String id) {
            this.id = id;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getId() {
            return id;
        }

        public String getSmall() {
            return small;
        }

        public String getNormal() {
            return normal;
        }

        public String getAddtime() {
            return addtime;
        }
    }

    public static class UserimgsEntity {
        private String id;
        private String small;
        private String normal;
        private String addtime;

        public void setId(String id) {
            this.id = id;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getId() {
            return id;
        }

        public String getSmall() {
            return small;
        }

        public String getNormal() {
            return normal;
        }

        public String getAddtime() {
            return addtime;
        }
    }
}
