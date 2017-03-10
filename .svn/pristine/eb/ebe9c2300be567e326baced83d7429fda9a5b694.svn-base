package com.example.yangbang.miowner.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.AddQuestionOrCommentActivity;
import com.example.yangbang.miowner.entity.QuestionObject;
import com.example.yangbang.miowner.view.HintDialog;
import com.hbw.library.AbsBaseAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/25.
 */
public class QuestionObjectAdapter extends AbsBaseAdapter<QuestionObject> {
    Activity context;
    HashMap<Integer, Boolean> isSelect = new HashMap<>();
    OnTypeClicklistener onTypeClicklistener;
    int flag = 0;//1代表评论
    HintDialog mHintDialog;

    //    TextView tv_item;
    public QuestionObjectAdapter(Activity context, List<QuestionObject> dataList) {
        super(dataList);
        this.context = context;
        isSelect.put(0, true);
        for (int i = 1; i < dataList.size(); i++) {
            isSelect.put(i, false);
        }
    }

    @Override
    public void notifyDataSetChanged(List<QuestionObject> dataList) {
        isSelect.put(0, true);
        for (int i = 1; i < dataList.size(); i++) {
            isSelect.put(i, false);
        }
        super.notifyDataSetChanged(dataList);
    }

    public void setOnTypeClicklistener(OnTypeClicklistener onTypeClicklistener) {
        this.onTypeClicklistener = onTypeClicklistener;
    }

    public interface OnTypeClicklistener {
        void onClilck(QuestionObject assistDetails);
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.assist_type_item, parent, false);
        }
//        tv_item=((TextView)ViewHolders.get(convertView,R.id.tv_assist_type_item));
        final TextView tv_item = (TextView) convertView.findViewById(R.id.tv_assist_type_item);
        tv_item.setText(dataList.get(position).getName());
        if (isSelect.get(position)) {
            tv_item.setTextColor(context.getResources().getColor(R.color.white));
            tv_item.setBackgroundResource(R.drawable.shape_red_solid_stroke);
        } else {
            tv_item.setTextColor(context.getResources().getColor(R.color.gray_7d7d7d));
//            tv_item.setTextColor(context.getResources().getColor(R.drawable.selector_white_text_bg));
            tv_item.setBackgroundResource(R.drawable.selector_red_bg);
        }
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    if (mHintDialog == null) {
                        mHintDialog = new HintDialog(context);
                    }
                    mHintDialog.setTitleText("当前评价还未保存，是否发布？");
                    mHintDialog.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHintDialog.dismiss();
                            for (int i = 0; i < isSelect.size(); i++) {
                                isSelect.put(i, false);
                            }
                            isSelect.put(position, true);
                            if (onTypeClicklistener != null) {
                                onTypeClicklistener.onClilck(dataList.get(position));
                            }
                            notifyDataSetChanged();
                        }
                    });
                    mHintDialog.setConfirmListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHintDialog.dismiss();
                            ((AddQuestionOrCommentActivity) context).submitComment();
                        }
                    });
                    mHintDialog.show();
                } else {
                    for (int i = 0; i < isSelect.size(); i++) {
                        isSelect.put(i, false);
                    }
                    isSelect.put(position, true);
                    if (onTypeClicklistener != null) {
                        onTypeClicklistener.onClilck(dataList.get(position));
                    }
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }
}
