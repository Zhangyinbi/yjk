package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.hbw.library.BasePopupWindow;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistTypeOrderAdapter;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;

import java.util.List;

/**
 * 主、辅材类型Pop
 *
 * @FileName: com.xiaomizhuang.buildcaptain.view.PopMaterialType.java
 * @author: Yangbang
 * @date: 2016-01-15 15:53
 */
public class PopMaterialType extends BasePopupWindow implements View.OnClickListener {
    GridView gv_pop_material_type;
    View view_pop_material_type_translucence_layer;
    AssistTypeOrderAdapter adapter;
    List<MaterialType> materialTypes;
    int materialType = 1;//1代表主材、2代表辅材
    ImageView imageView;

    public PopMaterialType(Activity activity, List<MaterialType> materialTypes, int materialType, ImageView imageView) {
        super(activity);
        this.materialTypes = materialTypes;
        this.materialType = materialType;
        this.imageView = imageView;
        setIsAlpha(false);
        initView();
    }

    @Override
    public void dismiss() {
        imageView.setImageResource(R.mipmap.ic_build_greyarrowdown);
        if (materialType == 1) {
            imageView.setColorFilter(Color.BLACK);
        } else {
            imageView.clearColorFilter();
        }
        super.dismiss();
    }

    private void initView() {
        gv_pop_material_type = (GridView) getContentView().findViewById(R.id.gv_pop_material_type);
        view_pop_material_type_translucence_layer = getContentView().findViewById(R.id.view_pop_material_type_translucence_layer);
        view_pop_material_type_translucence_layer.setOnClickListener(this);
        adapter = new AssistTypeOrderAdapter(activity, materialTypes);
        gv_pop_material_type.setAdapter(adapter);
    }

    public AssistTypeOrderAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AssistTypeOrderAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected int inItContentViewId() {
        return R.layout.pop_material_type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_pop_material_type_translucence_layer:
                dismiss();
                break;
        }
    }
}
