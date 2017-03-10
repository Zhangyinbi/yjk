package com.example.yangbang.miowner.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

/**
 * Created by user on 2016/1/5.关联手机号
 */
public class OwnerRelevanceMobileActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {
	private EditText edMobile1;
	private EditText edMobile2;
	private String mobile1;
	private String mobile2;
	private Drawable drawable;

	private void assignViews() {
		edMobile1 = (EditText) findViewById(R.id.ed_mobile1);
		edMobile2 = (EditText) findViewById(R.id.ed_mobile2);

		edMobile1.setOnTouchListener(this);
		edMobile2.setOnTouchListener(this);
		edMobile1.addTextChangedListener(new MyTextWatcher(edMobile1, mobile1));
		edMobile2.addTextChangedListener(new MyTextWatcher(edMobile2, mobile2));

		if (mobile1 != null && !"".equals(mobile1.trim())) {
			edMobile1.setText(mobile1);
		}
		if (mobile2 != null && !"".equals(mobile2.trim())) {
			edMobile2.setText(mobile2);
		}
	}


	@Override
	protected void initWidget() {
		titleBar.setTitleText("关联手机号");
		titleBar.setRightText0("确认");
		titleBar.setRightText0Listener(this);
		titleBar.setLeftImgDefaultBack(this);
		assignViews();
	}

	@Override
	protected void initData() {
		mobile1 = getIntent().getStringExtra("mobile1");
		mobile2 = getIntent().getStringExtra("mobile2");
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_owner_relevance_mobile;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.right_text0:
				String m1 = edMobile1.getText().toString().trim();
				String m2 = edMobile2.getText().toString().trim();
				if(!"".equals(m1) && !Tools.isCellPhoneNumber(m1)
						|| !"".equals(m2) && !Tools.isCellPhoneNumber(m2)){
					ToastUtil.show(OwnerRelevanceMobileActivity.this,"请输入正确的手机号码!");
					return;
				}
				params.put("token", MyApplication.getApp().getOwnerUser().getToken());
				params.put("mobile_1", edMobile1.getText().toString().trim());
				params.put("mobile_2", edMobile2.getText().toString().trim());
				analyzeJson.requestData(HttpConstant.QUERY_ASSOCIATED_UMBER_URL, params, REQUEST_SUCCESS);

				break;
			default:
				break;
		}
	}


	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ToastUtil.show(getApplicationContext(), "修改成功");
				finishActivity();
				break;
			case ERROR:
				ToastUtil.show(getApplicationContext(), "修改失败");
				break;
			default:
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	//清空文本实现
	public boolean onTouch(View v, MotionEvent event) {
		// getCompoundDrawables()得到一个长度为4的数组，分别表示左上右下四张图片
		Drawable drawable = ((EditText) v).getCompoundDrawables()[2];
		//如果右边没有图片，不再处理
		if (drawable == null)
			return false;
		//如果不是按下事件，不再处理
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return false;
		if (event.getX() > ((EditText) v).getWidth()
				- ((EditText) v).getPaddingRight()
				- drawable.getIntrinsicWidth()) {
			((EditText) v).setText("");
		}
		return false;
	}


	private void initEditTextRightDrawable() {
		drawable = getResources().getDrawable(R.mipmap.x);
		//必须设置图片大小，否则不显示
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
	}


	private class MyTextWatcher implements TextWatcher {
		private EditText editText;
		private String text;

		public MyTextWatcher(EditText editText, String text) {
			this.editText = editText;
			this.text = text;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			if (text != null && !text.equals(s.toString())) {
				editText.setTextColor(Color.parseColor("#000000"));
			}
			//如果文本框为空 隐藏清空图片
			if (editText.getText() == null || "".equals(editText.getText().toString())) {
				editText.setCompoundDrawables(null, null, null, null);
			} else {
				if (drawable == null) {
					initEditTextRightDrawable();
				}
				editText.setCompoundDrawables(null, null, drawable, null);
			}
		}
	}
}
