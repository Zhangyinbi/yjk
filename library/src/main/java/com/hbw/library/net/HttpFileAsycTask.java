package com.hbw.library.net;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseErrorData;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * 上传图片
 */
public class HttpFileAsycTask extends AsyncTask<String, Integer, String> {
    private Map<String, Map<String, File>> files;
    private Map<String, String> params;
    private ProgressDialog pDialog;
    private Activity activity;
    private boolean isCloseActivity = true;//是否关闭当前上传图片的页面
    private Handler handler;
    private Gson mGson;
    private Message msg;
    public static final String TAG_HTTP = "图片上传HttpFileAsycTask-->";
    private boolean isShowToastFailureInfo = true;// 是否提示请求错误信息，默认为显示
    private int what;

    public HttpFileAsycTask(Map<String, Map<String, File>> files, Activity activity, Map<String, String> params, Handler handler, int what) {
        this.files = files;
        this.params = params;
        this.activity = activity;
        this.handler = handler;
        this.what = what;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("数据上传中...");
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... url) {
        return new HttpFileUpTool().post(url[0], params, files);
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        pDialog.dismiss();
        if (isCloseActivity) {
            activity.finish();
        }
        if (response == null){
            ToastUtil.show(activity, "服务异常,上传失败!");
            return;
        }
        try {
            response = response.substring(response.indexOf("{"));
        } catch (Exception e) {
            handler.sendEmptyMessage(BaseActivity.DATAERROR);
            ToastUtil.show(activity, "数据解析异常4");
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
                L.e(TAG_HTTP, "error_code：" + error_code + ",FailMessages" + data.message.toString());
                if (isShowToastFailureInfo)
                    ToastUtil.show(activity, data.message.getMsg());
                msg.obj = data;
                msg.what = BaseActivity.ERROR;
                msg.sendToTarget();
            }
            // 200代表访问成功
            else if ("200".equals(status)) {
                L.e(TAG_HTTP, "[接口反馈]SuccessMsg：" + json.getString("message"));
                ResponseSucceedData data = new ResponseSucceedData();
                data.status = json.getString("status");
                data.message = json.getString("message");
                if (json.has("data")) {
                    data.data = json.getString("data");
                    msg.obj = data;
                }
                msg.what = what;
                msg.sendToTarget();
            } else {
                handler.sendEmptyMessage(BaseActivity.DATAERROR);
                ToastUtil.show(activity, "数据解析异常5");
            }
        } catch (JSONException e1) {
            Log.i(TAG_HTTP, e1.toString());
            handler.sendEmptyMessage(BaseActivity.DATAERROR);
            ToastUtil.show(activity, "数据解析异常6");
        }

    }

    public boolean isCloseActivity() {
        return isCloseActivity;
    }

    public void setIsCloseActivity(boolean isCloseActivity) {
        this.isCloseActivity = isCloseActivity;
    }
}
