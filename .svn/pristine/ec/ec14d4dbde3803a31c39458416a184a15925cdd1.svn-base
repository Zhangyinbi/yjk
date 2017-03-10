package com.youjuke.miprojectmanager.util;

import android.content.Context;

import com.hbw.library.utils.SPUtils;

/**
 * @author 董贤刚
 * @ClassName: HelpClass
 * @Description:
 * @date 2014-12-24 下午2:53:30
 */
public class HelpClass {

    public static final String[] MianJiStr = {"全部", "60㎡以下", "60㎡-90㎡",
            "90㎡-200㎡", "200㎡以上"};
    public static final String[] StyleStr = {"全部", "简约", "欧式", "中国风", "其他"};
    public static final String[] FangXingStr = {"全部", "公寓", "复式", "别墅", "其他"};
    public static final String SpAccount = "SpAccount";
    public static final String SpPwd = "SpPwd";
    public static final String SpToken = "SpToken";
    public static final String SpLastlogin = "SpLastlogin";
    public static final String SpUid = "SpUid";
    public static final String SpIsLogin = "SpIsLogin";
    public static final String SploginType = "loginType";
    public static final String SploginTypeStr = "loginTypeStr";
    public static final String SpWho = "who";
    public static final String SpIsFirstRunApp = "isFirstRunApp";
    public static final String[] positionArray = {"全部", "卢湾区", "静安区", "徐汇区",
            "长宁区", "虹口区", "杨浦区", "闸北区", "普陀区", "浦东新区", "闵行区", "宝山区", "嘉定区",
            "金山区", "松江区", "青浦区", "南汇区", "奉贤区", "杨浦区"};
    public static final String[] ReserveType = {"验房", "泥土", "水电", "收尾", "竣工"};

    public static final String PRO_POST_ID = "pro_post_id";
    public static final String TOKEN = "token";
    public static final String PATROLRECORDDETAILS = "patrolRecordDetails";
    public static final String ID = "id";
    public static final String IMAGE_ID = "image_id";
    public static final String ISONLINE = "isonline";

    /**
     * 登出gotye和app
     *
     * @param context
     * @param isLogoutGotye 是否同时登出gotye
     */
    public static void logout(Context context, boolean isLogoutGotye) {
        // 删除token
        SPUtils.remove(context, SpToken);
    }
}
