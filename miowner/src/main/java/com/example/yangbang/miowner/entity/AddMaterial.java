package com.example.yangbang.miowner.entity;

/**
 * Created by mwy on 2016/3/30.
 * 付款金额-增项金额-主材明细
 */
public class AddMaterial {
    private double total_price;
    private double total_price_pay;
    private int order_id;
    private String material_name;
    private String brand_name;
    private String type_name;
    private double act_count;
    private double act_price;
    private double act_total_price;
    private int shipping;
    private String zjx_pay;
    private String zjx_paytime;

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getTotal_price_pay() {
        return total_price_pay;
    }

    public void setTotal_price_pay(double total_price_pay) {
        this.total_price_pay = total_price_pay;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public double getAct_count() {
        return act_count;
    }

    public void setAct_count(double act_count) {
        this.act_count = act_count;
    }

    public double getAct_price() {
        return act_price;
    }

    public void setAct_price(double act_price) {
        this.act_price = act_price;
    }

    public double getAct_total_price() {
        return act_total_price;
    }

    public void setAct_total_price(double act_total_price) {
        this.act_total_price = act_total_price;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getZjx_pay() {
        return zjx_pay;
    }

    public void setZjx_pay(String zjx_pay) {
        this.zjx_pay = zjx_pay;
    }

    public String getZjx_paytime() {
        return zjx_paytime;
    }

    public void setZjx_paytime(String zjx_paytime) {
        this.zjx_paytime = zjx_paytime;
    }
}
