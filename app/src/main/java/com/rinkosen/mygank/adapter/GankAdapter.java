package com.rinkosen.mygank.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.entity.Gank;
import com.rinkosen.mygank.util.StringUtil;

import java.util.List;

/**
 * Created by rinkousen on 17/10/26.
 */

public class GankAdapter extends BaseQuickAdapter<Gank, BaseViewHolder> {

    public GankAdapter(@Nullable List<Gank> data) {
        super(R.layout.item_gank, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Gank item) {
        TextView tv_category = helper.getView(R.id.tv_category);
        TextView tv_desc = helper.getView(R.id.tv_gank_desc);
        tv_category.setText(item.type);
        tv_desc.setText(StringUtil.getGankStyleStr(item));

    }
}
