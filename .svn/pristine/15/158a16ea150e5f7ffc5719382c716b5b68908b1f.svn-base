package com.youjuke.miprojectmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.activity.AssistProgectAddMatterActivity;
import com.youjuke.miprojectmanager.activity.MessageActivity;
import com.youjuke.miprojectmanager.activity.PayRecordActivity;
import com.youjuke.miprojectmanager.entity.BuildingSite;

import java.util.List;

/**
 * 在建工地adapter
 *
 * @author Yangbang
 * @ClassName BuildingSiteAdapter
 * @description 类的描述
 * @date 2015年5月13日
 */
public class BuildingSiteAdapter extends AbsBaseAdapter<BuildingSite> {

    Context context;
    String last_time = "";

    public BuildingSiteAdapter(Context context, List<BuildingSite> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.building_site_item, null);
        }
        final BuildingSite owner = dataList.get(position);
        if (!TextUtils.isEmpty(owner.last_add_time)
                && owner.last_add_time.length() > 10) {
            ((TextView) ViewHolders.get(convertView, R.id.textView_status))
                    .setVisibility(View.VISIBLE);
            last_time = owner.last_add_time.substring(5, 7) + "月"
                    + owner.last_add_time.substring(8, 10) + "日";
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.textView_status))
                    .setVisibility(View.GONE);
        }

                ((TextView) ViewHolders.get(convertView, R.id.textView_item_name))
                .setText(owner.name);
        ((TextView) ViewHolders.get(convertView, R.id.textView_item_telephone))
                .setText(owner.mobile);
        ((TextView) ViewHolders.get(convertView, R.id.textView_item_address))
                .setText(owner.zx_address);
        ((TextView) ViewHolders.get(convertView, R.id.textView_status))
                .setText(last_time);
        ((ImageView) ViewHolders.get(convertView, R.id.img_jiaodi))
                .setImageResource(owner.jd_status == 1 ? R.drawable.already_sure
                        : R.drawable.nosure);
        ((ImageView) ViewHolders.get(convertView, R.id.img_shuidian))
                .setImageResource(owner.sd_status == 1 ? R.drawable.already_sure
                        : R.drawable.nosure);
        ((ImageView) ViewHolders.get(convertView, R.id.img_nimo))
                .setImageResource(owner.lm_status == 1 ? R.drawable.already_sure
                        : R.drawable.nosure);
        ((ImageView) ViewHolders.get(convertView, R.id.img_youqi))
                .setImageResource(owner.yq_status == 1 ? R.drawable.already_sure
                        : R.drawable.nosure);
        ((ImageView) ViewHolders.get(convertView, R.id.img_jungong))
                .setImageResource(owner.jg_status == 1 ? R.drawable.already_sure
                        : R.drawable.nosure);
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind_count)).setText(owner.my_msg_count + "");
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind_count)).setVisibility(owner.my_msg_count > 0 ? View.VISIBLE : View.GONE);
        //付款记录
        ((TextView) ViewHolders.get(convertView, R.id.tv_build_site_item_pay_record)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PayRecordActivity.class);
                intent.putExtra("bm_id", owner.id + "");
                context.startActivity(intent);
            }
        });
        //消息提醒
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("bm_id", owner.id + "");
                intent.putExtra("titleType", "消息提醒");
                context.startActivity(intent);
            }
        });
        //新增工程项
        ((TextView)  ViewHolders.get(convertView,R.id.tv_build_site_item_material_detail_add_order))
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AssistProgectAddMatterActivity.class);
                intent.putExtra("bm_id", owner.id + "");
                context.startActivity(intent);

            }
        });
/*        //新增巡查记录
        ((TextView) ViewHolders.get(convertView, R.id.tv_orders_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PatrolRecordAddActivity.class);
                intent.putExtra("baoming_id", owner.id + "");
                context.startActivity(intent);
            }
        });*/
        return convertView;
    }

}
