package com.os.police.module.home.bean;

import java.util.List;

/**
 * Created by LSH on 2017/7/11.
 * 轮播图的实体类
 */

public class BannerBean {

    private boolean succ;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String GUID;
        private String TITLE;
        private int TYPE;
        private String CONTENT;
        private int ISDELETED;
        private int ISSHOW;
        private String CREATETIME;
        private String TEMP1;
        private String TEMP2;
        private String FGUID;
        private String MATERIALTYPE;
        private String FILEREALNAME;
        private String FILESUFFIXNAME;
        private int FILESIZE;
        private String FILEPATH;
        private int STORAGEWAY;
        private String DOWNLOADCOUNT;
        private String FILESOURCE;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public int getTYPE() {
            return TYPE;
        }

        public void setTYPE(int TYPE) {
            this.TYPE = TYPE;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public int getISDELETED() {
            return ISDELETED;
        }

        public void setISDELETED(int ISDELETED) {
            this.ISDELETED = ISDELETED;
        }

        public int getISSHOW() {
            return ISSHOW;
        }

        public void setISSHOW(int ISSHOW) {
            this.ISSHOW = ISSHOW;
        }

        public String getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(String CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public String getTEMP1() {
            return TEMP1;
        }

        public void setTEMP1(String TEMP1) {
            this.TEMP1 = TEMP1;
        }

        public String getTEMP2() {
            return TEMP2;
        }

        public void setTEMP2(String TEMP2) {
            this.TEMP2 = TEMP2;
        }

        public String getFGUID() {
            return FGUID;
        }

        public void setFGUID(String FGUID) {
            this.FGUID = FGUID;
        }

        public String getMATERIALTYPE() {
            return MATERIALTYPE;
        }

        public void setMATERIALTYPE(String MATERIALTYPE) {
            this.MATERIALTYPE = MATERIALTYPE;
        }

        public String getFILEREALNAME() {
            return FILEREALNAME;
        }

        public void setFILEREALNAME(String FILEREALNAME) {
            this.FILEREALNAME = FILEREALNAME;
        }

        public String getFILESUFFIXNAME() {
            return FILESUFFIXNAME;
        }

        public void setFILESUFFIXNAME(String FILESUFFIXNAME) {
            this.FILESUFFIXNAME = FILESUFFIXNAME;
        }

        public int getFILESIZE() {
            return FILESIZE;
        }

        public void setFILESIZE(int FILESIZE) {
            this.FILESIZE = FILESIZE;
        }

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }

        public int getSTORAGEWAY() {
            return STORAGEWAY;
        }

        public void setSTORAGEWAY(int STORAGEWAY) {
            this.STORAGEWAY = STORAGEWAY;
        }

        public String getDOWNLOADCOUNT() {
            return DOWNLOADCOUNT;
        }

        public void setDOWNLOADCOUNT(String DOWNLOADCOUNT) {
            this.DOWNLOADCOUNT = DOWNLOADCOUNT;
        }

        public String getFILESOURCE() {
            return FILESOURCE;
        }

        public void setFILESOURCE(String FILESOURCE) {
            this.FILESOURCE = FILESOURCE;
        }
    }
}
