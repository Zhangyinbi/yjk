package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.AllActasPurchasingAgency;
import com.example.yangbang.miowner.entity.AllActasPurchasingAgencyAdapter;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mwy on 2016/6/29.
 * 事项提醒fragment---主材代购fragment
 */
public class AllActasPurchasingAgencyOfEventRemindFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

	private ListView event_remind_list;
	private AllActasPurchasingAgencyAdapter allActasPurchasingAgencyAdapter;
	private List<AllActasPurchasingAgency> datalist = new ArrayList<AllActasPurchasingAgency>();
	private RadioButton rb_all;
	private RadioGroup radio_group;
	HashMap<String, String> mMap;
	private MyEmptyView emptyView;

	@Override
	protected void initWidget() {
		event_remind_list = (ListView) view.findViewById(R.id.event_remind_list);
		allActasPurchasingAgencyAdapter = new AllActasPurchasingAgencyAdapter(getActivity(), datalist);
		event_remind_list.setAdapter(allActasPurchasingAgencyAdapter);
		rb_all = (RadioButton) view.findViewById(R.id.rb_all);
		radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
		rb_all.setChecked(true);

		radio_group.setOnCheckedChangeListener(this);

		mMap = new HashMap<String, String>();
		mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.ALLACTASPURCHASINGAGENCY_URL, mMap, REQUEST_SUCCESS);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.fragment_all_actas_purchasing_agency_of_event_remind;
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				datalist = gson.fromJson(data.data, new TypeToken<ArrayList<AllActasPurchasingAgency>>() {
				}.getType());
				allActasPurchasingAgencyAdapter.notifyDataSetChanged(datalist);
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		mMap.clear();
		mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
		switch (checkedId) {
			case R.id.rb_all://全部

				break;
			case  R.id.rb_installed://已安装
				mMap.put("status","1");
				break;
			case  R.id.rb_no_installed://未安装
				mMap.put("status","0");
				break;
			default:
				break;
		}
		analyzeJson.requestData(HttpConstant.ALLACTASPURCHASINGAGENCY_URL, mMap, REQUEST_SUCCESS);
	}

	@Override
	public void setEmptyView(Message msg) {
		emptyView = new MyEmptyView(getContext());
		emptyView.setEmptyViewImg(R.mipmap.item);
		emptyView.setEmptyViewContent("暂无事项");
		((ViewGroup) event_remind_list.getParent()).addView(emptyView.getEmptyView());
		event_remind_list.setEmptyView(emptyView.getEmptyView());
	}
}
