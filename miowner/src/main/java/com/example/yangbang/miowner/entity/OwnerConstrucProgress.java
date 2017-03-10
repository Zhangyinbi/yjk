package com.example.yangbang.miowner.entity;

import java.util.ArrayList;

/**
 * Created by user on 2015/12/28.施工进度一级列表
 */
public class    OwnerConstrucProgress {

    private String bm_id;
    private String step_id;
    private String step_name;
    private String start_time;
    private String end_time;
    private String status;//0,进行中，1.完成，2未开始
    private ArrayList<OwnerConstrucProgressDetails> create_msg_time = new ArrayList<OwnerConstrucProgressDetails>();

    public OwnerConstrucProgress() {

    }

    public String getBm_id() {
        return bm_id;
    }

    public void setBm_id(String bm_id) {
        this.bm_id = bm_id;
    }

    public String getStep_id() {
        return step_id;
    }

    public void setStep_id(String step_id) {
        this.step_id = step_id;
    }

    public String getStep_name() {
        return step_name;
    }

    public void setStep_name(String step_name) {
        this.step_name = step_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public ArrayList<OwnerConstrucProgressDetails> getCreate_msg_time() {
        return create_msg_time;
    }

    public void setCreate_msg_time(ArrayList<OwnerConstrucProgressDetails> create_msg_time) {
        this.create_msg_time = create_msg_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
