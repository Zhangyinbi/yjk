package com.youjuke.miprojectmanager.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.net.HttpOneFileAsycTask;
import com.hbw.library.pictureview.ImageGalleryActivity;
import com.hbw.library.utils.DensityUtils;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseFragmentActivity;
import com.youjuke.miprojectmanager.entity.CheckBillItem;
import com.youjuke.miprojectmanager.util.HelpClass;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.util.MyApplication;
import com.youjuke.miprojectmanager.view.PopwChange;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


public class CheckBillActivity extends MiBaseFragmentActivity implements OnClickListener, ViewBinder {
	public static final int PHOTORETURNVALUE = 1;
	public static final int CAMERAPHOTOTWO = 2;
	private static int type = 0;
	private static int title_id = 0;
	private String picFileFullName;
	public static final String TAG = "CheckBillActivity  ";
	private mThread myThread;
	private PopwChange popwChange;
	private HashMap<String, String> params;
	private Map<String, File> mFiles = new HashMap<String, File>();
	public static final int REQUEST_SUCCESS_DELETE_IMAGES = 6666;// 删除图片成功
	private AlertDialog alert = null;
	private boolean isShowDelete = false;
	private String msg_id;
	private List<TextView> tabList = null;
	private ListView mListView;
	private List<CheckBillItem> cbList;
	private CheckBillAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initWidget() {
		titleBar.setTitleText("验收单");
		titleBar.setLeftImgDefaultBack(this, R.drawable.back);
		msg_id = getIntent().getStringExtra("msg_id");
		if (!isShowDelete) {
			titleBar.setRightText0("删除");
		} else {
			titleBar.setRightText0("完成");
		}
		titleBar.setRightText0Listener(this);
		mListView = (ListView) findViewById(R.id.check_bill_listView);
		cbList = new ArrayList<CheckBillItem>();
		adapter = new CheckBillAdapter(cbList, this);
		mListView.setAdapter(adapter);
		initTab();

		//默认选中交底
		changeTab(0);


	}

	@Override
	protected void initData() {
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_check_bill;
	}

