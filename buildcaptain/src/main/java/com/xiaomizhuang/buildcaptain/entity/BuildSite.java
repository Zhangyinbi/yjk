package com.xiaomizhuang.buildcaptain.entity;

/**
 * Created by user on 2015/11/20.
 */
public class BuildSite {

	/**
	 * uid : 11040
	 * name : 刘辉
	 * yz_mobile : 18917618562
	 * zx_address : 松江区千新公路718弄91号
	 * superior : you
	 * superior_mobile : 13511111111
	 * designer : 张振帆
	 * designer_mobile : 13818760375
	 * actual_start_time : 2016-01-19
	 * msgcount : 0
	 * is_jgstatus : 0
	 */

	private String uid;
	private String name;
	private String yz_mobile;
	private String zx_address;
	private String superior;
	private String superior_mobile;
	private String designer;
	private String designer_mobile;
	private String actual_start_time;
	private String hetong_complete_time;
	private String muser; //下单员
	private String muser_mobile;
	private double area;

	private int confirm_num;

	private int msgcount;
	private int is_jgstatus;
	private String start_msg;//是否开工 信息  为空 则说明已开工
	private String taocan_type;

	private String refuse_msg; //是否显示拒单按钮  不为空 则弹出拒单信息  为空 则隐藏拒单按钮
	private int set_project;//是否显示排期按钮

	private int yjk_jianli;//是否是优居客监理

	private String jdimg_msg;//只有在交底单完成之后才能 设置排期 为空则允许设置 消息不为空 则禁止

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYz_mobile(String yz_mobile) {
		this.yz_mobile = yz_mobile;
	}

	public void setZx_address(String zx_address) {
		this.zx_address = zx_address;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public void setSuperior_mobile(String superior_mobile) {
		this.superior_mobile = superior_mobile;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public void setDesigner_mobile(String designer_mobile) {
		this.designer_mobile = designer_mobile;
	}

	public void setActual_start_time(String actual_start_time) {
		this.actual_start_time = actual_start_time;
	}

	public void setMsgcount(int msgcount) {
		this.msgcount = msgcount;
	}

	public void setIs_jgstatus(int is_jgstatus) {
		this.is_jgstatus = is_jgstatus;
	}

	public String getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getYz_mobile() {
		return yz_mobile;
	}

	public String getZx_address() {
		return zx_address;
	}

	public String getSuperior() {
		return superior;
	}

	public String getSuperior_mobile() {
		return superior_mobile;
	}

	public String getDesigner() {
		return designer;
	}

	public String getDesigner_mobile() {
		return designer_mobile;
	}

	public String getActual_start_time() {
		return actual_start_time;
	}

	public int getMsgcount() {
		return msgcount;
	}

	public int getIs_jgstatus() {
		return is_jgstatus;
	}

	public String getHetong_complete_time() {
		return hetong_complete_time;
	}

	public void setHetong_complete_time(String hetong_complete_time) {
		this.hetong_complete_time = hetong_complete_time;
	}
	public int getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(int confirm_num) {
		this.confirm_num = confirm_num;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getMuser() {
		return muser;
	}

	public void setMuser(String muser) {
		this.muser = muser;
	}

	public String getMuser_mobile() {
		return muser_mobile;
	}

	public void setMuser_mobile(String muser_mobile) {
		this.muser_mobile = muser_mobile;
	}


	public String getTaocan_type() {
		return taocan_type;
	}

	public void setTaocan_type(String taocan_type) {
		this.taocan_type = taocan_type;
	}


	public int getSet_project() {
		return set_project;
	}

	public void setSet_project(int set_project) {
		this.set_project = set_project;
	}

	public int getYjk_jianli() {
		return yjk_jianli;
	}

	public void setYjk_jianli(int yjk_jianli) {
		this.yjk_jianli = yjk_jianli;
	}


	public String getStart_msg() {
		return start_msg;
	}

	public void setStart_msg(String start_msg) {
		this.start_msg = start_msg;
	}

	public String getRefuse_msg() {
		return refuse_msg;
	}

	public void setRefuse_msg(String refuse_msg) {
		this.refuse_msg = refuse_msg;
	}

	public String getJdimg_msg() {
		return jdimg_msg;
	}

	public void setJdimg_msg(String jdimg_msg) {
		this.jdimg_msg = jdimg_msg;
	}
}
