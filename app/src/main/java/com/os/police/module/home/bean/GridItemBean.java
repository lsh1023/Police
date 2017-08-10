package com.os.police.module.home.bean;

import java.util.List;

/**
 * Created by LSH on 2017/7/14.
 * 底部第三方返回实体类
 */

public class GridItemBean {

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

    public static class DataBean {


        private int totalcount;
        private List<AppstoreInfoBean> appstoreInfo;

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }

        public List<AppstoreInfoBean> getAppstoreInfo() {
            return appstoreInfo;
        }

        public void setAppstoreInfo(List<AppstoreInfoBean> appstoreInfo) {
            this.appstoreInfo = appstoreInfo;
        }

        public static class AppstoreInfoBean {

            private String GUID;
            private String APPTITLE;
            private String APPURL;
            private String APPIMGURL;
            private int ISENABLE;
            private int SORT;
            private String CREATEUSERID;
            private String CREATEUSERNAME;
            private String CREATETIME;
            private Object UPDATEUSERID;
            private Object UPDATEUSERNAME;
            private String UPDATETIME;
            private Object TEMP1;
            private Object TEMP2;
            private Object TEMP3;
            private Object TEMP4;
            private Object TEMP5;
            private boolean deletable;

            public boolean isDeletable() {
                return deletable;
            }

            public void setDeletable(boolean deletable) {
                this.deletable = deletable;
            }

            public String getGUID() {
                return GUID;
            }

            public void setGUID(String GUID) {
                this.GUID = GUID;
            }

            public String getAPPTITLE() {
                return APPTITLE;
            }

            public void setAPPTITLE(String APPTITLE) {
                this.APPTITLE = APPTITLE;
            }

            public String getAPPURL() {
                return APPURL;
            }

            public void setAPPURL(String APPURL) {
                this.APPURL = APPURL;
            }

            public String getAPPIMGURL() {
                return APPIMGURL;
            }

            public void setAPPIMGURL(String APPIMGURL) {
                this.APPIMGURL = APPIMGURL;
            }

            public int getISENABLE() {
                return ISENABLE;
            }

            public void setISENABLE(int ISENABLE) {
                this.ISENABLE = ISENABLE;
            }

            public int getSORT() {
                return SORT;
            }

            public void setSORT(int SORT) {
                this.SORT = SORT;
            }

            public String getCREATEUSERID() {
                return CREATEUSERID;
            }

            public void setCREATEUSERID(String CREATEUSERID) {
                this.CREATEUSERID = CREATEUSERID;
            }

            public String getCREATEUSERNAME() {
                return CREATEUSERNAME;
            }

            public void setCREATEUSERNAME(String CREATEUSERNAME) {
                this.CREATEUSERNAME = CREATEUSERNAME;
            }

            public String getCREATETIME() {
                return CREATETIME;
            }

            public void setCREATETIME(String CREATETIME) {
                this.CREATETIME = CREATETIME;
            }

            public Object getUPDATEUSERID() {
                return UPDATEUSERID;
            }

            public void setUPDATEUSERID(Object UPDATEUSERID) {
                this.UPDATEUSERID = UPDATEUSERID;
            }

            public Object getUPDATEUSERNAME() {
                return UPDATEUSERNAME;
            }

            public void setUPDATEUSERNAME(Object UPDATEUSERNAME) {
                this.UPDATEUSERNAME = UPDATEUSERNAME;
            }

            public String getUPDATETIME() {
                return UPDATETIME;
            }

            public void setUPDATETIME(String UPDATETIME) {
                this.UPDATETIME = UPDATETIME;
            }

            public Object getTEMP1() {
                return TEMP1;
            }

            public void setTEMP1(Object TEMP1) {
                this.TEMP1 = TEMP1;
            }

            public Object getTEMP2() {
                return TEMP2;
            }

            public void setTEMP2(Object TEMP2) {
                this.TEMP2 = TEMP2;
            }

            public Object getTEMP3() {
                return TEMP3;
            }

            public void setTEMP3(Object TEMP3) {
                this.TEMP3 = TEMP3;
            }

            public Object getTEMP4() {
                return TEMP4;
            }

            public void setTEMP4(Object TEMP4) {
                this.TEMP4 = TEMP4;
            }

            public Object getTEMP5() {
                return TEMP5;
            }

            public void setTEMP5(Object TEMP5) {
                this.TEMP5 = TEMP5;
            }
        }
    }


}
