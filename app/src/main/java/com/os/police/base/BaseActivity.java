package com.os.police.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.os.police.R;
import com.os.police.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Created by LSH on 2017/7/11.
 * 基类activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private TextView tvDetailTitle;
    private ImageView btnDetailBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();
        initView();


    }


    //数据初始化完成操作
    protected abstract void initView();

    //界面加载类
    protected abstract void initData();

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public void initLceView(View view) {
//
//        if (tvDetailTitle == null) {
//            tvDetailTitle = (TextView) view.findViewById(R.id.tv_title);
//        }
//        if (btnDetailBack == null) {
//            btnDetailBack = (ImageView) view.findViewById(R.id.btn_detail_back);
//        }
//        if (tvDetailTitle == null) {
//            throw new NullPointerException("txtDetailTitle is not null!");
//        }
//        if (btnDetailBack == null) {
//            throw new NullPointerException("btnDetailBack is not null!");
//        }
//    }
//
//    //设置标题
//    public void setTitle(String title) {
//        try {
//            initLceView(getWindow().getDecorView());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        tvDetailTitle.setText(title);
//        btnDetailBack.setVisibility(View.VISIBLE);
//        btnDetailBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onContentChanged() {
//        super.onContentChanged();
//        setTitle(R.string.app_name);
//    }
}
