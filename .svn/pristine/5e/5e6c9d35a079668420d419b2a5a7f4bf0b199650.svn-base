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

/**
 * Created by mwy on 2016/6/28.
 */
public class ModifyNickNameActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener, TextWatcher {

	private EditText ed_nick_name;
	private String nickName;
	private Drawable drawable;

	@Override
	protected void initWidget() {
		titleBar.setLeftImgDefaultBack(this);
		titleBar.setTitleText("修改昵称");
		titleBar.setRightText0("确认");
		titleBar.setRightText0Listener(this);
		ed_nick_name = (EditText) findViewById(R.id.ed_nick_name);
		ed_nick_name.setOnTouchListener(this);
		ed_nick_name.addTextChangedListener(this);

		nickName = getIntent().getStringExtra("nickName");
		if (nickName != null && !"".equals(nickName.trim())) {
			ed_nick_name.setText(nickName);
		}
		initEditTextRightDrawable();
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_modify_nick_name;
	}


	@Override
	//清空文本实现
	public boolean onTouch(View v, MotionEvent event) {
		// getCompoundDrawables()得到一个长度为4的数组，分别表示左上右下四张图片
		Drawable drawable = ed_nick_name.getCompoundDrawables()[2];
		//如果右边没有图片，不再处理
		if (drawable == null)
			return false;
		//如果不是按下事件，不再处理
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return false;
		if (event.getX() > ed_nick_name.getWidth()
				- ed_nick_name.getPaddingRight()
				- drawable.getIntrinsicWidth()) {
			ed_nick_name.setText("");
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.right_text0:
				if (ed_nick_name.getText().toString().trim().length() == 0) {
					ToastUtil.show(ModifyNickNameActivity.this, "昵称不能为空");
				} else if (ed_nick_name.getText().toString().trim().length() < 2) {
					ToastUtil.show(ModifyNickNameActivity.this, "昵称不能少于2个字符");
				} else if (nickName == ed_nick_name.getText().toString().trim()) {//如果没修改昵称
					return;
				} else {
					nickName = ed_nick_name.getText().toString().trim();
					params.put("token", MyApplication.getApp().getOwnerUser().getToken());
					params.put("nikename", nickName);
					analyzeJson.requestData(HttpConstant.EditNickname, params, REQUEST_SUCCESS);
				}
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
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (nickName != null && !nickName.equals(s.toString())) {
			ed_nick_name.setTextColor(Color.parseColor("#000000"));
		}
		//如果文本框为空 隐藏清空图片
		if (ed_nick_name.getText() == null || "".equals(ed_nick_name.getText().toString())){
			ed_nick_name.setCompoundDrawables(null,null,null,null);
		}else{
			if (drawable == null){
				initEditTextRightDrawable();
			}
			ed_nick_name.setCompoundDrawables(null,null,drawable,null);
		}
	}

	private void initEditTextRightDrawable(){
		drawable = getResources().getDrawable(R.mipmap.x);
		//必须设置图片大小，否则不显示
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
	}
}
