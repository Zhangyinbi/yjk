package com.example.yangbang.miowner.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerMyMessage;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.AvatarSelectTool;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.PopwChange;
import com.hbw.library.ActivityManager;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.net.HttpFileAsycTask;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by user on 2016/1/4.用户设置
 */
public class OwnerSettingsActivity extends BaseActivity implements View.OnClickListener {

	private ImageView owner_settings_img = null;
	private LinearLayout owner_settings_icon = null;
	private LinearLayout owner_settings_phone = null;
	private LinearLayout owner_settings_modify_pasword = null;
	private LinearLayout owner_settings_exit = null;
	private OwnerMyMessage mOwnerMyMessage = null;
	private LinearLayout owner_settings_nickname;
	private TextView tv_settings_nick_name;

	private AvatarSelectTool avatarSelect;
	private PopwChange popwChange;
	private Map<String, File> files = null;

	@Override
	protected void initWidget() {
		titleBar.setTitleText("设置");
		titleBar.setLeftImgDefaultBack(this);
		avatarSelect = new AvatarSelectTool(this);
		files = new HashMap<String, File>();

		owner_settings_img = (ImageView) findViewById(R.id.owner_settings_img);
		owner_settings_icon = (LinearLayout) findViewById(R.id.owner_settings_icon);
		owner_settings_phone = (LinearLayout) findViewById(R.id.owner_settings_phone);
		owner_settings_modify_pasword = (LinearLayout) findViewById(R.id.owner_settings_modify_pasword);
		owner_settings_exit = (LinearLayout) findViewById(R.id.owner_settings_exit);
		owner_settings_nickname = (LinearLayout) findViewById(R.id.owner_settings_nickname);
		tv_settings_nick_name = (TextView) findViewById(R.id.tv_settings_nick_name);


		owner_settings_icon.setOnClickListener(this);
		owner_settings_phone.setOnClickListener(this);
		owner_settings_modify_pasword.setOnClickListener(this);
		owner_settings_exit.setOnClickListener(this);
		owner_settings_nickname.setOnClickListener(this);

		loadData();

	}

