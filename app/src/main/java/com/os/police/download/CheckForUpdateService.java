package com.os.police.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by LSH on 2017/7/25.
 * 用户检测是否有新版本的Service
 */

public class CheckForUpdateService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    private void CheckUpgrade() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
