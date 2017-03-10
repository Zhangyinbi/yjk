package com.example.yangbang.miowner.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yangbang.miowner.R;

/**
 * 我的订单列表item材料View
 *
 * @FileName: com.example.yangbang.miowner.view.MaterialViewItem.java
 * @author: Yangbang
 * @date: 2016-06-27 18:42
 */
public class MaterialViewItem {
    Context context;
    View view;
    TextView tv_item_my_order_material_name;//材料名
    TextView tv_item_my_order_material_brand;//材料品牌
    TextView tv_item_my_order_material_price;//价格

    public MaterialViewItem(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.item_my_order_material, null);
        tv_item_my_order_material_name = (TextView) view.findViewById(R.id.tv_item_my_order_material_name);
        tv_item_my_order_material_brand = (TextView) view.findViewById(R.id.tv_item_my_order_material_brand);
        tv_item_my_order_material_price = (TextView) view.findViewById(R.id.tv_item_my_order_material_price);
    }

    /**
     * 设置材料名称
     *
     * @param materialName
     */
    public void setMaterialName(String materialName) {
        tv_item_my_order_material_name.setText(materialName);
    }

    /**
     * 设置材料品牌
     *
     * @param materialBrand
     */
    public void setMaterialBrand(String materialBrand) {
        tv_item_my_order_material_brand.setText(materialBrand);
    }

    /**
     * 设置材料价格
     *
     * @param materialPrice
     */
    public void setMaterialPrice(String materialPrice) {
        tv_item_my_order_material_price.setText(materialPrice);
    }

    public View getView() {
        return view;
    }

}
