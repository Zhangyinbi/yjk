package com.youjuke.miprojectmanager.entity;

/**
 * Created by mwy on 2016/3/30.
 * 退款记录
 */
public class RefundAmount {
    private String checkout_time;
    private float num;
    private String method_format;
    private String remark;

    public String getCheckout_time() {
        return checkout_time;
    }

    public void setCheckout_time(String checkout_time) {
        this.checkout_time = checkout_time;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getMethod_format() {
        return method_format;
    }

    public void setMethod_format(String method_format) {
        this.method_format = method_format;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
