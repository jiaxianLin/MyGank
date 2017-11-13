package com.rinkosen.mygank.base;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

/**
 * Created by rinkousen on 17/10/16.
 */

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static App gApp;

    private Drawable shareDrawble;

    @Override
    public void onCreate() {
        super.onCreate();
        gApp = this;
        registerActivityLifecycleCallbacks(this);
    }

    public static App getgApp() {
        return gApp;
    }

    public Drawable getShareDrawble() {
        return shareDrawble;
    }

    public void setShareDrawble(Drawable shareDrawble) {
        this.shareDrawble = shareDrawble;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        System.out.println("onActivityCreated" + activity.toString());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        System.out.println("onActivityStarted" + activity.toString());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        System.out.println("onActivityResumed" + activity.toString());
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
