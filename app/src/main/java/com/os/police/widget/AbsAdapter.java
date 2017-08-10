package com.os.police.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by LSH on 2017/7/12.
 * 通用Adapter<T>
 */

public abstract class AbsAdapter<T> extends BaseAdapter {

    protected Context mContext = null;
    protected LayoutInflater mInflater;
    protected List<T> mList = null;

    public AbsAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<T> getmList() {
        return mList;
    }

    public void setmList(List<T> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }
}
