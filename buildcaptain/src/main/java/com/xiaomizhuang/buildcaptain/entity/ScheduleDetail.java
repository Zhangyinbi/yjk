package com.xiaomizhuang.buildcaptain.entity;

/**
 * 排期详情订单实体类
 *
 * @FileName: com.xiaomizhuang.buildcaptain.entity.ScheduleDetail.java
 * @author: Yangbang
 * @date: 2016-01-22 11:25
 */
public class ScheduleDetail {

    /**
     * id : 3531
     * baoming_id : 10369
     * material_budget_id : 16264
     * user_material_class_id : 2897
     * project_material_id : 183
     * material_phase_id : 117
     * m_type : 1
     * remind_time : 2016-03-07
     * confirm_time : 0000-00-00
     * interval_time : 0
     * edit_group : 8
     * operation_id : 0
     * status : 0
     * create_time : 2016-02-23 17:49:40
     * update_time : 0000-00-00 00:00:00
     * type : 1222
     * amerce_id : 0
     * fid : 571
     * brand : 98
     * norms : 粉色自然石
     * unit : m²
     * m_name : 玻化砖
     * brand_name : 好美佳(玻化石)
     * type_name : 玻化砖
     * distributor_name : 恒晟批发仓库好美家
     * act_count : 1.00
     * act_price : 69.00
     * parent : 瓷砖
     * material_name : 玻化砖
     * operation : 发货确认
     * order_code : 118
     * is_delay : 1
     * get_order_time : 0000-00-00 00:00:00
     * shouhuo_time : 0000-00-00 00:00:00
     * expectedtime : 0000-00-00 00:00:00
     * istype : 0
     * order_status : 收货
     */

    private String id;
    private String baoming_id;
    private String material_budget_id;
    private String user_material_class_id;
    private String project_material_id;
    private String material_phase_id;
    private String m_type;
    private String remind_time;
    private String confirm_time;
    private String interval_time;
    private String edit_group;
    private String operation_id;
    private String status;
    private String create_time;
    private String update_time;
    private String type;
    private String amerce_id;
    private String fid;
    private String brand;
    private String norms;
    private String unit;
    private String m_name;
    private String brand_name;
    private String type_name;
    private String distributor_name;
    private String act_count;
    private String act_price;
    private String parent;
    private String material_name;
    private String operation;
    private String order_code;  //显示状态 0下单 104复测下单  117收货|安装  115下单 119确认收货
    private int is_delay;
    private String get_order_time;
    private String shouhuo_time;
    private String expectedtime;
    private int istype;
    private String order_status;

    public void setId(String id) {
        this.id = id;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }

    public void setMaterial_budget_id(String material_budget_id) {
        this.material_budget_id = material_budget_id;
    }

    public void setUser_material_class_id(String user_material_class_id) {
        this.user_material_class_id = user_material_class_id;
    }

    public void setProject_material_id(String project_material_id) {
        this.project_material_id = project_material_id;
    }

    public void setMaterial_phase_id(String material_phase_id) {
        this.material_phase_id = material_phase_id;
    }

    public void setM_type(String m_type) {
        this.m_type = m_type;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }

    public void setEdit_group(String edit_group) {
        this.edit_group = edit_group;
    }

    public void setOperation_id(String operation_id) {
        this.operation_id = operation_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmerce_id(String amerce_id) {
        this.amerce_id = amerce_id;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public void setAct_count(String act_count) {
        this.act_count = act_count;
    }

    public void setAct_price(String act_price) {
        this.act_price = act_price;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public void setIs_delay(int is_delay) {
        this.is_delay = is_delay;
    }

    public void setGet_order_time(String get_order_time) {
        this.get_order_time = get_order_time;
    }

    public void setShouhuo_time(String shouhuo_time) {
        this.shouhuo_time = shouhuo_time;
    }

    public void setExpectedtime(String expectedtime) {
        this.expectedtime = expectedtime;
    }

    public void setIstype(int istype) {
        this.istype = istype;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getId() {
        return id;
    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public String getMaterial_budget_id() {
        return material_budget_id;
    }

    public String getUser_material_class_id() {
        return user_material_class_id;
    }

    public String getProject_material_id() {
        return project_material_id;
    }

    public String getMaterial_phase_id() {
        return material_phase_id;
    }

    public String getM_type() {
        return m_type;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public String getInterval_time() {
        return interval_time;
    }

    public String getEdit_group() {
        return edit_group;
    }

    public String getOperation_id() {
        return operation_id;
    }

    public String getStatus() {
        return status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getType() {
        return type;
    }

    public String getAmerce_id() {
        return amerce_id;
    }

    public String getFid() {
        return fid;
    }

    public String getBrand() {
        return brand;
    }

    public String getNorms() {
        return norms;
    }

    public String getUnit() {
        return unit;
    }

    public String getM_name() {
        return m_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getType_name() {
        return type_name;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public String getAct_count() {
        return act_count;
    }

    public String getAct_price() {
        return act_price;
    }

    public String getParent() {
        return parent;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public String getOperation() {
        return operation;
    }

    public String getOrder_code() {
        return order_code;
    }

    public int getIs_delay() {
        return is_delay;
    }

    public String getGet_order_time() {
        return get_order_time;
    }

    public String getShouhuo_time() {
        return shouhuo_time;
    }

    public String getExpectedtime() {
        return expectedtime;
    }

    public int getIstype() {
        return istype;
    }

    public String getOrder_status() {
        return order_status;
    }
}
