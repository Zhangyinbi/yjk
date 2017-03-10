package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.Tools;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistMaterialTypeAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistMaterialType;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/24. 材料类型
 */
public class AssistMaterialTypeActivity extends BaseActivity implements View.OnClickListener{

    private EditText materialTypeEdittext;
    private LinearLayout materialTypeText;
    private LinearLayout materialTypeSearch;
    private ListView materialTypeLsitview = null;
    private String typekey = "";//搜素关键字 为空位查询全部
    private List<AssistMaterialType> mAssistMaterialTypes = new ArrayList<AssistMaterialType>();
    private AssistMaterialTypeAdapter assistMaterialTypeAdapter = null;
    private String fid = null,brand = null,dl_id = null,bm_id;

    @Override
    protected void initWidget() {
        brand = getIntent().getStringExtra("brand");
        fid = getIntent().getStringExtra("fid");
        dl_id = getIntent().getStringExtra("dl_id");
        bm_id = MyApplication.getApp().getBuildSite().getUid();
        titleBar.setTitleText("材料类型");
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(this);
        materialTypeEdittext = (EditText) findViewById(R.id.material_type_edittext);
        materialTypeText = (LinearLayout) findViewById(R.id.material_type_text);
        materialTypeSearch = (LinearLayout) findViewById(R.id.material_type_search);
        materialTypeLsitview =  (ListView) findViewById(R.id.material_type_lsitview);
        materialTypeEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                typekey = s.toString();
                //查询材料类型
                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put(AppFlag.TYPEKEY, typekey);
                mMap.put(AppFlag.FID, fid);
                mMap.put("brand", brand);
                mMap.put("dl_id", dl_id);
                mMap.put("bm_id", bm_id);
                analyzeJson.requestData(HttpConstant.SelectTypeUrl, mMap, REQUEST_SUCCESS);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialTypeLsitview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AssistMaterialTypeActivity.this,AssistOrderDetailsActivity.class);
                it.putExtra(AppFlag.ASSISTMATERIALTYPE,mAssistMaterialTypes.get(position));
                setResult(AssistOrderDetailsActivity.MATERIALTYPE,it);
                finishActivity();
            }
        });
        materialTypeEdittext.setOnClickListener(this);
        materialTypeText.setOnClickListener(this);
        //查询材料类型
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(AppFlag.TYPEKEY, typekey);
        mMap.put(AppFlag.FID, fid);
        mMap.put("brand", brand);
        mMap.put("dl_id", dl_id);
	    mMap.put("bm_id", bm_id);
        analyzeJson.requestData(HttpConstant.SelectTypeUrl, mMap, REQUEST_SUCCESS);
        assistMaterialTypeAdapter = new AssistMaterialTypeAdapter(this,mAssistMaterialTypes);
        materialTypeLsitview.setAdapter(assistMaterialTypeAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_material_type;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.material_type_edittext:
                materialTypeEdittext.setHint(R.string.order_search);
                materialTypeEdittext.setCompoundDrawables(getResources().getDrawable(R.drawable.search), null, null, null);
                materialTypeText.setVisibility(View.VISIBLE);
                materialTypeSearch.setVisibility(View.GONE);
                break;
            case R.id.material_type_text://取消搜素
                materialTypeEdittext.setHint(null);
                materialTypeEdittext.setCompoundDrawables(null, null, null, null);
                materialTypeEdittext.setText("");
                materialTypeText.setVisibility(View.GONE);
                materialTypeSearch.setVisibility(View.VISIBLE);
                Tools.HideKeyBoard(this);
                typekey = "";
                //查询材料类型
                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put(AppFlag.TYPEKEY, typekey);
                mMap.put(AppFlag.FID, fid);
                mMap.put("brand", brand);
                mMap.put("dl_id", dl_id);
	            mMap.put("bm_id", bm_id);
                analyzeJson.requestData(HttpConstant.SelectTypeUrl, mMap, REQUEST_SUCCESS);
                break;
            case R.id.left_igame_relative://设置返回事件
                CloseActivity();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                mAssistMaterialTypes = gson.fromJson(data.data, new TypeToken<List<AssistMaterialType>>() {}.getType());
                assistMaterialTypeAdapter.notifyDataSetChanged(mAssistMaterialTypes);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        CloseActivity();
        return super.onKeyDown(keyCode, event);
    }

    private void CloseActivity(){
        Intent it = new Intent(AssistMaterialTypeActivity.this,AssistOrderDetailsActivity.class);
        AssistMaterialType obj = new AssistMaterialType();
        it.putExtra(AppFlag.ASSISTMATERIALTYPE,obj);
        setResult(AssistOrderDetailsActivity.MATERIALTYPE,it);
        finishActivity();
    }
}
