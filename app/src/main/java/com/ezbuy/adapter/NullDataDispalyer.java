package com.ezbuy.adapter;

import android.content.Context;

/**
 * 作者：余天然 on 2017/6/7 上午11:52
 */
public class NullDataDispalyer extends DataDisplayer {
    public NullDataDispalyer(Context activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, LayoutWrapper wrapper, Object item, int position) {

    }
}
