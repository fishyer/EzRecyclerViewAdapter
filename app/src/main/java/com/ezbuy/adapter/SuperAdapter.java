package com.ezbuy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 多布局多列的Adapter
 * ---------------
 * 复杂数据类型，使用LayoutWrapper包装
 */
public class SuperAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<LayoutWrapper> data = new ArrayList<>();
    private Map<Integer, Integer> layoutMap = new HashMap<>();
    private SparseArray<Boolean> listenerMap = new SparseArray<>();

    public SuperAdapter(Context context, int[] layoutIds) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < layoutIds.length; i++) {
            layoutMap.put(i, layoutIds[i]);
        }
    }

    public SuperAdapter(Context context, List<Integer> layoutIds) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < layoutIds.size(); i++) {
            layoutMap.put(i, layoutIds.get(i));
        }
    }

    public SuperAdapter(DataDisplayer... dataDisplayers) {
        if (dataDisplayers.length == 0) {
            throw new RuntimeException("dataDisplayers 至少要有一个");
        }
        this.context = dataDisplayers[0].getContext();
        this.inflater = LayoutInflater.from(context);
        int i = 0;
        for (DataDisplayer displayer : dataDisplayers) {
            int layoutId = displayer.getLayoutId();
            layoutMap.put(i, layoutId);
            i++;
        }
    }

    public Context getContext() {
        return context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder = new BaseViewHolder(inflater.inflate(getLayoutId(viewType), parent, false));
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                LayoutWrapper wrapper = data.get(position);
                DataDisplayer dataDisplayer = wrapper.getHolder();
                if (dataDisplayer != null) {
                    dataDisplayer.onItemClick(holder, wrapper, wrapper.getData(), position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        LayoutWrapper wrapper = data.get(position);
        DataDisplayer dataDisplayer = wrapper.getHolder();
        if (dataDisplayer != null) {
            dataDisplayer.bindData(holder, wrapper, wrapper.getData(), position);
            boolean hasListener = listenerMap.get(position, false);
            if (!hasListener) {
                dataDisplayer.bindListener(holder, wrapper, wrapper.getData(), position);
                listenerMap.put(position, true);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //LayoutId转ViewType
    public int getViewType(int position) {
        LayoutWrapper wrapper = data.get(position);
        Iterator iter = layoutMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (val == wrapper.getLayoutId()) {
                return key;
            }
        }
        return 0;
    }

    public List<LayoutWrapper> getData() {
        return data;
    }

    //ViewType转LayoutId
    public int getLayoutId(int viewType) {
        return layoutMap.get(viewType);
    }

    //设置数据源
    public void setData(List<LayoutWrapper> list) {
        if (list == null) {
            return;
        }
        this.data = list;
        notifyDataSetChanged();
    }

    //设置更多数据源
    public void setMoreData(List<LayoutWrapper> list) {
        if (list == null) {
            return;
        }
        int start = data.size();
        this.data.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }

}