	/**
	 * 初始化选项卡
	 */
	private void initTab() {
		tabList = new ArrayList<TextView>();
		TextView tv_jiaodi_yanshou = (TextView) findViewById(R.id.tv_jiaodi_yanshou);
		tv_jiaodi_yanshou.setOnClickListener(this);
		TextView tv_shuidian_yanshou = (TextView) findViewById(R.id.tv_shuidian_yanshou);
		tv_shuidian_yanshou.setOnClickListener(this);
		TextView tv_nimu_yanshou = (TextView) findViewById(R.id.tv_nimu_yanshou);
		tv_nimu_yanshou.setOnClickListener(this);
		TextView tv_zengbu_yanshou = (TextView) findViewById(R.id.tv_zengbu_yanshou);
		tv_zengbu_yanshou.setOnClickListener(this);
		TextView tv_youqi_yanshou = (TextView) findViewById(R.id.tv_youqi_yanshou);
		tv_youqi_yanshou.setOnClickListener(this);
		TextView tv_jungong_yanshou = (TextView) findViewById(R.id.tv_jungong_yanshou);
		tv_jungong_yanshou.setOnClickListener(this);
		tabList.add(tv_jiaodi_yanshou);
		tabList.add(tv_shuidian_yanshou);
		tabList.add(tv_nimu_yanshou);
		tabList.add(tv_youqi_yanshou);
		tabList.add(tv_jungong_yanshou);
		tabList.add(tv_zengbu_yanshou);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.textView_pop_chang_camera:
				//请求权限  #0001
				PermissionGen.with(CheckBillActivity.this)
						.addRequestCode(100)
						.permissions(
								Manifest.permission.CAMERA,
								Manifest.permission.WRITE_EXTERNAL_STORAGE,
								Manifest.permission.READ_EXTERNAL_STORAGE)
						.request();
				popwChange.dismiss();
				break;
			case R.id.textView_pop_chang_photo:
				//请求权限  #0001
				PermissionGen.with(CheckBillActivity.this)
						.addRequestCode(101)
						.permissions(
								Manifest.permission.WRITE_EXTERNAL_STORAGE,
								Manifest.permission.READ_EXTERNAL_STORAGE)
						.request();
				popwChange.dismiss();
				break;
			case R.id.textView_pop_window_cancel:
				popwChange.dismiss();
				break;
			case R.id.right_text0:
				if (!isShowDelete) {
					isShowDelete = !isShowDelete;
					titleBar.setRightText0("完成");
				} else {
					titleBar.setRightText0("删除");
					isShowDelete = !isShowDelete;
				}
				//刷新界面
				loadDate(type);
				break;
			case R.id.tv_jiaodi_yanshou:
				changeTab(0);
				break;
			case R.id.tv_shuidian_yanshou:
				changeTab(1);
				break;
			case R.id.tv_nimu_yanshou:
				changeTab(2);
				break;
			case R.id.tv_youqi_yanshou:
				changeTab(3);
				break;
			case R.id.tv_jungong_yanshou:
				changeTab(4);
				break;
			case R.id.tv_zengbu_yanshou:
				changeTab(5);
				break;
			default:
				break;
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				cbList = gson.fromJson(data.data, new TypeToken<List<CheckBillItem>>() {
				}.getType());
				adapter.notifyDataSetChanged(cbList);
				break;
			case REQUEST_SUCCESS_DELETE_IMAGES:
				//刷新界面
				loadDate(type);
				break;
			case BaseActivity.TIMEOUT:
			case BaseActivity.ERROR:
				cbList.clear();
				adapter.notifyDataSetChanged(cbList);
				break;
			default:
				break;
		}
		return super.handleMessage(msg);
	}

	// 相册返回
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PHOTORETURNVALUE && resultCode == RESULT_OK) {
			Uri pathImage = data.getData();
			if (!TextUtils.isEmpty(pathImage.getAuthority())) {
				Cursor cursor = getContentResolver().query(pathImage,
						new String[]{MediaStore.Images.Media.DATA}, null,
						null, null);
				if (null == cursor) {
					return;
				}
				cursor.moveToFirst();
				picFileFullName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
				cursor.close();
				L.i(TAG + "onActivityResult  picFileFullName", picFileFullName);
				//上传图片
				mFiles.clear();
				if (picFileFullName != null) {
					File file = new File(picFileFullName);
					mFiles.put(picFileFullName, file);
					params = new HashMap<String, String>();
					params.put("token", HttpConstant.token);
					params.put("type", type + "");
					params.put("title_id", title_id + "");
					params.put("baoming_id", getIntent().getStringExtra("baoming_id"));
					if (msg_id != null) {
						params.put("msg_id", msg_id);
					}
					Map<String, Map<String, File>> files = new HashMap<String, Map<String, File>>();
					files.put("files[]", mFiles);
					HttpOneFileAsycTask asycTask = new HttpOneFileAsycTask(files, CheckBillActivity.this, params, new mBack());
					asycTask.execute(HttpConstant.ManagerProjectUploadJdysimgs);
				}
			}
		} else if (requestCode == CAMERAPHOTOTWO && resultCode == RESULT_OK) {
			if (!TextUtils.isEmpty(picFileFullName)) {
				//上传图片
				mFiles.clear();
				if (picFileFullName != null) {
					File file = new File(picFileFullName);
					mFiles.put(picFileFullName, file);
					params = new HashMap<String, String>();
					params.put("token", HttpConstant.token);
					params.put("type", type + "");
					params.put("title_id", title_id + "");
					params.put("baoming_id", getIntent().getStringExtra("baoming_id"));
					if (msg_id != null) {
						params.put("msg_id", msg_id);
					}
					Map<String, Map<String, File>> files = new HashMap<String, Map<String, File>>();
					files.put("files[]", mFiles);
					HttpOneFileAsycTask asycTask = new HttpOneFileAsycTask(files, CheckBillActivity.this, params, new mBack());
					asycTask.execute(HttpConstant.ManagerProjectUploadJdysimgs);
				}
			}
		}
	}

	class mBack implements HttpOneFileAsycTask.MyAsyncBack {

		@Override
		public void mAsyncBack() {
			//刷新界面
			loadDate(type);
		}

	}

	private void showPop() {
		if (popwChange == null) {
			popwChange = new PopwChange(this, this);
		}
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.7f;
		getWindow().setAttributes(lp);
		popwChange.setBackgroundDrawable(this.getResources().getDrawable(
				R.drawable.shape_transparent));
		popwChange.setAnimationStyle(R.style.AnimBottom);
		popwChange.setOutsideTouchable(true);
		popwChange.setFocusable(true);
		popwChange.showAtLocation(this.findViewById(R.id.patrol_record_add_ll),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		popwChange.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});
	}
	//请求拍照权限成功时调用 #0001
	@PermissionSuccess(requestCode = 100)
	private void cameraPhoto() {
		myThread = new mThread();
		myThread.setDaemon(true);
		myThread.start();
	}

	//请求拍照权限失败时调用 #0001
	private void onRequestCameraFail(){
		ToastUtil.show(CheckBillActivity.this,"请允许拍照权限");
	}

	//请求读写内存卡权限成功时调用 #0001
	@PermissionSuccess(requestCode = 101)
	private void invokPhoto() {
		Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PHOTORETURNVALUE);
	}

	//请求读写SD卡权限失败时调用 #0001
	@PermissionFail(requestCode = 101)
	private void  onRequestWRSDFail(){
		ToastUtil.show(CheckBillActivity.this,"请允许读取内存卡权限");
	}

	@Override
	public boolean setViewValue(View view, Object data,
	                            String textRepresentation) {
		if (view instanceof ImageView && data instanceof Bitmap) {
			ImageView i = (ImageView) view;
			i.setImageBitmap((Bitmap) data);
			return true;
		}
		return false;
	}


	class mThread extends Thread {
		@Override
		public void run() {
			super.run();
			String state = Environment.getExternalStorageState();
			if (state.equals(Environment.MEDIA_MOUNTED)) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File outDir = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				if (!outDir.exists()) {
					outDir.mkdirs();
				}
				File outFile = new File(outDir, System.currentTimeMillis()
						+ ".jpg");
				picFileFullName = outFile.getAbsolutePath();
				L.i(TAG + "picFileFullName", picFileFullName);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,
						PHOTORETURNVALUE);
				startActivityForResult(intent, CAMERAPHOTOTWO);
			} else {
				ToastUtil.show(getApplicationContext(), "手机相机发生故障");
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (popwChange != null && popwChange.isShowing()) {
			popwChange.dismiss();
		}
		tabList.clear();
	}

	class CheckBillAdapter extends AbsBaseAdapter<CheckBillItem> {
		private Context context;
		private ArrayList<String> listImgs = new ArrayList<String>();
		public static final String TAG = "CheckBillAdapter";

		public CheckBillAdapter(List<CheckBillItem> dataList, Context context) {
			super(dataList);
			this.dataList = dataList;
			this.context = context;
		}


		@Override
		public View getView(final int positions, View convertView, ViewGroup parent) {
			final int mPosition = positions;
			View v = View.inflate(context, R.layout.item_check_bill, null);
			LinearLayout mLinearLayout = (LinearLayout) v.findViewById(R.id.item_check_bill_linearLayout);
			TextView mTime = (TextView) v.findViewById(R.id.item_check_bill_time);
			TextView tv_item_title = (TextView) v.findViewById(R.id.tv_item_title);
			final ImageView item_check_bill_add = (ImageView) v.findViewById(R.id.item_check_bill_add);

			item_check_bill_add.setTag(dataList.get(positions).getTitle_id());
			tv_item_title.setText(dataList.get(positions).getTitle());

			if (dataList.get(positions).getAddtime() != null) {
				mTime.setText(dataList.get(positions).getAddtime());
			}
			// 点击添加图片
			item_check_bill_add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					title_id = (int) v.getTag();
					showPop();
				}
			});
			if (dataList.get(mPosition).getImgs().size() >= 1) {
				item_check_bill_add.setVisibility(ImageView.GONE);
			}
			listImgs.clear();
			for (int i = 0; i < dataList.get(mPosition).getImgs().size(); i++) {
				if (!(dataList.get(mPosition).getImgs().get(i).getNormal() == null
						|| dataList.get(mPosition).getImgs().get(i).getNormal().equals(""))) {
					listImgs.add(dataList.get(mPosition).getImgs().get(i).getNormal());
				}
			}
			for (int i = 0; i < dataList.get(mPosition).getImgs().size(); i++) {
				//只显示一张图片
				if (i >= 1) {
					break;
				}
				final int image_position = i;
				View mView = View.inflate(context, R.layout.griditem_updatepic, null);
				ImageView imageView_record = (ImageView) mView.findViewById(R.id.imageView_record);
				RelativeLayout rl_image_container = (RelativeLayout) mView.findViewById(R.id.rl_image_container);
				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl_image_container.getLayoutParams();
				params.setMargins(DensityUtils.dp2px(context, 3.0f), 0, 0, 0);
				rl_image_container.setLayoutParams(params);

				MyApplication.imageLoader.displayImage(
						HttpConstant.IMAGEADDRESS + dataList.get(mPosition).getImgs().get(i).getSmall(), imageView_record);
				// 设置点击图片跳转到图片浏览
				imageView_record.setOnClickListener(new ImageOnclic(image_position, listImgs));
				ImageView imageView_delete = (ImageView) mView.findViewById(R.id.imageView_delete);
				if (!isShowDelete) {
					imageView_delete.setVisibility(ImageView.GONE);
				}
				imageView_delete.setOnClickListener(new MyOnclick(dataList.get(mPosition).getImgs().get(i).getId(), type));
				imageView_delete.setTag(dataList.get(positions).getTitle_id());
				if (mLinearLayout.getChildCount() == 1) {
					mLinearLayout.addView(mView, 0);
				} else {
					mLinearLayout.addView(mView, mLinearLayout.getChildCount() - 1);
				}
			}
			return v;
		}

	}

	//图片预览
	class ImageOnclic implements OnClickListener {

		private int Mimage_position;
		private ArrayList<String> list;

		public ImageOnclic(int image_position, ArrayList<String> list) {
			this.Mimage_position = image_position;
			this.list = new ArrayList<String>(list);
		}

		@Override
		public void onClick(View v) {
			Intent intentImage = new Intent(CheckBillActivity.this, ImageGalleryActivity.class);
			intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, list);
			intentImage.putExtra(ImageGalleryActivity.IMAGE_ADDRESS, HttpConstant.IMAGEADDRESS);
			intentImage.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, list);
			intentImage.putExtra(ImageGalleryActivity.EXTRA_INDEX, Mimage_position);
			startActivity(intentImage);
		}
	}

	//删除图片
	class MyOnclick implements OnClickListener {
		private int image_id;
		private int type;

		public MyOnclick(int image_id, int type) {
			this.image_id = image_id;
			this.type = type;
		}

		@Override
		public void onClick(View v) {
			title_id = (int) v.getTag();
			initDialog(image_id, type);
			alert.show();
		}

	}

	private void initDialog(final int id, final int type) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 设置点击图片删除图片
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(HelpClass.TOKEN, HttpConstant.token);
				map.put("baoming_id", getIntent().getStringExtra("baoming_id"));
				map.put("image_id", id + "");
				map.put("type", type + "");
				map.put("title_id", title_id + "");
				analyzeJson.requestData(HttpConstant.DeleteBaomingJdysimgs, map, REQUEST_SUCCESS_DELETE_IMAGES);
			}
		});
		alert = builder.create();
	}

	/**
	 * tab切换效果
	 *
	 * @param selectedPosition 选中的下标
	 */
	private void changeStyle(int selectedPosition) {
		for (TextView textView : tabList) {
			textView.setTextColor(getResources().getColor(R.color.gray_7d7d7d));
			textView.setBackgroundResource(R.drawable.selector_red_bg);
		}
		TextView selectedTv = tabList.get(selectedPosition);
		selectedTv.setTextColor(getResources().getColor(R.color.white));
		selectedTv.setBackgroundResource(R.drawable.shape_red_solid_stroke);
	}

	/**
	 * 切换tab
	 *
	 * @param selectedPosition 选中的下标
	 */
	private void changeTab(int selectedPosition) {
		type = selectedPosition;
		changeStyle(selectedPosition);
		//如果在删除状态下 切换标签   清除删除状态
		if (isShowDelete) {
			isShowDelete = false;
			titleBar.setRightText0("删除");
		}
		loadDate(selectedPosition);
	}

	/**
	 * 根据类型 加载对应数据
	 *
	 * @param selectedPosition
	 */
	private void loadDate(int selectedPosition) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put(HelpClass.TOKEN, HttpConstant.token);
		map.put("baoming_id", getIntent().getStringExtra("baoming_id"));
		if (msg_id != null) {
			map.put("msg_id", msg_id);
		}
		map.put("type", selectedPosition + "");
		analyzeJson.requestData(HttpConstant.ManagerProjectJdysimgs, map, REQUEST_SUCCESS);
	}

}
