package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AssistAsBuiltRecords;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/11/19.s
 */
public class AssistAsBuiltRecordsAdapter extends AbsBaseAdapter<AssistAsBuiltRecords> {

    private Context context;
    private ArrayList<String> listImgs;
    private DeleteRecordListener deleteRecordListener;
    private HintDialog hintDialog;
    private DeleteOnclick deleteOnclick;
    private int is_jgstatus;


    public AssistAsBuiltRecordsAdapter(Context context, List<AssistAsBuiltRecords> dataList) {
        super(dataList);
        this.context = context;
    }

    public AssistAsBuiltRecordsAdapter(Context context, List<AssistAsBuiltRecords> dataList, DeleteRecordListener deleteRecordListener) {
        super(dataList);
        this.context = context;
        this.deleteRecordListener = deleteRecordListener;
        deleteOnclick = new DeleteOnclick();
    }

    public AssistAsBuiltRecordsAdapter(Context context, List<AssistAsBuiltRecords> dataList, DeleteRecordListener deleteRecordListener,int is_jgstatus) {
        super(dataList);
        this.context = context;
        this.deleteRecordListener = deleteRecordListener;
        deleteOnclick = new DeleteOnclick();
        this.is_jgstatus = is_jgstatus;
    }


    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        v = View.inflate(context, R.layout.list_item_assist_as_built_records, null);
        final TextView listview_as_text = (TextView) v.findViewById(R.id.listview_as_text);
        final LinearLayout listview_as_LinearLayout = (LinearLayout) v.findViewById(R.id.listview_as_LinearLayout);
        final TextView listview_as_isshow = (TextView) v.findViewById(R.id.listview_as_isshow);
        TextView listview_as_time = (TextView) v.findViewById(R.id.listview_as_time);
        TextView tv_delete_built_record = (TextView) v.findViewById(R.id.tv_delete_built_record);
	    if (is_jgstatus==1){
		    tv_delete_built_record.setVisibility(View.GONE);
	    }else{
		    tv_delete_built_record.setOnClickListener(deleteOnclick);
	    }
        tv_delete_built_record.setTag(getDataList().get(position).getId());
        ImageView as_img1 = (ImageView) v.findViewById(R.id.as_img1);
        ImageView as_img2 = (ImageView) v.findViewById(R.id.as_img2);
        ImageView as_img3 = (ImageView) v.findViewById(R.id.as_img3);
        ImageView as_img4 = (ImageView) v.findViewById(R.id.as_img4);
        ImageView as_img5 = (ImageView) v.findViewById(R.id.as_img5);
        ImageView as_img6 = (ImageView) v.findViewById(R.id.as_img6);
        ImageView as_img7 = (ImageView) v.findViewById(R.id.as_img7);
        ImageView as_img8 = (ImageView) v.findViewById(R.id.as_img8);
        ImageView as_img9 = (ImageView) v.findViewById(R.id.as_img9);

        ImageView[] imgs = {as_img1, as_img2, as_img3, as_img4, as_img5, as_img6, as_img7, as_img8, as_img9};

        listview_as_text.setText(getDataList().get(position).getContent());
        listview_as_time.setText("上传时间:" + getDataList().get(position).getAddtime());


        //异步获取textview的行数
        listview_as_text.post(new Runnable() {
            @Override
            public void run() {
                //字符长度
                double textlengh = listview_as_text.getTextSize() * getDataList().get(position).getContent().length();
                //字符间距
                double textSacleX = listview_as_text.getTextScaleX() * getDataList().get(position).getContent().length();

                if (textlengh + textSacleX > listview_as_text.getWidth() * 2) {//超过2行显示
                    listview_as_LinearLayout.setVisibility(View.VISIBLE);
                    //默认不展开
                    listview_as_isshow.setTag(false);

                    listview_as_isshow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean flag = (boolean) v.getTag();
                            if (!flag) {//为未展开状态 点击展开
                                v.setTag(true);
                                listview_as_text.setMaxLines(Integer.MAX_VALUE);
                                listview_as_text.setEllipsize(null);
                                listview_as_isshow.setText("收起");
                            } else {//展开状态 点击收起
                                v.setTag(false);
                                listview_as_text.setMaxLines(2);
                                listview_as_text.setEllipsize(TextUtils.TruncateAt.END);
                                listview_as_isshow.setText("点击展开");
                            }
                        }
                    });
                }
            }
        });


        listImgs = new ArrayList<String>();
        for (int i = 0; i < getDataList().get(position).getImgs().size(); i++) {
            imgs[i].setVisibility(ImageView.VISIBLE);
            imgs[i].setTag(i);
            listImgs.add(getDataList().get(position).getImgs().get(i).getNormal());
            imgs[i].setOnClickListener(new ImageOnclick());
            MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + getDataList().get(position).getImgs().get(i).getSmall(), imgs[i]);
        }
        ((LinearLayout) as_img1.getParent().getParent()).setTag(listImgs);
        return v;
    }

    /**
     * 点击图片预览
     */
    class ImageOnclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            ArrayList<String> imgs = (ArrayList<String>) ((LinearLayout) v.getParent().getParent()).getTag();
            Intent intentImage = new Intent(context.getApplicationContext(), ImageGalleryActivity.class);
            intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, imgs);
            intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, imgs);
            intentImage.putExtra(ImageGalleryActivity.IMAGE_ADDRESS, HttpConstant.IMAGEADDRESS);
            intentImage.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
            context.startActivity(intentImage);
        }
    }

    /**
     * 点击删除
     */
    class DeleteOnclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final String id = (String) v.getTag();
            hintDialog = new HintDialog(context)
                    .setTitleText("确定删除此条记录?");

            hintDialog.setConfirmListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteRecordListener.deleteRecod(id);
                    hintDialog.dismiss();
                }
            }).setCancelListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hintDialog.dismiss();
                }
            });

            hintDialog.show();
        }
    }

    public interface DeleteRecordListener {
        void deleteRecod(String id);
    }


}