	private void loadData() {
		HashMap<String, String> mMap = new HashMap<String, String>();
		mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.PersonnalMessage, mMap, 0x4399);
	}

	@Override
	protected void initData() {
		setIsShowAnimation(false);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_owner_settings;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.owner_settings_icon: //修改头像
				showPop();
				break;
			case R.id.owner_settings_nickname: //修改昵称
				Intent intent = new Intent(OwnerSettingsActivity.this, ModifyNickNameActivity.class);
				intent.putExtra("nickName", mOwnerMyMessage.getNikename());
				startActivity(intent);
				break;
			case R.id.owner_settings_phone://修改手机
				Intent intent2 = new Intent(OwnerSettingsActivity.this, OwnerRelevanceMobileActivity.class);
				intent2.putExtra("mobile1", mOwnerMyMessage.getMobile_2());
				intent2.putExtra("mobile2", mOwnerMyMessage.getMobile_3());
				startActivity(intent2);
				break;
			case R.id.owner_settings_modify_pasword: //修改密码
				startActivity(new Intent(OwnerSettingsActivity.this, OwnerChangePasswordActivity.class));
				break;
			case R.id.owner_settings_exit://退出登录
				HashMap<String, String> mMap = new HashMap<String, String>();
				mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
				analyzeJson.requestData(HttpConstant.LOGOUT_URL, mMap, REQUEST_SUCCESS);
				break;
		}
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				//清除用户信息
				ToastUtil.show(this, "退出成功");
				MyApplication.getApp().setOwnerUser(null);
				SPUtils.remove(this, AppFlag.OWNER_PASSWORD);
				//退出所有activity
				ActivityManager.getActivityManager().finish(null);
				startActivity(new Intent(OwnerSettingsActivity.this, OwnerLoginActivity.class));
				break;
			case 0x4399:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				mOwnerMyMessage = gson.fromJson(data.data, OwnerMyMessage.class);
				MyApplication.imageLoader.displayImage(mOwnerMyMessage.getHead_img(), owner_settings_img);
				tv_settings_nick_name.setText(mOwnerMyMessage.getNikename());
				//如果是主手机号 就允许关联手机号 如果是从手机号 就不许关联手机号
				if (MyApplication.getApp().getOwnerUser().getIs_display() == 1) {
					owner_settings_phone.setVisibility(View.VISIBLE);
				} else {
					owner_settings_phone.setVisibility(View.GONE);
				}
				break;
			case 0x4599:
				ToastUtil.show(this, "上传成功");
				ResponseSucceedData headUrlData = (ResponseSucceedData) msg.obj;
				try {
					JSONObject jsonObject = new JSONObject(headUrlData.data);
					MyApplication.getApp().getOwnerUser().setHead_img(jsonObject.getString("head_img"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
		}
		return super.handleMessage(msg);
	}

	private void showPop() {
		if (popwChange == null) {
			popwChange = new PopwChange(this, clickListener);
		}
		popwChange.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_transparent));
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.7f;
		getWindow().setAttributes(lp);
		popwChange.setOutsideTouchable(true);
		popwChange.showAtLocation(findViewById(R.id.owner_settings_exit),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		popwChange.setOnDismissListener(dismissListener);
	}

	// 对popwChange的控件进行监听
	private View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.textView_pop_chang_camera:
					//请求权限  #0001
					PermissionGen.with(OwnerSettingsActivity.this)
							.addRequestCode(100)
							.permissions(
									Manifest.permission.CAMERA,
									Manifest.permission.WRITE_EXTERNAL_STORAGE,
									Manifest.permission.READ_EXTERNAL_STORAGE)
							.request();
					break;
				case R.id.textView_pop_chang_photo:
					//请求权限  #0001
					PermissionGen.with(OwnerSettingsActivity.this)
							.addRequestCode(101)
							.permissions(
									Manifest.permission.WRITE_EXTERNAL_STORAGE,
									Manifest.permission.READ_EXTERNAL_STORAGE)
							.request();
					break;
				case R.id.textView_pop_window_cancel:
					break;
			}
			popwChange.dismiss();
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (popwChange != null && popwChange.isShowing()) {
			popwChange.dismiss();
		}
	}

	PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
		@Override
		public void onDismiss() {
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			lp.alpha = 1.0f;
			getWindow().setAttributes(lp);
		}
	};

	// 获取相册里返回的图片地址
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK
				&& requestCode == AvatarSelectTool.CROP_COMPLETE_CODE) {// 相册返回的裁剪图片
			if (avatarSelect == null) {
				Toast.makeText(this, "图片剪辑发生错误，请重试。", Toast.LENGTH_SHORT).show();
				return;
			}
			Bitmap bmp = null;
			bmp = BitmapFactory.decodeFile(avatarSelect.getTempImgFile()
					.getAbsolutePath());
			if (bmp != null) {
				owner_settings_img.setImageBitmap(bmp);
				files.put(avatarSelect.getTempImgFile().getAbsolutePath(), avatarSelect.getTempImgFile());
				//上传图片
				params = new HashMap<String, String>();
				params.put("token", MyApplication.getApp().getOwnerUser().getToken());
				Map<String, Map<String, File>> map = new HashMap<String, Map<String, File>>();
				map.put("files[]", files);
				HttpFileAsycTask asycTask = new HttpFileAsycTask(map, OwnerSettingsActivity.this, params, handler, 0x4599);
				asycTask.setIsCloseActivity(false);
				asycTask.execute(HttpConstant.HEAD_PORTRAIT_URL);
			} else {
				avatarSelect.setAvatar(null);
			}

		}
		if (resultCode == RESULT_CANCELED) {
			avatarSelect.getTempImgFile().delete();
			avatarSelect.setAvatar(null);
		}
		/** 从相册选择 **/
		if (requestCode == AvatarSelectTool.AVTATR_ALBUM_CROP_CODE) {
			if (null != data) {
				avatarSelect.startPhotoZoom(data.getData());
			}else{
				avatarSelect.setAvatar(null);
			}
		}
		/** 拍摄 **/
		if (requestCode == AvatarSelectTool.AVATAR_CAMERA_CROP_CODE) {
			avatarSelect.startPhotoZoom(Uri.fromFile(avatarSelect.getTempImgFile()));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}

	//请求拍照权限成功时调用 #0001
	@PermissionSuccess(requestCode = 100)
	private void  onRequestCameraSuccess(){
		avatarSelect.getCamera();
	}

	//请求拍照权限失败时调用 #0001
	@PermissionFail(requestCode = 100)
	private void  onRequestCameraFail(){
		ToastUtil.show(OwnerSettingsActivity.this,"请允许拍照权限");
	}

	//请求读写SD卡权限成功时调用 #0001
	@PermissionSuccess(requestCode = 101)
	private void  onRequestWRSDSuccess(){
		avatarSelect.getPhotoAlbum();
	}

	//请求读写SD卡权限失败时调用 #0001
	@PermissionFail(requestCode = 101)
	private void  onRequestWRSDFail(){
		ToastUtil.show(OwnerSettingsActivity.this,"请允许读取内存卡权限");
	}
}
