package com.hbw.library.net;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.hbw.library.InitApplication;
import com.hbw.library.R;
import com.hbw.library.entity.ResponseErrorData;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.CustomProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * 异步任务进行下载（单个图片）
 *
 * @author Youjk
 */
public class HttpOneFileAsycTask extends AsyncTask<String, Integer, String> {

    public static final String TAG_HTTP = "tag_http";
    private Map<String, Map<String, File>> files;
    private Map<String, String> params;
    private CustomProgressDialog mCustomProgressDialog;
    private Activity activity;
    private MyAsyncBack mAsyncBack;

    public HttpOneFileAsycTask(Map<String, Map<String, File>> files,
                               Activity activity, Map<String, String> params,
                               MyAsyncBack mAsyncBack) {
        this.files = files;
        this.params = params;
        this.activity = activity;
        this.mAsyncBack = mAsyncBack;
    }

    public interface MyAsyncBack {
        public void mAsyncBack();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgressDialog();
    }

    @Override
    protected String doInBackground(String... url) {
        //如果是debug模式 并且是线下模式时   <#0001
        if (null != InitApplication.host && InitApplication.getIsDebug() && InitApplication.isOnline == false){
            String host = InitApplication.host;
            for (int i = 0; i < url.length; i++) {
                String oldhost = url[0].substring(0,url[0].indexOf(".com")+5);
                url[0] = url[0].replace(oldhost,host);
            }
        }
        String result = new HttpFileUpTool().post(url[0], params, files);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        dismissProgressDialog();
        if (result==null){
            ToastUtil.show(activity, "数据解析异常7");
            L.i("HttpOneFileAsycTask","服务器返回字符串:result = "+result);
            return;
        }
        mAsyncBack.mAsyncBack();
        try {
            result = result.substring(result.indexOf("{"));
        } catch (Exception e) {
            ToastUtil.show(activity, "数据解析异常8");
        }
        try {
            JSONObject json = new JSONObject(result);
            String status = json.getString("status");
            // 400代表错误信息
            if (status.equals("400")) {
                String msg = json.getString("message");
                Gson mGson = new Gson();
                ResponseErrorData data = mGson.fromJson(json.toString(), ResponseErrorData.class);
                L.e(TAG_HTTP, "msg：" + msg + ",FailMessages" + data.message.toString());
                ToastUtil.show(activity, data.message.getMsg());
            }
            // 200代表访问成功
            else if ("200".equals(status)) {
                L.e(TAG_HTTP, "SuccessMsg：" + json.getString("msg"));
                ToastUtil.show(activity, json.getString("msg"));
            } else {
                ToastUtil.show(activity, "数据解析异常9");
            }
        } catch (JSONException e1) {
            L.e(TAG_HTTP, e1.toString());
            ToastUtil.show(activity, "数据解析异常10");
        }
    }

    /********************************* dialog的显示与取消 ********************************************/

    /**
     * 显示一个ProgressDialog
     * <p/>
     * 要显示的对话框内容
     */
    private void showProgressDialog() {

        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(activity,
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
        if (mCustomProgressDialog != null || mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.dismiss();
        }
    }

}
