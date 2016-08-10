package com.example.qsr.p2pfinance.app;

/**************************************
 * FileName : com.example.qsr.p2pfinance.app
 * Author : qsr
 * Time : 2016/6/10 17:15
 * Description : 配置程序当中所有的接口请求地址
 **************************************/
public class AppNetConfig {
    public static String HOST = "192.168.3.10";//主机地址
//    public static String HOST = "192.168.1.108";//主机地址

    public static final String BASEURL = "http://" + HOST + ":8080/P2PInvest/";

    public static final String LOGIN = BASEURL+"login";

    public static final String PRODUCT = BASEURL+"product";

    public static final String INDEX  = BASEURL+"index";
}
