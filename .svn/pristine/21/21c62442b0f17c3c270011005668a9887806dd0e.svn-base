package com.youjuke.miprojectmanager.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.entity.Contacts;

public class ContactsAdapter extends AbsBaseAdapter<Contacts> {
	Context context;

	public ContactsAdapter(List<Contacts> dataList, Context context) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.contacts_list_item, null);
		}
		Contacts linkman = dataList.get(position);
		((TextView) ViewHolders.get(convertView, R.id.list_item_name_tv))
				.setText(linkman.name + "(" + linkman.type + ")");
		((TextView) ViewHolders.get(convertView, R.id.list_item_number_tv))
				.setText(dataList.get(position).telephone);
		return convertView;
	}

}
