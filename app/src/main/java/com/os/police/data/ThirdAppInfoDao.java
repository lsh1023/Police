package com.os.police.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LSH on 2017/7/19.
 * 系统数据库实体类
 */
@Entity
public class ThirdAppInfoDao {

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
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public String getCREATEUSERID() {
        return this.CREATEUSERID;
    }
    public void setCREATEUSERID(String CREATEUSERID) {
        this.CREATEUSERID = CREATEUSERID;
    }
    public String getTime() {
        return this.Time;
    }
    public void setTime(String Time) {
        this.Time = Time;
    }
    public String getGUID() {
        return this.GUID;
    }
    public void setGUID(String GUID) {
        this.GUID = GUID;
    }
    public String getInstall() {
        return this.install;
    }
    public void setInstall(String install) {
        this.install = install;
    }
    public boolean getDeletable() {
        return this.deletable;
    }
    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }
    public int getAppSize() {
        return this.appSize;
    }
    public void setAppSize(int appSize) {
        this.appSize = appSize;
    }
    public String getStartPage() {
        return this.startPage;
    }
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }
    public String getAppType() {
        return this.appType;
    }
    public void setAppType(String appType) {
        this.appType = appType;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Long getAppId() {
        return this.appId;
    }
    public void setAppId(Long appId) {
        this.appId = appId;
    }
    @Generated(hash = 1180229809)
    public ThirdAppInfoDao(Long appId, int id, String title, String icon,
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
    @Generated(hash = 139636203)
    public ThirdAppInfoDao() {
    }

}
