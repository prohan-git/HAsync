package com.example.hxj_tjun.hasync;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.stetho.StethoMiddleware;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * author: HXJ_TJun
 * created on: 2019/1/2 17:10
 * description:
 */
public class HAsyncApp extends Application {

    private static HAsyncApp hAsyncInstance;

    public static HAsyncApp getInstance() {
        return hAsyncInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hAsyncInstance = this;
        init();
    }

    private void init() {
        //初始化Stetho调试工具
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        AsyncHttpClient.getDefaultInstance().getMiddleware().add(new StethoMiddleware());
        //orhanobut/logger
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
