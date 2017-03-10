package com.youjuke.miprojectmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.youjuke.miprojectmanager.R;

/**
 * 拨打电话
 * 
 * @author Youjk
 *
 */
public class TeleActivity extends Activity {
	String tel = "";
	TextView activity_tel_num_tv;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new BitmapDrawable());
		setContentView(R.layout.activity_tele);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
		activity_tel_num_tv = (TextView) this
				.findViewById(R.id.activity_tel_num_tv);
		setFinishOnTouchOutside(false);
		tel = getIntent().getStringExtra("tel");
		if (TextUtils.isEmpty(tel)) {
			// 400-920-1616
			activity_tel_num_tv.setText("拨打电话400-710-3366");
		} else {
			activity_tel_num_tv.setText("拨打电话" + tel);
		}
	}

	public void clickButton(View v) {
		switch (v.getId()) {
		case R.id.textView_dialog_cancel:
			finish();
			break;

		case R.id.textView_dialog_sure:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			if (TextUtils.isEmpty(tel)) {
				intent.setData(Uri.parse("tel:400-710-3366"));
			} else {
				intent.setData(Uri.parse("tel:" + tel));
			}
			startActivity(intent);
			new Thread().stop();
			break;
		}
	}
}
