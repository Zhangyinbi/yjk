package com.example.yangbang.miowner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时button
 *
 * @author hebiwen
 */
public class TimeButton extends Button implements OnClickListener {

    private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "s后重新获取";
    private String textbefore = "获取验证码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private EditText editText;//获取用户输入的手机号码
    private int defaultColor;//默认的字体颜色
    private int changeColor;//变化时的颜色
    private TimeButtonListener mTimeButtonListener;//回调事件，判断号码正确之后的回调事件
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();
    private Context context;

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.defaultColor = context.getResources().getColor(R.color.Owner_white_ffffff);
        this.changeColor = context.getResources().getColor(R.color.Owner_grey_959595);
        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(time / 1000 + textafter);
            TimeButton.this.setTextColor(changeColor);
            TimeButton.this.setBackground(getResources().getDrawable(R.color.Owner_grey_d2d2d2));
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                TimeButton.this.setTextColor(defaultColor);
                TimeButton.this.setBackground(getResources().getDrawable(R.color.Owner_red_e76270));
                clearTimer();
            }
        }

        ;
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                L.e("TimeButton", time / 1000 + "");
                han.sendEmptyMessage(0x01);
            }
        };
    }

    public void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (editText == null) {
            if (mOnclickListener != null)
                mOnclickListener.onClick(v);
            initTimer();
            this.setText(time / 1000 + textafter);
            this.setEnabled(false);
            t.schedule(tt, 0, 1000);
            mTimeButtonListener.Back();
        } else {
            Tools.closeKeybord(editText, context);
            if (mOnclickListener != null)
                mOnclickListener.onClick(v);
            if (Tools.isMobileNO(editText.getText().toString())) {
                initTimer();
                this.setText(time / 1000 + textafter);
                this.setEnabled(false);
                t.schedule(tt, 0, 1000);
                mTimeButtonListener.Back();
            } else {
                ToastUtil.showShort(context, "请输入正确的手机号码");
            }
        }

    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (MyApplication.TimeButtonMap == null)
            MyApplication.TimeButtonMap = new HashMap<String, Long>();
        MyApplication.TimeButtonMap.put(TIME, time);
        MyApplication.TimeButtonMap.put(CTIME, System.currentTimeMillis());
        clearTimer();
        L.e("TimeButton", "onDestroy");
    }

    /**
     * 和activity的onCreate()方法同步
     */
    public void onCreate(Bundle bundle) {
        L.e("TimeButton", MyApplication.TimeButtonMap + "");
        if (MyApplication.TimeButtonMap == null)
            return;
        if (MyApplication.TimeButtonMap.size() <= 0)// 这里表示没有上次未完成的计时
            return;
        long time = System.currentTimeMillis() - MyApplication.TimeButtonMap.get(CTIME)
                - MyApplication.TimeButtonMap.get(TIME);
        MyApplication.TimeButtonMap.clear();
        if (time > 0)
            return;
        else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time + textafter);
            this.setEnabled(false);
        }
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        this.setTextColor(defaultColor);
        return this;
    }

    /**
     * 设置用户输入的手机号码的EditText
     */
    public TimeButton setEditText(EditText editText) {
        this.editText = editText;
        return this;
    }

    /**
     * 设置字体默认时的颜色
     */
    public TimeButton setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        return this;
    }

    /**
     * 设置变化时字体的颜色
     */
    public TimeButton setChangeColor(int changeColor) {
        this.changeColor = changeColor;
        return this;
    }

    /**
     * 设置监听事件
     */
    public void setTimeButtonListener(TimeButtonListener mTimeButtonListener) {
        this.mTimeButtonListener = mTimeButtonListener;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }

    /**
     * 回调接口
     */
    public interface TimeButtonListener {
        void Back();
    }
}
