package com.os.police.base;

/**
 * Created by LSH on 2017/7/14.
 */

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* 抽象Presenter类，提供给Activity与具体业务处理的管理类。
        */
public abstract class BasePresenter<T> {
    protected Reference<T> mViewRefer;

    public void attachView(T view) {
        mViewRefer = new WeakReference<T>(view);
    }

    public T getView() {
        return mViewRefer.get();
    }

    public void detailView() {
        if (mViewRefer != null) {
            mViewRefer.clear();
            mViewRefer = null;
        }
    }

    public void requestData(OutBaseEntity baseEntity) {

    }
}
