package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2016/1/20.
 */
public class AssistMaterialOrder {

    private String id;
    private String act_count;
    private String beizhu;
    private String act_total_price;
    private String sendtime;
    private float shl;
    private float shl_count;
    private float act_price;

    public float getAct_price() {
        return act_price;
    }

    public void setAct_price(float act_price) {
        this.act_price = act_price;
    }

    public float getShl() {
        return shl;
    }

    public void setShl(float shl) {
        this.shl = shl;
    }

    public float getShl_count() {
        return shl_count;
    }

    public void setShl_count(float shl_count) {
        this.shl_count = shl_count;
    }

    public AssistMaterialOrder(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAct_count() {
        return act_count;
    }

    public void setAct_count(String act_count) {
        this.act_count = act_count;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getAct_total_price() {
        return act_total_price;
    }

    public void setAct_total_price(String act_total_price) {
        this.act_total_price = act_total_price;
    }

    public String getOrdertime() {
        return sendtime;
    }

    public void setOrdertime(String ordertime) {
        this.sendtime = ordertime;
    }
}
