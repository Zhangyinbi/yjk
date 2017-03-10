package com.example.yangbang.miowner.entity;

import java.io.Serializable;
import java.util.List;

public class PatrolRecordData implements Serializable {


    /**
     *问题描述
     */
    private String content;
    /**
     * 材料描述
     */
    private String material_info;
    /**
     * 罚款金额
     */
    private String amerce_money;
    /**
     * 是否罚款	0 不罚 1 罚款
     */
    private int amerce;
    /**
     *项目阶段
     */
    private int projectphase;
    /**
     * 罚款项目id
     */
    private int amerce_id;
    /**
     * 罚款原因
     */
    private String reason;
    /**
     * 罚款阶段
     */
    private String phasename;


    /**
     * 现场照片
     */
    private List<ImgsBean> imgs;


    /**
     * 业主沟通图片
     */
    private List<ImgsBean> userimgs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMaterial_info() {
        return material_info;
    }

    public void setMaterial_info(String material_info) {
        this.material_info = material_info;
    }

    public String getAmerce_money() {
        return amerce_money;
    }

    public void setAmerce_money(String amerce_money) {
        this.amerce_money = amerce_money;
    }

    public int getAmerce() {
        return amerce;
    }

    public void setAmerce(int amerce) {
        this.amerce = amerce;
    }

    public int getProjectphase() {
        return projectphase;
    }

    public void setProjectphase(int projectphase) {
        this.projectphase = projectphase;
    }

    public int getAmerce_id() {
        return amerce_id;
    }

    public void setAmerce_id(int amerce_id) {
        this.amerce_id = amerce_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPhasename() {
        return phasename;
    }

    public void setPhasename(String phasename) {
        this.phasename = phasename;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<ImgsBean> getUserimgs() {
        return userimgs;
    }

    public void setUserimgs(List<ImgsBean> userimgs) {
        this.userimgs = userimgs;
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
