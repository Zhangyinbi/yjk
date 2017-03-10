package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2016/1/19.
 */
public class AssistAdvocateMaterialOrder {

    private String id;
    private String material_name;
    private String unit;
    private String distributor_name;
    private String brand;
    private String type;
    private String norms;
    private String act_price;
    private boolean isChoose = false;//是否选中，默认不选中
    private String act_count;
    private String beizhu;
    private String expectedtime;
    private String shl;          //损耗率
    private float shl_count;    //损耗数量

	public String getShl() {
		return shl;
	}

	public void setShl(String shl) {
		this.shl = shl;
	}

	public float getShl_count() {
		return shl_count;
	}

	public void setShl_count(float shl_count) {
		this.shl_count = shl_count;
	}

	public AssistAdvocateMaterialOrder() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getAct_price() {
        return act_price;
    }

    public void setAct_price(String act_price) {
        this.act_price = act_price;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
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

    public String getExpectedtime() {
        return expectedtime;
    }

    public void setExpectedtime(String expectedtime) {
        this.expectedtime = expectedtime;
    }
}
