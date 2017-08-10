package com.os.police.module.home.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.os.greendao.ThirdAppInfoDaoDao;
import com.os.greendao.UserThirdAppOrderDaoDao;
import com.os.police.R;
import com.os.police.base.BaseFragment;
import com.os.police.base.IBaseResultListener;
import com.os.police.data.GreenDaoManagerUtils;
import com.os.police.data.ThirdAppInfoDao;
import com.os.police.data.UserThirdAppOrderDao;
import com.os.police.module.home.adapter.HomeGridAdapter;
import com.os.police.module.home.bean.BannerBean;
import com.os.police.module.home.bean.GridItemBean;
import com.os.police.module.home.model.BannerModel;
import com.os.police.module.home.model.HomeGridModel;
import com.os.police.module.sys.WebViewUI;
import com.os.police.utils.AppLogMessageMgr;
import com.os.police.utils.AppToastMgr;
import com.os.police.utils.BaseToolsUtils;
import com.os.police.utils.GlideImageLoader;
import com.os.police.utils.SharePrefManager;
import com.os.police.widget.DragGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by LSH on 2017/7/11.
 * 主页的Fragment
 */

@SuppressLint("ALL")
public class HomeFragment extends BaseFragment {

    //图片轮播有关的信息
    Banner mBanner;
    private List<String> imageUrls;
    private List<String> bannerTitle;
    private BannerModel bannerModel;
    private ArrayList<BannerBean.DataBean> bannerBeanList;

    //自定义GridView有关的信息
    DragGridView mDragView;
    private HomeGridAdapter mGridAdapter;
    private HomeGridModel homeGridModel;
    private ArrayList<GridItemBean.DataBean.AppstoreInfoBean> gridList;
    private ThirdAppInfoDaoDao dao;           //系统数据库
    private UserThirdAppOrderDaoDao orderDao; //用户保存的含有顺序的数据库

