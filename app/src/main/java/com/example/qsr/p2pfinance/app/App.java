package com.example.qsr.p2pfinance.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.qsr.p2pfinance.utils.LogUtil;

/**************************************
 * FileName : com.example.qsr.p2pfinance.app
 * Author : qsr
 * Time : 2016/6/10 11:18
 * Description : 全局
 **************************************/
public class App extends Application {
    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myPid();
        initUtils();

    }
    //各种自定义的工具初始化
    private void initUtils() {
        //log信息是否打印
        LogUtil.openLog(true);
        //异常捕获处理器
//        CrashHandle.getInstance().init(this);
        //网络状况监听器
    }
}
