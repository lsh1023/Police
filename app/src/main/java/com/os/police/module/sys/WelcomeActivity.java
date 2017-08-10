package com.os.police.module.sys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.os.police.R;
import com.os.police.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
