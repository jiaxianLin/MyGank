package com.rinkosen.mygank.mvp.presenter;

import android.content.Context;

import com.rinkosen.mygank.entity.Gank;
import com.rinkosen.mygank.mvp.model.GankData;
import com.rinkosen.mygank.mvp.view.GankView;
import com.rinkosen.mygank.net.NetClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rinkousen on 17/10/18.
 */

public class GankPresenter extends BasePresenter {

    GankView view;

    Context context;

    public GankPresenter(GankView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void getGankData(int year, int month, int day) {
        view.showProgress();
        NetClient.getGankApi().getDailyData(year, month, day)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankData, List<Gank>>() {
                    @Override
                    public List<Gank> call(GankData data) {
                        return addAllData(data);
                    }
                })
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {
                        view.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        view.showGankData(ganks);
                    }
                });
    }

    public List<Gank> addAllData(GankData data){
        List<Gank> list = new ArrayList<>();
        list.addAll(data.results.androidList != null ? data.results.androidList : new ArrayList<Gank>());
        list.addAll(data.results.iOSList != null ? data.results.iOSList : new ArrayList<Gank>());
        list.addAll(data.results.休息视频List != null ? data.results.休息视频List : new ArrayList<Gank>());
        list.addAll(data.results.妹纸List != null ? data.results.妹纸List : new ArrayList<Gank>());
        list.addAll(data.results.前端List != null ? data.results.前端List : new ArrayList<Gank>());
        return list;
    }
}
