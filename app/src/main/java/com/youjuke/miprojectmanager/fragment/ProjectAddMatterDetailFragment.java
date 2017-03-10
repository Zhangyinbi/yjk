package com.youjuke.miprojectmanager.fragment;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.MoneySimpleFormat;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.ProjectAddMatterDetailAdapter;
import com.youjuke.miprojectmanager.entity.ProjectAddmatterDetail;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.MyEmptyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwy on 2016/3/29.
 */
public class ProjectAddMatterDetailFragment extends BaseFragment {
    private ListView lvFmProjectAddMatterDetail;
    private TextView tv_total_price;        //主材增项总额
    private TextView tv_total_price_pay;       //主材增项实付总额
    private ProjectAddMatterDetailAdapter adapter;

    private String baoming;
    private static final int PROJECT_ADD_MATTER_DETAIL_SUCCESS = 0x90;

    private List<ProjectAddmatterDetail> dataList = new ArrayList<ProjectAddmatterDetail>();
    private MyEmptyView emptyView;

    @Override
    protected void initWidget() {
        lvFmProjectAddMatterDetail = (ListView) getActivity().findViewById(R.id.lv_fm_project_add_matter_detail);
        tv_total_price = (TextView) getActivity().findViewById(R.id.tv_total_price);
        tv_total_price_pay = (TextView) getActivity().findViewById(R.id.tv_total_price_pay);

        adapter = new ProjectAddMatterDetailAdapter(getActivity(),dataList);
        lvFmProjectAddMatterDetail.setAdapter(adapter);

        baoming =  getArguments().getString("baoming_id");
        params.put("token", HttpConstant.token);
        params.put("baoming_id", baoming);
        analyzeJson.requestData(HttpConstant.ProjectAddListUrl, params,PROJECT_ADD_MATTER_DETAIL_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_project_add_matter_detail;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case PROJECT_ADD_MATTER_DETAIL_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<ProjectAddmatterDetail> temp = gson.fromJson(data.data,
                        new TypeToken<List<ProjectAddmatterDetail>>(){}.getType());

                if(null!=temp&&temp.size()>0){
                    dataList.clear();
                    dataList.addAll(temp);
                    tv_total_price.setText("主材增项总额 : " + MoneySimpleFormat.getYuanType(dataList.get(0).getTotal_price()));
                    tv_total_price_pay.setText("主材增项实付总额 : "
                            +MoneySimpleFormat.getYuanType(dataList.get(0).getTotal_price_pay()));
                }else{//如果没有数据
                    ((ViewGroup)tv_total_price.getParent()).setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void setEmptyView(Message msg) {
        emptyView = new MyEmptyView(getActivity());
        emptyView.setEmptyViewImg(R.drawable.ic_empty_orders);
        emptyView.setEmptyViewContent("暂无数据");
        ((ViewGroup)lvFmProjectAddMatterDetail.getParent()).addView(emptyView.getEmptyView());
        lvFmProjectAddMatterDetail.setEmptyView(emptyView.getEmptyView());
    }
}
