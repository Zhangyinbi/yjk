package com.example.yangbang.miowner.entity;

import java.io.Serializable;

/**
 * Created by user on 2015/12/23.
 */
public class OwnerUser implements Serializable{

    private String baoming;
    private String baoming_mobile;
    private String superior;
    private String superior_mobile;
    private String designer;
    private String designer_mobile;
    private String token;
    private String builder;
    private String builder_mobile;
    private String baoming_id;
    private String head_img;
    private String nikename;
    private int status_trace;  //     <6未签约  >=6已签约
    private int roomtype;   //  1代表别墅  其余都是公寓

    //是否显示(为了区分主从手机号 如果是主手机号那就显示，可以添加关联手机号，否则就不显示)
    private int is_display;//1显示 表示主手机号 2 不显示 表示从手机号



    public OwnerUser(){

    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }

    public String getBaoming() {
        return baoming;
    }

    public void setBaoming(String baoming) {
        this.baoming = baoming;
    }

    public String getBaoming_mobile() {
        return baoming_mobile;
    }

    public void setBaoming_mobile(String baoming_mobile) {
        this.baoming_mobile = baoming_mobile;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getSuperior_mobile() {
        return superior_mobile;
    }

    public void setSuperior_mobile(String superior_mobile) {
        this.superior_mobile = superior_mobile;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDesigner_mobile() {
        return designer_mobile;
    }

    public void setDesigner_mobile(String designer_mobile) {
        this.designer_mobile = designer_mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getBuilder_mobile() {
        return builder_mobile;
    }

    public void setBuilder_mobile(String builder_mobile) {
        this.builder_mobile = builder_mobile;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public int getIs_display() {
        return is_display;
    }

    public void setIs_display(int is_display) {
        this.is_display = is_display;
    }

    public int getStatus_trace() {
        return status_trace;
    }

    public void setStatus_trace(int status_trace) {
        this.status_trace = status_trace;
    }

    public int getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(int roomtype) {
        this.roomtype = roomtype;
    }
}
