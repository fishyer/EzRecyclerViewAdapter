package com.ezbuy.adapter;

/**
 * 布局包装类
 */
public class LayoutWrapper<T> {

    private int layoutId;//布局id
    private int spanSize;//布局所占列数
    private T data;//数据源
    private DataDisplayer<T> holder;//控制器-负责将数据源绑定到布局上，并设置监听
    private int flag;//额外的标记位
    private int group;//额外的标记位,表示组

    //默认每个Item占spanSize列
    public LayoutWrapper(int spanSize, int flag, int group, T data, DataDisplayer<T> holder) {
        this.layoutId = holder.getLayoutId();
        this.spanSize = spanSize;
        this.data = data;
        this.flag = flag;
        this.group = group;
        this.holder = holder;
    }

    //默认每个Item占spanSize列
    public LayoutWrapper(int spanSize, int flag, T data, DataDisplayer<T> holder) {
        this.layoutId = holder.getLayoutId();
        this.spanSize = spanSize;
        this.data = data;
        this.flag = flag;
        this.holder = holder;
    }

    //默认每个Item占spanSize列
    public LayoutWrapper(int spanSize, T data, DataDisplayer<T> holder) {
        this.layoutId = holder.getLayoutId();
        this.spanSize = spanSize;
        this.data = data;
        this.holder = holder;
    }

    //默认每个Item占一列
    public LayoutWrapper(T data, DataDisplayer<T> holder) {
        this.layoutId = holder.getLayoutId();
        this.spanSize = 1;
        this.data = data;
        this.holder = holder;
    }

    //默认每个Item占一列
    public LayoutWrapper(T data, int layoutId) {
        this.layoutId = layoutId;
        this.spanSize = 1;
        this.data = data;
        this.holder = null;
    }

    public DataDisplayer<T> getHolder() {
        return holder;
    }

    public void setHolder(DataDisplayer<T> holder) {
        this.holder = holder;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
