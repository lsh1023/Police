package com.os.police.download;

/**
 * Created by LSH on 2017/7/25.
 * 更新的回调接口
 */

public interface DownLoadLisener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCancle();

}
