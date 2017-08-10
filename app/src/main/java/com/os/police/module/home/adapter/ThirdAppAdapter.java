package com.os.police.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.os.police.utils.AppToastMgr;
import com.os.police.widget.AbsAdapter;


/**
 * Created by LSH on 2017/7/24.
 * 底部第三方链接的adapter
 */

public class ThirdAppAdapter extends AbsAdapter<ThirdAppInfoDao> {

    private ThirdAppInfoDaoDao dao;
    private UserThirdAppOrderDaoDao orderDao;

    public ThirdAppAdapter(Context context) {
        super(context);
        dao = GreenDaoManagerUtils.getInstance()
                .getDaoSession().getThirdAppInfoDaoDao();
        orderDao = GreenDaoManagerUtils.getInstance()
                .getDaoSession().getUserThirdAppOrderDaoDao();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.third_app_item, null);
            holder.btnDown = (Button) convertView.findViewById(R.id.btn_download);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_item);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ThirdAppInfoDao thirdAppInfoDao=mList.get(position);
        holder.tvTitle.setText(thirdAppInfoDao.getTitle());
        //从系统数据库中读取
        holder.btnDown.setText(dao.queryBuilder().build().list().get(position).getInstall());
        Glide.with(mContext).load(thirdAppInfoDao.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.icon);
        //设置点击事件
        final ViewHolder finalH = holder;
        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一步先查询到某一条
                ThirdAppInfoDao thirdAppInfoDao1=dao.queryBuilder()
                        .where(ThirdAppInfoDaoDao.Properties.Title.eq(finalH.tvTitle.getText()))
                        .build().unique();
                if (finalH.btnDown.getText().equals("移除")){
                    finalH.btnDown.setText("安装");
                    //保存到系统数据库中
                    //第二步再修改
                    if (thirdAppInfoDao1!=null){
                        thirdAppInfoDao1.setInstall("安装");
                        dao.update(thirdAppInfoDao1);
                    }else{
                        AppToastMgr.ToastShortCenter(mContext,"用户不存在");
                    }

                    //用户的数据库,删除掉(不能根据position来删除，这样删除不准确)
                    //先查找,在删除
                    UserThirdAppOrderDao userDao1=orderDao.queryBuilder()
                            .where(UserThirdAppOrderDaoDao.Properties.Title.eq(finalH.tvTitle.getText()))
                            .build().unique();
                    if (userDao1!=null){
                        orderDao.delete(userDao1);
                    }else{
//                        ToastUtil.showToast(mContext,"用户不存在！");
                    }

                }else {
                    finalH.btnDown.setText("移除");
                    //第二步再修改
                    if (thirdAppInfoDao1!=null){
                        thirdAppInfoDao1.setInstall("移除");
                        dao.update(thirdAppInfoDao1);
                    }
                    //用户的数据库添加
                    UserThirdAppOrderDao userDao=new UserThirdAppOrderDao();
                    userDao.setInstall("移除");
                    userDao.setUrl(thirdAppInfoDao1.getUrl());
                    userDao.setStartPage(thirdAppInfoDao1.getStartPage());
                    userDao.setTitle(thirdAppInfoDao1.getTitle());
                    userDao.setIcon(thirdAppInfoDao1.getIcon());
                    userDao.setDeletable(thirdAppInfoDao1.getDeletable());
                    orderDao.insert(userDao);
                }
            }
        });
        return convertView;
    }


    static class ViewHolder {
        TextView tvTitle;
        Button btnDown;
        ImageView icon;
    }

}
