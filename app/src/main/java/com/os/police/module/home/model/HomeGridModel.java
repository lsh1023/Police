package com.os.police.module.home.model;

import android.content.Context;

import com.google.gson.Gson;
import com.os.police.base.AbsIBaseModelImpl;
import com.os.police.base.IBaseResultListener;
import com.os.police.module.home.bean.GridItemBean;
import com.os.police.module.home.bean.RequestGridItemBean;
import com.os.police.okhttp.OkHttpUtils;
import com.os.police.okhttp.callback.Callback;
import com.os.police.utils.UrlUtil;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;


/**
 * Created by LSH on 2017/7/14.
 * 主页第三方的网络请求
 */

public class HomeGridModel extends AbsIBaseModelImpl {

    public HomeGridModel(Context context) {
        super(context);
    }

    public void getGridViewInfo(final IBaseResultListener<GridItemBean> listener) {
        OkHttpUtils.postString()
                .url(UrlUtil.http("api/AppStore/GetAppStoreListFirst"))
                .content(new Gson().toJson(new RequestGridItemBean("oceansoft", 0, "123456", "1", "100", "", "", "", "")))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new GridItemCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        listener.onLoadFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(GridItemBean response, int id) {
                        listener.onLoadSuccess(response);
                    }
                });
    }


    abstract class GridItemCallBack extends Callback<GridItemBean> {
        @Override
        public GridItemBean parseNetworkResponse(Response response, int id) throws Exception {
            String s = response.body().string();
            GridItemBean griditembean = new Gson().fromJson(s, GridItemBean.class);
            return griditembean;
        }
    }

}
