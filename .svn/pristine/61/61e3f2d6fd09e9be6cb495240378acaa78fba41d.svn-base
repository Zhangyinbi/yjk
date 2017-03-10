package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.QuestionProgress;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;

import java.util.List;

/**
 * 问题处理进度adapter
 *
 * @FileName: com.example.yangbang.miowner.adapter.QuestionHanlderProgressAdapter.java
 * @author: Yangbang
 * @date: 2016-06-27 17:46
 */
public class QuestionHanlderProgressAdapter extends AbsBaseAdapter<QuestionProgress> {
    Context context;

    public QuestionHanlderProgressAdapter(List<QuestionProgress> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_handler_progress, null);
        }
        if (position == 0) {
            ViewHolders.get(convertView, R.id.view_handler_progress_divide).setVisibility(View.GONE);
            ViewHolders.get(convertView, R.id.view_item_left_top_line).setVisibility(View.INVISIBLE);
            ((ImageView) ViewHolders.get(convertView, R.id.img_item_left_icon)).setImageResource(R.mipmap.greenpoint);
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_name)).setTextColor(context.getResources().getColor(R.color.green_32b16c));
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_time)).setTextColor(context.getResources().getColor(R.color.green_32b16c));
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_desc)).setTextColor(context.getResources().getColor(R.color.black));
        } else {
            ViewHolders.get(convertView, R.id.view_handler_progress_divide).setVisibility(View.VISIBLE);
            ViewHolders.get(convertView, R.id.view_item_left_top_line).setVisibility(View.VISIBLE);
            ((ImageView) ViewHolders.get(convertView, R.id.img_item_left_icon)).setImageResource(R.mipmap.greypoint);
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_name)).setTextColor(context.getResources().getColor(R.color.gray_959595));
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_time)).setTextColor(context.getResources().getColor(R.color.gray_959595));
            ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_desc)).setTextColor(context.getResources().getColor(R.color.gray_959595));
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_name)).setText("跟进人：" + dataList.get(position).getRealname() + "    职位：" + dataList.get(position).getRankname());
        ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_desc)).setText(dataList.get(position).getContent());
        ((TextView) ViewHolders.get(convertView, R.id.tv_item_progress_time)).setText(dataList.get(position).getAddtime());
        return convertView;
    }
}
