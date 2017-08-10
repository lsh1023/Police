package com.os.police.module.home.model;

import android.content.Context;

import com.google.gson.Gson;
import com.os.police.base.AbsIBaseModelImpl;
import com.os.police.base.IBaseResultListener;
import com.os.police.module.home.bean.BannerBean;
import com.os.police.module.home.bean.BannerRequestBean;
import com.os.police.okhttp.OkHttpUtils;
import com.os.police.okhttp.callback.Callback;
import com.os.police.utils.UrlUtil;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by LSH on 2017/7/11.
 * 轮播图网络请求
 */

public class BannerModel extends AbsIBaseModelImpl {

    public BannerModel(Context context) {
        super(context);
    }

    public void getBannerBeanList(final IBaseResultListener<BannerBean> listener) {
        OkHttpUtils.postString()
                .url(UrlUtil.http("api/NETWORK/NetWork_ListImg"))
                .content(new Gson().toJson(new BannerRequestBean("", "", "", "", "0", "oceansoft", "123456")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new BannerModelCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(BannerBean response, int id) {
                listener.onLoadSuccess(response);
            }
        });

    }


    abstract class BannerModelCallBack extends Callback<BannerBean> {
        @Override
        public BannerBean parseNetworkResponse(Response response, int id) throws Exception {
            String s = response.body().string();
            BannerBean banner = new Gson().fromJson(s, BannerBean.class);
            return banner;
        }

    }


}
