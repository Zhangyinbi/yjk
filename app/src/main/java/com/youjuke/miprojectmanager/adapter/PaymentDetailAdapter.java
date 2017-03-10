package com.youjuke.miprojectmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.MoneySimpleFormat;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.entity.PaymentDetail;

import java.util.List;

/**
 * Created by mwy on 2016/3/30.
 */
public class PaymentDetailAdapter extends AbsBaseAdapter<PaymentDetail> {

    private Context context;

    public PaymentDetailAdapter(Context context, List<PaymentDetail> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listitem_fragment_payment_detail, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvBili = (TextView) convertView.findViewById(R.id.tv_bili);
            holder.tvFee = (TextView) convertView.findViewById(R.id.tv_fee);
            holder.tvPayment = (TextView) convertView.findViewById(R.id.tv_payment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(dataList.get(position).getTitle());
        //定金不用显示比例
        if (position > 0) {
            holder.tvBili.setText(dataList.get(position).getBili());
        } else {
            holder.tvBili.setText("");
        }
        holder.tvFee.setText(MoneySimpleFormat.getSimpleType(dataList.get(position).getFee()));
        holder.tvPayment.setText(MoneySimpleFormat.getSimpleType(dataList.get(position).getPayment()));
        return convertView;
    }


    class ViewHolder {
        private TextView tvTitle;
        private TextView tvBili;
        private TextView tvFee;
        private TextView tvPayment;
    }
}
