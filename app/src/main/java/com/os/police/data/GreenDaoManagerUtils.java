package com.os.police.data;

import com.os.greendao.DaoMaster;
import com.os.greendao.DaoSession;
import com.os.police.application.BaseApplication;

/**
 * Created by LSH on 2017/7/24.
 * GreenDao的工具类
 */

public class GreenDaoManagerUtils {

    private static GreenDaoManagerUtils mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    //数据库的初始化
    public GreenDaoManagerUtils() {
        //此处为自己需要处理的表
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), "thirdapp-db");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManagerUtils getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManagerUtils.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManagerUtils();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

}
