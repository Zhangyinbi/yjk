package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.widget.ExpandableListView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.OwnerConstrucProgressAdapter;
import com.example.yangbang.miowner.entity.OwnerConstrucProgress;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * 施工进度Fragment
 */
public class FragmentConstrucProgress extends BaseFragment {

    private ExpandableListView construction_progress_expandable_listview = null;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private ArrayList<OwnerConstrucProgress> mOwnerConstrucProgress = new ArrayList<OwnerConstrucProgress>();
    private OwnerConstrucProgressAdapter mOwnerConstrucProgressAdapter = null;

    @Override
    protected void initWidget() {
        mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
        analyzeJson.requestData(HttpConstant.SCHEDULINGDETAILSLIST_URL, mMap, REQUEST_SUCCESS);
        construction_progress_expandable_listview = (ExpandableListView) getActivity().findViewById(R.id.construction_progress_expandable_listview);
        //设置属性去掉默认向下的箭头
        construction_progress_expandable_listview.setGroupIndicator(null);
        mOwnerConstrucProgressAdapter = new OwnerConstrucProgressAdapter(getActivity(), mOwnerConstrucProgress);
        construction_progress_expandable_listview.setAdapter(mOwnerConstrucProgressAdapter);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_construction_progress;
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mOwnerConstrucProgress = gson.fromJson(data.data, new TypeToken<ArrayList<OwnerConstrucProgress>>() {
                }.getType());
                mOwnerConstrucProgressAdapter.setNotifyDataSetChangedList(mOwnerConstrucProgress);
                break;
        }
        return super.handleMessage(msg);
    }
}
