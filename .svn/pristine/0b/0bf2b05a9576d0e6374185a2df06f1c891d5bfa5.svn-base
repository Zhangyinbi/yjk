package com.youjuke.miprojectmanager.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.net.HttpFileAsycTask;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.AccountInfo;
import com.youjuke.miprojectmanager.util.AvatarSelectTool;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.util.MyApplication;
import com.youjuke.miprojectmanager.util.MyImageCache;
import com.youjuke.miprojectmanager.view.PopwChange;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 更改个人信息
 *
 * @author Youjk
 * #0001    mwy     添加6.0权限认证
 */
public class ChangSelfDataActivity extends MiBaseActivity {
    private EditText editText_name;
    private PopwChange popwChange;
    private ImageView imageView_head;
    private ImageView imageView_head_down;
    private Map<String, File> files = null;
    private Map<String, String> params;
    private String name;
    private String imageUrl;
    private ProgressDialog dialog;
    private AvatarSelectTool avatarSelect;

    OnClickListener titleListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_text0:
                    params = new HashMap<String, String>();
                    params.put("token", HttpConstant.token);
                    params.put("nikename", editText_name.getText().toString());
                    Map<String, Map<String, File>> map = new HashMap<String, Map<String, File>>();
                    map.put("files[]", files);
                    HttpFileAsycTask asycTask = new HttpFileAsycTask(map, ChangSelfDataActivity.this, params, handler, 0x4599);
                    asycTask.execute(HttpConstant.ChangeUserInfoUrl);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avatarSelect = new AvatarSelectTool(this);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("数据加载中");
        dialog.show(); // 展示dialog
        initView();
        load();

    }

    // 获取个人信息
    private void load() {
        analyzeJson.requestData(HttpConstant.GetUserInfo,
                HttpConstant.getMapToken(), REQUEST_SUCCESS);
    }

    protected void setInfo(AccountInfo info) {
        name = info.getRealname();
        editText_name.setText(name);
        imageUrl = info.getHeader_small();
        loadImage(imageUrl);
    }

    // 根据URL 获取用户的头像图片
    private void loadImage(String imageUrl) {
        ImageLoader imageLoader = new ImageLoader(MyApplication.mRequestQueue,
                MyImageCache.getInstance(getApplicationContext()));
        ImageListener mImageListener = ImageLoader.getImageListener(
                imageView_head_down, R.drawable.defaulthead,
                R.drawable.defaulthead);

        imageLoader.get(HttpConstant.IMAGEADDRESS + imageUrl, mImageListener,
                250, 250);
        // dialog.dismiss();
    }

    // 初始化数据
    private void initView() {
        editText_name = (EditText) findViewById(R.id.edittext_change_name);
        imageView_head = (ImageView) findViewById(R.id.imageView_change_head);
        imageView_head_down = (ImageView) findViewById(R.id.imageView_change_head_down);
        files = new HashMap<String, File>();
    }

    // 对popwChange的控件进行监听
    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView_pop_chang_camera://拍照
                    //请求权限  #0001
                    PermissionGen.with(ChangSelfDataActivity.this)
                            .addRequestCode(100)
                            .permissions(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE)
                            .request();
                    break;
                case R.id.textView_pop_chang_photo://从相册获取
                    // #0001
                    PermissionGen.with(ChangSelfDataActivity.this)
                            .addRequestCode(101)
                            .permissions(
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE)
                            .request();
                    break;
                case R.id.textView_pop_window_cancel:
                    break;
            }
            popwChange.dismiss();
        }
    };

    // 获取相册里返回的图片地址
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK
                && requestCode == AvatarSelectTool.CROP_COMPLETE_CODE) {// 相册返回的裁剪图片
            if (avatarSelect == null) {
                Toast.makeText(this, "图片剪辑发生错误，请重试。", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            Bitmap bmp = null;
            bmp = BitmapFactory.decodeFile(avatarSelect.getTempImgFile()
                    .getAbsolutePath());
            if (bmp != null) {
                // bmp = BitmapTool.getRoundBitmap(bmp);
                // avatarSelect.setAvatar(bmp);
                imageView_head_down.setImageBitmap(bmp);
                files.put(avatarSelect.getTempImgFile().getAbsolutePath(),
                        avatarSelect.getTempImgFile());
                // saveToService();
            } else {
                avatarSelect.setAvatar(null);
            }
        }
        if (resultCode == RESULT_CANCELED) {
            avatarSelect.getTempImgFile().delete();
            avatarSelect.setAvatar(null);
        }
        /** 从相册选择 **/
        if (requestCode == AvatarSelectTool.AVTATR_ALBUM_CROP_CODE) {
            if (null != data) {
                avatarSelect.startPhotoZoom(data.getData());
            }else{
                avatarSelect.setAvatar(null);
            }
        }
        /** 拍摄 **/
        if (requestCode == AvatarSelectTool.AVATAR_CAMERA_CROP_CODE) {
            Log.i("YANGBANG", "AvatarSelectTool.AVATAR_CAMERA_CROP_CODE");
            avatarSelect.startPhotoZoom(Uri.fromFile(avatarSelect
                    .getTempImgFile()));
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showPop() {
        if (popwChange == null) {
            popwChange = new PopwChange(this, clickListener);
        }
        popwChange.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.shape_transparent));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popwChange.setOutsideTouchable(true);
        popwChange.showAtLocation(findViewById(R.id.layout_pop_change),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popwChange.setOnDismissListener(dismissListener);
    }

    OnDismissListener dismissListener = new OnDismissListener() {
        @Override
        public void onDismiss() {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 1.0f;
            getWindow().setAttributes(lp);
        }
    };

    public void clickButton(View v) {
        switch (v.getId()) {
            // 点击头像展示popwChange
            case R.id.imageView_change_head:
                showPop();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popwChange != null && popwChange.isShowing()) {
            popwChange.dismiss();
        }
    }

    @Override
    protected void initWidget() {
        titleBar.setTitleText("修改个人资料");
        titleBar.setLeftImgDefaultBack(this, R.drawable.back);
        titleBar.setRightText0("保存");
        titleBar.setRightText0Listener(titleListener);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.chang_selfdata_activity;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                AccountInfo info = gson.fromJson(data.data, AccountInfo.class);
                dialog.dismiss();
                setInfo(info);
                break;
            case 0x4599:
                ToastUtil.show(this, "上传成功");
                break;
            default:
                break;
        }

        return super.handleMessage(msg);
    }

    //请求拍照权限成功时调用 #0001
    @PermissionSuccess(requestCode = 100)
    private void  onRequestCameraSuccess(){
        avatarSelect.getCamera();
    }

    //请求拍照权限失败时调用 #0001
    @PermissionFail(requestCode = 100)
    private void  onRequestCameraFail(){
        ToastUtil.show(ChangSelfDataActivity.this,"请允许拍照权限");
    }

    //请求读写SD卡权限成功时调用 #0001
    @PermissionSuccess(requestCode = 101)
    private void  onRequestWRSDSuccess(){
        avatarSelect.getPhotoAlbum();
    }

    //请求读写SD卡权限失败时调用 #0001
    @PermissionFail(requestCode = 101)
    private void  onRequestWRSDFail(){
        ToastUtil.show(ChangSelfDataActivity.this,"请允许读取内存卡权限");
    }

}
