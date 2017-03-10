package com.xiaomizhuang.buildcaptain.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.hbw.library.BaseActivity;
import com.hbw.library.net.HttpFileAsycTask;
import com.hbw.library.utils.BitmapTool;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.PatrolRecordUpdateAdapter;
import com.xiaomizhuang.buildcaptain.entity.PatrolRecordImages;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;
import com.xiaomizhuang.buildcaptain.view.MyGridView;
import com.xiaomizhuang.buildcaptain.view.PopwChange;
import com.zhy.imageloader.MyAdapter;
import com.zhy.imageloader.PhotoMultiSelectActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 添加施工记录
 * Created by user on 2016/1/26.
 * #0001    mwy     2016/8/4    增加对6.0权限的判断
 */
public class AssistAsBuiltRecordsAddActivity extends BaseActivity implements View.OnClickListener {
    private MyGridView gridViewOne;
    private PatrolRecordUpdateAdapter patrolRecordDetailsAdapterOne = null;
    private List<PatrolRecordImages> imgs = new ArrayList<PatrolRecordImages>() {
    };
    private PopwChange popwChange;
    private Map<String, File> filesOne = null;
    private MyThread myThread;
    private String picFileFullName;
    private List<String> mImgs;
    public static final int PHOTORETURNVALUE = 1;
    private Button order_submit;
    private HintDialog mHintDialog = null, mMustDialog = null;
    private EditText selector_edit_text;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private int imgsPosition;//记录点击的容器中图片的位置
    public String image_id = null;//服务器中要删除图片时要传id

