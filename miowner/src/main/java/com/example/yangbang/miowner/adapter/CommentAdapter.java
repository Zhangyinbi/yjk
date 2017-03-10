package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.Comment;
import com.example.yangbang.miowner.fragment.FragmentComment;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.HintDialog;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.hbw.library.utils.ViewHolders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 显示评价adapter
 *
 * @FileName: com.example.yangbang.miowner.adapter.CommentAdapter.java
 * @author: Yangbang
 * @date: 2016-06-27 17:42
 */
public class CommentAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Comment> dataList;
    AnalyzeJson analyzeJson;
    HintDialog hintDialog;

    public CommentAdapter(List<Comment> dataList, Context context, AnalyzeJson analyzeJson) {
        this.dataList = dataList;
        this.context = context;
        this.analyzeJson = analyzeJson;
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataList.get(groupPosition).getReview_list().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).getReview_list().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void notifyDataSetChanged(List<Comment> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_comment_group, null);
        }
        if (groupPosition==0){
            (ViewHolders.get(convertView, R.id.view_listitem_comment_child_divide)).setVisibility(View.GONE);
        }else{
            (ViewHolders.get(convertView, R.id.view_listitem_comment_child_divide)).setVisibility(View.VISIBLE);
        }

        if (isExpanded) {
            ((ImageView) ViewHolders.get(convertView, R.id.img_listitem_comment_icon)).setImageResource(R.mipmap.ic_arrow_up);
        } else {
            ((ImageView) ViewHolders.get(convertView, R.id.img_listitem_comment_icon)).setImageResource(R.mipmap.ic_arrow_down);
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_name)).setText(dataList.get(groupPosition).getStatus_name());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_comment_child, null);
        }
//        ViewHolders.get(convertView, R.id.view_listitem_comment_child_divide).setVisibility(childPosition == 0 ? View.GONE : View.VISIBLE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_desc)).setText(dataList.get(groupPosition).getReview_list().get(childPosition).getContent());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_date)).setText(dataList.get(groupPosition).getReview_list().get(childPosition).getAddtime());
        switch (Integer.parseInt(dataList.get(groupPosition).getReview_list().get(childPosition).getScore())) {
            case 1://好评
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setText("好评");
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setTextColor(context.getResources().getColor(R.color.green_style_51c12d));
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.mipmap.good), null, null, null);
                break;
            case 2://中评
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setText("中评");
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setTextColor(context.getResources().getColor(R.color.orange_f19149));
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.mipmap.normal), null, null, null);
                break;
            case 3://差评
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setText("差评");
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setTextColor(context.getResources().getColor(R.color.red_e70719));
                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_status)).setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.mipmap.bad), null, null, null);
                break;
        }

        ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_imgs)).setVisibility(dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().size() > 0 ? View.VISIBLE : View.GONE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info1)).setVisibility(View.INVISIBLE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info2)).setVisibility(View.INVISIBLE);
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info3)).setVisibility(View.INVISIBLE);
        for (int i = 0; i < dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().size(); i++) {
            if (i == 0) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info1)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info1));
            }
            if (i == 1) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info2)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info2));
            }
            if (i == 2) {
                ((ImageView) ViewHolders.get(convertView, R.id.img_project_info3)).setVisibility(View.VISIBLE);
                MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().get(i).getSmall(), (ImageView) ViewHolders.get(convertView, R.id.img_project_info3));
            }
        }

        final ArrayList<String> img_urls = new ArrayList<String>();
        for (int i = 0; i < dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().size(); i++) {
            img_urls.add(HttpConstant.IMAGEADDRESS + dataList.get(groupPosition).getReview_list().get(childPosition).getImgs().get(i).getNormal());
        }
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(context,
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
                context.startActivity(intentImage);
            }
        });
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(context,
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
                context.startActivity(intentImage);
            }
        });
        ((ImageView) ViewHolders.get(convertView, R.id.img_project_info3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(context,
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
                context.startActivity(intentImage);
            }
        });

        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_comment_child_del)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintDialog == null) {
                    hintDialog = new HintDialog(context);
                    hintDialog.setTitleText("确定删除该条评论？");
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
                        params.put("id", dataList.get(groupPosition).getReview_list().get(childPosition).getId());
                        analyzeJson.requestData(HttpConstant.CommentDel, params, FragmentComment.COMMENT_DEL);
                    }
                });
                hintDialog.show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
