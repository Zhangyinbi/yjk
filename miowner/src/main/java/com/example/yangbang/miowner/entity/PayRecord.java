package com.example.yangbang.miowner.entity;

/**
 * Created by mwy on 2016/3/29.
 */
public class PayRecord {
    private double total_actual_payment;        //实付金额
    private double payment;                     //期款实付金额
    private double total_advance_payment;       //增加项预付金额
    private double total_refund_amount;         //退款金额
    private double zjx_total_price;             //增项金额
    private double total_fee;                   //合同总额

    public double getTotal_actual_payment() {
        return total_actual_payment;
    }

    public void setTotal_actual_payment(double total_actual_payment) {
        this.total_actual_payment = total_actual_payment;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getTotal_advance_payment() {
        return total_advance_payment;
    }

    public void setTotal_advance_payment(double total_advance_payment) {
        this.total_advance_payment = total_advance_payment;
    }

    public double getTotal_refund_amount() {
        return total_refund_amount;
    }

    public void setTotal_refund_amount(double total_refund_amount) {
        this.total_refund_amount = total_refund_amount;
    }

    public double getZjx_total_price() {
        return zjx_total_price;
    }

    public void setZjx_total_price(double zjx_total_price) {
        this.zjx_total_price = zjx_total_price;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }
}
