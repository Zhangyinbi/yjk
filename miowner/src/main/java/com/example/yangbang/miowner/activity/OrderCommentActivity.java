package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.MyGridViewAdapter;
import com.example.yangbang.miowner.entity.PatrolRecordImages;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.HintDialog;
import com.example.yangbang.miowner.view.MyGridView;
import com.example.yangbang.miowner.view.PopwChange;
import com.hbw.library.BaseActivity;
import com.hbw.library.net.HttpFileAsycTask;
import com.hbw.library.utils.BitmapTool;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.zhy.imageloader.MyAdapter;
import com.zhy.imageloader.PhotoMultiSelectActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单评价activity
 *
 * @FileName: com.example.yangbang.miowner.activity.OrderCommentActivity.java
 * @author: Yangbang
 * @date: 2016-07-01 10:11
 */
public class OrderCommentActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private TextView tvFragmentAddCommentObj;
    private TextView tvFragmentAddCommentGood;
    private TextView tvFragmentAddCommentNormal;
    private TextView tvFragmentAddCommentBad;
    private EditText etFragmentAddQuestionInput;
    private TextView tvFragmentAddQuestionTextLimit;
    private MyGridView patrolRecordAddONE;
    private TextView tvFragmentAddQuestionPhotoLimit;

    private MyGridViewAdapter patrolRecordDetailsAdapterOne = null;
    private List<PatrolRecordImages> imgs = new ArrayList<PatrolRecordImages>();
    private PopwChange popwChange;
    private Map<String, File> filesOne = null;
    private MyThread myThread;
    private String picFileFullName;
    private List<String> mImgs;
    public static final int PHOTORETURNVALUE = 1;
    private HintDialog mHintDialog = null, mMustDialog = null;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private int imgsPosition;//记录点击的容器中图片的位置
    public String image_id = null;//服务器中要删除图片时要传id

    String score = "1";//1=>好评,2=>中评,3=>差评
    String material_id = "";
    String material_name = "";

    @Override
    protected void initWidget() {
        material_id = getIntent().getStringExtra("material_id");
        material_name = getIntent().getStringExtra("material_name");
        titleBar.setTitleText("订单评价");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("发布");
        titleBar.setRightText0Listener(this);
        assignViews();
        initGridView();
    }

    private void assignViews() {
        tvFragmentAddCommentObj = (TextView) findViewById(R.id.tv_fragment_add_comment_obj);
        tvFragmentAddCommentGood = (TextView) findViewById(R.id.tv_fragment_add_comment_good);
        tvFragmentAddCommentNormal = (TextView) findViewById(R.id.tv_fragment_add_comment_normal);
        tvFragmentAddCommentBad = (TextView) findViewById(R.id.tv_fragment_add_comment_bad);
        etFragmentAddQuestionInput = (EditText) findViewById(R.id.et_fragment_add_question_input);
        tvFragmentAddQuestionTextLimit = (TextView) findViewById(R.id.tv_fragment_add_question_text_limit);
        patrolRecordAddONE = (MyGridView) findViewById(R.id.patrol_record_add_ONE);
        tvFragmentAddQuestionPhotoLimit = (TextView) findViewById(R.id.tv_fragment_add_question_photo_limit);
        tvFragmentAddCommentObj.setText(material_name);
        etFragmentAddQuestionInput.addTextChangedListener(this);
        tvFragmentAddCommentGood.setOnClickListener(this);
        tvFragmentAddCommentNormal.setOnClickListener(this);
        tvFragmentAddCommentBad.setOnClickListener(this);
    }

    private void initGridView() {
        filesOne = new HashMap<String, File>();
        patrolRecordDetailsAdapterOne = new MyGridViewAdapter(this, imgs, 10);
        PatrolRecordImages image = new PatrolRecordImages();
        image.setId("0");
        image.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addphoto));
        imgs.add(image);

        patrolRecordAddONE.setAdapter(patrolRecordDetailsAdapterOne);
        patrolRecordAddONE.setSelector(new ColorDrawable(getResources().getColor(R.color.grey)));
        patrolRecordAddONE.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if (position == imgs.size() - 1) {
                    showPop();
                } else {
                    if (mHintDialog == null) {
                        mHintDialog = new HintDialog(OrderCommentActivity.this);
                    }
                    mHintDialog.setTitleText("确定删除该张图片？");
                    mHintDialog.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHintDialog.dismiss();
                        }
                    });
                    mHintDialog.setConfirmListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            image_id = imgs.get(position).getId();
                            imgsPosition = position;
                            filesOne.remove(imgs.get(imgsPosition).getPath());
                            imgs.remove(imgsPosition);
                            //删除
                            if (image_id.equals("0")) {
                                //直接刷新数据即可，不用提交数据库操作
                                patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                            }
                            mHintDialog.dismiss();
                            tvFragmentAddQuestionPhotoLimit.setText("发照片(" + (imgs.size() - 1) + "/3)");
                        }
                    });
                    mHintDialog.show();
                }
            }

        });

        //初始化提示框
        if (mHintDialog == null) {
            mHintDialog = new HintDialog(this);
        }
        mHintDialog.setTitleText("确认发布吗").setConfirmListener(this).setCancelListener(this);
        if (mMustDialog == null) {
            mMustDialog = new HintDialog(this);
        }
        mMustDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMustDialog.dismiss();
            }
        }).setCancelHint();
    }

    public void submitComment() {
        //上传图片
        Map<String, Map<String, File>> map = new HashMap<>();
        map.put("files[]", filesOne);
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("score", score);
        params.put("material_id", material_id);
        params.put("content", etFragmentAddQuestionInput.getText().toString());
        HttpFileAsycTask asycTask = new HttpFileAsycTask(map, this, params, handler, REQUEST_SUCCESS);
        asycTask.setIsCloseActivity(false);
        asycTask.execute(HttpConstant.OrderComment);
        L.i("filesOne.Size-->" + filesOne.size());
    }

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
        tvFragmentAddQuestionPhotoLimit.setText("发照片(" + (imgs.size() - 1) + "/3)");
    }

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
                        if (imgs.size() <= 3) {
                            File file = new File(mImgs.get(i));
                            filesOne.put(mImgs.get(i), file);
                            PatrolRecordImages images = new PatrolRecordImages();
                            images.setId("0");
                            images.setBitmap(addbmp);
                            images.setPath(mImgs.get(i));
                            imgs.add(0, images);
                        } else {
                            ToastUtil.show(this, "所选图片不要超过3张");
                            break;
                        }
                    }
                }
                mImgs = null;
                patrolRecordDetailsAdapterOne.notifyDataSetChanged();
            }
        }
        tvFragmentAddQuestionPhotoLimit.setText("发照片(" + (imgs.size() - 1) + "/3)");
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
                case R.id.textView_pop_chang_camera:
                    cameraPhoto();
                    break;
                case R.id.textView_pop_chang_photo:
                    invokPhoto();
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

    private void invokPhoto() {
        if (imgs.size() >= 4) {
            ToastUtil.show(this, "只能上传3张");
        } else {
//            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, PHOTORETURNVALUE);

            //图片多选
            MyAdapter.mSelectedImage.clear();
            Intent intent = new Intent(this, PhotoMultiSelectActivity.class);
            intent.putExtra(PhotoMultiSelectActivity.PHOTO_SELECT_NUM, 3);
            startActivityForResult(intent, 1);
        }
    }

    private void cameraPhoto() {
        if (imgs.size() >= 4) {
            ToastUtil.show(this, "只能上传3张");
        } else {
            myThread = new MyThread();
            myThread.setDaemon(true);
            myThread.start();
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ToastUtil.show(this, "评价成功!");
                finishActivity();
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        tvFragmentAddQuestionTextLimit.setText("你还可以输入" + (200 - etFragmentAddQuestionInput.length()) + "字");
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
                ToastUtil.show(OrderCommentActivity.this, "手机相机发生故障");
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_order_comment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0://发布评价
                submitComment();
                break;
            case R.id.tv_fragment_add_comment_good:
                tvFragmentAddCommentGood.setEnabled(false);
                tvFragmentAddCommentNormal.setEnabled(true);
                tvFragmentAddCommentBad.setEnabled(true);
                score = "1";
                break;
            case R.id.tv_fragment_add_comment_normal:
                tvFragmentAddCommentGood.setEnabled(true);
                tvFragmentAddCommentNormal.setEnabled(false);
                tvFragmentAddCommentBad.setEnabled(true);
                score = "2";
                break;
            case R.id.tv_fragment_add_comment_bad:
                tvFragmentAddCommentGood.setEnabled(true);
                tvFragmentAddCommentNormal.setEnabled(true);
                tvFragmentAddCommentBad.setEnabled(false);
                score = "3";
                break;
        }
    }
}
