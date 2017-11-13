package com.rinkosen.mygank.mvp.presenter;

import android.content.Context;

import com.rinkosen.mygank.mvp.model.FunnyData;
import com.rinkosen.mygank.mvp.model.MeiziData;
import com.rinkosen.mygank.mvp.view.MainView;
import com.rinkosen.mygank.net.NetClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by rinkousen on 17/10/17.
 */

public class MainPresenter extends BasePresenter {

    private MainView view;

    private Context context;

    public MainPresenter(MainView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void getMeiziData(int page) {
        view.showProgress();
        Observable.zip(NetClient.getGankApi().getMeiziData(page),
                NetClient.getGankApi().getFunnyData(page),
                new Func2<MeiziData, FunnyData, MeiziData>() {
                    @Override
                    public MeiziData call(MeiziData data, FunnyData data2) {
                        return createMeiziDataWithRestDesc(data, data2);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MeiziData>() {
                    @Override
                    public void onCompleted() {
                        view.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(MeiziData data) {
                        view.showMeiziList(data.results);
                    }
                });
    }

    private MeiziData createMeiziDataWithRestDesc(MeiziData meiziData, FunnyData funnyData) {
        int size = Math.min(meiziData.results.size(), funnyData.results.size());
        for (int i = 0; i < size; i++) {
            meiziData.results.get(i).desc = meiziData.results.get(i).desc + "，" + funnyData.results.get(i).desc;
            meiziData.results.get(i).who = funnyData.results.get(i).who;
        }
        return meiziData;
    }
}
