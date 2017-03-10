package com.xiaomizhuang.buildcaptain.util;

public class HttpConstant {

    public static final String HOST_ONLINE = "http://api.xiaomizhuang.com/";//线上地址
    public static final String HOST_OFFLINE = "http://api.xmz.com/";//线下测试地址*/


    /**勿改 默认线上 切换请到登陆页面切换*/
    public static final String INTERFACEADDRESS = HOST_ONLINE;

    /**
     * 图片前缀
     */
    public static final String IMAGES_ROOT_URL = "http://image.xiaomizhuang.com";
    public static final String IMAGEADDRESS = "http://image.xiaomizhuang.com";


// 1、登陆
    public static final String LoginUrl = INTERFACEADDRESS+"users/builder_login";
    // 2、工地列表
    public static final String GongDiListUrl = INTERFACEADDRESS + "material/gongdilist";

    // 3、铺材明细-大类
    public static final String DaLeiUrl = INTERFACEADDRESS + "material/dalei";

    // 4、铺材明细-小类
    public static final String XiaoLeiUrl = INTERFACEADDRESS + "material/xiaolei";

    // 5、订单详情
    public static final String OrderDetailsUrl = INTERFACEADDRESS + "material/orderdetails";

    // 6、选择材料类型
    public static final String SelectTypeUrl = INTERFACEADDRESS + "material/selecttype";

    // 7、选择材料规格
    public static final String SelectNormsUrl = INTERFACEADDRESS + "material/selectnorms";

    // 8、下单操作
    public static final String MaterialPlanOrderFucaiUrl = INTERFACEADDRESS + "material/material_plan_order_fucai";

    // 9、订单明细
    public static final String OrderPlanDetailUrl = INTERFACEADDRESS + "material/plan";

    // 10、确认收货
//    public static final String confirmReceiptUrl = INTERFACEADDRESS + "addgoodstime";
    public static final String confirmReceiptUrl = INTERFACEADDRESS + "material/confirm_order";

    // 11、按时间查询
    public static final String OrderTimeQueryUrl = INTERFACEADDRESS + "material/order_time_query";

    // 12、全部消息
    public static final String QueryMessageUrl = INTERFACEADDRESS + "material/userbuildmsg";

    // 13、申请验收
    public static final String ApplyCheckUrl = INTERFACEADDRESS + "material/user_build_msg_query";

    // 14、选择施工阶段
    public static final String GetProjectUrl = INTERFACEADDRESS + "material/get_project";

    // 15、获取消息提醒总数
    public static final String GetMessageTotalUrl = INTERFACEADDRESS + "material/tolote_msg";

    // 16、选择材料品牌
    public static final String GetBandInfoUrl = INTERFACEADDRESS + "material/get_band_info";

    // 17、版本更新
    public static final String GetVersion = INTERFACEADDRESS + "material/version";

    // 18、选择材料单位
    public static final String SelectUnitUrl = INTERFACEADDRESS + "material/selectunit";

    // 19、排期列表
    public static final String GETPLANUrl = INTERFACEADDRESS + "material/getplan";

    // 20、主材类型，大类
    public static final String MainMaterialType = INTERFACEADDRESS + "material/main_categoriesplan";

    // 21、修改密码
    public static final String EditPwdUrl = INTERFACEADDRESS + "material/edit_pwd";

    // 22、退出登录
    public static final String LogOutUrl = INTERFACEADDRESS + "material/logout";

    // 23、主材下单数据
    public static final String ZcOrderDetails = INTERFACEADDRESS + "material/zc_orderdetails";

    // 24、主材下单
    public static final String ZcOrderFucaiUrl = INTERFACEADDRESS + "material/zc_order_fucai";

    //25、工程量列表
    public static final String AddQuantitiesList = INTERFACEADDRESS + "material/ProjectOrderList";

    //26、罚款记录列表
    public static final String AmerceRecordList = INTERFACEADDRESS + "material/AmerceList";

    //27、罚款记录详情
    public static final String AmerceRecordDetail = INTERFACEADDRESS + "material/AmerceInfo";

    //28、所有罚款记录列表
    public static final String AmerceRecordAllList = INTERFACEADDRESS + "material/AmerceAllList";

    //29、获取排期详情列表
    public static final String ScheduleDetailList = INTERFACEADDRESS + "material/get_detail";

    //30、获取备注
    public static final String GetScheduleRemark = INTERFACEADDRESS + "material/get_logs";

    //31、添加备注
    public static final String AddScheduleRemark = INTERFACEADDRESS + "material/add_logs";

    //32、订单详情操作
    public static final String ScheduleOrderDetailAction = INTERFACEADDRESS + "material/detail_operation";

    //33、增加工程项目类别
    public static final String  ProjectTypeUrl = INTERFACEADDRESS + "material/ProjectType";

    //34、工程增加项分类下对应列表
    public static final String ProjectListUrl = INTERFACEADDRESS + "material/ProjectList";

    //34、工程增加项加入订单
    public static final String AddProjectOrderUrl = INTERFACEADDRESS + "material/AddProjectOrder";

    //34、施工队长的施工记录列表
    public static final String BuildRecordListUrl = INTERFACEADDRESS + "material/BuildRecordList";

    //35、施工队长添加施工记录
    public static final String AddBuildRecordUrl = INTERFACEADDRESS + "material/AddBuildRecord";

    //36、获取节点备注
    public static final String Get_logsUrl = INTERFACEADDRESS + "material/get_logs";

    //37、删除施工记录
    public static final String DelBuildRecord = INTERFACEADDRESS + "material/DelBuildRecord";

    // 38、下上次下单操作
    public static final String MaterialPlanOrderFucaiLastUrl = INTERFACEADDRESS + "material/material_plan_order_fucai_last";

    //39 工地列表 拒接
    public static final String SystemSendOrder = INTERFACEADDRESS + "material/SystemSendOrder";

    //40 设置排期 施工节点
    public static final String GetProjects = INTERFACEADDRESS + "userplan/get_projects";

    //41 设置排期 设置和保存排期
    public static final String SetPlan = INTERFACEADDRESS + "userplan/setplan";

    //42 验收状态
    public static final String YanShouStatus = INTERFACEADDRESS + "material/YanShouStatus";

    //43 申请验收
    public static final String YanShouApplication = INTERFACEADDRESS + "material/YanShouApplication";

    //44 自购材料列表
    public static final String BuySelfOrderList = INTERFACEADDRESS + "material/BuySelfOrderList";

    //45 工期收款
    public static final String Payment = INTERFACEADDRESS + "material/payment";
    //46材料追加款
    public static final String AddMoney = INTERFACEADDRESS + "Material/payment2";
}
