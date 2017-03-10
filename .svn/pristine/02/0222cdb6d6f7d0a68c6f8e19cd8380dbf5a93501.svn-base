package com.example.yangbang.miowner.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.BuildRecordActivity;
import com.example.yangbang.miowner.activity.PatrolRecordActivity;
import com.example.yangbang.miowner.activity.SupervisionReportActivity;
import com.hbw.library.BaseFragment;

/**
 * 工地管理
 * Created by mwy on 2016/6/22.
 */
public class SiteManagementFragment extends BaseFragment implements View.OnClickListener {

	private RelativeLayout rl_acceptance;
	private RelativeLayout rl_check;
	private RelativeLayout rl_daily;

	@Override
	protected void initWidget() {
		rl_acceptance = (RelativeLayout) getView().findViewById(R.id.rl_acceptance);
		rl_check = (RelativeLayout) getView().findViewById(R.id.rl_check);
		rl_daily = (RelativeLayout) getView().findViewById(R.id.rl_daily);

		rl_acceptance.setOnClickListener(this);
		rl_check.setOnClickListener(this);
		rl_daily.setOnClickListener(this);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.fragment_site_management;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_acceptance://节点验收
				startActivity(new Intent(getActivity(), SupervisionReportActivity.class));
				break;
			case R.id.rl_check://项目经理巡查
				startActivity(new Intent(getActivity(), PatrolRecordActivity.class) );
				break;
			case R.id.rl_daily://施工队长日志
				startActivity(new Intent(getActivity(), BuildRecordActivity.class) );
				break;
			default:
				break;
		}
	}
}
