package com.hbw.library;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machuang
 * @Description: Adapter的基类
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    protected List<T> dataList;

    public AbsBaseAdapter(List<T> dataList) {
        if (dataList != null && dataList.size() > 0) {
            this.dataList = dataList;
        } else {
            this.dataList = new ArrayList<T>();
        }

    }

    public int getCount() {
        return dataList.size();
    }

    public T getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // 这个方法必须要实现,item
    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

    /**
     * 防止dataList引用断掉
     *
     * @param dataList
     */
    public void notifyDataSetChanged(List<T> dataList) {
        if (dataList != null && dataList.size() > 0) {
            this.dataList = dataList;
        } else {
            this.dataList = new ArrayList<T>();
        }
        notifyDataSetChanged();
    }

    /**
     * 分页加载数据时候用到
     *
     * @param dataList
     */
    public void notifyDataSetChangedForLoad(List<T> dataList) {
        if (dataList != null && dataList.size() > 0) {
            this.dataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    protected List<T> getDataList() {
        return dataList;
    }

}
