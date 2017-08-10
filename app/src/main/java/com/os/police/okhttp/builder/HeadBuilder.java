package com.os.police.okhttp.builder;


import com.os.police.okhttp.OkHttpUtils;
import com.os.police.okhttp.request.OtherRequest;
import com.os.police.okhttp.request.RequestCall;

/**
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
