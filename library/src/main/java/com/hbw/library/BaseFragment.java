package com.hbw.library;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.L;
import com.hbw.library.view.CustomProgressDialog;

import java.util.HashMap;

/**
 * BaseFragment
 *
 * @FileName: com.hbw.library.BaseFragment.java
 * @author: Yangbang
 * @date: 2015-12-17 14:38
 * <p>
 * 工程:
 * #0001    mwy     解决fragment 在内存重启时 发生的页面重叠问题
 */
public abstract class BaseFragment extends Fragment implements Handler.Callback {

	private static final String TAG = "tag_activity";
	/**
	 * 单条请求数据成功标示
	 */
	public static final int REQUEST_SUCCESS = 8888;// 请求成功

	/**
	 * 网络超时
	 */
	public static final int TIMEOUT = -1;//网络超时

	/**
	 * 非200状态吗，请求失败或者错误
	 */
	public static final int ERROR = -2;//400请求失败

	/**
	 * 网络不可用
	 */
	public static final int NONET = -3;//网络不可用

	/**
	 * 数据异常、或者解析异常
	 */
	public static final int DATAERROR = -4;//数据异常，数据解析错误等

	/**
	 * handler
	 */
	protected Handler handler;

	/**
	 * 网络请求工具
	 */
	protected AnalyzeJson analyzeJson;

	protected Gson gson = null;

	protected HashMap<String, String> params = null;

	private CustomProgressDialog mCustomProgressDialog;

	/**
	 * 是否已经设置了EmptyView
	 */
	private boolean isHadEmptyView = false;

	private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

	protected View view;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		L.i(TAG, this.getClass().getSimpleName() + "[被创建]--->onCreate");
		handler = new Handler(this);
		analyzeJson = new AnalyzeJson(getActivity(), handler);
		gson = new Gson();
		params = new HashMap<>();

		// “内存重启”时调用  解决重叠问题  #0001
		if (savedInstanceState != null) {
			boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			if (isSupportHidden) {
				ft.hide(this);
			} else {
				ft.show(this);
			}
			ft.commit();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(initPageLayoutID(), container, false);
		this.view = view;
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//初始化值，比如是否显示自定义头部，是否全屏等等。
		initWidget();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	/**
	 * initData 初始化数据
	 */
	protected abstract void initWidget();

	/**
	 * 生成主文件布局ID
	 */
	protected abstract int initPageLayoutID();

	/**
	 * 设置EmptyView
	 * 注释 见BaseActiviy setEmptyView
	 */
	public void setEmptyView(Message msg) {
	}


	@Override
	public boolean handleMessage(Message msg) {

		if (!isHadEmptyView) {
			isHadEmptyView = true;
			setEmptyView(msg);
		}
		return false;
	}

	public boolean isHadEmptyView() {
		return isHadEmptyView;
	}

	public void setHadEmptyView(boolean hadEmptyView) {
		isHadEmptyView = hadEmptyView;
	}


	/********************************* dialog的显示与取消 ********************************************/

	/**
	 * 显示一个ProgressDialog
	 */
	protected void showProgressDialog() {
		if (mCustomProgressDialog == null) {
			mCustomProgressDialog = new CustomProgressDialog(getActivity(),
					R.drawable.anim_progressr);
			mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
		}
		if (!mCustomProgressDialog.isShowing())
			mCustomProgressDialog.show();
	}

	/**
	 * 取消当前显示的ProgressDialog
	 */
	protected void dismissProgressDialog() {
		if (mCustomProgressDialog != null
				|| mCustomProgressDialog.isShowing()) {
			mCustomProgressDialog.dismiss();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());//#0001
	}
}
