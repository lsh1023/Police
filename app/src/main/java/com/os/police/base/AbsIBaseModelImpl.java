package com.os.police.base;

import android.content.Context;

/**
 * Created by LSH on 2017/7/11.
 */

public abstract class AbsIBaseModelImpl implements IBaseModel{

    private Context context;
    public AbsIBaseModelImpl(Context context){
        this.context=context;
    }

    public Context getContext() {
        return context;
    }
}
