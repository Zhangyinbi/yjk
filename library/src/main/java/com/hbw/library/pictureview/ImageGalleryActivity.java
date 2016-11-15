package com.hbw.library.pictureview;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.view.View;

import com.hbw.library.BaseActivity;
import com.hbw.library.R;

import java.util.ArrayList;

/**
 * @author Yangbang
 * @ClassName ImageGalleryActivity
 * @description 查看图片activity
 * @date 2015年5月14日
 */
public class ImageGalleryActivity extends BaseActivity implements
        OnPageChangeListener, PhotoViewAttacher.OnPhotoTapListener {
    public static final String EXTRA_URLS = "EXTRA_URLS";// ArrayList图片url对应key
    public static final String EXTRA_FILE_PATHS = "EXTRA_FILE_PATHS";// ArrayList图片缓存路径对应key
    public static final String EXTRA_INDEX = "EXTRA_INDEX";// index对象key
    public static final String IMAGE_ADDRESS = "IMAGE_ADDRESS";// 图片对应的服务器开头地址
    public static final String EXTRA_TITLE = "EXTRA_TITLE";// 界面标题，默认不传为"图片预览"
    private ViewPager pager;
    private ArrayList<String> urls;
    private ArrayList<String> filePaths;
    private int currentIndex;
    private GalleryPagerAdapter adapter;
    private String imageAddress;
    private String title;

    private void selectPage(int currentIndex) {
        titleBar.setLeftText(currentIndex + 1 + "/" + urls.size());
        if (pager.getCurrentItem() != currentIndex) {
            pager.setCurrentItem(currentIndex);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {
        selectPage(arg0);
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
    }

    public void clickButton(View v) {
        onBackPressed();
    }

    @Override
    protected void initWidget() {
        title = getIntent().getStringExtra(EXTRA_TITLE);
        if (title != null) {
            titleBar.setTitleText(title);
        } else {
            titleBar.setTitleText("图片预览");
        }
        titleBar.setLeftImgDefaultBack(this);
        pager = (HackyViewPager) findViewById(R.id.pager);

        imageAddress = getIntent().getStringExtra(IMAGE_ADDRESS);
        urls = getIntent().getStringArrayListExtra(EXTRA_URLS);
        filePaths = getIntent().getStringArrayListExtra(EXTRA_FILE_PATHS);
        currentIndex = getIntent().getIntExtra(EXTRA_INDEX, 0);

        adapter = new GalleryPagerAdapter(this, urls, filePaths, this, imageAddress);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);
        selectPage(currentIndex);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_picture;
    }
}
