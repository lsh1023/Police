package com.os.police.widget;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.os.police.module.home.adapter.HomeGridAdapter;

/**
 * Created by LSH on 2017/7/11.
 * 自定义GridView加载第三方的链接
 * 可拖拽的GridView
 */
public class DragGridView<T> extends GridView {

    private AbsDragAdapter<T> mAdapter = null;

    private boolean islongClick = false;
    //是否可以拖拽，默认不可以
    private boolean isDrag = false;

    //手指按下的坐标
    private int mDownX, mDownY;

    private int moveX, moveY;

    //正在拖拽的position
    private int mDragPosition;

    //刚开始拖拽的item对应的View
    private View mStartDragItemView = null;

    //用于拖拽的镜像，添加到WindowManager的View
    private View mTempDragView;

    //震动器
    private Vibrator mVibrator;

    private WindowManager mWindowManager;

    //item镜像的布局参数
    private WindowManager.LayoutParams mWindowLayoutParams;


    /**
     * 按下的点到所在item的上边缘的距离
     */
    private int mPoint2ItemTop;

    /**
     * 按下的点到所在item的左边缘的距离
     */
    private int mPoint2ItemLeft;

    /**
     * DragGridView距离屏幕顶部的偏移量
     */
    private int mOffset2Top;

    /**
     * DragGridView距离屏幕左边的偏移量
     */
    private int mOffset2Left;

    /**
     * 状态栏的高度
     */
    private int mStatusHeight;

    /**
     * DragGridView自动向下滚动的边界值
     */
    private int mDownScrollBorder;

    /**
     * DragGridView自动向上滚动的边界值
     */
    private int mUpScrollBorder;

    /**
     * DragGridView自动滚动的速度
     */
    private static final int speed = 20;

    /**
     * item发生变化回调的接口
     */
    private OnChangeListener onChangeListener;


    public DragGridView(Context context) {
        this(context, null);
    }

