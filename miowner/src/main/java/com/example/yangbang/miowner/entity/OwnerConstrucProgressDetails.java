package com.example.yangbang.miowner.entity;

import java.util.ArrayList;

/**
 * Created by user on 2015/12/28.施工进度二级列表
 */
public class OwnerConstrucProgressDetails {

    private String create_time;
    private String get_day;
    private String checked;
    private String status_color;
    private ArrayList<OwnerConstrucProgressDetailsChild> msg_list = new ArrayList<OwnerConstrucProgressDetailsChild>();

    public OwnerConstrucProgressDetails() {

    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getGet_day() {
        return get_day;
    }

    public void setGet_day(String get_day) {
        this.get_day = get_day;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public ArrayList<OwnerConstrucProgressDetailsChild> getMsg_list() {
        return msg_list;
    }

    public void setMsg_list(ArrayList<OwnerConstrucProgressDetailsChild> msg_list) {
        this.msg_list = msg_list;
    }

    public String getStatus_color() {
        return status_color;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
    }
}
