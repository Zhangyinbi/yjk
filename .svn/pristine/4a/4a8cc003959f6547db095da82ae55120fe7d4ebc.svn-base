package com.xiaomizhuang.buildcaptain.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.hbw.library.BaseFragment;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.AssistAdvocateMaterialOrderActivity;
import com.xiaomizhuang.buildcaptain.activity.AssistDetailsActivity;
import com.xiaomizhuang.buildcaptain.activity.AssistProgectAddMatterActivity;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;
import com.xiaomizhuang.buildcaptain.activity.MaterialOrderDetailActivity;
import com.xiaomizhuang.buildcaptain.activity.QuantitiesActivity;
import com.xiaomizhuang.buildcaptain.activity.UserPurchaseActivity;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

/**
 * 材料明细Fragment
 *
 * @FileName: com.xiaomizhuang.buildcaptain.fragment.FragmentMaterialDetail.java
 * @author: Yangbang
 * @date: 2016-01-15 11:48
 */
public class FragmentMaterialDetail extends BaseFragment implements View.OnClickListener {
    private LinearLayout llFmMaterialDetailAssistOrder;
    private LinearLayout llFmMaterialDetailAssistToOrder;
    private View viewFmMaterialDetailDivide;
    private LinearLayout llFmMaterialDetailMainOrder;
    private LinearLayout llFmMaterialDetailMainToOrder;
    private View viewFmMaterialDetailDivide2;
    private LinearLayout llFmMaterialDetailAddOrder;
    private LinearLayout llFmMaterialDetailAddToOrder;
    private LinearLayout llFmMaterialDetailUserPurchase;
	private int is_jgstatus;
    private int confirm_num;
    private String bm_id;
    private String ownerPhone;//业主电话
    private String ownerName;//业主姓名
	private HintDialog mustDialog;
    @Override
    protected void initWidget() {
        assignViews();
    }

    private void assignViews() {
        llFmMaterialDetailAssistOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_assist_order);
        llFmMaterialDetailAssistToOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_assist_to_order);
        viewFmMaterialDetailDivide = getView().findViewById(R.id.view_fm_material_detail_divide);
        llFmMaterialDetailMainOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_main_order);
        llFmMaterialDetailMainToOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_main_to_order);
        viewFmMaterialDetailDivide2 = getView().findViewById(R.id.view_fm_material_detail_divide2);
        llFmMaterialDetailAddOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_add_order);
        llFmMaterialDetailAddToOrder = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_add_to_order);
        llFmMaterialDetailUserPurchase = (LinearLayout) getView().findViewById(R.id.ll_fm_material_detail_user_purchase);
        llFmMaterialDetailAssistOrder.setOnClickListener(this);
        llFmMaterialDetailAssistToOrder.setOnClickListener(this);
        llFmMaterialDetailMainOrder.setOnClickListener(this);
        llFmMaterialDetailMainToOrder.setOnClickListener(this);
        llFmMaterialDetailAddOrder.setOnClickListener(this);
        llFmMaterialDetailAddToOrder.setOnClickListener(this);
        llFmMaterialDetailUserPurchase.setOnClickListener(this);

        mustDialog = new HintDialog(getActivity());
        mustDialog.setTitleText("此工地已竣工！")
                    .setConfirmListener(new View.OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
		                    mustDialog.dismiss();
	                    }
                    }).setCancelHint();
	    is_jgstatus = getArguments().getInt("is_jgstatus");
        confirm_num = getArguments().getInt("confirm_num");
        ownerPhone = getArguments().getString("ownerPhone");
        ownerName = getArguments().getString("ownerName");
        bm_id = getArguments().getString("bm_id");

    }


    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_material_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_fm_material_detail_assist_order://辅材订单明细
                Intent intentAssist = new Intent(getActivity(), MaterialOrderDetailActivity.class);
                intentAssist.putExtra("bm_id", ((BuildDetailActivity) getActivity()).getBm_id());
                intentAssist.putExtra("materialType", 2);
                startActivity(intentAssist);
                break;
            case R.id.ll_fm_material_detail_assist_to_order://辅材下单
				if (is_jgstatus==1){//竣工
					mustDialog.show("此工地已竣工");
					break;
				}
                Intent assistOrderIntent = new Intent(getActivity(), AssistDetailsActivity.class);
                assistOrderIntent.putExtra("uid", ((BuildDetailActivity) getActivity()).getBm_id());
                assistOrderIntent.putExtra("bm_id", bm_id);
                startActivity(assistOrderIntent);
                break;
            case R.id.ll_fm_material_detail_main_order://主材订单明细
                Intent intentMain = new Intent(getActivity(), MaterialOrderDetailActivity.class);
                intentMain.putExtra("bm_id", ((BuildDetailActivity) getActivity()).getBm_id());
                intentMain.putExtra("materialType", 1);




                startActivity(intentMain);
                break;
            case R.id.ll_fm_material_detail_main_to_order://主材下单
	            if (is_jgstatus==1){//竣工
		            mustDialog.show("此工地已竣工");
		            break;
	            }else if(confirm_num==2){
                    mustDialog.show("此工地三期款未付，暂不能下单!");
                    break;
                }
                Intent mainOrderIntent = new Intent(getActivity(), AssistAdvocateMaterialOrderActivity.class);
                mainOrderIntent.putExtra("uid", ((BuildDetailActivity) getActivity()).getBm_id());
                startActivity(mainOrderIntent);
                break;
            case R.id.ll_fm_material_detail_add_order://增加工程量订单明细
                Intent quantitiesIntent = new Intent(getActivity(), QuantitiesActivity.class);
                quantitiesIntent.putExtra("bm_id", ((BuildDetailActivity) getActivity()).getBm_id());
                startActivity(quantitiesIntent);
                break;
            case R.id.ll_fm_material_detail_add_to_order://增加工程量下单
                Intent addProjectIntent = new Intent(getActivity(), AssistProgectAddMatterActivity.class);
                addProjectIntent.putExtra("bm_id", ((BuildDetailActivity) getActivity()).getBm_id());
                startActivity(addProjectIntent);
                break;
            case R.id.ll_fm_material_detail_user_purchase://自购材料订单明细
                Intent userPurchaseIntent = new Intent(getActivity(), UserPurchaseActivity.class);
                userPurchaseIntent.putExtra("bm_id", ((BuildDetailActivity) getActivity()).getBm_id());
                userPurchaseIntent.putExtra("ownerPhone", ownerPhone);
                userPurchaseIntent.putExtra("ownerName", ownerName);
                startActivity(userPurchaseIntent);
                break;
        }
    }

}
