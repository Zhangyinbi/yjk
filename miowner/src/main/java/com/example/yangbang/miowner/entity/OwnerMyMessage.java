package com.example.yangbang.miowner.entity;

import java.io.Serializable;

/**
 * Created by user on 2015/12/23.
 */
public class OwnerMyMessage implements Serializable{


    /**
     * head_img :
     * nikename : efrcfr
     * mobile_2 : 13401798067
     * mobile_3 : 18898625551
     */

    private String head_img;
    private String nikename;
    private String mobile_2;
    private String mobile_3;

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

    public String getMobile_2() {
        return mobile_2;
    }

    public void setMobile_2(String mobile_2) {
        this.mobile_2 = mobile_2;
    }

    public String getMobile_3() {
        return mobile_3;
    }

    public void setMobile_3(String mobile_3) {
        this.mobile_3 = mobile_3;
    }
}
