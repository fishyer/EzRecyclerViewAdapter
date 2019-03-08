package com.ezbuy.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 单布局的Adapter
 * ---------------
 * 数据类型是泛型
 */
public class SingleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    Context context;
    LayoutInflater inflater;
    public List<T> data = new ArrayList<>();
    private int layoutId;
    private DataDisplayer<T> displayer;

    public SingleAdapter(DataDisplayer<T> displayer) {
        this.context = displayer.getContext();
        this.inflater = LayoutInflater.from(context);
        this.layoutId = displayer.getLayoutId();
        this.displayer = displayer;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        final BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        displayer.bindData(holder, null, data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Context getContext() {
        return context;
    }

    public void setData(List<T> list) {
        this.data = list;
        temp = new ArrayList<>(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    protected List<T> temp; // 用于保存修改之前的数据源的副本

    public void notifyDiff() {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return temp.size();
            }

            @Override
            public int getNewListSize() {
                return data.size();
            }

            // 判断是否是同一个 item
            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return displayer.areItemsTheSame(oldItemPosition, newItemPosition);
            }

            // 如果是同一个 item 判断内容是否相同
            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return displayer.areContentsTheSame(oldItemPosition, newItemPosition);
            }
        });
        diffResult.dispatchUpdatesTo(this);
        // 通知刷新了之后，要更新副本数据到最新
        temp.clear();
        temp.addAll(data);
    }
}
