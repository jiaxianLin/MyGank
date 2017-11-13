package com.rinkosen.mygank.net;

import com.rinkosen.mygank.mvp.model.FunnyData;
import com.rinkosen.mygank.mvp.model.GankData;
import com.rinkosen.mygank.mvp.model.MeiziData;
import com.rinkosen.mygank.util.Config;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rinkousen on 17/10/17.
 */

public interface GankApi {

    // http://gank.io/api/data/数据类型/请求个数/第几页
    @GET(value = "data/福利/" + Config.MEIZI_SIZE + "/{page}")
    Observable<MeiziData> getMeiziData(@Path("page") int page);
    @GET("data/休息视频/" + Config.MEIZI_SIZE + "/{page}")
    Observable<FunnyData> getFunnyData(@Path("page") int page);

    //请求某天干货数据
    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

//    //请求不同类型干货（通用）
//    @GET("data/{type}/"+Config.GANK_SIZE+"/{page}")
//    Observable<GanHuoData> getGanHuoData(@Path("type") String type, @Path("page") int page);
}
