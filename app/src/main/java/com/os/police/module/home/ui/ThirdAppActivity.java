package com.os.police.module.home.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.os.greendao.ThirdAppInfoDaoDao;
import com.os.police.R;
import com.os.police.base.BaseActivity;
import com.os.police.data.GreenDaoManagerUtils;
import com.os.police.data.ThirdAppInfoDao;
import com.os.police.module.home.adapter.ThirdAppAdapter;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * created by on LSH 2017/7/24.
 * 首页第三方链接的列表展示
 */

public class ThirdAppActivity extends BaseActivity {

    private ListView mListView;
    private ThirdAppAdapter thirdAppAdapter;
    private ArrayList<ThirdAppInfoDao> list;
    private ThirdAppInfoDaoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        thirdAppAdapter = new ThirdAppAdapter(this);
        mListView = (ListView) findViewById(R.id.lisView);

        dao = GreenDaoManagerUtils.getInstance()
                .getDaoSession().getThirdAppInfoDaoDao();
        list = (ArrayList<ThirdAppInfoDao>) dao.queryBuilder().build().list();
        thirdAppAdapter = new ThirdAppAdapter(ThirdAppActivity.this);
        thirdAppAdapter.setmList(list);
        mListView.setAdapter(thirdAppAdapter);
        thirdAppAdapter.notifyDataSetChanged();


    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_third_app;
    }
}
