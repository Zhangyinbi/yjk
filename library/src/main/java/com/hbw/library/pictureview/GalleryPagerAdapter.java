package com.hbw.library.pictureview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GalleryPagerAdapter extends PagerAdapter {
    private ArrayList<String> urls;
    private ArrayList<String> filePaths;
    private PhotoViewAttacher.OnPhotoTapListener onPhotoTapListener;
    private String imageAddress;

    public GalleryPagerAdapter(Context context, ArrayList<String> urls,
                               ArrayList<String> filePaths, PhotoViewAttacher.OnPhotoTapListener onPhotoTapListener,String imageAddress) {
        super();
        this.onPhotoTapListener = onPhotoTapListener;
        this.urls = urls;
        this.filePaths = filePaths;
        this.imageAddress = imageAddress;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PhotoView photoView = (PhotoView) object;
        ((BitmapDrawable) photoView.getDrawable()).getBitmap().recycle();
        container.removeView(photoView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        try {
            String url_img = urls.get(position).startsWith("http") ? urls
                    .get(position) : imageAddress
                    + urls.get(position);
            Log.i("http", position + url_img);
            photoView.setImageInfo(new URL(url_img), filePaths.get(position));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (onPhotoTapListener != null) {
            photoView.setOnPhotoTapListener(onPhotoTapListener);
        }
        container.addView(photoView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        return photoView;
    }
}
