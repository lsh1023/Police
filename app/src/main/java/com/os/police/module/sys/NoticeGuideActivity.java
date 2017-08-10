package com.os.police.module.sys;

import android.os.Bundle;
import android.widget.Button;

import com.os.police.R;
import com.os.police.base.BaseActivity;
import com.os.police.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NoticeGuideActivity extends BaseActivity {

    @BindView(R.id.btn_guide_enter)
    Button btnGuideEnter;
    @BindView(R.id.banner_guide)
    Banner bannerGuide;

    List<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_guide;
    }
}
