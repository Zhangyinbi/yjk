package com.example.yangbang.miowner.entity;

/**
 * Created by user on 2015/12/23.
 */
public class OwnerProjectSchedul {

    private String bm_id;
    private String proportion;
    private String days;
    private String projectphase;
    private String start_day;
    private String end_day;
    private String sum;

    public OwnerProjectSchedul() {

    }

    public String getBm_id() {
        return bm_id;
    }

    public void setBm_id(String bm_id) {
        this.bm_id = bm_id;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getProjectphase() {
        return projectphase;
    }

    public void setProjectphase(String projectphase) {
        this.projectphase = projectphase;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getEnd_day() {
        return end_day;
    }

    public void setEnd_day(String end_day) {
        this.end_day = end_day;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
