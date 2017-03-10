package com.example.yangbang.miowner.fragment;

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
import com.example.yangbang.miowner.activity.PublishSuccessActivity;
import com.example.yangbang.miowner.adapter.MyGridViewAdapter;
import com.example.yangbang.miowner.adapter.QuestionObjectAdapter;
import com.example.yangbang.miowner.entity.PatrolRecordImages;
import com.example.yangbang.miowner.entity.QuestionObject;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.HintDialog;
import com.example.yangbang.miowner.view.MyGridView;
import com.example.yangbang.miowner.view.PopwChange;
import com.hbw.library.BaseFragment;
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

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 添加问题fragment
 *
 * @FileName: com.example.yangbang.miowner.fragment.FragmentAddQuestion.java
 * @author: Yangbang
 * @date: 2016-06-24 17:26
 */
public class FragmentAddQuestion extends BaseFragment implements View.OnClickListener, TextWatcher, QuestionObjectAdapter.OnTypeClicklistener {
    private MyGridView gvPopMaterialType;
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

    String[] questionNames = {"设计师", "选材员", "施工队长", "项目经理", "下单员", "材料商"};
    String[] com_types = {"4", "5", "2", "1", "3", "6"};
    String com_type = "4";//默认4为设计师
    List<QuestionObject> questionObjects = new ArrayList<>();
    QuestionObjectAdapter questionObjectAdapter;

    @Override
    protected void initWidget() {
        assignViews();
        initQuestionObject();
        initGridView();
    }

    private void assignViews() {
        gvPopMaterialType = (MyGridView) getView().findViewById(R.id.gv_pop_material_type);
        etFragmentAddQuestionInput = (EditText) getView().findViewById(R.id.et_fragment_add_question_input);
        tvFragmentAddQuestionTextLimit = (TextView) getView().findViewById(R.id.tv_fragment_add_question_text_limit);
        patrolRecordAddONE = (MyGridView) getView().findViewById(R.id.patrol_record_add_ONE);
        tvFragmentAddQuestionPhotoLimit = (TextView) getView().findViewById(R.id.tv_fragment_add_question_photo_limit);
        etFragmentAddQuestionInput.addTextChangedListener(this);
    }

    public void submitQuestion() {
        //上传图片
        Map<String, Map<String, File>> map = new HashMap<String, Map<String, File>>();
        map.put("files[]", filesOne);
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("com_type", com_type);
        params.put("content", etFragmentAddQuestionInput.getText().toString());
        HttpFileAsycTask asycTask = new HttpFileAsycTask(map, getActivity(), params, handler, REQUEST_SUCCESS);
        asycTask.setIsCloseActivity(false);
        asycTask.execute(HttpConstant.QuestionAdd);
        L.i("filesOne.Size-->" + filesOne.size());
    }

    private void initQuestionObject() {
        for (int i = 0; i < questionNames.length; i++) {
            QuestionObject object = new QuestionObject();
            object.setName(questionNames[i]);
            object.setId(com_types[i]);
            questionObjects.add(object);
        }
        questionObjectAdapter = new QuestionObjectAdapter(getActivity(), questionObjects);
        questionObjectAdapter.setOnTypeClicklistener(this);
        gvPopMaterialType.setAdapter(questionObjectAdapter);
    }

    private void initGridView() {
        filesOne = new HashMap<String, File>();
        patrolRecordDetailsAdapterOne = new MyGridViewAdapter(getActivity(), imgs, 10);
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
                        mHintDialog = new HintDialog(getActivity());
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
            mHintDialog = new HintDialog(getActivity());
        }
        mHintDialog.setTitleText("确认发布吗").setConfirmListener(this).setCancelListener(this);
        if (mMustDialog == null) {
            mMustDialog = new HintDialog(getActivity());
        }
        mMustDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMustDialog.dismiss();
            }
        }).setCancelHint();
    }


    /**
     * 显示图片
     */
    public void myFragmentOnResume() {
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

    public void myFragmentOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTORETURNVALUE && resultCode == getActivity().RESULT_OK) {
            Uri pathImage = data.getData();
            if (!TextUtils.isEmpty(pathImage.getAuthority())) {
                Cursor cursor = getActivity().getContentResolver().query(pathImage,
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
                            ToastUtil.show(getActivity(), "所选图片不要超过3张");
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
            popwChange = new PopwChange(getActivity(), clickListener);
        }
        popwChange.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_transparent));
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popwChange.setOutsideTouchable(true);
        popwChange.showAtLocation(getView().findViewById(R.id.patrol_record_add_ONE),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popwChange.setOnDismissListener(dismissListener);
    }

    // 对popwChange的控件进行监听
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView_pop_chang_camera:
	                //请求权限  #0001
	                PermissionGen.with(FragmentAddQuestion.this)
			                .addRequestCode(100)
			                .permissions(
					                Manifest.permission.CAMERA,
					                Manifest.permission.WRITE_EXTERNAL_STORAGE,
					                Manifest.permission.READ_EXTERNAL_STORAGE)
			                .request();
                    break;
                case R.id.textView_pop_chang_photo:
	                //请求权限  #0001
	                PermissionGen.with(FragmentAddQuestion.this)
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
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.alpha = 1.0f;
            getActivity().getWindow().setAttributes(lp);
        }
    };
	//请求读写SD卡权限成功时调用 #0001
	@PermissionSuccess(requestCode = 101)
    private void invokPhoto() {
        if (imgs.size() >= 4) {
            ToastUtil.show(getActivity(), "只能上传3张");
        } else {
//            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, PHOTORETURNVALUE);

            //相册多选
            MyAdapter.mSelectedImage.clear();
            Intent intent = new Intent(getActivity(), PhotoMultiSelectActivity.class);
            intent.putExtra(PhotoMultiSelectActivity.PHOTO_SELECT_NUM, 3);
            startActivityForResult(intent, 1);
        }
    }
	//请求读写SD卡权限失败时调用 #0001
	@PermissionFail(requestCode = 101)
	private void  onRequestWRSDFail(){
		ToastUtil.show(getActivity(),"请允许读取内存卡权限");
	}

	//请求拍照权限成功时调用 #0001
	@PermissionSuccess(requestCode = 100)
    private void cameraPhoto() {
        if (imgs.size() >= 4) {
            ToastUtil.show(getActivity(), "只能上传3张");
        } else {
            myThread = new MyThread();
            myThread.setDaemon(true);
            myThread.start();
        }
    }
	//请求拍照权限失败时调用 #0001
	@PermissionFail(requestCode = 100)
	private void  onRequestCameraFail(){
		ToastUtil.show(getActivity(),"请允许拍照权限");
	}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                MyApplication.isPublishSuccess = true;
                Intent intent = new Intent(getActivity(), PublishSuccessActivity.class);
                intent.putExtra("flag", PublishSuccessActivity.PUBLISH_QUESTION_SUCCESS);
                startActivity(intent);
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

    @Override
    public void onClilck(QuestionObject assistDetails) {
        com_type = assistDetails.getId();
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
                ToastUtil.show(getActivity(), "手机相机发生故障");
            }
        }
    }


    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_add_question;
    }
}
