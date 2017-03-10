package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.anyan.client.model.ClientModel;
import com.anyan.client.sdk.JDeviceBasic;
import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.SiteMonitoringAdapter;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.DividerGridItemDecoration;
import com.example.yangbang.miowner.view.PopTelphone;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.L;
import com.hbw.library.view.CustomProgressDialog;

/**
 * Created by mwy on 2016/6/22.
 */
public class SiteMonitoringActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

	private static final int LOGIN_SUCCESS = 88;
	private static final int LOGIN_FAIL = 89;
	private CustomProgressDialog mCustomProgressDialog;
	RecyclerView rv_site_monitoring;
	SwipeRefreshLayout srl_site_monitoring;
	SiteMonitoringAdapter siteMonitoringAdapter;
	ClientModel clientModel;
	PopTelphone popTelphone;

	@Override
	protected void initData() {

	}

	@Override
	protected void initWidget() {
		titleBar.setTitleText("现场监管");
		titleBar.setLeftImgDefaultBack(this);
		titleBar.setRightImg0Res(R.mipmap.phone);
		titleBar.setRightImg0Listener(this);
		initRecyclerView();
		initRefreshLayout();
	}

	private void initRecyclerView() {
		rv_site_monitoring = (RecyclerView)findViewById(R.id.rv_site_monitoring);
		showProgressDialog();
		clientModel = ClientModel.getClientModel();
		clientModel.SetCompanyMode("xiaomizhuang.com");//设置企业ID
		initMonitoringList();
		loginAnyan();
	}

	private void initRefreshLayout() {
		srl_site_monitoring = (SwipeRefreshLayout) findViewById(R.id.srl_site_monitoring);
		srl_site_monitoring.setColorSchemeResources(R.color.white);//设置旋转箭头颜色
		srl_site_monitoring.setProgressBackgroundColorSchemeResource(R.color.red);//设置背景颜色
		srl_site_monitoring.setOnRefreshListener(this);
//        srl_site_monitoring.setEnabled(false);
	}

	private void loginAnyan() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (clientModel.Login("12345678910", "123456")) {
					handler.sendEmptyMessage(LOGIN_SUCCESS);
				} else {
					handler.sendEmptyMessage(LOGIN_FAIL);
				}
			}
		}).start();
	}

	private void initMonitoringList() {
		siteMonitoringAdapter = new SiteMonitoringAdapter(this, clientModel.getMyDeviceList());
		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
		rv_site_monitoring.setLayoutManager(layoutManager);
		rv_site_monitoring.setAdapter(siteMonitoringAdapter);
		rv_site_monitoring.addItemDecoration(new DividerGridItemDecoration(this));
		siteMonitoringAdapter.setOnItemClickListener(new SiteMonitoringAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position, JDeviceBasic jDeviceBasic) {
				if (position < ClientModel.getClientModel().getMyDeviceList().size()) {
					Intent intent = new Intent(getApplicationContext(), DevicePlayActivity.class);
					ClientModel.getClientModel().SetCurDevice(JDeviceBasic.DeviceOwner.Device_My, position);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_site_monitoring;
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case LOGIN_SUCCESS:
				L.i("YANGBANG", "clientModel.getShareDeviceList().Size-->" + clientModel.getShareDeviceList().size());
				L.i("YANGBANG", "clientModel.getMyDeviceList().Size-->" + clientModel.getMyDeviceList().size());
				siteMonitoringAdapter.notifyDataSetChanged(clientModel.getMyDeviceList());
				srl_site_monitoring.setRefreshing(false);
				dismissProgressDialog();
				break;
			case LOGIN_FAIL:
				Toast.makeText(getApplicationContext(), clientModel.mLastErrorDesc, Toast.LENGTH_SHORT).show();
				dismissProgressDialog();
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	public void onRefresh() {
		loginAnyan();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.right_igame0_relative:
				if (popTelphone == null) {
					popTelphone = new PopTelphone(SiteMonitoringActivity.this, MyApplication.getApp().getOwnerUser());
				}
				popTelphone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
				break;
		}
	}

	/**
	 * 显示一个ProgressDialog
	 */
	private void showProgressDialog() {
		if (mCustomProgressDialog == null) {
			mCustomProgressDialog = new CustomProgressDialog(this,
					com.hbw.library.R.drawable.anim_progressr);
			mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
		}
		if (!mCustomProgressDialog.isShowing())
			mCustomProgressDialog.show();
	}

	/**
	 * 取消当前显示的ProgressDialog
	 */
	private void dismissProgressDialog() {
		if (mCustomProgressDialog != null
				|| mCustomProgressDialog.isShowing()) {
			mCustomProgressDialog.dismiss();
		}
	}
}
