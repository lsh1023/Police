package com.os.police.module.sys;

import android.content.Context;
import android.text.TextUtils;

import com.os.police.R;
import com.os.police.application.BaseApplication;

import onekeyshare.OnekeyShare;

/**
 * Created by LSH on 2017/7/14.
 * 分享模块
 */

public class ShareModule {

    private static ShareModule module = null;
    private static final String DEFAULT_INFO = String.valueOf(R.string.app_name);


    public static ShareModule getModule() {
        synchronized (ShareModule.class) {
            if (module == null)
                module = new ShareModule();
        }
        return module;
    }

    private ShareModule(){
        Context context = BaseApplication.getInstance();
//        ShareSDK.initSDK(context);
    }

    /**
     * 接口调用失败等异常情况下
     * 使用默认的文本分享
     */
    public void share(){
        share("", "", "", "");
    }

    /**
     *
     * @param info 分享内容
     * @param title 分享标题
     * @param url  分享链接地址
     * @param pic  分享图片地址
     */
    public void share(String info, String title, String url, String pic){
        Context context = BaseApplication.getInstance();
        OnekeyShare oks = new OnekeyShare();
        // text是分享文本，所有平台都需要这个字段
        oks.setTitle(TextUtils.isEmpty(title) ? DEFAULT_INFO : title);
        oks.setText(TextUtils.isEmpty(info) ? DEFAULT_INFO : info);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数0
        if(!TextUtils.isEmpty(pic))
            oks.setImageUrl(pic);
        if(!TextUtils.isEmpty(url)){
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl(url);
            //titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
            oks.setTitleUrl(url);
        }
        // 启动分享GUI
        oks.show(context);
    }
}
