package com.example.yangbang.miowner.fragment;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.QuestionDetailActivity;
import com.example.yangbang.miowner.entity.QuestionDetail;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.pictureview.ImageGalleryActivity;

import java.util.ArrayList;

/**
 * 问题详情信息Fragment
 *
 * @FileName: com.example.yangbang.miowner.fragment.FragmentQuestionInfo.java
 * @author: Yangbang
 * @date: 2016-06-27 17:15
 */
public class FragmentQuestionInfo extends BaseFragment {
    private LinearLayout llFragmentQuestionInfoHead;
    private ImageView imgFragmentQuestionInfoIcon;
    private TextView tvFragmentQuestionInfoStatic;
    private TextView tvFragmentQuestionInfoDep;
    private TextView tvFragmentQuestionInfoDuty;
    private TextView tvFragmentQuestionInfoDesc;
    private LinearLayout llListitemImgs;
    private ImageView imgProjectInfo1;
    private ImageView imgProjectInfo2;
    private ImageView imgProjectInfo3;
    private TextView tvFragmentQuestionInfoStartTime;
    private TextView tvFragmentQuestionInfoIssuedTime;
    private TextView tvFragmentQuestionInfoPreTime;
    private TextView tvFragmentQuestionInfoSolveTime;

    private QuestionDetail questionDetail;

    @Override
    protected void initWidget() {
        assignViews();
        getQuestionDetail();
    }

    private void assignViews() {
        llFragmentQuestionInfoHead = (LinearLayout) getView().findViewById(R.id.ll_fragment_question_info_head);
        imgFragmentQuestionInfoIcon = (ImageView) getView().findViewById(R.id.img_fragment_question_info_icon);
        tvFragmentQuestionInfoStatic = (TextView) getView().findViewById(R.id.tv_fragment_question_info_static);
        tvFragmentQuestionInfoDep = (TextView) getView().findViewById(R.id.tv_fragment_question_info_dep);
        tvFragmentQuestionInfoDuty = (TextView) getView().findViewById(R.id.tv_fragment_question_info_duty);
        tvFragmentQuestionInfoDesc = (TextView) getView().findViewById(R.id.tv_fragment_question_info_desc);
        llListitemImgs = (LinearLayout) getView().findViewById(R.id.ll_listitem_imgs);
        imgProjectInfo1 = (ImageView) getView().findViewById(R.id.img_project_info1);
        imgProjectInfo2 = (ImageView) getView().findViewById(R.id.img_project_info2);
        imgProjectInfo3 = (ImageView) getView().findViewById(R.id.img_project_info3);
        tvFragmentQuestionInfoStartTime = (TextView) getView().findViewById(R.id.tv_fragment_question_info_start_time);
        tvFragmentQuestionInfoIssuedTime = (TextView) getView().findViewById(R.id.tv_fragment_question_info_issued_time);
        tvFragmentQuestionInfoPreTime = (TextView) getView().findViewById(R.id.tv_fragment_question_info_pre_time);
        tvFragmentQuestionInfoSolveTime = (TextView) getView().findViewById(R.id.tv_fragment_question_info_solve_time);
    }


