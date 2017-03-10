package com.youjuke.miprojectmanager.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Spinner;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.net.HttpFileAsycTask;
import com.hbw.library.utils.BitmapTool;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.PatrolRecordUpdateAdapter;
import com.youjuke.miprojectmanager.adapter.SpinnerProgjectCostAdapter;
import com.youjuke.miprojectmanager.adapter.SpinnerProgjectFineCostAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.PatrolRecordCause;
import com.youjuke.miprojectmanager.entity.PatrolRecordImages;
import com.youjuke.miprojectmanager.entity.SpinnerProgjectCost;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.MyGridView;
import com.youjuke.miprojectmanager.view.PopwChange;
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
 * 添加工地巡查记录
 *
 * @author hebiwen
 * #0001    mwy     添加6.0权限认证
 */
public class PatrolRecordAddActivity extends MiBaseActivity implements
        OnClickListener, ViewBinder {

    public static final String TAG = "PatrolRecordAddActivity-->";
    public static final int PHOTORETURNVALUE = 1;
    public static final int ONE = 1;
    public static final int TWO = 2;
    private int type;
    private PopwChange popwChange;
    private MyThread myThread;
    private String picFileFullName;
    private Map<String, File> filesTwo = null, filesOne = null;
    private MyGridView gridViewTwo, gridViewOne;
    private HashMap<String, String> params;
    private EditText patrol_record_add_content;
    private AlertDialog alert;
    private EditText patrol_record_add_material_info;
    private List<String> mImgs;//多选图片地址集合

    //2015.11.11 增加删除
    private PatrolRecordUpdateAdapter patrolRecordDetailsAdapterOne = null;
    private PatrolRecordUpdateAdapter patrolRecordDetailsAdapterTwo = null;
    private List<PatrolRecordImages> userimgs = new ArrayList<PatrolRecordImages>() {
    };
    private List<PatrolRecordImages> imgs = new ArrayList<PatrolRecordImages>() {
    };
    private int userimgsPosition;//记录点击的容器中图片的位置
    private int imgsPosition;//记录点击的容器中图片的位置
    public String image_id = null;//服务器中要删除图片时要传id
    private AlertDialog mAlert;

    //2015.12.8 增加罚款金额
    private RadioButton fine;
    private RadioButton nofine;
    private RadioGroup myfine;
    private LinearLayout isShowfinemoney;
    private EditText finemoney;
    private String amerce = "0";//0不罚，1处罚

    //2015.12.10 增加施工阶段
    private LinearLayout my_spinner_project_cost;
    private Spinner spinner_project_cost;
    private ArrayList<SpinnerProgjectCost> mSpinnerProgjectCosts = new ArrayList<SpinnerProgjectCost>();
    private SpinnerProgjectCostAdapter mSpinnerProgjectCostAdapter;
    private SpinnerProgjectCost mSpinnerProgjectCost;

    //2015.12.28 增加罚款原因
    private LinearLayout patrol_record_add_fine_cause_LinearLayout;
    private Spinner patrol_record_add_fine_cause;
    private ArrayList<PatrolRecordCause> mPatrolRecordCauses = new ArrayList<PatrolRecordCause>();
    private SpinnerProgjectFineCostAdapter mSpinnerProgjectFineCostAdapter;
    private PatrolRecordCause mPatrolRecordCause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialg();
    }

    private void initDialg() {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确认取消操作?");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishActivity();
            }
        });
        alert = builder.create();
    }

    @Override
    protected void initWidget() {
        gridViewTwo = (MyGridView) findViewById(R.id.patrol_record_add_TWO);
        gridViewOne = (MyGridView) findViewById(R.id.patrol_record_add_ONE);
        patrol_record_add_content = (EditText) findViewById(R.id.patrol_record_add_content);
        patrol_record_add_material_info = (EditText) findViewById(R.id.patrol_record_add_material_info);

        fine = (RadioButton) findViewById(R.id.fine);
        nofine = (RadioButton) findViewById(R.id.nofine);
        myfine = (RadioGroup) findViewById(R.id.myfine);
        isShowfinemoney = (LinearLayout) findViewById(R.id.isShowfinemoney);
        finemoney = (EditText) findViewById(R.id.finemoney);

        my_spinner_project_cost = (LinearLayout) findViewById(R.id.my_spinner_project_cost);
        spinner_project_cost = (Spinner) findViewById(R.id.spinner_project_cost);
        analyzeJson.requestData(HttpConstant.GetProgectPhase, null, REQUEST_SUCCESS);
        mSpinnerProgjectCostAdapter = new SpinnerProgjectCostAdapter(this, mSpinnerProgjectCosts);
        spinner_project_cost.setAdapter(mSpinnerProgjectCostAdapter);

        //2015.12.28 增加罚款原因
        patrol_record_add_fine_cause_LinearLayout = (LinearLayout) findViewById(R.id.patrol_record_add_fine_cause_LinearLayout);
        patrol_record_add_fine_cause = (Spinner) findViewById(R.id.patrol_record_add_fine_cause);
        analyzeJson.requestData(HttpConstant.GET_REASON_URL, null, 2468);
        mSpinnerProgjectFineCostAdapter = new SpinnerProgjectFineCostAdapter(this, mPatrolRecordCauses);
        patrol_record_add_fine_cause.setAdapter(mSpinnerProgjectFineCostAdapter);

        spinner_project_cost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerProgjectCost = mSpinnerProgjectCosts.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        patrol_record_add_fine_cause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPatrolRecordCause = mPatrolRecordCauses.get(position);
                if (Float.parseFloat(mPatrolRecordCause.getMoney()) != 0f) {
                    finemoney.setText(mPatrolRecordCauses.get(position).getMoney());
                    finemoney.setInputType(InputType.TYPE_NULL);
                } else {
                    finemoney.setText("");
                    finemoney.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myfine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == fine.getId()) {
                    amerce = "1";
                    isShowfinemoney.setVisibility(View.VISIBLE);
                    my_spinner_project_cost.setVisibility(View.VISIBLE);
                    patrol_record_add_fine_cause_LinearLayout.setVisibility(View.VISIBLE);
                } else if (checkedId == nofine.getId()) {
                    amerce = "0";
                    isShowfinemoney.setVisibility(View.GONE);
                    my_spinner_project_cost.setVisibility(View.GONE);
                    patrol_record_add_fine_cause_LinearLayout.setVisibility(View.GONE);
                }
            }
        });

        patrol_record_add_content.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText textView = (EditText) v;
                String hint;
                if (hasFocus) {
                    hint = textView.getHint().toString();
                    textView.setTag(hint);
                    textView.setHint("");
                } else {
                    hint = textView.getTag().toString();
                    textView.setHint(hint);
                }
            }
        });
        patrol_record_add_material_info.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText textView = (EditText) v;
                String hint;
                if (hasFocus) {
                    hint = textView.getHint().toString();
                    textView.setTag(hint);
                    textView.setHint("");
                } else {
                    hint = textView.getTag().toString();
                    textView.setHint(hint);
                }
            }
        });
        initDatas();
    }

    private void initDatas() {
        titleBar.setTitleText("新增");
        titleBar.setRightText0("发布");
        titleBar.setRightText0Listener(this);
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(this);
        initDialog();
        filesTwo = new HashMap<String, File>();
        filesOne = new HashMap<String, File>();

        //2015.11.11 增加删除
        patrolRecordDetailsAdapterTwo = new PatrolRecordUpdateAdapter(this, userimgs, 4);
        PatrolRecordImages images = new PatrolRecordImages();
        images.setId("0");
        images.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addphoto));
        userimgs.add(images);
        gridViewTwo.setAdapter(patrolRecordDetailsAdapterTwo);
        gridViewTwo.setSelector(new ColorDrawable(getResources().getColor(R.color.grey)));
        gridViewTwo.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == userimgs.size() - 1) {
                    showPop(TWO);
                } else {
                    type = TWO;
                    image_id = userimgs.get(position).getId();
                    userimgsPosition = position;
                    mAlert.show();
                }
            }
        });

        patrolRecordDetailsAdapterOne = new PatrolRecordUpdateAdapter(this, imgs, 10);
        PatrolRecordImages image = new PatrolRecordImages();
        image.setId("0");
        image.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addphoto));
        imgs.add(image);
        gridViewOne.setAdapter(patrolRecordDetailsAdapterOne);
        gridViewOne.setSelector(new ColorDrawable(getResources().getColor(R.color.grey)));
        gridViewOne.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == imgs.size() - 1) {
                    showPop(ONE);
                } else {
                    type = ONE;
                    image_id = imgs.get(position).getId();
                    imgsPosition = position;
                    mAlert.show();
                }
            }

        });

    }

    private void initDialog() {
        AlertDialog.Builder builder = new Builder(this);
        builder.setMessage("确认删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (type == ONE) {
                    filesOne.remove(imgs.get(imgsPosition).getPath());
                    imgs.remove(imgsPosition);
                } else if (type == TWO) {
                    filesTwo.remove(userimgs.get(userimgsPosition).getPath());
                    userimgs.remove(userimgsPosition);
                }
                //删除
                if (image_id.equals("0")) {
                    //直接刷新数据即可，不用提交数据库操作
                    patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                    patrolRecordDetailsAdapterTwo.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        mAlert = builder.create();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_patrol_record_add;
    }


    @Override
    public boolean setViewValue(View view, Object data,
                                String textRepresentation) {
        if (view instanceof ImageView && data instanceof Bitmap) {
            ImageView i = (ImageView) view;
            i.setImageBitmap((Bitmap) data);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0:// 发布
                if (patrol_record_add_content.getText().toString().equals("")) {
                    ToastUtil.show(this, "问题描述不能为空");
                    break;
                }
                if ("".equals(patrol_record_add_material_info.getText().toString().trim())) {
                    ToastUtil.show(this, "材料描述不能为空");
                    break;
                }
                if (amerce.equals("1") && mSpinnerProgjectCost == null) {
                    ToastUtil.show(this, "施工阶段不能为空");
                    break;
                }
                if (amerce.equals("1") && mPatrolRecordCause == null) {
                    ToastUtil.show(this, "罚款原因不能为空");
                    break;
                }
                if (amerce.equals("1") && "".equals(finemoney.getText().toString())) {
                    ToastUtil.show(this, "罚款金额不能为空");
                    break;
                }
                if (filesOne.size() == 0) {
                    ToastUtil.show(this, "现场照片不能为空");
                    break;
                }
                if (filesTwo.size() == 0) {
                    ToastUtil.show(this, "业主沟通照片不能为空");
                    break;
                }
                params = new HashMap<String, String>();
                params.put("token", HttpConstant.token);
                params.put("content", patrol_record_add_content.getText().toString());
                params.put("material_info", patrol_record_add_material_info.getText().toString());
                params.put("baoming_id", getIntent().getStringExtra("baoming_id"));
                params.put("amerce", amerce);
                if ("1".equals(amerce)) {
                    params.put("amerce_money", finemoney.getText().toString());
                    params.put("projectphase", mSpinnerProgjectCost.getStep());
                    params.put("reason", mPatrolRecordCause.getTitle());
                    params.put("amerce_id", mPatrolRecordCause.getId());
                } else {
                    params.put("amerce_money", "0");
                }
                Map<String, Map<String, File>> files = new HashMap<String, Map<String, File>>();
                files.put("files[]", filesOne);
                files.put("files2[]", filesTwo);
                HttpFileAsycTask asycTask = new HttpFileAsycTask(files,
                        PatrolRecordAddActivity.this, params, handler, 0x4599);
                asycTask.execute(HttpConstant.RecordDetailUrl);
                break;
            case R.id.left_igame_relative:
                alert.show();
                break;
            case R.id.textView_pop_chang_camera:
                //请求权限  #0001
                PermissionGen.with(PatrolRecordAddActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
                popwChange.dismiss();
                break;
            case R.id.textView_pop_chang_photo:
                //请求权限  #0001
                PermissionGen.with(PatrolRecordAddActivity.this)
                        .addRequestCode(101)
                        .permissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
                popwChange.dismiss();
                break;
            case R.id.textView_pop_window_cancel:
                popwChange.dismiss();
                break;
            default:
                break;
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
                picFileFullName = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
                L.i(TAG + "picFileFullName", picFileFullName);
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
                        if (type == ONE) {
                            if (imgs.size() <= 9) {
                                File file = new File(mImgs.get(i));
                                filesOne.put(mImgs.get(i), file);
                                PatrolRecordImages images = new PatrolRecordImages();
                                images.setId("0");
                                images.setBitmap(addbmp);
                                images.setPath(mImgs.get(i));
                                imgs.add(0, images);
                            } else {
                                ToastUtil.show(this, "现场图片不能超过9张");
                                break;
                            }
                            patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                        } else if (type == TWO) {
                            if (userimgs.size() <= 3) {
                                File file = new File(mImgs.get(i));
                                filesTwo.put(mImgs.get(i), file);
                                PatrolRecordImages images = new PatrolRecordImages();
                                images.setId("0");
                                images.setBitmap(addbmp);
                                images.setPath(mImgs.get(i));
                                userimgs.add(0, images);
                            } else {
                                ToastUtil.show(this, "业主沟通图片不能超过3张");
                                break;
                            }
                            patrolRecordDetailsAdapterTwo.notifyDataSetChanged();
                        }

                    }
                }
                mImgs = null;
            }
        }
    }

    private void showPop(int tp) {
        type = tp;
        if (popwChange == null) {
            popwChange = new PopwChange(this, this);
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popwChange.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.shape_transparent));
        popwChange.setAnimationStyle(R.style.AnimBottom);
        popwChange.setOutsideTouchable(true);
        popwChange.setFocusable(true);
        popwChange.showAtLocation(findViewById(R.id.patrol_record_add_ll),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popwChange.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popwChange != null && popwChange.isShowing()) {
            popwChange.dismiss();
        }
    }
    //请求拍照权限成功时调用 #0001
    @PermissionSuccess(requestCode = 100)
    private void cameraPhoto() {
        if (type == ONE) {
            if (imgs.size() >= 10) {
                ToastUtil.show(this, "只能上传九张");
            } else {
                myThread = new MyThread();
                myThread.setDaemon(true);
                myThread.start();
            }
        } else if (type == TWO) {
            if (userimgs.size() >= 4) {
                ToastUtil.show(this, "只能上传三张");
            } else {
                myThread = new MyThread();
                myThread.setDaemon(true);
                myThread.start();
            }
        }

    }

    //请求拍照权限失败时调用 #0001
    private void onRequestCameraFail(){
        ToastUtil.show(PatrolRecordAddActivity.this,"请允许拍照权限");
    }

    //请求读写内存卡权限成功时调用 #0001
    @PermissionSuccess(requestCode = 101)
    private void invokPhoto() {
        if (type == ONE) {
            if (imgs.size() >= 10) {
                ToastUtil.show(this, "只能上传九张");
            } else {
                MyAdapter.mSelectedImage.clear();
                startActivityForResult(new Intent(this, PhotoMultiSelectActivity.class), 1);
            }
        } else if (type == TWO) {
            if (userimgs.size() >= 4) {
                ToastUtil.show(this, "只能上传三张");
            } else {
                MyAdapter.mSelectedImage.clear();
                startActivityForResult(new Intent(this, PhotoMultiSelectActivity.class), 1);
            }
        }
    }

    //请求读写SD卡权限失败时调用 #0001
    @PermissionFail(requestCode = 101)
    private void  onRequestWRSDFail(){
        ToastUtil.show(PatrolRecordAddActivity.this,"请允许读取内存卡权限");
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
                File outFile = new File(outDir, System.currentTimeMillis()
                        + ".jpg");
                picFileFullName = outFile.getAbsolutePath();
                L.i(TAG + "picFileFullName", picFileFullName);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,
                        PHOTORETURNVALUE);
                startActivity(intent);
            } else {
                ToastUtil.show(getApplicationContext(), "手机相机发生故障");
            }
        }
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
                if (type == ONE) {
                    File file = new File(picFileFullName);
                    filesOne.put(picFileFullName, file);
                    PatrolRecordImages images = new PatrolRecordImages();
                    images.setId("0");
                    images.setBitmap(addbmp);
                    images.setPath(picFileFullName);
                    imgs.add(0, images);
                    patrolRecordDetailsAdapterOne.notifyDataSetChanged();
                } else if (type == TWO) {
                    File file = new File(picFileFullName);
                    filesTwo.put(picFileFullName, file);
                    PatrolRecordImages images = new PatrolRecordImages();
                    images.setId("0");
                    images.setBitmap(addbmp);
                    images.setPath(picFileFullName);
                    userimgs.add(0, images);
                    patrolRecordDetailsAdapterTwo.notifyDataSetChanged();
                }
                picFileFullName = null;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            alert.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mSpinnerProgjectCosts = gson.fromJson(data.data, new TypeToken<List<SpinnerProgjectCost>>() {
                }.getType());
                mSpinnerProgjectCost = mSpinnerProgjectCosts.get(0);
                mSpinnerProgjectCostAdapter.notifyDataSetChanged(mSpinnerProgjectCosts);
                break;
            case 2468:
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                mPatrolRecordCauses = gson.fromJson(data2.data, new TypeToken<List<PatrolRecordCause>>() {
                }.getType());
                mPatrolRecordCause = mPatrolRecordCauses.get(0);
                mSpinnerProgjectFineCostAdapter.notifyDataSetChanged(mPatrolRecordCauses);
                break;
            case 0x4599:
                ToastUtil.show(this, "上传成功");
                break;
        }
        return super.handleMessage(msg);
    }

}
