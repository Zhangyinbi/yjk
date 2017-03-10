package com.youjuke.miprojectmanager.entity;

/**
 * 工程量实体类
 *
 * @FileName: com.xiaomizhuang.buildcaptain.entity.Quantities.java
 * @author: Yangbang
 * @date: 2016-01-20 16:57
 */
public class Quantities {

    /**
     * id : 16442
     * mt_id : 7130
     * act_total_price : 15.00
     * act_count : 5.00
     * posttime : 2016-03-14 17:28:03
     * zjx_name : 结构封闭
     * brand_name : 无
     * mtype_name : 无
     * norms : 结构硅胶
     * beizhu :
     */

    private String id;
    private String mt_id;
    private String act_total_price;
    private String act_count;
    private String posttime;
    private String zjx_name;
    private String brand_name;
    private String mtype_name;
    private String norms;
    private String beizhu;

    public void setId(String id) {
        this.id = id;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public void setAct_total_price(String act_total_price) {
        this.act_total_price = act_total_price;
    }

    public void setAct_count(String act_count) {
        this.act_count = act_count;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public void setZjx_name(String zjx_name) {
        this.zjx_name = zjx_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setMtype_name(String mtype_name) {
        this.mtype_name = mtype_name;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getId() {
        return id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public String getAct_total_price() {
        return act_total_price;
    }

    public String getAct_count() {
        return act_count;
    }

    public String getPosttime() {
        return posttime;
    }

    public String getZjx_name() {
        return zjx_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getMtype_name() {
        return mtype_name;
    }

    public String getNorms() {
        return norms;
    }

    public String getBeizhu() {
        return beizhu;
    }
}
