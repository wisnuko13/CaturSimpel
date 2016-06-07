package com.wisnu.catursimpel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by private on 07/06/2016.
 */
public abstract class CustomAdapter<T> extends BaseAdapter {

    private List<T> mTList;
    private List<T> shown;
    private FilterAdapter<T> filter;

    public CustomAdapter(){
        super();
        this.mTList = new ArrayList<T>();
        this.shown = new ArrayList<T>();
    }

    public abstract ViewHolder<T> createHolder();

    @Override
    public int getCount() {
        return shown.size();
    }

    @Override
    public T getItem(int position) {
        return shown.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder<T> holder = null;
        if(convertView == null){
            holder = createHolder();
            convertView = holder.createView(parent.getContext());
            convertView.setTag(holder);
        }else {
            holder = extractHolder(convertView);
        }
        holder.bind(position, getItem(position));
        return convertView;
    }

    @SuppressWarnings("uncheck")
    public ViewHolder<T> extractHolder(View view){
        return (ViewHolder<T>) view.getTag();
    }

    public void setList(List<T> list) {
        if (list == null) {
            mTList = Collections.<T>emptyList();
        } else {
            mTList = list;
        }
    }


    @Override
    public void notifyDataSetChanged() {
        if (filter == null) {
            shown = mTList;
        } else {
            shown.clear();
            for (T item : mTList) {
                if (filter.isFilter(item)) {
                    shown.add(item);
                }
            }
        }
        super.notifyDataSetChanged();
    }

    public static abstract class ViewHolder<T>{
        public abstract View createView(Context context);
        public abstract void bind(int pos, T object);
    }
}
