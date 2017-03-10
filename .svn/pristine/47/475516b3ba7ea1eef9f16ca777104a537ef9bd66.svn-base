package com.hbw.library.view;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hbw.library.R;

/**
 * 
 * @ClassName DialogPrompt
 * @description 提示对话框
 * @author Yangbang
 * @date 2015年7月2日
 */
public class DialogPrompt extends Dialog {
	Context context;
	TextView dialog_prompt_title;//标题
	TextView dialog_prompt_content;// 内容
	TextView dialog_prompt_left_tv;// 左边按钮
	TextView dialog_prompt_right_tv;// 右按钮

	public DialogPrompt(Context context) {
		super(context, R.style.MyDialogStyle);
		this.context = context;
		this.setContentView(R.layout.dialog_prompt);
		initView();
	}

	private void initView() {
		dialog_prompt_title=(TextView)this.findViewById(R.id.dialog_prompt_title);
		dialog_prompt_content = (TextView) this
				.findViewById(R.id.dialog_prompt_content);
		dialog_prompt_left_tv = (TextView) this
				.findViewById(R.id.dialog_prompt_left_tv);
		dialog_prompt_right_tv = (TextView) this
				.findViewById(R.id.dialog_prompt_right_tv);
		dialog_prompt_title.setVisibility(View.GONE);
	}

	public void setPromptTitle(String title){
		dialog_prompt_title.setVisibility(View.VISIBLE);
		dialog_prompt_title.setText(title);
	}

	/**
	 * 取得提示內容
	 * @return
	 */
	public String getPromptContent(){
		return this.dialog_prompt_content.getText().toString();
	}

	/**
	 * 设置提示对话框提示内容
	 * 
	 * @param content
	 */
	public void setPromptContent(String content) {
		this.dialog_prompt_content.setText(content);
	}

	/**
	 * 设置左边按钮内容
	 * 
	 * @param content
	 */
	public void setLeftBtnContent(String content) {
		dialog_prompt_left_tv.setText(content);
	}

	/**
	 * 设置右边按钮内容
	 * 
	 * @param content
	 */
	public void setRightBtnContent(String content) {
		dialog_prompt_right_tv.setText(content);
	}

	/**
	 * 设置提示对话框取消按钮监听
	 * 
	 * @param listener
	 */
	public void setLeftDialogPrompeListener(
			android.view.View.OnClickListener listener) {
		if (listener != null) {
			dialog_prompt_left_tv.setOnClickListener(listener);
		}
	}

	/**
	 * 设置提示对话框确认按钮监听
	 * 
	 * @param listener
	 */
	public void setRightDialogPrompeListener(
			android.view.View.OnClickListener listener) {
		if (listener != null) {
			dialog_prompt_right_tv.setOnClickListener(listener);
		}
	}

}
