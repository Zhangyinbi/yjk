package com.youjuke.miprojectmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.entity.PatrolRecord;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangbang
 * @ClassName PatrolRecordAdapter
 * @description 巡查记录adapter
 * @date 2015年5月14日
 */
public class PatrolRecordAdapter extends AbsBaseAdapter<PatrolRecord> {
    private Context context;
    String item_time;

    public PatrolRecordAdapter(Context context, List<PatrolRecord> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.patrol_record_list_item, null);
        }
        PatrolRecord record = dataList.get(position);
        item_time = record.addtime.substring(5, 7) + "月"
                + record.addtime.substring(8, 10) + "号";
        ((TextView) ViewHolders.get(convertView, R.id.tv_project_info_time))
                .setText(item_time);
        ((TextView) ViewHolders.get(convertView, R.id.tv_project_info_content))
                .setText(record.content);
        if (record.amerce == 1) {
            ((RelativeLayout) ViewHolders.get(convertView, R.id.project_info_list_relative)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_project_info_verify_status))
                    .setText(record.verify == 0 ? "待审核" : record.verify == 1 ? "通过" : "驳回");
            ((TextView) ViewHolders.get(convertView, R.id.tv_project_info_money))
                    .setText("罚款金额：" + record.amerce_money + "元");
        } else {
            ((RelativeLayout) ViewHolders.get(convertView, R.id.project_info_list_relative)).setVisibility(View.GONE);
        }
        LinearLayout img_layout = ViewHolders.get(convertView,
                R.id.img_project_layout);
        if (record.imgs == null || record.imgs.size() == 0) {
            img_layout.setVisibility(View.GONE);
        } else {
            int showCount = 3;
            final ArrayList<String> img_urls = new ArrayList<String>();
            if (record.imgs.size() <= 3) {
                showCount = record.imgs.size();
            }
            img_layout.setVisibility(View.VISIBLE);
            for (int i = 0; i < img_layout.getChildCount(); i++) {
                img_layout.getChildAt(i).setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < showCount; i++) {
                img_layout.getChildAt(i).setVisibility(View.VISIBLE);
                img_urls.add(HttpConstant.IMAGEADDRESS
                        + record.imgs.get(i).normal);
                MyApplication.imageLoader.displayImage(
                        HttpConstant.IMAGEADDRESS + record.imgs.get(i).small,
                        (ImageView) img_layout.getChildAt(i));
                // 暂时取消点击图片放大效果
                // final int index = i;
                // img_layout.getChildAt(i).setOnClickListener(
                // new OnClickListener() {
                //
                // @Override
                // public void onClick(View v) {
                // Intent intentImage = new Intent(context,
                // ImageGalleryActivity.class);
                // intentImage.putStringArrayListExtra(
                // ImageGalleryActivity.EXTRA_URLS,
                // img_urls);
                // intentImage.putStringArrayListExtra(
                // ImageGalleryActivity.EXTRA_FILE_PATHS,
                // img_urls);
                // intentImage
                // .putExtra(
                // ImageGalleryActivity.EXTRA_INDEX,
                // index);
                // context.startActivity(intentImage);
                // }
                // });
            }
        }
        return convertView;
    }

}
