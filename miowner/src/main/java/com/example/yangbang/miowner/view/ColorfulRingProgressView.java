package com.example.yangbang.miowner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.yangbang.miowner.R;

import java.util.ArrayList;

public class ColorfulRingProgressView extends View {
    private float mPercent = 0;
    private float mStrokeWidth;
    private int mBgColor = 0xffe1e1e1;
    private float mStartAngle = 0;
    private int mFgColorStart = 0xffffe400;
    private int mFgColorEnd = 0xffff4800;

    private LinearGradient mShader;
    private Context mContext;
    private RectF mOval;
    private Paint mPaint;

    public ArrayList<XYZ> mList;//施工节点坐标集合
    private Float r;//半径
    private int distance = 100;//进度圈离边界的距离
    private int stage = 50;//施工阶段离进度圈的距离
    private int divide = 6;//将圆等分成几份
    private float share;//将一个圆等分成n时每份所占的度数

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mTextBound;
    private Paint mTextPaint;

    private float startPercent = 0;//上一次的进度
    private int flag = 0;//标记是第一次设置percent的值

    private Drawable[] drawablesCompleted = {
            getResources().getDrawable(R.mipmap.yqcompleted),
            getResources().getDrawable(R.mipmap.azcompleted),
            getResources().getDrawable(R.mipmap.jdcjcompleted),
            getResources().getDrawable(R.mipmap.sdcompleted),
            getResources().getDrawable(R.mipmap.ntcompleted),
            getResources().getDrawable(R.mipmap.mgwgcompleted)};

    private Drawable[] drawablesNostart = {
            getResources().getDrawable(R.mipmap.yqnostart),
            getResources().getDrawable(R.mipmap.aznostart),
            getResources().getDrawable(R.mipmap.jdcjnostart),
            getResources().getDrawable(R.mipmap.sdnostart),
            getResources().getDrawable(R.mipmap.ntnostart),
            getResources().getDrawable(R.mipmap.mgwgnostart)};

