package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AssistNodeNote;
import com.xiaomizhuang.buildcaptain.entity.AssistNodeNoteDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/12/28.
 */
public class AssistNodeNoteAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String bm_id;
    private ArrayList<AssistNodeNote> mAssistNodeNote = new ArrayList<AssistNodeNote>();

    public AssistNodeNoteAdapter(Context context, ArrayList<AssistNodeNote> mAssistNodeNote) {
        this.mAssistNodeNote = mAssistNodeNote;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return mAssistNodeNote.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mAssistNodeNote.get(groupPosition).getData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.item_node_note_group, null);
            holder.node_note_date = (TextView) v.findViewById(R.id.node_note_date);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.node_note_date.setText(mAssistNodeNote.get(groupPosition).getDays());
        return v;
    }

    class ViewHolder {
        public TextView node_note_date;
    }

    @Override
    public View getChildView(int groupPosition, int position, boolean isLastChild, View v, ViewGroup parent) {
        List<AssistNodeNoteDetails> dataList = mAssistNodeNote.get(groupPosition).getData();
        ViewHolderChild holder = null;
        if (v == null) {
            holder = new ViewHolderChild();
            v = View.inflate(context, R.layout.item_node_note_child, null);
            holder.t1 = (TextView) v.findViewById(R.id.t1);
            holder.t2 = (TextView) v.findViewById(R.id.t2);
            holder.t3 = (TextView) v.findViewById(R.id.t3);
            holder.t4 = (TextView) v.findViewById(R.id.t4);
            v.setTag(holder);
        } else {
            holder = (ViewHolderChild) v.getTag();
        }
        holder.t1.setText(dataList.get(position).getTitle());
        holder.t2.setText("延期" + dataList.get(position).getInterval_time() + "天");
        holder.t3.setText(dataList.get(position).getOperation_group());
        holder.t4.setText(dataList.get(position).getReason());
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setNotifyDataSetChangedList(ArrayList<AssistNodeNote> mAssistNodeNote) {
        this.mAssistNodeNote = mAssistNodeNote;
        this.notifyDataSetChanged();
    }

    class ViewHolderChild {
        public TextView t1;
        public TextView t2;
        public TextView t3;
        public TextView t4;
    }

}
