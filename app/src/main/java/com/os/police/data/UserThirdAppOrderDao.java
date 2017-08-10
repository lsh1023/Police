package com.os.police.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LSH on 2017/7/19.
 * 用户数据库实体类
 */

@Entity
public class UserThirdAppOrderDao {

    @Id
    private Long appId;
    private int id;
    private String title;
    private String icon;
    private String url;
    private String desc;
    private String appType;
    private String startPage;
    private int appSize;
    private boolean deletable;
    private String install;

    private String GUID;
    private String Time;
    private String CREATEUSERID;
    private int sort;

    @Generated(hash = 719900090)
    public UserThirdAppOrderDao(Long appId, int id, String title, String icon,
            String url, String desc, String appType, String startPage, int appSize,
            boolean deletable, String install, String GUID, String Time,
            String CREATEUSERID, int sort) {
        this.appId = appId;
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.url = url;
        this.desc = desc;
        this.appType = appType;
        this.startPage = startPage;
        this.appSize = appSize;
        this.deletable = deletable;
        this.install = install;
        this.GUID = GUID;
        this.Time = Time;
        this.CREATEUSERID = CREATEUSERID;
        this.sort = sort;
    }

    @Generated(hash = 623642447)
    public UserThirdAppOrderDao() {
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public int getAppSize() {
        return appSize;
    }

    public void setAppSize(int appSize) {
        this.appSize = appSize;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCREATEUSERID() {
        return CREATEUSERID;
    }

    public void setCREATEUSERID(String CREATEUSERID) {
        this.CREATEUSERID = CREATEUSERID;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean getDeletable() {
        return this.deletable;
    }
}
