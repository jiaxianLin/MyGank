package com.rinkosen.mygank.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rinkosen.mygank.R;
import com.rinkosen.mygank.base.App;
import com.rinkosen.mygank.entity.Meizi;
import com.rinkosen.mygank.mvp.model.MeiziData;
import com.rinkosen.mygank.ui.activity.GankActivity;
import com.rinkosen.mygank.ui.activity.MeiziActivity;
import com.rinkosen.mygank.ui.widget.RatioImageView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rinkousen on 17/10/17.
 */

public class MainAdapter extends BaseQuickAdapter<Meizi, BaseViewHolder> {

    public MainAdapter(Context context, @Nullable List<Meizi> data) {
        super(R.layout.item_meizi, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Meizi item) {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        final RatioImageView iv_meizi = helper.getView(R.id.iv_meizi);
        iv_meizi.setOriginalSize(300, 150);
        iv_meizi.setBackgroundColor(Color.argb(204, red, green, blue));
        Glide.with(mContext).load(item.url).into(iv_meizi);

        helper.setText(R.id.tv_who, item.who);
        helper.setText(R.id.tv_avatar, TextUtils.isEmpty(item.who)?"":item.who.substring(0, 1).toUpperCase());
        helper.setText(R.id.tv_desc, item.desc);
//        helper.setText(R.id.tv_time, item.publishedAt.toString());
        RelativeLayout rl = helper.getView(R.id.rl_gank);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getgApp().setShareDrawble(iv_meizi.getDrawable());
                Intent intent = new Intent(mContext, GankActivity.class);
                intent.putExtra("meizi", item);
                mContext.startActivity(intent);
            }
        });

        helper.addOnClickListener(R.id.iv_meizi);

        iv_meizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MeiziActivity.class);
                intent.putExtra("meizi", item);
                mContext.startActivity(intent);

            }
        });
    }
}
