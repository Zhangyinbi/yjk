package com.xiaomizhuang.buildcaptain.entity;

import java.io.Serializable;

/**
 * Created by user on 2016/1/14.
 */
public class AssistSelectUnit implements Serializable{

    private String unit;
    private String act_price;
    private String material_id;

    public AssistSelectUnit() {

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAct_price() {
        return act_price;
    }

    public void setAct_price(String act_price) {
        this.act_price = act_price;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }
}
