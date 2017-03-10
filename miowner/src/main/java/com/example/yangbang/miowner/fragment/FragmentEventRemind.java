package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.RemindMessage;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 事项提醒Fragment
 */
public class FragmentEventRemind extends BaseFragment {

	private static final int REQUEST_MESSAGE_NUM = 0x19001;
	private TabLayout tl_spv_re_tab_layout;
    private FrameLayout fl_fragment_container;
	private IntermediateAcceptanceOfEventRemindFragment intermediateAcceptanceOfEventRemindFragment;
	private MoneyMattersOfEventRemindFragment moneyMattersOfEventRemindFragment;
	private SincePurchaseOfEventRemindFragment sincePurchaseOfEventRemindFragment;
	private AllActasPurchasingAgencyOfEventRemindFragment allActasPurchasingAgencyOfEventRemindFragment;
	private FragmentTransaction transaction ;
	private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initWidget() {
        tl_spv_re_tab_layout = (TabLayout) getActivity().findViewById(R.id.tl_spv_re_tab_layout);
        fl_fragment_container = (FrameLayout) getActivity().findViewById(R.id.fl_fragment_container);
        tl_spv_re_tab_layout.newTabItem("阶段验收", 0);
        tl_spv_re_tab_layout.newTabItem("打款事宜", 1);
        tl_spv_re_tab_layout.newTabItem("主材自购", 2);
        tl_spv_re_tab_layout.newTabItem("主材代购", 3);

	    //请求各类消息数量
	    params.put("token", MyApplication.getApp().getOwnerUser().getToken());
	    analyzeJson.requestData(HttpConstant.MESSAGE_REMIND_NUM,params,REQUEST_MESSAGE_NUM);

	    /*//默认请求阶段验收
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
        analyzeJson.requestData(HttpConstant.INTERMEDIATE_ACCEPTANCE_URL, mMap, REQUEST_SUCCESS);*/

        tl_spv_re_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
	            showFragment(position);
            }
        });
	    tl_spv_re_tab_layout.setCurrentPosition(0);
    }

	@Override
	protected int initPageLayoutID() {
		return R.layout.fragment_event_remind;
	}

	private void showFragment(int position) {
		transaction = getChildFragmentManager().beginTransaction();
		hideFragment(transaction);
		switch (position) {
			case 0://阶段验收
				if (intermediateAcceptanceOfEventRemindFragment == null) {
					intermediateAcceptanceOfEventRemindFragment = new IntermediateAcceptanceOfEventRemindFragment();
					transaction.add(R.id.fl_fragment_container,intermediateAcceptanceOfEventRemindFragment);
					fragmentList.add(intermediateAcceptanceOfEventRemindFragment);
				} else {
					transaction.show(intermediateAcceptanceOfEventRemindFragment);
				}
				break;
			case 1://打款事宜
				if (moneyMattersOfEventRemindFragment == null) {
					moneyMattersOfEventRemindFragment = new MoneyMattersOfEventRemindFragment();
					transaction.add(R.id.fl_fragment_container,moneyMattersOfEventRemindFragment);
					fragmentList.add(moneyMattersOfEventRemindFragment);
				} else {
					transaction.show(moneyMattersOfEventRemindFragment);
				}
				break;
			case 2://主材自购
				if (sincePurchaseOfEventRemindFragment == null) {
					sincePurchaseOfEventRemindFragment = new SincePurchaseOfEventRemindFragment();
					transaction.add(R.id.fl_fragment_container,sincePurchaseOfEventRemindFragment);
					fragmentList.add(sincePurchaseOfEventRemindFragment);
				} else {
					transaction.show(sincePurchaseOfEventRemindFragment);
				}
				break;
			case 3://主材代购
				if (allActasPurchasingAgencyOfEventRemindFragment == null) {
					allActasPurchasingAgencyOfEventRemindFragment = new AllActasPurchasingAgencyOfEventRemindFragment();
					transaction.add(R.id.fl_fragment_container,allActasPurchasingAgencyOfEventRemindFragment);
					fragmentList.add(allActasPurchasingAgencyOfEventRemindFragment);
				} else {
					transaction.show(allActasPurchasingAgencyOfEventRemindFragment);
				}
				break;
			default:
				break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (fragmentList!=null){
			for (int i = 0; i < fragmentList.size(); i++) {
				if (fragmentList.get(i) != null) {
					transaction.hide(fragmentList.get(i));
				}
			}
		}
	}

    public boolean handleMessage(Message msg) {
	    switch (msg.what) {
		    case REQUEST_MESSAGE_NUM://请求消息数量
			    ResponseSucceedData data = (ResponseSucceedData) msg.obj;
			    List<RemindMessage> messages = gson.fromJson(data.data, new TypeToken<List<RemindMessage>>(){}.getType());
			    if (null != messages && messages.size() > 0){
				    for (int i = 0; i <messages.size(); i++) {
					    tl_spv_re_tab_layout.setTabMsgRemind(i,messages.get(i).getStatus_count());
				    }
			    }
			    break;
		    default:
			    break;
	    }

        return super.handleMessage(msg);
    }

}
