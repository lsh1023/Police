package com.os.police.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;
import com.os.police.data.GreenDaoManagerUtils;
import com.os.police.exception.CrashHandler;


/**
 * Created by LSH on 2017/7/11.
 * 初始化类
 */

public class BaseApplication extends Application {

    public static BaseApplication instance;
    private static Context context;
    private CrashHandler crashHandler;

    public BaseApplication() {
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

//        crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);

        MobSDK.init(context);

        MultiDex.install(this);
        //greenDao全局配置,只希望有一个数据库操作对象
        GreenDaoManagerUtils.getInstance();
    }



}
