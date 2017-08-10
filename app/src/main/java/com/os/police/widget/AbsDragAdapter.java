package com.os.police.widget;


import android.content.Context;
import android.view.View;

/**
 * Created by LSH on 2017/7/12.
 * * 使用方式：
 * 1、子类实现 inflateView(int position)渲染GridView中选中的View，用于加入WindowManager
 * 示例代码：
 *
 * @Override public View inflateView(int position) {
 * View temview = mInflater.inflate(R.layout.home_adapter_item, null);
 * <p/>
 * NetworkImageView imageView = (NetworkImageView) temview.findViewById(R.id.img);
 * TextView name = (TextView) temview.findViewById(R.id.name);
 * <p/>
 * GridItem gridItem = getItem(position);
 * <p/>
 * Netroid.displayImage(gridItem.getIcon_url(), imageView);
 * name.setText(gridItem.getTitle());
 * return temview;
 * }
 * 只需要渲染布局中的每个View，不要加事件之类的。
 * ================================================
 * 2、必须在子类的getView方法的最后加上一段代码：
 * if (i == mHidePosition)//i是getView的第一个参数
 * view.setVisibility(View.INVISIBLE);
 * else
 * view.setVisibility(View.VISIBLE);
 * 用于在长按镜像出现的时候GridView中选中的这项 不显示。就好象GridView中的这项被分离出来了。
 */

public abstract class AbsDragAdapter<T> extends AbsAdapter<T> {

    protected int mHidePosition = -1;

    public AbsDragAdapter(Context context) {
        super(context);
    }

    /**
     * 设置某项隐藏
     *
     * @param position
     */
    public void setItemHide(int position) {
        this.mHidePosition = position;
        notifyDataSetChanged();
    }

    //渲染GridView选中的Item,只需要渲染出View就可以，比如title，image等等 只是一个镜像
    public abstract View inflateView(int position);

}
