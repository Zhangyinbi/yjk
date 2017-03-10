package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.QuestionDetailActivity;
import com.example.yangbang.miowner.entity.Question;
import com.example.yangbang.miowner.fragment.FragmentQuestion;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.HintDialog;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.ViewHolders;

import java.util.HashMap;
import java.util.List;

/**
 * 显示问题adapter
 *
 * @FileName: com.example.yangbang.miowner.adapter.QuestionAdapter.java
 * @author: Yangbang
 * @date: 2016-06-27 17:38
 */
public class QuestionAdapter extends AbsBaseAdapter<Question> {
    Context context;
    HintDialog hintDialog;
    AnalyzeJson analyzeJson;

    public QuestionAdapter(List<Question> dataList, Context context, AnalyzeJson analyzeJson) {
        super(dataList);
        this.context = context;
        this.analyzeJson = analyzeJson;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_question, null);
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_department)).setText("相关部门：" + dataList.get(position).getCom_type());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_duty)).setText(dataList.get(position).getRole_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_desc)).setText(dataList.get(position).getContent());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_upload_date)).setText(dataList.get(position).getAddtime());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setText(dataList.get(position).getButton_status().equals("2") ? "撤消问题" : "删除");
        switch (Integer.parseInt(dataList.get(position).getStatus() + "")) {
            case 1://待解决
                ((ImageView) ViewHolders.get(convertView, R.id.img_listitem_question_status)).setImageResource(R.mipmap.grey);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setBackgroundResource(R.drawable.shape_text_red_stroke);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setTextColor(context.getResources().getColor(R.color.red_e76170));
                break;
            case 2://解决中
                ((ImageView) ViewHolders.get(convertView, R.id.img_listitem_question_status)).setImageResource(R.mipmap.orange);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setBackgroundResource(R.drawable.shape_text_red_stroke);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setTextColor(context.getResources().getColor(R.color.red_e76170));
                break;
            case 3://已解决
                ((ImageView) ViewHolders.get(convertView, R.id.img_listitem_question_status)).setImageResource(R.mipmap.green);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setBackgroundResource(R.drawable.shape_text_gray_stroke);
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setTextColor(context.getResources().getColor(R.color.gray_959595));
                break;
        }
        ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_imgs)).setVisibility(dataList.get(position).getImgs().size() > 0 ? View.VISIBLE : View.GONE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info1)).setVisibility(View.INVISIBLE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info2)).setVisibility(View.INVISIBLE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info3)).setVisibility(View.INVISIBLE);
        for (int i = 0; i < dataList.get(position).getImgs().size(); i++) {
            if (i == 0) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info1)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(position).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info1));
            }
            if (i == 1) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info2)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(position).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info2));
            }
            if (i == 2) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info3)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(position).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info3));
            }
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_question_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintDialog == null) {
                    hintDialog = new HintDialog(context);
                }
                if (dataList.get(position).getStatus().equals("3")) {
                    hintDialog.setTitleText("确定删除该记录？");
                } else {
                    hintDialog.setTitleText("确定撤消该记录？");
                }
                hintDialog.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hintDialog.dismiss();
                    }
                });
                hintDialog.setConfirmListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hintDialog.dismiss();
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", MyApplication.TOKEN);
                        params.put("id", dataList.get(position).getId());
                        analyzeJson.requestData(HttpConstant.QuestionCancel, params, FragmentQuestion.QUESTION_DEL);
                    }
                });
                hintDialog.show();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionDetailActivity.class);
                intent.putExtra("id", dataList.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
