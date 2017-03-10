package com.xiaomizhuang.buildcaptain.entity;

import java.io.Serializable;

/**
 * Created by user on 2015/11/27.
 */
public class AssistConstructionStage implements Serializable{

    private String step;
    private String name;

    public AssistConstructionStage(){

    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