    private TextView tvTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initView(View view) {

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(R.string.tab_home);

        /******************初始化轮播图信息，加载图片，点击事件，进入详情界面H5  START ***************/
        mBanner = (Banner) view.findViewById(R.id.banner);
        imageUrls = new ArrayList<>();
        bannerTitle = new ArrayList<>();
        bannerBeanList = new ArrayList<>();
        bannerModel = new BannerModel(getActivity());
        bannerModel.getBannerBeanList(new IBaseResultListener<BannerBean>() {
            @Override
            public void onLoadSuccess(BannerBean bannerBean) {
                if (bannerBean.isSucc()) {
                    bannerBeanList.addAll(bannerBean.getData());
                    for (int i = 0; i < bannerBeanList.size(); i++) {
                        imageUrls.add(bannerBeanList.get(i).getFILEPATH());
                        bannerTitle.add(bannerBeanList.get(i).getTITLE());
                    }
                    mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                    mBanner.isAutoPlay(true);
                    mBanner.setDelayTime(2000);
                    mBanner.setBannerAnimation(Transformer.DepthPage);
                    mBanner.setImageLoader(new GlideImageLoader());
                    mBanner.setImages(imageUrls);
                    mBanner.setBannerTitles(bannerTitle);
                    mBanner.start();
                }
            }

            @Override
            public void onLoadFailed(String error) {
                AppToastMgr.ToastShortCenter(getActivity(), "网络错误");
                AppLogMessageMgr.d("HomeFragment", error.toString());
            }
        });

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewUI.class);
                String guid = bannerBeanList.get(position).getGUID();
                String detail = "http://www.wujiangfawu.gov.cn:8002/mainpages/app/NewsDetails?guid=" + guid;
                intent.putExtra("title", "热点新闻");
                intent.putExtra("url", detail);
                startActivity(intent);
            }
        });
        /***************************** END **********************************/


        /*************************** 获取底部第三方信息 *******************************************/
        //初始化GreenDao
        mGridAdapter = new HomeGridAdapter(getActivity());
        mDragView = (DragGridView) view.findViewById(R.id.drag_view);
        //系统
        dao = GreenDaoManagerUtils.getInstance().getDaoSession().getThirdAppInfoDaoDao();
        //用户
        orderDao = GreenDaoManagerUtils.getInstance().getDaoSession().getUserThirdAppOrderDaoDao();
        gridList = new ArrayList<>();

        homeGridModel = new HomeGridModel(getActivity());
        homeGridModel.getGridViewInfo(new IBaseResultListener<GridItemBean>() {
            @Override
            public void onLoadSuccess(GridItemBean gridItemBean) {
                if (gridItemBean.isSucc()) {

                    gridList.clear();
                    gridList.addAll(gridItemBean.getData().getAppstoreInfo());
                    for (int i = 0; i < gridList.size(); i++) {
                        ThirdAppInfoDao thirdAppInfoDao = new ThirdAppInfoDao();
                        thirdAppInfoDao.setAppId(Long.valueOf(i));
                        thirdAppInfoDao.setTitle(gridList.get(i).getAPPTITLE());
                        thirdAppInfoDao.setIcon(gridList.get(i).getAPPIMGURL());
                        thirdAppInfoDao.setStartPage(gridList.get(i).getAPPURL());
                        Log.e("TAG", gridList.get(i).getAPPURL());
                        thirdAppInfoDao.setInstall("移除");
                        //dao.insert(thirdAppInfoDao);//该数据库为系统数据库，不是用户保存删除的数据库
                        //使用insertOrReplace代替insert是为了防止数据库插入式出现UNIQUE 的问题
                        dao.insertOrReplace(thirdAppInfoDao);
                        insertUserDao(i); //该数据库为用户保存删除（有顺序的数据库）
                    }
                    notifyGridView();
                }
                refreshThirdApp();
            }

            @Override
            public void onLoadFailed(String error) {
                AppToastMgr.ToastShortCenter(getActivity(), "网络错误");
            }
        });

        initDataMore();

    }

    @Override
    public void initData() {

    }

    public void initDataMore() {

        mGridAdapter.setmList(gridList);
        mDragView.setAdapter(mGridAdapter);
        mDragView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridAdapter.setRowHeight((int) (BaseToolsUtils.getWindowsWidth(getActivity()) / 4 * 0.8));
        mDragView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridItemBean.DataBean.AppstoreInfoBean gridItem = gridList.get(position);
                Log.e("LSH", gridList.get(position).toString());
                if (gridItem == null || (TextUtils.isEmpty(gridItem.getAPPTITLE())))
                    return;
                //删除的相关逻辑处理
                if (mGridAdapter.getDeleteIndex() >= 0) {
                    mGridAdapter.setDeleteIndex(-1);
                    mGridAdapter.notifyDataSetChanged();
                    return;
                }
                if (gridItem.getAPPTITLE().equals("添加更多")) {
                    getActivity().startActivity(new Intent(getActivity(), ThirdAppActivity.class));
                    return;
                }

                Intent intent = new Intent(getActivity(), WebViewUI.class);
                intent.putExtra("title", gridItem.getAPPTITLE());
                intent.putExtra("url", gridItem.getAPPURL());
                getActivity().startActivity(intent);

            }
        });
        mDragView.setOnChangeListener(new DragGridView.OnChangeListener() {
            @Override
            public boolean onChange(int from, int to) {
                //需求：堵路移车和地图服务 和 “添加更多”按钮（以及之后的按钮）都不可以移动
                if (mGridAdapter != null) {
                    if (to <= 1 || to >= mGridAdapter.getUnMoveIndex()) {
                        return false;
                    }
                }
                if (from < 0) {
                    return false;
                }
                //调换顺序
                GridItemBean.DataBean.AppstoreInfoBean temp = gridList.get(from);
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(gridList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(gridList, i, i - 1);
                    }
                }
                gridList.set(to, temp);
                mGridAdapter.setDeleteIndex(to);
                // 设置新到的item隐藏，不用调用notifyDataSetChanged来刷新界面，因为setItemHide方法里面调用了
                mGridAdapter.setItemHide(to);
                //实时保存APP的顺序(简单粗暴,保证顺序没问题)
                orderDao.deleteAll();
                for (int i = 0; i < gridList.size(); i++) {
                    if (gridList.get(i).getAPPTITLE().equals("添加更多")) {
                        break;
                    }
                    //将所有Item插入到用户保存顺序的数据库中
                    insertUserDao(i);
                }
                return true;
            }

            @Override
            public void afterChange() {
                if (mGridAdapter != null) {
                    mGridAdapter.setDeleteIndex(-1);
                    mGridAdapter.setItemHide(-1);
                }
            }
        });

        mDragView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //需要进行判断是否可以删除
                        if (gridList.get(position).getAPPTITLE().equals("添加更多") || TextUtils.isEmpty(gridList.get(position).getAPPTITLE())) {
                            return true;
                        }
                        mGridAdapter.setDeleteIndex(position);
                        mGridAdapter.notifyDataSetChanged();
                        return true;
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    /**
     * 将所有的Item插入到用户保存的数据库中
     *
     * @param i
     */
    public void insertUserDao(int i) {
        UserThirdAppOrderDao userThirdAppOrderDao = new UserThirdAppOrderDao();
        userThirdAppOrderDao.setAppId(Long.valueOf(i));
        userThirdAppOrderDao.setTitle(gridList.get(i).getAPPTITLE());
        userThirdAppOrderDao.setIcon(gridList.get(i).getAPPIMGURL());
        userThirdAppOrderDao.setStartPage(gridList.get(i).getAPPURL());
//        orderDao.insert(userThirdAppOrderDao);
        orderDao.insertOrReplace(userThirdAppOrderDao);
    }


    /**
     * 刷新第三方连接的顺序(GridView的顺序)
     */
    private void refreshThirdApp() {
        //------------------从用户保存顺序的数据库中读取------------
        try {
            gridList.clear();
            if (orderDao.queryBuilder().build().list().size() > 0) {
                for (int i = 0; i < orderDao.queryBuilder().build().list().size(); i++) {
                    GridItemBean.DataBean.AppstoreInfoBean dateBean = new GridItemBean.DataBean.AppstoreInfoBean();
                    dateBean.setAPPIMGURL(orderDao.queryBuilder().build().list().get(i).getIcon());
                    dateBean.setAPPTITLE(orderDao.queryBuilder().build().list().get(i).getTitle());
                    dateBean.setAPPURL(orderDao.queryBuilder().build().list().get(i).getUrl());
                    dateBean.setDeletable(orderDao.queryBuilder().build().list().get(i).getDeletable());
                    dateBean.setAPPURL(orderDao.queryBuilder().build().list().get(i).getStartPage());
                    gridList.add(dateBean);
                }
            }
            notifyGridView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置添加更多按钮，添加空白格
     */
    public void notifyGridView() {
        GridItemBean.DataBean.AppstoreInfoBean add_more = new GridItemBean.DataBean.AppstoreInfoBean();
        add_more.setAPPTITLE("添加更多");
        add_more.setDeletable(false);
        add_more.setAPPIMGURL(R.drawable.icon_add_gray + "");
        gridList.add(add_more);
        Log.e("zlz", gridList.size() + "last");
        //增加空格行
        int size = gridList.size();
        int null_count = 4 - size % 4;
        if (null_count != 4) {
            for (int k = 0; k < null_count; k++) {
                GridItemBean.DataBean.AppstoreInfoBean item = new GridItemBean.DataBean.AppstoreInfoBean();
                item.setAPPTITLE("");
                item.setDeletable(false);
                gridList.add(item);
            }
        }
        mGridAdapter.setmList(gridList);
        mGridAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
