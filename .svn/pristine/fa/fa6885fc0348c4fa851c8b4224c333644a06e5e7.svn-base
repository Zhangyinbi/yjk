package com.xiaomizhuang.buildcaptain.entity;

import java.util.List;

/**
 * 描述: 验收状态
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-27 11:25
 */

public class YanShouStatus {


    /**
     * yanshoudan_status : [{"text":"水电已验收","status":0},{"text":"泥木已验收","status":0},{"text":"油漆已验收","status":0},{"text":"竣工已验收","status":0}]
     * text : 申请水电验收
     * type : 1
     * massage : 确定当前水电节点施工完成通知监理上门验收吗?
     */

    private String text;
    private String type;
    private String massage;
    /**
     * text : 水电已验收
     * status : 0
     */

    private List<YanshoudanStatusBean> yanshoudan_status;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public List<YanshoudanStatusBean> getYanshoudan_status() {
        return yanshoudan_status;
    }

    public void setYanshoudan_status(List<YanshoudanStatusBean> yanshoudan_status) {
        this.yanshoudan_status = yanshoudan_status;
    }

    public static class YanshoudanStatusBean {
        private String text;
        private int status;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
