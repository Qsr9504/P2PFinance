package com.example.qsr.p2pfinance.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**************************************
 * FileName : com.example.qsr.p2pfinance.base
 * Author : qsr
 * Time : 2016/7/20 12:53
 * Description :
 **************************************/
public abstract class SimpleBaseAdapter<T> extends BaseAdapter{
    protected List<T> list;
    public SimpleBaseAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getYourView(position, convertView, parent);
    }

    public abstract View getYourView(int position, View convertView, ViewGroup parent);
}
