package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.ContactsAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.Contacts;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yangbang
 * @ClassName ContactsActivity
 * @description 通讯录activity
 * @date 2015年5月26日
 */
public class ContactsActivity extends MiBaseActivity implements OnClickListener,
        OnItemClickListener {
    ListView activity_contacts_lv;
    List<Contacts> linkmans = new ArrayList<Contacts>();
    ContactsAdapter adapter;
    String baoming_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initWidget() {
        baoming_id = getIntent().getStringExtra("baoming_id");
        activity_contacts_lv = (ListView) this
                .findViewById(R.id.activity_contacts_lv);
        activity_contacts_lv.setOnItemClickListener(this);
        titleBar.setTitleText("通讯录");
        titleBar.setLeftImgDefaultBack(this,R.drawable.back);
        adapter = new ContactsAdapter(linkmans, this);
        activity_contacts_lv.setAdapter(adapter);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", HttpConstant.token);
        params.put("baoming_id", baoming_id);
        analyzeJson.requestData(HttpConstant.GetContactsUrl, params,
                REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_contacts;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_image:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                linkmans = gson.fromJson(data.data,new TypeToken<List<Contacts>>(){}.getType());
                adapter.notifyDataSetChanged(linkmans);
                break;

            default:
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Contacts linkman = (Contacts) ((ContactsAdapter) parent.getAdapter())
                .getItem(position);
        Intent intent = new Intent(ContactsActivity.this, TeleActivity.class);
        intent.putExtra("tel", linkman.telephone);
        startActivity(intent);
    }

}
