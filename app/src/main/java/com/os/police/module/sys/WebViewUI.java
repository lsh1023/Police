package com.os.police.module.sys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.os.police.R;
import com.os.police.base.BaseActivity;
import com.os.police.utils.AppLogMessageMgr;
import com.os.police.utils.AppToastMgr;

/**
 * Created by LSH on 2017/7/11.
 * 网络加载信息
 */

public class WebViewUI extends BaseActivity {

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    private Toolbar mToolbar;
    private TextView mTitleTextView;

    private String title, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(R.id.toolbar_title);
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewUI.this.finish();
            }
        });

        long p = System.currentTimeMillis();

        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(getUrl());

        mAgentWeb.getLoader().loadUrl(getUrl());

        long n = System.currentTimeMillis();
        Log.i("LSH", "init used time:" + (n - p));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view_ui;
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        //        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//            if (url.startsWith("intent://") && url.contains("com.youku.phone")) {
//                return true;
//            } else if (isAlipay(view, url)) {
//                return true;
//            }
//            return false;
//        }
    };

    private boolean isAlipay(final WebView view, String url) {
        final PayTask task = new PayTask(getParent());
        final String ex = task.fetchOrderInfoFromH5PayUrl(url);
        Log.i("LSH", "alipay:" + ex);
        if (!TextUtils.isEmpty(ex)) {
            System.out.println("paytask:::::" + url);
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("payTask:::" + ex);
                    final H5PayResultModel result = task.h5Pay(ex, true);
                    if (!TextUtils.isEmpty(result.getReturnUrl())) {
                        getParent().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                view.loadUrl(result.getReturnUrl());
                            }
                        });
                    }
                }
            }).start();

            return true;
        }
        return false;
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.i("LSH", "progress:" + newProgress);
        }
    };

    public String getUrl() {
        if (url == null) {
            AppToastMgr.ToastShortCenter(getApplicationContext(), "网络错误");
        }
        return url;
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null)
                mTitleTextView.setText(title);
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("LSH", "result:" + requestCode + " result:" + resultCode);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
