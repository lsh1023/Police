package com.os.police.download;

import java.io.Serializable;

/**
 * Created by LSH on 2017/7/25.
 * 更新的实体类
 */

public class UpdateBean implements Serializable {

    private boolean succ;
    private String msg;
    private DataBean data;

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * Version :
         * UpdateInfo :
         * Url :
         */

        private String Version;
        private String UpdateInfo;
        private String Url;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getUpdateInfo() {
            return UpdateInfo;
        }

        public void setUpdateInfo(String UpdateInfo) {
            this.UpdateInfo = UpdateInfo;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }

}
