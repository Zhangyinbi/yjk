package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2015/11/25.日期筛选实体类
 */
public class DateFilter {

    /**
     * allmonth : 0
     * getallcount : 1
     * onemonth : 1
     * getonecount : 1
     * threemonth : 3
     * getthreecount : 1
     */

    private int allmonth;
    private int getallcount;
    private int onemonth;
    private int getonecount;
    private int threemonth;
    private int getthreecount;

    public void setAllmonth(int allmonth) {
        this.allmonth = allmonth;
    }

    public void setGetallcount(int getallcount) {
        this.getallcount = getallcount;
    }

    public void setOnemonth(int onemonth) {
        this.onemonth = onemonth;
    }

    public void setGetonecount(int getonecount) {
        this.getonecount = getonecount;
    }

    public void setThreemonth(int threemonth) {
        this.threemonth = threemonth;
    }

    public void setGetthreecount(int getthreecount) {
        this.getthreecount = getthreecount;
    }

    public int getAllmonth() {
        return allmonth;
    }

    public int getGetallcount() {
        return getallcount;
    }

    public int getOnemonth() {
        return onemonth;
    }

    public int getGetonecount() {
        return getonecount;
    }

    public int getThreemonth() {
        return threemonth;
    }

    public int getGetthreecount() {
        return getthreecount;
    }
}
