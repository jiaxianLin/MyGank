package com.rinkosen.mygank.mvp.view;

import com.rinkosen.mygank.base.BaseView;
import com.rinkosen.mygank.entity.Gank;

import java.util.List;

/**
 * Created by rinkousen on 17/10/19.
 */

public interface GankView extends BaseView{

    public void showGankData(List<Gank> data);
}