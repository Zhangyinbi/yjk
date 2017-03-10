package com.hbw.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hbw.library.utils.L;

/**
 * Created by Yangbang on 2015/9/9.
 */
public class CustomImageView extends ImageView implements Runnable, Handler.Callback {
    //分段颜色
    private static final int[] SECTION_COLORS = {Color.GREEN, Color.YELLOW,
            Color.RED};
    Handler handler;
    Thread thread;
    private Paint paint;//画笔
    private Context context;
    private int startAngle;//开始角度
    private int center;//中心
    private int convertInnerCircle; //转换后的内圆半径
    private int convertRingWidth;   //转换后的圆环宽度

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**他
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    /**
     * 圆环内圆半径
     */
    private float roundInnerRadius = 13;

    /**
     * 圆环的宽度
     */
    private float roundWidth = 4;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;

    /**
     * 自动旋转
     */
    private boolean isAutoRotate = false;


    /**
     * 变化颜色
     */
    private int changeColors;

    /**
     * 是否变色
     */
    private boolean isDisColors = false;


    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initData();
    }

    private void initData() {
//        setRoundColor(context.getResources().getColor(R.color.red_c30d22));
//        setChangeColors(context.getResources().getColor(R.color.green_style_51c12d));
        setRoundColor(Color.RED);
        setChangeColors(Color.GREEN);
        this.paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
        this.handler = new Handler(this);
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        center = getWidth() / 2;
        convertInnerCircle = dip2px(context, roundInnerRadius); //内圆半径
        convertRingWidth = dip2px(context, roundWidth);   //圆环宽度
//        float section = progress / max;
        // 第一种方法绘制圆环
        //绘制内圆
//        this.paint.setARGB(255, 138, 43, 226);
//        this.paint.setStrokeWidth(2);
//        canvas.drawCircle(center, center, innerCircle, this.paint);

        //绘制外圆
//        this.paint.setARGB(255, 138, 43, 226);
//        this.paint.setStrokeWidth(2);
//        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);

        RectF rect2 = new RectF(center - (convertInnerCircle + 1 + convertRingWidth / 2), center - (convertInnerCircle + 1 + convertRingWidth / 2), center + (convertInnerCircle + 1 + convertRingWidth / 2), center + (convertInnerCircle + 1 + convertRingWidth / 2));


        //绘制圆环
//        this.paint.setColor(roundColor);
//        this.paint.setStrokeWidth(convertRingWidth);
//        canvas.drawArc(rect2, 270, 360 * progress / max, false, paint);
//        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

        if (progress < max) {
            this.paint.setStrokeWidth(convertRingWidth);
            this.paint.setStrokeCap(Paint.Cap.ROUND);
            this.paint.setColor(Color.TRANSPARENT);
            canvas.drawArc(rect2, 0, 360, false, paint);
            this.paint.setColor(Color.BLACK);
            LinearGradient shader = new LinearGradient(center - (convertInnerCircle + 1 + convertRingWidth / 2), center + (convertInnerCircle + 1 + convertRingWidth / 2), center - (convertInnerCircle + 1 + convertRingWidth / 2), center - (convertInnerCircle + 1 + convertRingWidth / 2), SECTION_COLORS
                    , null, Shader.TileMode.MIRROR);
            paint.setShader(shader);
            canvas.drawArc(rect2, 90, (180 * progress / max), false, paint);
            canvas.drawArc(rect2, 90, -(180 * progress / max), false, paint);
        } else {
            this.paint.setShader(null);
            this.paint.setColor(roundColor);
            canvas.drawArc(rect2, 0, 360, false, paint);
            if (isAutoRotate()) {
                //设置ARGB,也可以直接设置color和alpha
//                this.paint.setARGB(200, 127, 255, 212);
                this.paint.setColor(roundColor);
                this.paint.setStrokeWidth(convertRingWidth);
                startAngle += 10;
                //绘制不透明部分
                canvas.drawArc(rect2, 180 + startAngle, 90, false, paint);
                canvas.drawArc(rect2, 0 + startAngle, 90, false, paint);
                //绘制透明部分
//                this.paint.setARGB(30, 127, 255, 212);
                this.paint.setColor(changeColors);
                canvas.drawArc(rect2, 90 + startAngle, 90, false, paint);
                canvas.drawArc(rect2, 270 + startAngle, 90, false, paint);
                if (startAngle == 180) {
                    startAngle = 0;
                }
                invalidate();
            } else {
                if (isDisColors) {
//                    this.paint.setARGB(200, 127, 255, 212);
                    this.paint.setColor(roundColor);
                    this.paint.setStrokeWidth(convertRingWidth);
                    canvas.drawArc(rect2, 0, 360, false, paint);
                    isDisColors = false;
                } else {
//                    this.paint.setARGB(30, 127, 255, 212);
                    this.paint.setColor(changeColors);
                    this.paint.setStrokeWidth(convertRingWidth);
                    canvas.drawArc(rect2, 0, 360, false, paint);
                    isDisColors = true;
                }
            }
        }


        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundInnerRadius() {
        return roundInnerRadius;
    }

    public void setRoundInnerRadius(float roundInnerRadius) {
        this.roundInnerRadius = roundInnerRadius;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getChangeColors() {
        return changeColors;
    }

    public void setChangeColors(int changeColors) {
        this.changeColors = changeColors;
    }

    public synchronized boolean isAutoRotate() {
        return isAutoRotate;
    }

    public synchronized void setIsAutoRotate(boolean isAutoRotate) {
        this.isAutoRotate = isAutoRotate;
        postInvalidate();
    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
//            throw new IllegalArgumentException("progress not less than 0");
            progress = 0;
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public void clear(Canvas canvas) {
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        invalidate();
    }

    @Override
    public void run() {
        while (true) {
            handler.sendEmptyMessage(1);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1://
                if (isAutoRotate() == false) {
                    postInvalidate();
                }
                break;
        }
        return false;
    }
}