    public ColorfulRingProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ColorfulRingProgressView, 0, 0);
        try {
            mBgColor = a.getColor(R.styleable.ColorfulRingProgressView_bgColor, 0xffe1e1e1);
            mFgColorEnd = a.getColor(R.styleable.ColorfulRingProgressView_fgColorEnd, 0xffff4800);
            mFgColorStart = a.getColor(R.styleable.ColorfulRingProgressView_fgColorStart, 0xffffe400);
            mPercent = a.getFloat(R.styleable.ColorfulRingProgressView_percent, 0);
            mStartAngle = a.getFloat(R.styleable.ColorfulRingProgressView_startAngle, 0) + 270;
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.ColorfulRingProgressView_strokeWidth, dp2px(21));
            mTitleText = a.getString(R.styleable.ColorfulRingProgressView_mTitleText);
            mTitleTextColor = a.getColor(R.styleable.ColorfulRingProgressView_mTitleTextColor, Color.RED);
            mTitleTextSize = a.getDimensionPixelSize(R.styleable.ColorfulRingProgressView_mTitleTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        startPercent = mPercent;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        share = (float) (100) / divide;

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTitleTextSize);
        mTextPaint.setColor(mTitleTextColor);
        // 计算描绘字体所需要的范围
        mTextPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);
    }

    private int dp2px(float dp) {
        return (int) (mContext.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;// 获得圆心的x坐标
        int centerY = getHeight() / 2;// 获得圆心的y坐标
        /** step1、画背景圈 */
        mPaint.setShader(null);
        mPaint.setColor(mBgColor);
        // 设定阴影 (柔边, X轴位移, Y轴位移, 阴影颜色)
        mPaint.setShadowLayer(2, 3, 3, 0x8e1b1a);
        canvas.drawArc(mOval, 0, 360, false, mPaint);
        /** step2、画进度圈 */
        mPaint.setShader(mShader);
        canvas.drawArc(mOval, mStartAngle, mPercent * 3.6f, false, mPaint);

        /** step3、画百分比 */
        mPaint.setAntiAlias(true);// 消除锯齿
        //计算文字的起始点
        //计算baseline（参考文献） http://blog.csdn.net/sirnuo/article/details/21165665
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float descentY = centerY + fontMetrics.descent;
        // 设置字符串的左边在屏幕的中间
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //根据要显示的百分比与圆圈进度的百分比之比来换算要显示的百分比
        canvas.drawText((int) (getPercent() * (Float.parseFloat(getmTitleText()) / startPercent)) + "", centerX, descentY, mTextPaint);//100

        /** step3、画施工节点 */
        for (int i = 0; i < mList.size(); i++) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesNostart[i]).getBitmap(), (float) mList.get(i).x - drawablesNostart[i].getIntrinsicWidth() * 0.5f, (float) mList.get(i).y - drawablesNostart[i].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        }

        if (getPercent() >= share * 0 && getPercent() < share * 1) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        } else if (getPercent() >= share * 1 && getPercent() < share * 2) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[3]).getBitmap(), (float) mList.get(3).x - drawablesCompleted[3].getIntrinsicWidth() * 0.5f, (float) mList.get(3).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        } else if (getPercent() >= share * 2 && getPercent() < share * 3) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[3]).getBitmap(), (float) mList.get(3).x - drawablesCompleted[3].getIntrinsicWidth() * 0.5f, (float) mList.get(3).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[4]).getBitmap(), (float) mList.get(4).x - drawablesCompleted[4].getIntrinsicWidth() * 0.5f, (float) mList.get(4).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        } else if (getPercent() >= share * 3 && getPercent() < share * 4) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[3]).getBitmap(), (float) mList.get(3).x - drawablesCompleted[3].getIntrinsicWidth() * 0.5f, (float) mList.get(3).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[4]).getBitmap(), (float) mList.get(4).x - drawablesCompleted[4].getIntrinsicWidth() * 0.5f, (float) mList.get(4).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[5]).getBitmap(), (float) mList.get(5).x - drawablesCompleted[5].getIntrinsicWidth() * 0.5f, (float) mList.get(5).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        } else if (getPercent() >= share * 4 && getPercent() < share * 5) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[3]).getBitmap(), (float) mList.get(3).x - drawablesCompleted[3].getIntrinsicWidth() * 0.5f, (float) mList.get(3).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[4]).getBitmap(), (float) mList.get(4).x - drawablesCompleted[4].getIntrinsicWidth() * 0.5f, (float) mList.get(4).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[5]).getBitmap(), (float) mList.get(5).x - drawablesCompleted[5].getIntrinsicWidth() * 0.5f, (float) mList.get(5).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[0]).getBitmap(), (float) mList.get(0).x - drawablesCompleted[0].getIntrinsicWidth() * 0.5f, (float) mList.get(0).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        } else if (getPercent() >= share * 5 && getPercent() <= share * 6) {
            canvas.save();
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[2]).getBitmap(), (float) mList.get(2).x - drawablesCompleted[2].getIntrinsicWidth() * 0.5f, (float) mList.get(2).y - drawablesCompleted[0].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[3]).getBitmap(), (float) mList.get(3).x - drawablesCompleted[3].getIntrinsicWidth() * 0.5f, (float) mList.get(3).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[4]).getBitmap(), (float) mList.get(4).x - drawablesCompleted[4].getIntrinsicWidth() * 0.5f, (float) mList.get(4).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[5]).getBitmap(), (float) mList.get(5).x - drawablesCompleted[5].getIntrinsicWidth() * 0.5f, (float) mList.get(5).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[0]).getBitmap(), (float) mList.get(0).x - drawablesCompleted[0].getIntrinsicWidth() * 0.5f, (float) mList.get(0).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.drawBitmap(((BitmapDrawable) drawablesCompleted[1]).getBitmap(), (float) mList.get(1).x - drawablesCompleted[1].getIntrinsicWidth() * 0.5f, (float) mList.get(1).y - drawablesCompleted[1].getIntrinsicHeight() * 0.5f, mPaint);
            canvas.restore();
        }
    }

    //求一个圆划分成n等份，r是半径,(x,y)为圆心坐标的所有坐标值
    private ArrayList<XYZ> onCoordinatePoints(int n, double r, double x, double y) {
        ArrayList<XYZ> mXYZs = new ArrayList<XYZ>();
        // n为分成了多少份
        for (int i = 1; i <= n; i++) {
            double rad = (2 * Math.PI / n) * (double) i;
            rad += Math.PI / 2f;
            double mx = r * Math.cos(rad);
            double my = r * Math.sin(rad);
            //加上圆心坐标
            mXYZs.add(new XYZ(mx + x, my + y));
        }
        return mXYZs;
    }

    class XYZ {
        public double x;
        public double y;

        public XYZ(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateOval();

        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
    }

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        if(flag == 0){
            flag++;
            startPercent = mPercent;
        }
        refreshTheLayout();
    }

    public String getmTitleText() {
        return mTitleText;
    }

    public void setmTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
        refreshTheLayout();
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        mPaint.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    private void updateOval() {
        //修改：增加50距离，来画外圈节点
        int xp = getPaddingLeft() + distance + getPaddingRight() + distance;
        int yp = getPaddingBottom() + distance + getPaddingTop() + distance;
        mOval = new RectF(getPaddingLeft() + distance + mStrokeWidth, getPaddingTop() + distance + mStrokeWidth,
                getPaddingLeft() + distance + (getWidth() - xp) - mStrokeWidth,
                getPaddingTop() + distance + (getHeight() - yp) - mStrokeWidth);
        //计算r
        r = (mOval.right - mOval.left) / 2;
        //增加半径长度，画节点
        mList = onCoordinatePoints(divide, r + stage, r + mOval.left, r + mOval.top);
    }

    public void setStrokeWidthDp(float dp) {
        this.mStrokeWidth = dp2px(dp);
        mPaint.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    public void refreshTheLayout() {
        invalidate();
        requestLayout();
    }

    public int getFgColorStart() {
        return mFgColorStart;
    }

    public void setFgColorStart(int mFgColorStart) {
        this.mFgColorStart = mFgColorStart;
        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
        refreshTheLayout();
    }

    public int getFgColorEnd() {
        return mFgColorEnd;
    }

    public void setFgColorEnd(int mFgColorEnd) {
        this.mFgColorEnd = mFgColorEnd;
        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
        refreshTheLayout();
    }


    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle + 270;
        refreshTheLayout();
    }
}
