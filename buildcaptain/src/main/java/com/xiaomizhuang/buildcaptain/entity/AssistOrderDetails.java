package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2015/11/24.
 */
public class AssistOrderDetails {

    private String baoming_id;
    private String mt_id;
    private String step;
    private String name;

    public AssistOrderDetails(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }
}
