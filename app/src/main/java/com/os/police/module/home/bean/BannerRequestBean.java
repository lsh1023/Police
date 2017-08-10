package com.os.police.module.home.bean;

/**
 * Created by LSH on 2017/7/11.
 * 轮播图的请求实体类
 */

public class BannerRequestBean {

    private String PageIndex;
    private String PageSize;
    private String UserGuid;
    private String user_Type;
    private String DataSource;
    private String AreaId;
    private String EncryptPass;

    public BannerRequestBean(String pageIndex, String pageSize, String userGuid, String user_Type, String dataSource, String areaId, String encryptPass) {
        PageIndex = pageIndex;
        PageSize = pageSize;
        UserGuid = userGuid;
        this.user_Type = user_Type;
        DataSource = dataSource;
        AreaId = areaId;
        EncryptPass = encryptPass;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getUserGuid() {
        return UserGuid;
    }

    public void setUserGuid(String userGuid) {
        UserGuid = userGuid;
    }

    public String getUser_Type() {
        return user_Type;
    }

    public void setUser_Type(String user_Type) {
        this.user_Type = user_Type;
    }

    public String getDataSource() {
        return DataSource;
    }

    public void setDataSource(String dataSource) {
        DataSource = dataSource;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

    public String getEncryptPass() {
        return EncryptPass;
    }

    public void setEncryptPass(String encryptPass) {
        EncryptPass = encryptPass;
    }
}
