package com.rinkosen.mygank.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rinkousen on 17/10/17.
 */

public class NetClient {
    public static final String HOST = "http://gank.io/api/";

    private static GankApi gankApi;

    protected static final Object monitor = new Object();

    private static Retrofit retrofit;

    private NetClient(){

    }

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static GankApi getGankApi() {
        synchronized (monitor){
            if(gankApi == null){
                gankApi = retrofit.create(GankApi.class);
            }
            return gankApi;
        }
    }
}
