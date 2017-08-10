package com.os.police.utils;

import com.os.police.config.Config;

/**
 * Created by LSH on 2017/7/11.
 * 格式化访问的URL地址
 */

public class UrlUtil {

    public static final String HTTP = "http";

    public static final String HTTPS = "https";

    /**
     * 格式化URL
     *
     * @param protocol 协议类型
     * @param host     主机
     * @param path     资源路径
     * @return URL
     */
    public static String url(String protocol, String host, String path) {
        return String.format("%1$s://%2$s:%3$d/%4$s", protocol, host, Config.PORT, path);
    }

    /**
     * 格式化URL
     *
     * @param protocol 协议类型
     * @param path     资源路径
     * @return URL
     */
    public static String url(String protocol, String path) {
        return String.format("%1$s://%2$s:%3$d/%4$s", protocol, Config.HOST, Config.PORT, path);
    }

    /**
     * 格式化URL
     *
     * @param port 端口号
     * @param path 资源路径
     * @return URL
     */
    public static String http(int port, String path) {
        return String.format("%1$s://%2$s:%3$d/%4$s", Config.HTTP, Config.HOST, port, path);
    }

    /**
     * 格式化URL
     *
     * @param port 端口号
     * @param path 资源路径
     * @return URL
     */
    public static String http(String host, int port, String path) {
        return String.format("%1$s://%2$s:%3$d/%4$s", Config.HTTP, host, port, path);
    }
    /**
     * 格式化URL
     *
     * @param path 资源路径
     * @return URL
     */
    public static String http(String path) {
        return String.format("%1$s://%2$s:%3$s/%4$s", Config.HTTP, Config.HOST, Config.PORT, path);
    }



    /**
     * 格式化URL
     *
     * @param host 主机
     * @param path 资源路径
     * @return URL
     */
    public static String http(String host, String path) {
        return url(HTTP, host, path);
    }

}
