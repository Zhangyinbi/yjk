package com.example.yangbang.miowner.entity;

/**
 * Created by mwy on 2016/3/30.
 * 付款记录-合同金额-期款明细
 */
public class PaymentDetail {
    private String title;
    private String bili;
    private double fee;
    private double payment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBili() {
        return bili;
    }

    public void setBili(String bili) {
        this.bili = bili;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
