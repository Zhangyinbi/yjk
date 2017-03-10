package com.example.yangbang.miowner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 2015/11/17.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    //从系统中拿默认的list分割线，也可以自己通过shape画
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDrawable;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public MyItemDecoration(Context context, int orientation) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDrawable = ta.getDrawable(0);
//        mDrawable=context.getResources().getDrawable(R.drawable.shape_divider);
        ta.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        Log.i("YANGBANG","onDraw");
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalDivider(c, parent);
//            Log.i("YANGBANG", "onDraw_drawVerticalDivider");
        } else {
            drawHorizontalDivider(c, parent);
//            Log.i("YANGBANG", "onDraw_drawHorizontalDivider");
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        if(mOrientation==HORIZONTAL_LIST){
//            outRect.contains(0,0,mDrawable.getIntrinsicHeight(),0);
//        }else{
//            outRect.contains(0,0,0,mDrawable.getIntrinsicHeight());
//        }
    }

    /**
     * 画横向分割线
     *
     * @param c
     * @param parent
     */
    private void drawHorizontalDivider(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    /**
     * 画纵向分割线
     *
     * @param c
     * @param parent
     */
    private void drawVerticalDivider(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }
}