    @Override
    protected void initWidget() {
        titleBar.setTitleText("添加施工记录");
        titleBar.setLeftImgDefaultBack(this);

        filesOne = new HashMap<String, File>();
        gridViewOne = (MyGridView) findViewById(R.id.patrol_record_add_ONE);
        order_submit = (Button) findViewById(R.id.order_submit);
        selector_edit_text = (EditText) findViewById(R.id.patrol_record_add_content);

        order_submit.setOnClickListener(this);

        patrolRecordDetailsAdapterOne = new PatrolRecordUpdateAdapter(this, imgs, 10);
        PatrolRecordImages image = new PatrolRecordImages();
        image.setId("0");
        image.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addphoto));
        imgs.add(image);

        gridViewOne.setAdapter(patrolRecordDetailsAdapterOne);
        gridViewOne.setSelector(new ColorDrawable(getResources().getColor(R.color.grey)));
        gridViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
	            gridViewOne.requestFocus();
                if (position == imgs.size() - 1) {
                    showPop();
                } else {
                    image_id = imgs.get(position).getId();
                    imgsPosition = position;
                    filesOne.remove(imgs.get(imgsPosition).getPath());
                    imgs.remove(imgsPosition);
                    //删除
                    if (image_id.equals("0")) {
                        //直接刷新数据即可，不用提交数据库操作
                        patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                    }
                }
            }

        });

        //初始化提示框
        if (mHintDialog == null) {
            mHintDialog = new HintDialog(this);
        }
        mHintDialog.setTitleText("确认添加吗").setConfirmListener(this).setCancelListener(this);
        if (mMustDialog == null) {
            mMustDialog = new HintDialog(this);
        }
        mMustDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMustDialog.dismiss();
            }
        }).setCancelHint();

        selector_edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
	                Tools.forceHideSoftInput(v,getApplicationContext());
                }
            }
        });
    }

    /**
     * 显示图片
     */
    @Override
    protected void onResume() {
        super.onResume();
        Bitmap addbmp = null;
        if (!TextUtils.isEmpty(picFileFullName)) {
            try {
                addbmp = BitmapTool.compressBySize(picFileFullName, 720, 1200);
                int k = BitmapTool.readPictureDegree(picFileFullName);
                addbmp = BitmapTool.rotateBitmap(addbmp, k);
                BitmapTool.saveFile(addbmp, picFileFullName);
            } catch (Exception e) {
            }
            if (addbmp != null) {
                File file = new File(picFileFullName);
                filesOne.put(picFileFullName, file);
                PatrolRecordImages images = new PatrolRecordImages();
                images.setId("0");
                images.setBitmap(addbmp);
                images.setPath(picFileFullName);
                imgs.add(0, images);
                patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                picFileFullName = null;
            }
        }
    }

    // 相册返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTORETURNVALUE && resultCode == RESULT_OK) {
            Uri pathImage = data.getData();
            if (!TextUtils.isEmpty(pathImage.getAuthority())) {
                Cursor cursor = getContentResolver().query(pathImage,
                        new String[]{MediaStore.Images.Media.DATA}, null,
                        null, null);
                if (null == cursor) {
                    return;
                }
                cursor.moveToFirst();
                picFileFullName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        }
        if (resultCode == PhotoMultiSelectActivity.PHOTO_MULTI_SELECT) {
            mImgs = MyAdapter.mSelectedImage;
            if (mImgs != null) {
                for (int i = 0; i < mImgs.size(); i++) {
                    Bitmap addbmp = null;
                    try {
                        addbmp = BitmapTool.compressBySize(mImgs.get(i), 720, 1200);
                        int k = BitmapTool.readPictureDegree(mImgs.get(i));
                        addbmp = BitmapTool.rotateBitmap(addbmp, k);
                        BitmapTool.saveFile(addbmp, mImgs.get(i));
                    } catch (Exception e) {
                    }
                    if (addbmp != null) {
                        if (imgs.size() <= 9) {
                            File file = new File(mImgs.get(i));
                            filesOne.put(mImgs.get(i), file);
                            PatrolRecordImages images = new PatrolRecordImages();
                            images.setId("0");
                            images.setBitmap(addbmp);
                            images.setPath(mImgs.get(i));
                            imgs.add(0, images);
                        } else {
                            ToastUtil.show(this, "所选图片不要超过9张");
                            break;
                        }
                    }
                }
                mImgs = null;
                patrolRecordDetailsAdapterOne.notifyDataSetChanged();
            }
        }
    }

    private void showPop() {
        if (popwChange == null) {
            popwChange = new PopwChange(this, clickListener);
        }
        popwChange.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_transparent));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popwChange.setOutsideTouchable(true);
        popwChange.showAtLocation(findViewById(R.id.patrol_record_add_ONE),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popwChange.setOnDismissListener(dismissListener);
    }

    // 对popwChange的控件进行监听
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView_pop_chang_camera://拍照    #0001
	                //请求权限
                    PermissionGen.with(AssistAsBuiltRecordsAddActivity.this)
                            .addRequestCode(100)
                            .permissions(
                                    Manifest.permission.CAMERA,
		                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
		                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            .request();
                    break;
                case R.id.textView_pop_chang_photo://本地相册   #0001
                    PermissionGen.with(AssistAsBuiltRecordsAddActivity.this)
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

    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 1.0f;
            getWindow().setAttributes(lp);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //请求读写内存卡权限 时调用 #0001
    @PermissionSuccess(requestCode = 101)
    private void invokPhoto() {
        if (imgs.size() >= 10) {
            ToastUtil.show(this, "只能上传九张");
        } else {
//            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, PHOTORETURNVALUE);
            MyAdapter.mSelectedImage.clear();
            startActivityForResult(new Intent(this, PhotoMultiSelectActivity.class), 1);
        }
    }

    //请求读写内存卡权限失败 时调用   #0001
    @PermissionFail(requestCode = 101)
    private void onRequestWRSDCardFail(){
        ToastUtil.show(AssistAsBuiltRecordsAddActivity.this,"请允许读取本地相册");
    }

    //请求拍照权限成功 时调用  #0001
    @PermissionSuccess(requestCode = 100)
    private void cameraPhoto() {
        if (imgs.size() >= 10) {
            ToastUtil.show(this, "只能上传九张");
        } else {
            myThread = new MyThread();
            myThread.setDaemon(true);
            myThread.start();
        }
    }
    //请求拍照权限失败 时调用  #0001
    @PermissionFail(requestCode = 100)
    private void onRequestCareraFail(){
        ToastUtil.show(AssistAsBuiltRecordsAddActivity.this,"拍照需要允许权限");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_submit:
                mHintDialog.show();
                break;
            case R.id.hintdialog_cancel://关闭表单提醒框
                mHintDialog.dismiss();
                break;
            case R.id.hintdialog_confirm:
                mHintDialog.dismiss();
                if ("".equals(selector_edit_text.getText().toString())) {
                    mMustDialog.setTitleText("请填写描述内容");
                    mMustDialog.show();
                    break;
                }
                if (filesOne.size() == 0) {
                    mMustDialog.setTitleText("请选择要上传的图片");
                    mMustDialog.show();
                    break;
                }
                //上传图片
                Map<String, Map<String, File>> map = new HashMap<String, Map<String, File>>();
                map.put("files[]", filesOne);
                params.put("token", MyApplication.TOKEN);
                params.put("baoming_id", getIntent().getStringExtra("baoming_id"));
                params.put("content", selector_edit_text.getText().toString());
                HttpFileAsycTask asycTask = new HttpFileAsycTask(map, AssistAsBuiltRecordsAddActivity.this, params, handler, REQUEST_SUCCESS);
                asycTask.setIsCloseActivity(false);
                asycTask.execute(HttpConstant.AddBuildRecordUrl);
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ToastUtil.show(this, "上传成功");
//                Intent it = new Intent(AssistAsBuiltRecordsAddActivity.this,AssistAsBuiltRecordsActivity.class);
//                it.putExtra("bm_id",getIntent().getStringExtra("baoming_id"));
//                startActivity(it);
                finishActivity();
                break;
        }
        return super.handleMessage(msg);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File outDir = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
                picFileFullName = outFile.getAbsolutePath();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, PHOTORETURNVALUE);
                startActivity(intent);
            } else {
                ToastUtil.show(getApplicationContext(), "手机相机发生故障");
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_as_built_records_add;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popwChange != null && popwChange.isShowing()) {
            popwChange.dismiss();
        }
        if (mHintDialog != null) {
            mHintDialog.dismiss();
        }
        if (mMustDialog != null) {
            mMustDialog.dismiss();
        }
    }

}
