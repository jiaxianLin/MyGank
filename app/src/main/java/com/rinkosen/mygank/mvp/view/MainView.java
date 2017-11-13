package com.rinkosen.mygank.mvp.view;

import com.rinkosen.mygank.base.BaseView;
import com.rinkosen.mygank.entity.Meizi;

import java.util.List;

/**
 * Created by rinkousen on 17/10/17.
 */

public interface MainView extends BaseView{
    void showMeiziList(List<Meizi> data);
}
