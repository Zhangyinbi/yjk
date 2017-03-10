package com.example.yangbang.miowner.entity;

/**
 * Created by user on 2015/11/23.
 */
//材料类型大类实体类，包括辅材大类和辅材大类
public class QuestionObject {

    private String img;
    private String id;
    private String bm_id;
    private String name;

    public QuestionObject() {

    }

    public String getBm_id() {
        return bm_id;
    }

    public void setBm_id(String bm_id) {
        this.bm_id = bm_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
