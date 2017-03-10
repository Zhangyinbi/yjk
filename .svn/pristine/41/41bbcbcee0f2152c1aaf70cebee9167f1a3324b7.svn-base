package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyan.client.sdk.JDeviceBasic;
import com.example.yangbang.miowner.R;

import java.util.List;

/**
 * 视频监控adapter
 *
 * @FileName: com.example.yangbang.miowner.adapter.SiteMonitoringAdapter.java
 * @author: Yangbang
 * @date: 2015-12-21 17:10
 */
public class SiteMonitoringAdapter extends RecyclerView.Adapter<SiteMonitoringAdapter.MyViewHolder> {
    Context context;
    OnItemClickListener onItemClickListener;
    List<JDeviceBasic> mDevices;

    public SiteMonitoringAdapter(Context context, List<JDeviceBasic> mDevices) {
        this.context = context;
        this.mDevices = mDevices;
    }

    public void notifyDataSetChanged(List<JDeviceBasic> mDevices) {
        this.mDevices = mDevices;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_site_monitoring, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_listitem_monitoring.setText(mDevices.get(position).getDeviceName());
        if (mDevices.get(position).getDeviceStatus() != JDeviceBasic.DeviceStatus.Offline) {
            holder.tv_monitoring_status.setText("Online");
            holder.tv_monitoring_status.setTextColor(Color.GREEN);
        } else {
            holder.tv_monitoring_status.setText("Offline");
            holder.tv_monitoring_status.setTextColor(Color.RED);
        }
        holder.imgv_listitem_site_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteMonitoringAdapter.this.onItemClickListener.onItemClick(position, mDevices.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_listitem_site_monitoring;
        TextView tv_listitem_monitoring;
        TextView tv_monitoring_status;

        public MyViewHolder(View view) {
            super(view);
            this.imgv_listitem_site_monitoring = (ImageView) view.findViewById(R.id.imgv_listitem_site_monitoring);
            this.tv_listitem_monitoring = (TextView) view.findViewById(R.id.tv_listitem_monitoring);
            this.tv_monitoring_status = (TextView) view.findViewById(R.id.tv_monitoring_status);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onItemClick(int position, JDeviceBasic jDeviceBasic);
    }
}
