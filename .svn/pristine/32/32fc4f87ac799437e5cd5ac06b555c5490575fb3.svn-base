package com.hbw.library.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.R;

/**
 * @author Yangbang
 * @ClassName TitleBar
 * @description 标题栏目
 * @date 2015年6月17日
 */
public class TitleBar extends RelativeLayout {
    private ImageView leftImage;
    private TextView leftText;
    private ImageView rightImage0;
    private ImageView rightImage1;
    private ImageView rightImage2;
    private ImageView right_image0_alert_dot;
    private TextView rightText0;
    private TextView rightText1;
    private TextView titleTv;
    private TextView right_text0_alert_dot;
    private RelativeLayout left_igame_relative;
    private RelativeLayout right_igame0_relative;
    private RelativeLayout right_igame1_relative;
    private RelativeLayout right_igame2_relative;

    protected LayoutInflater mInflater;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        View titleBarView = mInflater.inflate(R.layout.title_bar, this);
        leftImage = (ImageView) titleBarView.findViewById(R.id.left_image);
        rightImage0 = (ImageView) titleBarView.findViewById(R.id.right_image0);
        rightImage1 = (ImageView) titleBarView.findViewById(R.id.right_image1);
        rightImage2 = (ImageView) titleBarView.findViewById(R.id.right_image2);
        right_image0_alert_dot = (ImageView) titleBarView.findViewById(R.id.right_image0_alert_dot);
        leftText = (TextView) titleBarView.findViewById(R.id.left_text);
        rightText0 = (TextView) titleBarView.findViewById(R.id.right_text0);
        rightText1 = (TextView) titleBarView.findViewById(R.id.right_text1);
        titleTv = (TextView) titleBarView.findViewById(R.id.titleTv);
        right_text0_alert_dot = (TextView) titleBarView.findViewById(R.id.right_text0_alert_dot);
        left_igame_relative = (RelativeLayout) titleBarView.findViewById(R.id.left_igame_relative);
        right_igame0_relative = (RelativeLayout) titleBarView.findViewById(R.id.right_igame0_relative);
        right_igame1_relative = (RelativeLayout) titleBarView.findViewById(R.id.right_igame1_relative);
        right_igame2_relative = (RelativeLayout) titleBarView.findViewById(R.id.right_igame2_relative);
    }

    /**
     * 设置左边图片资源
     *
     * @param resId
     */
    public void setLeftImgRes(int resId) {
        if (resId != 0) {
            left_igame_relative.setVisibility(View.VISIBLE);
            leftImage.setVisibility(View.VISIBLE);
            leftImage.setImageResource(resId);
        } else {
            left_igame_relative.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左边图片点击事件监听
     *
     * @param l
     */
    public void setLeftImgListener(OnClickListener l) {
        if (l != null) {
            left_igame_relative.setOnClickListener(l);
        }
    }

    /**
     * 设置左边文字资源
     *
     * @param text
     */
    public void setLeftText(String text) {
        if (!TextUtils.isEmpty(text)) {
            leftText.setVisibility(View.VISIBLE);
            leftText.setText(text);
        }
    }

    /**
     * 设置左边文字点击事件监听
     *
     * @param l
     */
    public void setLeftTextListener(OnClickListener l) {
        if (l != null) {
            leftText.setOnClickListener(l);
        }
    }

    /**
     * 设置左边图片默认为返回键
     *
     * @param activity
     */
    public void setLeftImgDefaultBack(final Activity activity) {
        left_igame_relative.setVisibility(View.VISIBLE);
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(R.drawable.back);
        left_igame_relative.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    /**
     * 设置左图并默认设置为返回键
     *
     * @param activity 上下文环境
     * @param resId    左图资源文件
     */
    public void setLeftImgDefaultBack(final Activity activity, int resId) {
        left_igame_relative.setVisibility(View.VISIBLE);
        leftImage.setVisibility(View.VISIBLE);
        leftImage.setImageResource(resId);
        left_igame_relative.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param text
     */
    public void setTitleText(String text) {
        if (!TextUtils.isEmpty(text)) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(text);
        }
    }

    /**
     * 设置右边文字1资源
     *
     * @param text
     */
    public void setRightText0(String text) {
        if (!TextUtils.isEmpty(text)) {
            rightText0.setVisibility(View.VISIBLE);
            rightText0.setText(text);
        }
    }

    /**
     * 设置右边文字1点击事件监听
     *
     * @param l
     */
    public void setRightText0Listener(OnClickListener l) {
        if (l != null) {
            rightText0.setOnClickListener(l);
        }
    }

    /**
     * 设置右边文字2资源
     *
     * @param text
     */
    public void setRightText1(String text) {
        if (!TextUtils.isEmpty(text)) {
            rightText1.setVisibility(View.VISIBLE);
            rightText1.setText(text);
        }
    }

    /**
     * 设置右边文字2点击事件监听
     *
     * @param l
     */
    public void setRightText1Listener(OnClickListener l) {
        if (l != null) {
            rightText1.setOnClickListener(l);
        }
    }

    /**
     * 设置右边第一图资源
     *
     * @param resId
     */
    public void setRightImg0Res(int resId) {
        if (resId != 0) {
            right_igame0_relative.setVisibility(View.VISIBLE);
            rightImage0.setVisibility(View.VISIBLE);
            rightImage0.setImageResource(resId);
        } else {
            right_igame0_relative.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边第一图点击事件监听
     *
     * @param l
     */
    public void setRightImg0Listener(OnClickListener l) {
        if (l != null) {
            right_igame0_relative.setOnClickListener(l);
        }
    }

    public ImageView getRightImg0Dot() {
        return right_image0_alert_dot;
    }

    /**
     * 设置右边第二图资源
     *
     * @param resId
     */
    public void setRightImg1Res(int resId) {
        if (resId != 0) {
            right_igame1_relative.setVisibility(View.VISIBLE);
            rightImage1.setVisibility(View.VISIBLE);
            rightImage1.setImageResource(resId);
        } else {
            right_igame1_relative.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边第二图点击事件监听
     *
     * @param l
     */
    public void setRightImg1Listener(OnClickListener l) {
        if (l != null) {
            right_igame1_relative.setOnClickListener(l);
        }
    }

    /**
     * 设置右边第三图资源
     *
     * @param resId
     */
    public void setRightImg2Res(int resId) {
        if (resId != 0) {
            right_igame2_relative.setVisibility(View.VISIBLE);
            rightImage2.setVisibility(View.VISIBLE);
            rightImage2.setImageResource(resId);
        } else {
            right_igame2_relative.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边第三图点击事件监听
     *
     * @param l
     */
    public void setRightImg2Listener(OnClickListener l) {
        if (l != null) {
            right_igame2_relative.setOnClickListener(l);
        }
    }

    /**
     * 设置右边红点数字提示资源
     *
     * @param resId
     */
    public void setRightText0AlertDotNumBg(int resId) {
        if (resId != 0) {
            right_igame0_relative.setVisibility(View.VISIBLE);
            rightImage0.setVisibility(View.VISIBLE);
//            right_text0_alert_dot.setVisibility(View.VISIBLE);
            right_text0_alert_dot.setBackgroundResource(resId);
        } else {
            right_text0_alert_dot.setVisibility(View.GONE);
        }
    }

    public void setRightText0AlertDotNum(String number) {
        if (number != null) {
            right_igame0_relative.setVisibility(View.VISIBLE);
            rightImage0.setVisibility(View.VISIBLE);
            right_text0_alert_dot.setVisibility(View.VISIBLE);
            int num = Integer.parseInt(number);
            if (num > 99) {
                right_text0_alert_dot.setText("99");
            } else {
                right_text0_alert_dot.setText(number);
            }
            if (num <= 0) {
                right_text0_alert_dot.setVisibility(View.GONE);
            }
        } else {
            right_text0_alert_dot.setVisibility(View.GONE);
        }
    }

    /**
     * 取得右边红字提示控件
     *
     * @return
     */
    public TextView getRightText0AlertDot() {
        return right_text0_alert_dot;
    }

}
