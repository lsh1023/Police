package com.os.police.base;

/**
 * Created by LSH on 2017/7/11.
 */

public interface IBaseResultListener<T> {
    void onLoadSuccess(T t);
    void onLoadFailed(String error);
}