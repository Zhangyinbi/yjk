package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2015/11/23.
 */
public class LoginData {

    /**
     * nikename : 测试
     * mobile : 13918697830
     * lastlogin : 2015-11-23 16:48:18
     * uid : 124
     * token : B_aeb7997b-b42a-7ee5-0976-719e7196746b
     */

    private String nikename;
    private String mobile;
    private String lastlogin;
    private String uid;
    private String token;

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNikename() {
        return nikename;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public String getUid() {
        return uid;
    }

    public String getToken() {
        return token;
    }
}
