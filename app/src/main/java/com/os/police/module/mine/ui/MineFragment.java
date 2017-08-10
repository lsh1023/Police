package com.os.police.module.mine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.os.police.R;
import com.os.police.base.BaseFragment;
import com.os.police.utils.AppToastMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by LSH on 2017/7/11.
 * 个人中心
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.ll_user_phone)
    LinearLayout llUserPhone;
    @BindView(R.id.ll_my_business)
    LinearLayout llMyBusiness;
    @BindView(R.id.ll_friend_recommend)
    LinearLayout llFriendRecommend;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    @BindView(R.id.ll_sys_set)
    LinearLayout llSysSet;
    @BindView(R.id.ll_new_guide)
    LinearLayout llNewGuide;
    @BindView(R.id.ll_check_version)
    LinearLayout llCheckVersion;
    @BindView(R.id.ll_about_help)
    LinearLayout llAboutHelp;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;


    @Override
    public void initView(View mView) {
        tvTitle.setText(R.string.tab_mine);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragemnt_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_my_business, R.id.ll_friend_recommend, R.id.ll_feed_back, R.id.ll_sys_set, R.id.ll_new_guide, R.id.ll_check_version, R.id.ll_about_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_business:
                AppToastMgr.ToastShortCenter(getActivity(), "我的业务");
                break;
            case R.id.ll_friend_recommend:
                AppToastMgr.ToastShortCenter(getActivity(), "好友推荐");
                break;
            case R.id.ll_feed_back:
                AppToastMgr.ToastShortCenter(getActivity(), "意见反馈");
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.ll_sys_set:
                AppToastMgr.ToastShortCenter(getActivity(), "系统设置");
                break;
            case R.id.ll_new_guide:
                AppToastMgr.ToastShortCenter(getActivity(), "新手指导");
                break;
            case R.id.ll_check_version:
                AppToastMgr.ToastShortCenter(getActivity(), "检查更新");
                break;
            case R.id.ll_about_help:
                AppToastMgr.ToastShortCenter(getActivity(), "关于帮助");
                break;
        }
    }
}
