package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.hbw.library.utils.ViewHolders;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.Image;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @FileName: com.xiaomizhuang.buildcaptain.adapter.ImgGridViewAdapter.java
 * @author: Yangbang
 * @date: 2016-01-21 13:50
 */
public class ImgGridViewAdapter extends AbsBaseAdapter<Image> {
    private Context context;
    private ArrayList<String> nomarlImags;

    public ImgGridViewAdapter(List<Image> dataList, Context context) {
        super(dataList);
        this.context = context;
        nomarlImags = new ArrayList<String>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_img, null);
        }
//        ImageView imv = (ImageView) convertView.findViewById(R.id.listitem_img);
        convertView.setOnClickListener(new ImageOnclick(position));
//        imv.setTag(position);
        MyApplication.imageLoader.displayImage(HttpConstant.IMAGES_ROOT_URL + dataList.get(position).getSmall(), ((ImageView) ViewHolders.get(convertView, R.id.listitem_img)));

        //获取正常图片集合
        if (nomarlImags.size() == 0 && dataList.size() > 0) {
            for (Image image : dataList) {
                nomarlImags.add(image.getNormal());
            }
        }

        return convertView;
    }


    /**
     * 点击图片预览
     */
    class ImageOnclick implements View.OnClickListener {
        int position;

        public ImageOnclick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
//            int position = (int) v.getTag();

            Intent intentImage = new Intent(context.getApplicationContext(), ImageGalleryActivity.class);
            intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, nomarlImags);
            intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, nomarlImags);
            intentImage.putExtra(ImageGalleryActivity.IMAGE_ADDRESS, HttpConstant.IMAGEADDRESS);
            intentImage.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
            context.startActivity(intentImage);
        }
    }

}
