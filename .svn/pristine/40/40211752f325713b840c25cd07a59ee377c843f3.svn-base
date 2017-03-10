package com.xiaomizhuang.buildcaptain.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.hbw.library.BaseFragment;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;

/**
 * Created by Administrator on 2016/11/7.
 */

public class GatherFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected void initWidget() {
        ImageView imgqsk = (ImageView) getView().findViewById(R.id.im_gqsk);
        imgqsk.setOnClickListener(this);
        ImageView imclzjk = (ImageView) getView().findViewById(R.id.im_clzjk);
        imclzjk.setOnClickListener(this);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_gather;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_gqsk:
                /*FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fl_build_detail_container,new GatheringFragment());
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
*/
                ((BuildDetailActivity)getActivity()).selectedFragmentByIndex(3);
                break;
            case R.id.im_clzjk:
                /*FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft1.replace(R.id.fl_build_detail_container,new AddMoneyFragment());
                ft1.addToBackStack(null);
                ft1.commitAllowingStateLoss();*/
                ((BuildDetailActivity)getActivity()).selectedFragmentByIndex(4);
                break;
        }
    }

}
