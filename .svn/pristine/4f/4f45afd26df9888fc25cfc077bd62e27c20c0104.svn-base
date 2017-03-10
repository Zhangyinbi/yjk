package com.example.yangbang.miowner.base;

import android.content.Intent;
import android.os.Message;

import com.example.yangbang.miowner.activity.OwnerLoginActivity;
import com.hbw.library.BaseFragmentActivity;
import com.hbw.library.entity.ResponseErrorData;

/**
 * Created by mwy on 2016/3/29.
 */
public abstract class MiBaseFragmentActivity extends BaseFragmentActivity {

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ERROR:
                //接口返回020 004 就是token失效 需要重新登录
                ResponseErrorData mResponseErrorData = (ResponseErrorData)msg.obj;
                if("020".equals(mResponseErrorData.getMessage().getError_code() + "") ||
                        "004".equals(mResponseErrorData.getMessage().getError_code() + "")){
                    Intent it = new Intent(this,OwnerLoginActivity.class);
                    startActivity(it);
                }
                break;
        }
        return super.handleMessage(msg);
    }
}
