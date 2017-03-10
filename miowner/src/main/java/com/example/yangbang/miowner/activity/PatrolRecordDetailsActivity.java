package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.PatrolRecordDetailsAdapter;
import com.example.yangbang.miowner.entity.PatrolRecordData;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.hbw.library.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 工地巡查记录详情
 *
 * @author hebiwen
 */
public class PatrolRecordDetailsActivity extends BaseActivity {

    public static final String TAG = "PatrolRecordAddActivity-->";
    private MyGridView gridViewTwo, gridViewOne;
    private TextView patrol_record_add_content;
    private List<PatrolRecordData> patrolRecordDatas;
    private String pro_post_id;
    private ArrayList<String> listImgs;
    private ArrayList<String> listUserimgs;
    private TextView patrol_record_add_material_info;
    private ImageView iv_dashed1,iv_dashed2,iv_dashed3;

    private LinearLayout isAmerce_money;
    private TextView amerce_money;

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
        amerce_money = (TextView) findViewById(R.id.amerce_money);
        iv_dashed1 = (ImageView) findViewById(R.id.dashed1);
        iv_dashed2 = (ImageView) findViewById(R.id.dashed2);
        iv_dashed3 = (ImageView) findViewById(R.id.dashed3);


        isPhasename = (LinearLayout)findViewById(R.id.isPhasename);
        phasename = (TextView) findViewById(R.id.phasename);

        fine_cause_LinearLayout = (LinearLayout)findViewById(R.id.fine_cause_LinearLayout);
        fine_cause = (TextView) findViewById(R.id.fine_cause);

        titleBar.setTitleText("巡查记录详情");
        titleBar.setLeftImgDefaultBack(this);

        pro_post_id = getIntent().getStringExtra("id");
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
        params.put("id",pro_post_id);
        params.put("token",MyApplication.getApp().getOwnerUser().getToken());
        analyzeJson.requestData(HttpConstant.PatrolRecordDetail,params,REQUEST_SUCCESS);
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

                if("1".equals(patrolRecordDatas.get(0).getAmerce()+"")){
                    isAmerce_money.setVisibility(View.VISIBLE);
                    isPhasename.setVisibility(View.VISIBLE);
                    fine_cause_LinearLayout.setVisibility(View.VISIBLE);
                    iv_dashed1.setVisibility(View.VISIBLE);
                    iv_dashed2.setVisibility(View.VISIBLE);
                    iv_dashed3.setVisibility(View.VISIBLE);
                    amerce_money.setText(patrolRecordDatas.get(0).getAmerce_money());

                    phasename.setText(patrolRecordDatas.get(0).getPhasename());
                    fine_cause.setText(patrolRecordDatas.get(0).getReason());
                }else{
                    isAmerce_money.setVisibility(View.GONE);
                    isPhasename.setVisibility(View.GONE);
                    fine_cause_LinearLayout.setVisibility(View.GONE);
                    iv_dashed1.setVisibility(View.GONE);
                    iv_dashed2.setVisibility(View.GONE);
                    iv_dashed3.setVisibility(View.GONE);
                }

                List<PatrolRecordData.ImgsBean> userimgs = patrolRecordDatas.get(0).getUserimgs();
                listUserimgs = new ArrayList<String>();
                for (PatrolRecordData.ImgsBean img : userimgs) {
                    listUserimgs.add(img.getNormal());
                }

                List<PatrolRecordData.ImgsBean> imgs = patrolRecordDatas.get(0).getImgs();
                listImgs = new ArrayList<String>();
                for (PatrolRecordData.ImgsBean img : imgs) {
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
