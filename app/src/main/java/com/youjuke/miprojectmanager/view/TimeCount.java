package com.youjuke.miprojectmanager.view;


import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

import com.youjuke.miprojectmanager.R;

public class TimeCount extends CountDownTimer {
	Button view;
	Context context;

	public TimeCount(long millisInFuture, long countDownInterval, Button view,
			Context context) {
		super(millisInFuture, countDownInterval);
		this.view = view;
		this.context = context;
	}

	@Override
	public void onFinish() {
		view.setText("重新获取");
		view.setClickable(true);
		view.setBackgroundResource(R.drawable.code_bg_selecter);
	
	}

	@Override
	public void onTick(long millisUntilFinished) {
		view.setClickable(false);
		view.setText(millisUntilFinished / 1000 + "秒");
		view.setTextColor(android.graphics.Color.GREEN);
		// view.setBackgroundResource(R.drawable.bg_getverfication);
	}
}
