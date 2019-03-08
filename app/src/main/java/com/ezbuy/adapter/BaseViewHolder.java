package com.ezbuy.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * ViewHolder基类
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private Activity activity;

    public BaseViewHolder(View view) {
        super(view);
        if (activity instanceof Activity) {
            activity = (Activity) view.getContext();
        }
        this.views = new SparseArray<>();
    }

    public Activity getActivity() {
        return activity;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getRootView() {
        return itemView;
    }

    public void setHint(int tvId, String text) {
        TextView tv = getView(tvId);
        tv.setHint(text);
    }

    public void setText(int tvId, String text) {
        TextView tv = getView(tvId);
        if (text != null) {
            tv.setText(text);
        }
    }

    public void setText(int tvId, int text) {
        TextView tv = getView(tvId);
        tv.setText(String.valueOf(text));
    }

    public void setImage(int ivId, int iconRes) {
        ImageView iv = getView(ivId);
        iv.setImageResource(iconRes);
    }

    public void setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
    }

    public void setShow(int viewId, boolean show) {
        View view = getView(viewId);
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
    }

    public void setBackgroud(int viewId, int drawableId) {
        View view = getView(viewId);
        view.setBackgroundResource(drawableId);
    }

}