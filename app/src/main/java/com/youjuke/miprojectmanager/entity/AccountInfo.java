package com.youjuke.miprojectmanager.entity;

/**
 * Created by user on 2015/11/10.
 */
public class AccountInfo {

    /**
     * id : 94
     * realname : you
     * mobile : 13511111111
     * header : /users/header/thumb_imgs/20150707/normalheader14362342851890.jpg
     * header_small : /users/header/thumb_imgs/20150707/smallheader14362342851890.jpg
     */

    private String id;
    private String realname;
    private String mobile;
    private String header;
    private String header_small;

    public void setId(String id) {
        this.id = id;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setHeader_small(String header_small) {
        this.header_small = header_small;
    }

    public String getId() {
        return id;
    }

    public String getRealname() {
        return realname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHeader() {
        return header;
    }

    public String getHeader_small() {
        return header_small;
    }
}