    private void getQuestionDetail() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("id", ((QuestionDetailActivity) getActivity()).getQuestionId());
        analyzeJson.requestData(HttpConstant.QuestionDetail, params, REQUEST_SUCCESS);
    }

    private void setQuestionDetail() {
        switch (Integer.parseInt(questionDetail.getStatus())) {
            case 1://待解决
                llFragmentQuestionInfoHead.setBackgroundColor(getResources().getColor(R.color.text_d3d3d3));
                imgFragmentQuestionInfoIcon.setImageResource(R.mipmap.waiting);
                tvFragmentQuestionInfoStatic.setTextColor(getResources().getColor(R.color.white));
                tvFragmentQuestionInfoStatic.setText("问题待解决");
                break;
            case 2://解决中
                llFragmentQuestionInfoHead.setBackgroundColor(getResources().getColor(R.color.orange_ffbb55));
                imgFragmentQuestionInfoIcon.setImageResource(R.mipmap.ing);
                tvFragmentQuestionInfoStatic.setTextColor(getResources().getColor(R.color.white));
                tvFragmentQuestionInfoStatic.setText("问题解决中");
                break;
            case 3://已解决
                llFragmentQuestionInfoHead.setBackgroundColor(getResources().getColor(R.color.green_32b16e));
                imgFragmentQuestionInfoIcon.setImageResource(R.mipmap.ed);
                tvFragmentQuestionInfoStatic.setTextColor(getResources().getColor(R.color.white));
                tvFragmentQuestionInfoStatic.setText("问题已解决");
                break;
        }
        tvFragmentQuestionInfoDep.setText(questionDetail.getCom_type());
        tvFragmentQuestionInfoDuty.setText(questionDetail.getRole_name());
        tvFragmentQuestionInfoDesc.setText(questionDetail.getContent());
        tvFragmentQuestionInfoStartTime.setText("问题提出时间：" + questionDetail.getAddtime());
        tvFragmentQuestionInfoIssuedTime.setText("问题下发时间：" + questionDetail.getAllot_time_manager());
        tvFragmentQuestionInfoPreTime.setText("预计完成时间：" + questionDetail.getExpected_processing_time());
        tvFragmentQuestionInfoSolveTime.setText("实际解决时间：" + questionDetail.getAct_end_time());
        if (questionDetail.getImgs().size() <= 0) {
            llListitemImgs.setVisibility(View.GONE);
        } else {
            llListitemImgs.setVisibility(View.VISIBLE);
            imgProjectInfo1.setVisibility(View.INVISIBLE);
            imgProjectInfo2.setVisibility(View.INVISIBLE);
            imgProjectInfo3.setVisibility(View.INVISIBLE);
            final ArrayList<String> img_urls = new ArrayList<String>();
            for (int i = 0; i < questionDetail.getImgs().size(); i++) {
                img_urls.add(HttpConstant.IMAGEADDRESS + questionDetail.getImgs().get(i).getNormal());
                if (i == 0) {
                    imgProjectInfo1.setVisibility(View.VISIBLE);
                    MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + questionDetail.getImgs().get(i).getSmall(), imgProjectInfo1);
                }
                if (i == 1) {
                    imgProjectInfo2.setVisibility(View.VISIBLE);
                    MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + questionDetail.getImgs().get(i).getSmall(), imgProjectInfo2);
                }
                if (i == 2) {
                    imgProjectInfo3.setVisibility(View.VISIBLE);
                    MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + questionDetail.getImgs().get(i).getSmall(), imgProjectInfo3);
                }
            }
            imgProjectInfo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentImage = new Intent(getActivity(),
                            ImageGalleryActivity.class);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_URLS,
                            img_urls);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_FILE_PATHS,
                            img_urls);
                    intentImage
                            .putExtra(
                                    ImageGalleryActivity.EXTRA_INDEX,
                                    0);
                    startActivity(intentImage);
                }
            });
            imgProjectInfo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentImage = new Intent(getActivity(),
                            ImageGalleryActivity.class);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_URLS,
                            img_urls);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_FILE_PATHS,
                            img_urls);
                    intentImage
                            .putExtra(
                                    ImageGalleryActivity.EXTRA_INDEX,
                                    1);
                    startActivity(intentImage);
                }
            });
            imgProjectInfo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentImage = new Intent(getActivity(),
                            ImageGalleryActivity.class);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_URLS,
                            img_urls);
                    intentImage.putStringArrayListExtra(
                            ImageGalleryActivity.EXTRA_FILE_PATHS,
                            img_urls);
                    intentImage
                            .putExtra(
                                    ImageGalleryActivity.EXTRA_INDEX,
                                    2);
                    startActivity(intentImage);
                }
            });
        }
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_question_info;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                questionDetail = gson.fromJson(data.data, QuestionDetail.class);
                setQuestionDetail();
                break;
        }
        return super.handleMessage(msg);
    }
}
