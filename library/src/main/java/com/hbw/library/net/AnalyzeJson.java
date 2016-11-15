package com.hbw.library.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hbw.library.BaseActivity;
import com.hbw.library.InitApplication;
import com.hbw.library.R;
import com.hbw.library.entity.ResponseErrorData;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;
import com.hbw.library.view.CustomProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/*
测试返回结果：
请求成功：
{
    "status": "200",
    "error_code": "xxxxxx",
    "message": "xxxxxx",
    "data": {

    }
}
错误情况：
{
    "status": "400",
    "error_code": "xxxxxx",
    "message": {
            "error_code": "xxxxxx",
            "msg": "xxxxxx"
        }
}
*/

/**
 * 网络请求类
 *
 * #0001    mwy     2017/07/21      添加线上下线切换
 */
public class AnalyzeJson {

    public static final String TAG_HTTP = "[数据请求]tag_http";

    private Context context;
    private Handler handler;
    private CustomProgressDialog mCustomProgressDialog;

    private boolean isShowDialog = true;// 是否显示进度条，默认为显示

    private boolean isShowToastFailureInfo = true;// 是否提示请求错误信息，默认为显示

    private Gson mGson;
    private Message msg;

    public AnalyzeJson(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    /**
     * 请求数据
     *
     * @param url    请求url
     * @param params 请求参数,get请求无参数可为null
     * @param what   message what
     */
    public void requestData(String url, HashMap<String, String> params, int what) {
	    //如果是debug模式 并且是线下模式时   <#0001
        if (null != InitApplication.host && InitApplication.getIsDebug() && InitApplication.isOnline == false){
			String host = InitApplication.host;
	        String oldhost = url.substring(0,url.indexOf(".com")+5);
	        url = url.replace(oldhost,host);
        }// #0001>
        connectForHttp(url, params, what);
    }

    private void connectForHttp(String url, HashMap<String, String> params, int what) {
        if (!Tools.isAccessNetwork(context)) {
            handler.sendEmptyMessage(BaseActivity.NONET);
            ToastUtil.show(context, "当前网络不可访问");
            return;
        }
        if (params == null) {
            if (isShowDialog)
                showProgressDialog();
            doGet(url, what);
        } else {
            if (isShowDialog)
                showProgressDialog();
            doPost(url, params, what);
        }
    }

    /**
     * @param @param url 接口完整地址
     * @return
     * @throws
     * @Title: doGet
     * @Description:  get请求
     */
    private void doGet(final String url, final int what) {
        L.i(TAG_HTTP, "[接口地址]url = " + url);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dismissProgressDialog();
	                    try {
		                    L.i(TAG_HTTP, "[接口返回数据]response = " + new JSONObject(response).toString());
	                    } catch (JSONException e) {
		                    L.e(TAG_HTTP, "[接口返回数据]response = " + response);
		                    e.printStackTrace();
	                    }
	                    analyzeJsonData(response, what);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgressDialog();
                ToastUtil.show(context, "网络连接超时");
                L.i(TAG_HTTP, "[服务器错误信息反馈]response error = " + error.toString());
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                L.i(TAG_HTTP, "[服务器响应码]status_code-->" + response);
                String jsonString;
                try {
                    jsonString = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    jsonString = new String(response.data);
                }
                return Response.success(jsonString,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        }
        ;
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitApplication.mRequestQueue.add(stringRequest);
    }

    /**
     * @param @param url 接口完整地址
     * @param @param params post请求参数
     * @return
     * @throws
     * @Title: doPost
     * @Description: post请求
     */
    private void doPost(final String url, final HashMap<String, String> params, final int what) {
        L.i(TAG_HTTP, "[接口地址]url = " + url + " , [请求参数]params = " + params.toString());
        JsonStringPostRequest jsonObjectPostRequest = new JsonStringPostRequest(
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dismissProgressDialog();
	            try {
		            L.i(TAG_HTTP, "[接口返回数据]response = " + (new JSONObject(response).toString()));
	            } catch (JSONException e) {
		            L.e(TAG_HTTP, "[接口返回数据]response = " + response);
		            e.printStackTrace();
	            }
	            analyzeJsonData(response, what);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgressDialog();
                ToastUtil.show(context, "网络连接超时");
                handler.sendEmptyMessage(BaseActivity.TIMEOUT);
                L.i(TAG_HTTP, "[服务器异常]response error = " + error.toString());
            }
        }, params) {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                L.i(TAG_HTTP, "[服务器响应码]status_code-->" + response.statusCode);
                String jsonString;
                try {
                    jsonString = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    jsonString = new String(response.data);
                }
                return Response.success(jsonString,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        jsonObjectPostRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitApplication.mRequestQueue.add(jsonObjectPostRequest);
    }

    /**
     * @param @param response 当数据请求成功时返回String类型数据
     * @return void 返回类型
     * @throws
     * @Title: analyzeJsonData 解析请求成功数据 并返回handler
     * @Description:
     */
    private void analyzeJsonData(String response, int what) {
        try {
            response = response.substring(response.indexOf("{"));
        } catch (Exception e) {
            handler.sendEmptyMessage(BaseActivity.DATAERROR);
            ToastUtil.show(context, "数据解析异常3");
        }
        try {
            msg = handler.obtainMessage();
            JSONObject json = new JSONObject(response);
            String status = json.getString("status");
            // 400代表错误信息
            if (status.equals("400") || status.equals("404") || status.equals("500")) {
                String error_code = json.getString("error_code");
                if (mGson == null) {
                    mGson = new Gson();
                }
                ResponseErrorData data = mGson.fromJson(json.toString(), ResponseErrorData.class);
                L.e(TAG_HTTP, "[接口返回错误信息code]error_code：" + error_code + ",[接口返回错误信息]FailMessages" + data.message.toString());
                if (isShowToastFailureInfo)
                    ToastUtil.show(context, data.message.getMsg());
                msg.obj = data;
                msg.what = BaseActivity.ERROR;
                msg.sendToTarget();
            } else if ("200".equals(status)) {// 200代表访问成功
                L.e(TAG_HTTP, "[接口返回成功信息]SuccessMsg：" + json.getString("message"));
                ResponseSucceedData data = new ResponseSucceedData();
                data.status = json.getString("status");
//                data.error_code = json.getString("error_code");
                data.message = json.getString("message");
                data.data = json.getString("data");
                msg.obj = data;
                msg.what = what;
                msg.sendToTarget();
            } else {
                handler.sendEmptyMessage(BaseActivity.DATAERROR);
                ToastUtil.show(context, "数据解析异常2");
            }
        } catch (JSONException e1) {
            Log.i(TAG_HTTP, e1.toString());
            handler.sendEmptyMessage(BaseActivity.DATAERROR);
            ToastUtil.show(context, "数据解析异常1");
        }
    }

    /********************************* dialog的显示与取消 ********************************************/

    /**
     * 显示一个ProgressDialog
     */
    private void showProgressDialog() {

        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(context,
                    R.drawable.anim_progressr);
        }
        mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
        if (!mCustomProgressDialog.isShowing())
            mCustomProgressDialog.show();
    }

    /**
     * 取消当前显示的ProgressDialog
     */
    private void dismissProgressDialog() {
        if (isShowDialog) {
            if (mCustomProgressDialog != null
                    || mCustomProgressDialog.isShowing()) {
                mCustomProgressDialog.dismiss();
            }
        }
    }

    /**
     * ****************************** dialog方法结束 *******************************************
     */

    public void setShowDialog(boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
    }

    public void setShowToastFailureInfo(boolean isShowToastFailureInfo) {
        this.isShowToastFailureInfo = isShowToastFailureInfo;
    }


}