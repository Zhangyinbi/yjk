package com.example.yangbang.miowner.entity;

/**
 * 订单状态
 *
 * @FileName: com.xiaomizhuang.mimaterialsbusiness.entity.OrderStatus.java
 * @author: Yangbang
 * @date: 2016-03-30 10:49
 */
public class OrderStatus {

    /**
     * status_id : 116
     * status_name : 待接单
     * status_count : 1
     */

    private int status_id;
    private String status_name;
    private int status_count;

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public void setStatus_count(int status_count) {
        this.status_count = status_count;
    }

    public int getStatus_id() {
        return status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public int getStatus_count() {
        return status_count;
    }
}
