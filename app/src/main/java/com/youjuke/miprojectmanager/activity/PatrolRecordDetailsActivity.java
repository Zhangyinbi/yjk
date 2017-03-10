package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.PatrolRecordDetailsAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.PatrolRecordData;
import com.youjuke.miprojectmanager.entity.PatrolRecordImages;
import com.youjuke.miprojectmanager.util.HelpClass;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 工地巡查记录详情
 *
 * @author hebiwen
 */
public class PatrolRecordDetailsActivity extends MiBaseActivity implements
        OnClickListener {

    public static final String TAG = "PatrolRecordAddActivity-->";
    private MyGridView gridViewTwo, gridViewOne;
    private TextView patrol_record_add_content;
    private List<PatrolRecordData> patrolRecordDatas;
    private String pro_post_id;
    private ArrayList<String> listImgs;
    private ArrayList<String> listUserimgs;
    private TextView patrol_record_add_material_info;

    private LinearLayout isAmerce_money,isConfiguation_audit;
    private TextView amerce_money,configuation_audit;
    private ImageView myImageVidew;

    private LinearLayout isPhasename;
    private TextView phasename;

    private LinearLayout fine_cause_LinearLayout;
    private TextView fine_cause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        gridViewTwo = (MyGridView) findViewById(R.id.patrol_record_add_TWO);
        gridViewOne = (MyGridView) findViewById(R.id.patrol_record_add_ONE);
        patrol_record_add_content = (TextView) findViewById(R.id.patrol_record_add_content);
        patrol_record_add_material_info = (TextView) findViewById(R.id.patrol_record_add_material_info);
        isAmerce_money = (LinearLayout)findViewById(R.id.isAmerce_money);
        isConfiguation_audit = (LinearLayout)findViewById(R.id.isConfiguation_audit);
        amerce_money = (TextView) findViewById(R.id.amerce_money);
        configuation_audit = (TextView) findViewById(R.id.configuation_audit);
        myImageVidew = (ImageView)findViewById(R.id.myImageVidew);

        isPhasename = (LinearLayout)findViewById(R.id.isPhasename);
        phasename = (TextView) findViewById(R.id.phasename);

        fine_cause_LinearLayout = (LinearLayout)findViewById(R.id.fine_cause_LinearLayout);
        fine_cause = (TextView) findViewById(R.id.fine_cause);

        titleBar.setTitleText("详情");
        titleBar.setRightText0("编辑");
        titleBar.setRightText0Listener(this);
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(this);
        pro_post_id = getIntent().getStringExtra(HelpClass.PRO_POST_ID);
        mLoad();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_patrol_record_details;
    }

    private void mLoad() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(HelpClass.TOKEN, HttpConstant.token);
        map.put(HelpClass.ID, pro_post_id);
        analyzeJson.requestData(HttpConstant.EditRecordDetailUrl, map, REQUEST_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0:// 编辑
                if (patrolRecordDatas != null && patrolRecordDatas.size() > 0) {
                    Intent it = new Intent(this, PatrolRecordUpdateActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(HelpClass.PATROLRECORDDETAILS, patrolRecordDatas.get(0));
                    bundle.putString(HelpClass.PRO_POST_ID, pro_post_id);
                    it.putExtras(bundle);
                    if(!"1".equals(patrolRecordDatas.get(0).getVerify())){
                        startActivity(it);
                    }else{
                        ToastUtil.show(this, "审核已通过，无法编辑！");
                    }

                } else {
                    ToastUtil.show(this, "正在加载数据,请稍后。");
                }
                break;
            case R.id.left_igame_relative:
                finishActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                patrolRecordDatas = gson.fromJson(data.data, new TypeToken<List<PatrolRecordData>>() {
                }.getType());
                gridViewTwo.setAdapter(new PatrolRecordDetailsAdapter(this, patrolRecordDatas.get(0).getUserimgs()));
                gridViewOne.setAdapter(new PatrolRecordDetailsAdapter(this, patrolRecordDatas.get(0).getImgs()));
                patrol_record_add_content.setText(patrolRecordDatas.get(0).getContent());
                patrol_record_add_material_info.setText(patrolRecordDatas.get(0).getMaterial_info());

                if("1".equals(patrolRecordDatas.get(0).getAmerce())){
                    isAmerce_money.setVisibility(View.VISIBLE);
                    isConfiguation_audit.setVisibility(View.VISIBLE);
                    myImageVidew.setVisibility(View.VISIBLE);
                    isPhasename.setVisibility(View.VISIBLE);
                    fine_cause_LinearLayout.setVisibility(View.VISIBLE);
                    amerce_money.setText(patrolRecordDatas.get(0).getAmerce_money());
                    if("0".equals(patrolRecordDatas.get(0).getVerify())){
                        configuation_audit.setText("待审核");
                    }else if("1".equals(patrolRecordDatas.get(0).getVerify())){
                        configuation_audit.setText("通过");
                    }else if("2".equals(patrolRecordDatas.get(0).getVerify())){
                        configuation_audit.setText("驳回");
                    }
                    phasename.setText(patrolRecordDatas.get(0).getPhasename());
                    fine_cause.setText(patrolRecordDatas.get(0).getReason());
                }else{
                    isAmerce_money.setVisibility(View.GONE);
                    isConfiguation_audit.setVisibility(View.GONE);
                    myImageVidew.setVisibility(View.GONE);
                    isPhasename.setVisibility(View.GONE);
                    fine_cause_LinearLayout.setVisibility(View.GONE);
                }

                List<PatrolRecordImages> userimgs = patrolRecordDatas.get(0).getUserimgs();
                listUserimgs = new ArrayList<String>();
                for (PatrolRecordImages img : userimgs) {
                    listUserimgs.add(img.getNormal());
                }

                List<PatrolRecordImages> imgs = patrolRecordDatas.get(0).getImgs();
                listImgs = new ArrayList<String>();
                for (PatrolRecordImages img : imgs) {
                    listImgs.add(img.getNormal());
                }

                gridViewOne.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intentImage = new Intent(PatrolRecordDetailsActivity.this, ImageGalleryActivity.class);
                        intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, listImgs);
                        intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, listImgs);
                        intentImage.putExtra(ImageGalleryActivity.IMAGE_ADDRESS, HttpConstant.IMAGEADDRESS);
                        intentImage.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
                        intentImage.putExtra("qianshoudan","现场照片");
                        startActivity(intentImage);
                    }
                });
                gridViewTwo.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        for (int i=0;i<listImgs.size();i++){
                            Log.e("src-2",listImgs.get(i));
                        }
                        Intent intentImage = new Intent(PatrolRecordDetailsActivity.this, ImageGalleryActivity.class);
                        intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, listUserimgs);
                        intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, listUserimgs);
                        intentImage.putExtra(ImageGalleryActivity.IMAGE_ADDRESS, HttpConstant.IMAGEADDRESS);
                        intentImage.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
                        intentImage.putExtra("qianshoudan","业主沟通");
                        startActivity(intentImage);
                    }
                });
                break;
            default:
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoad();
    }

}
