package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.AssistNodeNoteActivity;
import com.xiaomizhuang.buildcaptain.activity.ScheduleOrderDetailActivity;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionSchedulingOne;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionSchedulingTwo;
import com.xiaomizhuang.buildcaptain.fragment.FragmentConstructScheduling;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015/12/28.
 */
public class AssistConstructionSchedulingOneAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String bm_id;
    private ArrayList<AssistConstructionSchedulingOne> mAssistConstructionSchedulingOnes = new ArrayList<AssistConstructionSchedulingOne>();

    public AssistConstructionSchedulingOneAdapter(Context context, ArrayList<AssistConstructionSchedulingOne> mAssistConstructionSchedulingOnes, String bm_id) {
        this.mAssistConstructionSchedulingOnes = mAssistConstructionSchedulingOnes;
        this.context = context;
        this.bm_id = bm_id;
    }

    @Override
    public int getGroupCount() {
        return mAssistConstructionSchedulingOnes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mAssistConstructionSchedulingOnes.get(groupPosition).getData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.listitem_owner_construc_progress_group, null);
            holder.ImageLeft = (ImageView) v.findViewById(R.id.listitem_owner_construc_progress_group_image_left);
            holder.ImageRight = (ImageView) v.findViewById(R.id.listitem_owner_construc_progress_group_image_right);
            holder.TextTop = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_top);
            holder.TextBottom = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_bottom);
            holder.TextType = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_type);
            holder.Text = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            holder.ImageRight.setBackgroundResource(R.mipmap.gouxuanspread);
        } else {
            holder.ImageRight.setBackgroundResource(R.mipmap.gouxuan);
        }
        holder.TextTop.setText(mAssistConstructionSchedulingOnes.get(groupPosition).getTitle());
        holder.TextBottom.setText(mAssistConstructionSchedulingOnes.get(groupPosition).getStart() + "  -  " + mAssistConstructionSchedulingOnes.get(groupPosition).getEnd());
        //防止数据错乱，必须清除上一次设置的数据
        holder.TextType.setText("");
        holder.ImageLeft.setImageBitmap(null);
        holder.Text.setVisibility(View.GONE);
        if (!"1".equals(FragmentConstructScheduling.plan_type)) {
            holder.Text.setVisibility(View.VISIBLE);
            //跳转到节点备注详情
            holder.Text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(context, AssistNodeNoteActivity.class);
                    it.putExtra("id", mAssistConstructionSchedulingOnes.get(groupPosition).getId());
                    it.putExtra("plan_type", FragmentConstructScheduling.plan_type);
                    it.putExtra("baoming_id", bm_id);
                    context.startActivity(it);
                }
            });
            // 0未开始 -1超时未完成 1已完成 2完成但有延期
            switch (mAssistConstructionSchedulingOnes.get(groupPosition).getStatus()) {
                case "0":
                    holder.TextType.setTextColor(context.getResources().getColor(R.color.grey));
                    holder.TextType.setText("未开始");
                    holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.nostart)).getBitmap());
                    break;
                case "-1":
                    holder.TextType.setTextColor(context.getResources().getColor(R.color.red));
                    holder.TextType.setText("延期" + mAssistConstructionSchedulingOnes.get(groupPosition).getInterval_time() + "天");
                    holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.underway)).getBitmap());
                    break;
                case "1":
                    holder.TextType.setTextColor(context.getResources().getColor(R.color.complete2));
                    holder.TextType.setText("已完成");
                    holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.start)).getBitmap());
                    break;
                case "2":
                    holder.TextType.setTextColor(context.getResources().getColor(R.color.red));
                    holder.TextType.setText("延期" + mAssistConstructionSchedulingOnes.get(groupPosition).getInterval_time() + "天");
                    holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.start)).getBitmap());
                    break;
                case "3":
                    holder.TextType.setTextColor(context.getResources().getColor(R.color.yello2));
                    holder.TextType.setText("进行中");
                    holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.underway)).getBitmap());
                    break;
            }
        }
        return v;
    }

    class ViewHolder {
        public ImageView ImageLeft;
        public ImageView ImageRight;
        public TextView TextTop;
        public TextView TextBottom;
        public TextView TextType;
        public TextView Text;
    }

    @Override
    public View getChildView(int groupPosition, int position, boolean isLastChild, View v, ViewGroup parent) {
        List<AssistConstructionSchedulingTwo> dataList = mAssistConstructionSchedulingOnes.get(groupPosition).getData();
        ViewHolderChild holder = null;
        if (v == null) {
            holder = new ViewHolderChild();
            v = View.inflate(context, R.layout.listitem_owner_construc_progress_child_item, null);
            holder.date = (TextView) v.findViewById(R.id.child_date);
            holder.imageView = (ImageView) v.findViewById(R.id.child_image_left);
            holder.child_LinearLayout = (LinearLayout) v.findViewById(R.id.child_LinearLayout);
            v.setTag(holder);
        } else {
            holder = (ViewHolderChild) v.getTag();
        }
        holder.date.setText(dataList.get(position).getDay() + "  " + dataList.get(position).getWeek());
        if (!"1".equals(FragmentConstructScheduling.plan_type)) {
            //0进行中 -1未完成 1已完成
            switch (dataList.get(position).getStatus()) {
                case "-1":
                    holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.maketimegrey)).getBitmap());
                    break;
                case "0":
                    holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.maketimeblue)).getBitmap());
                    break;
                case "1":
                    holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.gouxuangrey)).getBitmap());
                    break;
            }
            if (toSize(dataList.get(position).getDay())) {
                holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistnostart);
            } else {
                holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistcompleted);
            }
        } else {
            holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.maketimegrey)).getBitmap());
            holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistnostart);
        }

        //清除上次添加的view
        if (holder.child_LinearLayout.getChildCount() > 1) {
            for (int i = 1; i < holder.child_LinearLayout.getChildCount(); i++) {
                holder.child_LinearLayout.removeViewAt(i);
            }
        }

        //添加view
        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lllp.setMargins(0, 0, 0, 20);
        lllp.gravity = Gravity.CENTER_VERTICAL;
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(lllp);
        for (int i = 0; i < dataList.get(position).getData().size(); i++) {
            View view = View.inflate(context, R.layout.listitem_owner_construc_progress_child_item_addview, null);
            TextView child_text = (TextView) view.findViewById(R.id.child_text);
            View child_image_right_line = (View)view.findViewById(R.id.child_image_right_line);
            if(i == dataList.get(position).getData().size() -1){
                child_image_right_line.setVisibility(View.GONE);
            }
            ImageView child_image_right = (ImageView) view.findViewById(R.id.child_image_right);
            child_text.setText(dataList.get(position).getData().get(i).getTitle());
            if (!"1".equals(FragmentConstructScheduling.plan_type)) {
                //0未开始 -1超时未完成 1已完成 2完成但有延期
                switch (dataList.get(position).getData().get(i).getStatus()) {
                    case "0":
                        child_text.setTextColor(context.getResources().getColor(R.color.black));
                        child_image_right.setImageBitmap(null);
                        break;
                    case "-1":
                        child_text.setTextColor(context.getResources().getColor(R.color.black));
                        child_image_right.setImageBitmap(null);
                        break;
                    case "1":
                        child_text.setTextColor(context.getResources().getColor(R.color.gray_959595));
                        child_image_right.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.gouxuanblue)).getBitmap());
                        break;
                    case "2":
                        child_text.setTextColor(context.getResources().getColor(R.color.gray_959595));
                        child_image_right.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.gouxuanblue)).getBitmap());
                        break;
                }
            } else {
                child_text.setTextColor(context.getResources().getColor(R.color.black));
                child_image_right.setImageBitmap(null);
            }
            //给addview添加事件
            view.setOnClickListener(new MyOnclick(dataList, position, i));
            ll.addView(view);
        }
        holder.child_LinearLayout.addView(ll);
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setNotifyDataSetChangedList(ArrayList<AssistConstructionSchedulingOne> mAssistConstructionSchedulingOnes) {
        this.mAssistConstructionSchedulingOnes = mAssistConstructionSchedulingOnes;
        this.notifyDataSetChanged();
    }

    class ViewHolderChild {
        public TextView date;
        public ImageView imageView;
        public LinearLayout child_LinearLayout;
    }

    private boolean toSize(String str) {
        str = str + " 23:59:59";
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date nowDate = new Date();
        return date.getTime() > nowDate.getTime() ? true : false;
    }

    class MyOnclick implements View.OnClickListener {
        private List<AssistConstructionSchedulingTwo> dataList;
        private int position;
        private int i;

        public MyOnclick(List<AssistConstructionSchedulingTwo> dataList, int position, int i) {
            this.dataList = dataList;
            this.position = position;
            this.i = i;
        }

        @Override
        public void onClick(View v) {
            if (!"1".equals(FragmentConstructScheduling.plan_type)) {
                Intent it = new Intent(context, ScheduleOrderDetailActivity.class);
                it.putExtra("id", dataList.get(position).getData().get(i).getId());
                it.putExtra("plan_type", FragmentConstructScheduling.plan_type);
                it.putExtra("event_type",  dataList.get(position).getData().get(i).getEvent_type());
                it.putExtra("baoming_id", bm_id);
                it.putExtra("title", dataList.get(position).getData().get(i).getTitle());
                context.startActivity(it);
            }
        }
    }
}
