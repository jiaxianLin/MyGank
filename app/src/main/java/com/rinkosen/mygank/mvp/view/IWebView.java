package com.rinkosen.mygank.mvp.view;

import com.rinkosen.mygank.base.BaseView;

/**
 * Created by rinkousen on 17/10/26.
 */

public interface IWebView extends BaseView {

    void showProgress(int progress);

    void setWebTitle(String title);

    void loadFailure();
}
