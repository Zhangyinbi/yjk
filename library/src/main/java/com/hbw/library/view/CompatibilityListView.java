package com.hbw.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 * @author hebiwen
 *
 */
public class CompatibilityListView extends ListView {

	public CompatibilityListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CompatibilityListView(Context context) {
		super(context);
	}

	public CompatibilityListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	// 该自定义控件只是重写了ListView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套GridView也是同样的道理
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
