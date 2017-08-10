package com.os.police.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.os.police.application.BaseApplication;

/**
 * Created by LSH on 2017/7/13.
 * 保存一些配置信息
 */

public class SharePrefManager {

    //向导信息
    private static final String GUID_SHARED = "guid_shared";
    //账号信息
    private static final String ACCOUNT_SHARED = "account_shared";

    private static SharedPreferences GUIDE = BaseApplication.instance.getSharedPreferences(GUID_SHARED, Context.MODE_PRIVATE);

    private static SharedPreferences ACCOUNT = BaseApplication.instance.getSharedPreferences(ACCOUNT_SHARED, Context.MODE_PRIVATE);

    public static boolean isFirstLogin() {
        return GUIDE.getBoolean("is_first_login", true);
    }

    public static void setIsFirstTime(boolean isfirst) {
        GUIDE.edit().putBoolean("isFirst", isfirst).commit();
    }

    public static boolean isFirstTime() {
        return GUIDE.getBoolean("isFirst", true);
    }

    public static void setFirstLogin(boolean isFirstLogin) {
        GUIDE.edit().putBoolean("is_first_login", isFirstLogin).commit();
    }

    public static boolean isLogin() {
        return GUIDE.getBoolean("is_login", false);
    }

    public static void setLogin(boolean isLogin) {
        GUIDE.edit().putBoolean("is_login", isLogin).commit();
    }

    public static boolean isAutoLogin() {
        return GUIDE.getBoolean("is_auto_login", false);
    }

    public static void setAutoLogin(boolean isAutoLogin) {
        GUIDE.edit().putBoolean("is_auto_login", isAutoLogin).commit();
    }

    public static String getUserName() {
        return ACCOUNT.getString("username", "");
    }

    public static void setUserName(String username) {
        ACCOUNT.edit().putString("username", username).commit();
    }

    public static String getPwd() {
        return ACCOUNT.getString("pwd", "");
    }

    public static void setPwd(String pwd) {
        ACCOUNT.edit().putString("pwd", pwd).commit();
    }

    public static String getGuid() {
        return ACCOUNT.getString("guid", "");
    }

    public static void setguid(String guid) {
        ACCOUNT.edit().putString("guid", guid).commit();
    }

    public static String getIdentifyStatus() {
        return ACCOUNT.getString("identifystatus", "");
    }

    public static void setIdentifyStatus(String identifystatus) {
        ACCOUNT.edit().putString("identifystatus", identifystatus).commit();
    }

    public static String getAliasName() {
        return ACCOUNT.getString("aliasname", "");
    }

    public static void setAliasName(String aliasName) {
        ACCOUNT.edit().putString("aliasname", aliasName).commit();
    }

    public static String getIdCard() {
        return ACCOUNT.getString("idcard", "");
    }

    public static void setIdCard(String idCard) {
        ACCOUNT.edit().putString("idcard", idCard).commit();
    }

    public static String getMobile() {
        return ACCOUNT.getString("mobile", "");
    }

    public static void setMobile(String mobile) {
        ACCOUNT.edit().putString("mobile", mobile).commit();
    }
}
