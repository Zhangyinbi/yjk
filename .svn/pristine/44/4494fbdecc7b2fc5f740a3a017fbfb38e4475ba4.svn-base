package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.utils.MoneySimpleFormat;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AddPayment;
import com.xiaomizhuang.buildcaptain.entity.Payment;

import java.util.List;

/**
 * 描述: 工期收款
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-10-20 15:59
 */

public class AddMoneyAdapter extends RecyclerView.Adapter<AddMoneyAdapter.GatheringViewHolder> {

    private Context context;
    private List<AddPayment> datelist;

    public AddMoneyAdapter(Context context, List<AddPayment> datelist) {
        this.context = context;
        this.datelist = datelist;
    }

    @Override
    public GatheringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GatheringViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addmoney_adapter,null));
    }

    @Override
    public void onBindViewHolder(GatheringViewHolder holder, int position) {
        holder.mTvStep.setText(datelist.get(position).getTitle());
        holder.mTvReceivable.setText("¥ "+MoneySimpleFormat.getYuanType(datelist.get(position).getReceived()));
        holder.mTvReceivedTime.setText(datelist.get(position).getTime());
        holder.mTvDetail.setText(datelist.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }

    class GatheringViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvStep;
        private TextView mTvReceivable;
        private TextView mTvReceivedTime;
        private TextView mTvDetail;
        private View view;

        private void assignViews() {
            mTvStep = (TextView) view.findViewById(R.id.tv_step);
            mTvReceivable = (TextView) view.findViewById(R.id.tv_receivable);
            mTvReceivedTime = (TextView) view.findViewById(R.id.tv_received_time);
            mTvDetail = (TextView) view.findViewById(R.id.tv_detail);
        }

        public GatheringViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            assignViews();
        }
    }
}