    public DragGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getStatusHeight(context); //获取状态栏的高度

    }

    private Handler mHandler = new Handler();

    //用来处理是否为长按的Runnable
    private Runnable mLongClickRunnable = new Runnable() {

        @Override
        public void run() {
            isDrag = true; //设置可以拖拽
            mVibrator.vibrate(50); //震动一下
            mStartDragItemView.setVisibility(View.INVISIBLE);//隐藏该item

            //根据我们按下的点显示item镜像
            createDragImage(null, mDownX, mDownY);
        }
    };

    /**
     * 设置回调接口
     *
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    /**
     * ACTION_DOWN时间
     */
    private long downtime = 0;
    /**
     * ACTION_MOVE时间
     */
    private long movetime = 0;

    /**
     * 长按时间标准
     */
    private static final int LONGPRESS_SECONDS = 1000;

    /**
     * 移动阀值，超过这个值就算是移动操作
     */
    private static final int MOVETHRESHOLD = 20;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //true :告诉父控件 我的事件我自己处理
        getParent().requestDisallowInterceptTouchEvent(islongClick);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downtime = System.currentTimeMillis();
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                //根据按下的X,Y坐标获取所点击item的position
                mDragPosition = pointToPosition(mDownX, mDownY);
                //需求要求快测理赔 和 添加之后的item都不可以移动
                // TODO: 2017/7/25
                HomeGridAdapter adapter = (HomeGridAdapter) getAdapter();
                if (adapter != null) {
                    if (mDragPosition <= 1 || mDragPosition >= adapter.getUnMoveIndex()) {
                        return super.dispatchTouchEvent(ev);
                    }
                }

                if (mDragPosition == AdapterView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }

                //根据position获取该item所对应的View
                mStartDragItemView = getChildAt(mDragPosition - getFirstVisiblePosition());

                mPoint2ItemTop = mDownY - mStartDragItemView.getTop();
                mPoint2ItemLeft = mDownX - mStartDragItemView.getLeft();

                mOffset2Top = (int) (ev.getRawY() - mDownY);
                mOffset2Left = (int) (ev.getRawX() - mDownX);

                //获取DragGridView自动向上滚动的偏移量，小于这个值，DragGridView向下滚动
                mDownScrollBorder = getHeight() / 4;
                //获取DragGridView自动向下滚动的偏移量，大于这个值，DragGridView向上滚动
                mUpScrollBorder = getHeight() * 3 / 4;

                break;
            case MotionEvent.ACTION_MOVE:
                movetime = System.currentTimeMillis();

                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                //如果我们在按下的item上面移动，只要不超过item的边界我们就不移除mRunnable
                if (!isTouchInItem(mStartDragItemView, moveX, moveY)) {
                    mHandler.removeCallbacks(mLongClickRunnable);
                    break;
                }

                //判断是否长按 按下的时间大于1000毫秒 并且没有移动
                if (!islongClick && (movetime - downtime > LONGPRESS_SECONDS) && Math.abs(moveX - mDownX) < MOVETHRESHOLD && Math.abs(moveY - mDownY) < MOVETHRESHOLD) {
                    mHandler.postDelayed(mLongClickRunnable, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                mHandler.removeCallbacks(mLongClickRunnable);
                mHandler.removeCallbacks(mScrollRunnable);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 是否点击在GridView的item上面
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isTouchInItem(View dragView, int x, int y) {
        if (dragView == null) return false;
        int leftOffset = dragView.getLeft();
        int topOffset = dragView.getTop();
        if (x < leftOffset || x > leftOffset + dragView.getWidth()) {
            return false;
        }

        if (y < topOffset || y > topOffset + dragView.getHeight()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDrag && mTempDragView != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    moveX = (int) ev.getX();
                    moveY = (int) ev.getY();
                    //拖动item
                    onDragItem(moveX, moveY);
                    break;
                case MotionEvent.ACTION_UP:
                    onStopDrag();
                    isDrag = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 创建拖动的镜像
     *
     * @param bitmap
     * @param downX  按下的点相对父控件的X坐标
     * @param downY  按下的点相对父控件的X坐标
     */
    private void createDragImage(Bitmap bitmap, int downX, int downY) {
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; //图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowLayoutParams.x = downX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowLayoutParams.alpha = 0.55f; //透明度
        mWindowLayoutParams.width = mStartDragItemView.getWidth() + 20;
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        if (mAdapter == null)
            mAdapter = (AbsDragAdapter) getAdapter();

        mTempDragView = mAdapter.inflateView(mDragPosition);

        if (mTempDragView != null) {
            islongClick = true;
            mWindowManager.addView(mTempDragView, mWindowLayoutParams);
        }
    }

    /**
     * 从界面上面移动拖动镜像
     */
    private void removeDragImage() {
        if (mTempDragView != null) {
            mWindowManager.removeView(mTempDragView);
            islongClick = false;
            mTempDragView = null;
            if (onChangeListener != null)
                onChangeListener.afterChange();
        }
    }

    /**
     * 拖动item，在里面实现了item镜像的位置更新，item的相互交换以及GridView的自行滚动
     */
    private void onDragItem(int moveX, int moveY) {
        mWindowLayoutParams.x = moveX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowManager.updateViewLayout(mTempDragView, mWindowLayoutParams); //更新镜像的位置
        onSwapItem(moveX, moveY);
        //GridView自动滚动
        mHandler.post(mScrollRunnable);
    }


    /**
     * 当moveY的值大于向上滚动的边界值，触发GridView自动向上滚动
     * 当moveY的值小于向下滚动的边界值，触犯GridView自动向下滚动
     * 否则不进行滚动
     */
    private Runnable mScrollRunnable = new Runnable() {

        @Override
        public void run() {
            int scrollY;
            if (moveY > mUpScrollBorder) {
                scrollY = speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else if (moveY < mDownScrollBorder) {
                scrollY = -speed;
                mHandler.postDelayed(mScrollRunnable, 25);
            } else {
                scrollY = 0;
                mHandler.removeCallbacks(mScrollRunnable);
            }
            //当我们的手指到达GridView向上或者向下滚动的偏移量的时候，可能我们手指没有移动，但是DragGridView在自动的滚动
            //所以我们在这里调用下onSwapItem()方法来交换item
            onSwapItem(moveX, moveY);
            smoothScrollBy(scrollY, 10);
        }
    };


    /**
     * 交换item,并且控制item之间的显示与隐藏效果
     *
     * @param moveX
     * @param moveY
     */
    private void onSwapItem(int moveX, int moveY) {
        //获取我们手指移动到的那个item的position
        int tempPosition = pointToPosition(moveX, moveY);

        //假如tempPosition 改变了并且tempPosition不等于-1,则进行交换
        if (tempPosition != mDragPosition && tempPosition != AdapterView.INVALID_POSITION) {
            if (onChangeListener != null) {
                if (!onChangeListener.onChange(mDragPosition, tempPosition)) return;
            }
            getChildAt(tempPosition - getFirstVisiblePosition()).setVisibility(View.INVISIBLE);//拖动到了新的item,新的item隐藏掉
            getChildAt(mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);//之前的item显示出来
            mDragPosition = tempPosition;
        }
    }


    /**
     * 停止拖拽我们将之前隐藏的item显示出来，并将镜像移除
     */
    private void onStopDrag() {
        View view = getChildAt(mDragPosition - getFirstVisiblePosition());
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
        removeDragImage();
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }


    public interface OnChangeListener {

        /**
         * 当item交换位置的时候回调的方法，根据自身需求实现数据交换
         *
         * @param from 开始的position
         * @param to   拖拽到的position
         *             return :true 表示可以交换，false:不可以交换
         */
        public boolean onChange(int from, int to);


        /**
         * 拖动完成回调。
         */
        public void afterChange();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
