package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.SincePurchaseAdapter;
import com.example.yangbang.miowner.entity.SincePurchase;
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
 * 事项提醒fragment----主材自购frgment
 */
public class SincePurchaseOfEventRemindFragment extends BaseFragment {

	private ListView event_remind_list;
	private SincePurchaseAdapter sincePurchaseAdapter;
	private List<SincePurchase> datalist = new ArrayList<SincePurchase>();
	private MyEmptyView emptyView;

	@Override
	protected void initWidget() {
		event_remind_list = (ListView) view.findViewById(R.id.event_remind_list);
		sincePurchaseAdapter = new SincePurchaseAdapter(getActivity(), datalist);
		event_remind_list.setAdapter(sincePurchaseAdapter);


		HashMap<String, String> mMap = new HashMap<String, String>();
		mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.SINCEPURCHASE_URL, mMap, REQUEST_SUCCESS);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.fragment_event_remind_common;
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				datalist = gson.fromJson(data.data, new TypeToken<ArrayList<SincePurchase>>() {
				}.getType());
				sincePurchaseAdapter.notifyDataSetChanged(datalist);
				break;
		}
		return super.handleMessage(msg);
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
