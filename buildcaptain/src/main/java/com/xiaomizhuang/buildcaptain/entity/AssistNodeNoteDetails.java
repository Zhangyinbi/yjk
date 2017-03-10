package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2016/2/23.
 */
public class AssistNodeNoteDetails {

    private String interval_time;
    private String id;
    private String title;
    private String reason;
    private String operation_group;
    private String user_name;
    private String create_time;

    public AssistNodeNoteDetails(){

    }

    public String getInterval_time() {
        return interval_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOperation_group() {
        return operation_group;
    }

    public void setOperation_group(String operation_group) {
        this.operation_group = operation_group;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
