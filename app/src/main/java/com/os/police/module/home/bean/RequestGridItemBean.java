package com.os.police.module.home.bean;

/**
 * Created by LSH on 2017/7/14.
 * 首页第三方的请求实体类
 */

public class RequestGridItemBean {

    public RequestGridItemBean(String areaId, int dataSource, String encryptPass, String pageIndex, String pageSize, String status, String title, String userGuid, String userType) {
        AreaId = areaId;
        DataSource = dataSource;
        EncryptPass = encryptPass;
        PageIndex = pageIndex;
        PageSize = pageSize;
        this.status = status;
        this.title = title;
        UserGuid = userGuid;
        UserType = userType;
    }

    /**
     * title :
     * status :
     * UserGuid :
     * UserType : net
     * DataSource : 0
     * AreaId : oceansoft
     * EncryptPass : 123456
     * PageIndex : 1
     * PageSize : 5
     */

    private String title;
    private String status;
    private String UserGuid;
    private String UserType;
    private int DataSource;
    private String AreaId;
    private String EncryptPass;
    private String PageIndex;
    private String PageSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserGuid() {
        return UserGuid;
    }

    public void setUserGuid(String UserGuid) {
        this.UserGuid = UserGuid;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public int getDataSource() {
        return DataSource;
    }

    public void setDataSource(int DataSource) {
        this.DataSource = DataSource;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String AreaId) {
        this.AreaId = AreaId;
    }

    public String getEncryptPass() {
        return EncryptPass;
    }

    public void setEncryptPass(String EncryptPass) {
        this.EncryptPass = EncryptPass;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String PageIndex) {
        this.PageIndex = PageIndex;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String PageSize) {
        this.PageSize = PageSize;
    }
}
