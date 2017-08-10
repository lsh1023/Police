package com.os.police.module.home.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.os.greendao.ThirdAppInfoDaoDao;
import com.os.greendao.UserThirdAppOrderDaoDao;
import com.os.police.R;
import com.os.police.data.GreenDaoManagerUtils;
import com.os.police.data.ThirdAppInfoDao;
import com.os.police.data.UserThirdAppOrderDao;
import com.os.police.module.home.bean.GridItemBean;
import com.os.police.utils.AppLogMessageMgr;
import com.os.police.utils.GlideUtils;
import com.os.police.widget.AbsDragAdapter;

import static com.os.police.utils.GlideUtils.LOAD_BITMAP;

/**
 * Created by LSH on 2017//14.
 * 首页GridViewAdapter
 */

public class HomeGridAdapter extends AbsDragAdapter<GridItemBean.DataBean.AppstoreInfoBean> {

    private int row_height = 0;
    private int deleteIndex = -1;
    private ThirdAppInfoDaoDao dao; //系统数据库
    private UserThirdAppOrderDaoDao orderDao; //用户保存的含有顺序的数据库

    private int unMoveIndex = Integer.MAX_VALUE;

    public HomeGridAdapter(Context context) {
        super(context);
        dao = GreenDaoManagerUtils.getInstance().getDaoSession().getThirdAppInfoDaoDao();
        orderDao = GreenDaoManagerUtils.getInstance().getDaoSession().getUserThirdAppOrderDaoDao();
    }

    /**
     * 渲染GridView选中的Item,只需要渲染出View就可以，比如title，image等等 只是一个镜像
     *
     * @param position
     * @return
     */
    @Override
    public View inflateView(int position) {
        View tempView = null;
        if (tempView == null) {
            tempView = mInflater.inflate(R.layout.home_adapter_item, null);
        }
        ImageView imageView = (ImageView) tempView.findViewById(R.id.img);
        TextView name = (TextView) tempView.findViewById(R.id.name);

        GridItemBean.DataBean.AppstoreInfoBean gridItem = getItem(position);
        name.setText(gridItem.getAPPTITLE());
        Glide.with(mContext).load(gridItem.getAPPIMGURL()).placeholder(R.drawable.third_app_icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        return tempView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GridItemBean.DataBean.AppstoreInfoBean gridItem = getItem(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_adapter_item, null);
        }
        final ImageView icon = (ImageView) convertView.findViewById(R.id.img);
        final TextView name = (TextView) convertView.findViewById(R.id.name);
        final ImageView img_del = (ImageView) convertView.findViewById(R.id.img_del);
        name.setVisibility(View.VISIBLE);
        icon.setVisibility(View.VISIBLE);
        //设置文字
        name.setText(TextUtils.isEmpty(gridItem.getAPPTITLE()) ? "" : gridItem.getAPPTITLE());
        if (!TextUtils.isEmpty(gridItem.getAPPTITLE())) {
            if (gridItem.getAPPTITLE().equals("添加更多") && TextUtils.isEmpty(gridItem.getAPPIMGURL())) {
                icon.setImageResource(R.drawable.icon_add_gray);
                unMoveIndex = position;
            } else {
                Glide.with(mContext).load(gridItem.getAPPIMGURL())
                        .placeholder(R.drawable.icon_add_gray)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(icon);
            }
        } else {
            //设置空白格的图片不可见
            icon.setVisibility(View.GONE);
        }

        //设置何时显示删除图片
        img_del.setVisibility(deleteIndex == position ? View.VISIBLE : View.GONE);
        img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteIndex = -1;
                //从用户数据库中删除
                UserThirdAppOrderDao userDao = orderDao.queryBuilder()
                        .where(UserThirdAppOrderDaoDao.Properties.Title.eq(name.getText().toString()))
                        .build()
                        .unique();
                if (userDao != null) {
                    orderDao.delete(userDao);
                    AppLogMessageMgr.i("LSH", "用户数据库执行了删除操作");
                }

                //系统数据库中改为安装
                ThirdAppInfoDao thirdAppInfoDao = dao.queryBuilder()
                        .where(ThirdAppInfoDaoDao.Properties.Title.eq(name.getText().toString())
                        ).build().unique();

                if (thirdAppInfoDao != null) {
                    thirdAppInfoDao.setInstall("安装");
                    AppLogMessageMgr.i("LSH", "系统数据库变为安装");
                }
                mList.remove(position);
                for (int i = 0; i < mList.size(); i++) {
                    if (TextUtils.isEmpty(mList.get(i).getAPPTITLE()) || mList.get(i).getAPPTITLE().equals("添加更多")) {
                        mList.remove(i);
                        i--; //动态移除元素注意点
                    }
                }

                GridItemBean.DataBean.AppstoreInfoBean add_more = new GridItemBean.DataBean.AppstoreInfoBean();
                add_more.setAPPTITLE("添加更多");
                mList.add(add_more);
                notifyDataSetChanged();
                //增加空格行
                int size = mList.size();
                int null_count = 4 - size % 4;
                if (null_count != 4) {
                    for (int i = 0; i < null_count; i++) {
                        GridItemBean.DataBean.AppstoreInfoBean item = new GridItemBean.DataBean.AppstoreInfoBean();
                        item.setAPPTITLE("");
                        mList.add(item);
                    }
                }
                notifyDataSetChanged();
            }
        });

        if (deleteIndex == position) {
            img_del.setVisibility(View.VISIBLE);
        } else {
            img_del.setVisibility(View.GONE);
        }

        //设置Item的高度
        if (row_height > 0) {
            convertView.setMinimumHeight(row_height);
        }
        //设置Item隐藏
        if (position == mHidePosition) {
            convertView.setVisibility(View.INVISIBLE);
        } else {
            convertView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    /**
     * 设置Item的最新高度
     *
     * @param height
     */
    public void setRowHeight(int height) {
        row_height = height;
        this.notifyDataSetChanged();
    }
    /**
     * 不可移动的下标，从该下标后面的元素都不可移动
     *
     * @return
     */
    public int getUnMoveIndex() {
        return unMoveIndex;
    }

    public int getDeleteIndex() {
        return deleteIndex;
    }

    public void setDeleteIndex(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

}
