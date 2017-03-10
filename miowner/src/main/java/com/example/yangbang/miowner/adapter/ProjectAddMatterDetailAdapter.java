package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.ProjectAddmatterDetail;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.MoneySimpleFormat;

import java.util.List;

/**
 * Created by mwy on 2016/3/30.
 */
public class ProjectAddMatterDetailAdapter extends AbsBaseAdapter<ProjectAddmatterDetail> {

    private Context context;

    public ProjectAddMatterDetailAdapter(Context context, List<ProjectAddmatterDetail> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listitem_project_add_matter_detail, null);
            holder = new ViewHolder();
            holder.tv_material_name = (TextView) convertView.findViewById(R.id.tv_material_name);
            holder.tv_brand_name = (TextView) convertView.findViewById(R.id.tv_brand_name);
            holder.tv_type_name = (TextView) convertView.findViewById(R.id.tv_type_name);
            holder.tv_act_price = (TextView) convertView.findViewById(R.id.tv_act_price);
            holder.tv_act_count = (TextView) convertView.findViewById(R.id.tv_act_count);
            holder.tv_act_total_price = (TextView) convertView.findViewById(R.id.tv_act_total_price);
            holder.tv_shl = (TextView) convertView.findViewById(R.id.tv_shl);
            holder.tv_zjx_pay = (TextView) convertView.findViewById(R.id.tv_zjx_pay);
            holder.tv_zjx_paytime = (TextView) convertView.findViewById(R.id.tv_zjx_paytime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_material_name.setText(getItem(position).getMaterial_name());
        holder.tv_brand_name.setText(getItem(position).getBrand_name());
        holder.tv_type_name.setText(getItem(position).getType_name());
        holder.tv_act_price.setText(MoneySimpleFormat.getYuanType(getItem(position).getAct_price()));
        holder.tv_act_count.setText("x"+getItem(position).getAct_count());
        holder.tv_shl.setText("损耗率 "+getItem(position).getShl());
        holder.tv_act_total_price.setText("合计 : "+ MoneySimpleFormat.getYuanType(getItem(position).getAct_total_price())+"（含运费0元）");
        holder.tv_zjx_pay.setText(getItem(position).getZjx_pay());
        //判断付款时间是否为空
        if (null==getItem(position).getZjx_paytime()|| TextUtils.isEmpty(getItem(position).getZjx_paytime())
                ||"0000-00-00 00:00:00".equals(getItem(position).getZjx_paytime())){
            holder.tv_zjx_paytime.setText("");
        }else{
            holder.tv_zjx_paytime.setText("付款时间 : "+getItem(position).getZjx_paytime());
        }

        return convertView;
    }

    class ViewHolder {
        private TextView tv_material_name;
        private TextView tv_brand_name;
        private TextView tv_type_name;
        private TextView tv_act_price;
        private TextView tv_act_count;
        private TextView tv_act_total_price;
        private TextView tv_shl;
        private TextView tv_zjx_pay;
        private TextView tv_zjx_paytime;
    }
}
