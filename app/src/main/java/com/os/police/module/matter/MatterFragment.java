package com.os.police.module.matter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;

import com.just.library.ChromeClientCallbackManager;
import com.just.library.WebDefaultSettingsManager;
import com.just.library.WebSettings;
import com.os.police.R;
import com.os.police.base.BaseFragment;
import com.os.police.module.sys.FragmentKeyDown;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by LSH on 2017/7/12.
 * 事项中心
 */

public class MatterFragment extends BaseFragment implements FragmentKeyDown {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    protected AgentWeb mAgentWeb;


    @Override
    public void initView(View mView) {
        tvTitle.setText(R.string.tab_matter);
    }


    @Override
    public void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_matter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1, 2)
                .setWebSettings(getSettings())
                .setWebViewClient(mWebViewClient)
//                .setReceivedTitleCallback(mCallback)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go(getUrl());

    }

    //获得title
    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (tvTitle != null)
                tvTitle.setText(title);
        }
    };

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

    };

    public WebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    //获得要访问的链接地址
    public String getUrl() {
        String target = "http://www.jd.com";
        return target;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onResume() {
//        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
//        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }
}
