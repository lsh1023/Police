package com.os.police.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by LSH on 2017/7/11.
 * 基础的Fragment 为了防止Fragment包出差
 */

public abstract class BaseFragment extends Fragment {

    private View mView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, mView);
            initData();
            initView(mView);
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    public abstract void initView(View mView);

    public abstract void initData();

    public abstract int getLayoutId();
}
