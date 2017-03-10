package com.youjuke.miprojectmanager.entity;

import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class PatrolRecordData implements Serializable {

    private String id;
    private String content;
    private String material_info;
    private List<PatrolRecordImages> imgs;
    private List<PatrolRecordImages> userimgs;
    private String verify;
    private String amerce_money;
    private String amerce;
    private String projectphase;
    private String phasename;
    private String reason;

    public PatrolRecordData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<PatrolRecordImages> getImgs() {
        return imgs;
    }

    public void setImgs(List<PatrolRecordImages> imgs) {
        this.imgs = imgs;
    }

    public List<PatrolRecordImages> getUserimgs() {
        return userimgs;
    }

    public void setUserimgs(List<PatrolRecordImages> userimgs) {
        this.userimgs = userimgs;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getAmerce_money() {
        return amerce_money;
    }

    public void setAmerce_money(String amerce_money) {
        this.amerce_money = amerce_money;
    }

    public String getAmerce() {
        return amerce;
    }

    public void setAmerce(String amerce) {
        this.amerce = amerce;
    }

    public String getProjectphase() {
        return projectphase;
    }

    public void setProjectphase(String projectphase) {
        this.projectphase = projectphase;
    }

    public String getPhasename() {
        return phasename;
    }

    public void setPhasename(String phasename) {
        this.phasename = phasename;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
