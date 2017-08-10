package com.os.police.module.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.os.police.R;
import com.os.police.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LSH on 2017/7/12.
 * 新闻中心
 */

public class NewsFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    @Override
    public void initView(View mView) {
        tvTitle.setText(R.string.tab_news);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragemnt_news;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
