package com.ezbuy.adapter;

import android.content.Context;

/**
 * 数据显示器
 * ----------
 * 将ItemData和ViewHolder进行绑定，并设置监听
 */
public abstract class DataDisplayer<T> {

    private Context context;
    private int layoutId;

    public DataDisplayer(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public abstract void bindData(BaseViewHolder holder, LayoutWrapper<T> wrapper, T item, int position);

    public void bindListener(BaseViewHolder holder, LayoutWrapper<T> wrapper, T item, int position) {
    }

    public void onItemClick(BaseViewHolder holder, LayoutWrapper<T> wrapper, T item, int position) {

    }

    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == oldItemPosition;
    }

    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == oldItemPosition;
    }

}
