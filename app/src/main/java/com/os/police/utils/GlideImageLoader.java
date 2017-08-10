package com.os.police.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.os.police.R;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by LSH on 2017/7/11.
 * banner轮播图加载图片
 */

public class GlideImageLoader extends ImageLoader{

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).error(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }
}
