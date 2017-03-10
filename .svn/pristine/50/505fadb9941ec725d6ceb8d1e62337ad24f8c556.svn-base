package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.BuySelfOrder;

import java.util.List;

/**
 * 描述: 自购材料Adapter
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-10-19 18:22
 */

public class UserPurchaseAdapter  extends RecyclerView.Adapter<UserPurchaseAdapter.UserPurchaseViewHodler>{

    private List<BuySelfOrder> list;
    private Context context;

    public UserPurchaseAdapter(List<BuySelfOrder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public UserPurchaseViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserPurchaseViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_user_purchase,null));
    }

    @Override
    public void onBindViewHolder(UserPurchaseViewHodler holder, int position) {
        holder.mTvTime.setText(list.get(position).getAddtime());
        holder.mTvName.setText(list.get(position).getMaterial_name());
        holder.mTvBrandName.setText(list.get(position).getBrand());
        holder.mTvTypeName.setText(list.get(position).getNorms()+","+list.get(position).getVersion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserPurchaseViewHodler extends RecyclerView.ViewHolder{
        private View view;
        private TextView mTvTime;
        private TextView mTvName;
        private TextView mTvBrandName;
        private TextView mTvTypeName;
        private View mBigDivider;

        private void assignViews() {
            mTvTime = (TextView) view.findViewById(R.id.tv_time);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvBrandName = (TextView) view.findViewById(R.id.tv_brand_name);
            mTvTypeName = (TextView) view.findViewById(R.id.tv_type_name);
            mBigDivider = view.findViewById(R.id.big_divider);
        }

        public UserPurchaseViewHodler(View itemView) {
            super(itemView);
            view = itemView;
            assignViews();
        }
    }
}
