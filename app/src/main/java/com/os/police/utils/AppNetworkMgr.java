package com.os.police.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.os.police.application.BaseApplication;


/**
 * Created by LSH on 2017/7/26.
 * App网络管理
 */

public class AppNetworkMgr {

    /**
     * 判断网络是否可用
     */
    public static boolean isAvailable() {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

    /**
     * 判断wifi是否可用
     */
    public static boolean isWiFiAvailable() {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (NetworkInfo.State.CONNECTED == state) { // 判断是否正在使用WIFI网络
            flag = true;
        }
        return flag;
    }

    /**
     * 判断手机移动网络是否可用
     *
     * @return boolean
     */
    public static boolean isCellNetworkAvailable() {
        TelephonyManager telephoneManager = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        ConnectivityManager manager = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        final int networkType = telephoneManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_UMTS: {
                NetworkInfo networkInfo = manager.getNetworkInfo(networkType);
                return null != networkInfo && networkInfo.isAvailable();
            }
            default:
                return false;
        }
    }


    public static String getNetworkType() {
        String type = "未知";
        if (isWiFiAvailable()) {
            type = "Wifi";
        } else {
            ConnectivityManager manager = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    type = "Wifi";
                } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    switch (info.getSubtype()) {
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            type = "2G";
                            return getOperatorName().concat(type);
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            type = "3G";
                            return getOperatorName().concat(type);
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            type = "4G";
                            return getOperatorName().concat(type);
                        default:
                            break;

                    }
                }
            }
        }
        return type;
    }

    private static String getOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telephonyManager.getSimOperator();
        String operatorName = "";
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002")) {
                operatorName = "移动";
            } else if (operator.equals("46001")) {
                operatorName = "联通";
            } else if (operator.equals("46003")) {
                operatorName = "电信";
            }
        }
        return operatorName;
    }

}
