package com.example.qsr.p2pfinance.app;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**************************************
 * FileName : com.example.qsr.p2pfinance.app
 * Author : qsr
 * Time : 2016/6/10 19:12
 * Description : 全局异常捕获处理器
 * 设计为单例模式
 **************************************/
public class CrashHandle implements Thread.UncaughtExceptionHandler {
    private static CrashHandle crashHandle = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private CrashHandle(){}
    public static CrashHandle getInstance(){
        if(crashHandle == null){
            crashHandle = new CrashHandle();
        }
        return crashHandle;
    }
    public void init(Context context){
        //将cashHandler作为系统的默认异常捕获处理器
        this.mContext = context;
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    /**
     * 判断是否需要自己处理
     *
     * @param ex
     * @return
     */
    public boolean isHandle(Throwable ex) {
        if (ex == null) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(isHandle(ex))
        {
            //自己处理的异常
            handleException(thread, ex);
        }else {
            //自己不想处理的异常交回给系统处理
            exceptionHandler.uncaughtException(thread,ex);
        }
    }

    private void handleException(Thread thread, Throwable ex) {
        new Thread() {
            @Override
            public void run() {
                //Android系统当中，默认情况下，线程是没有开启looper消息处理的，但是主线程除外
                Looper.prepare();
                Toast.makeText(mContext, "抱歉，系统出现未知异常，即将退出....", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectionException(ex);
        try {
            thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            //关闭程序，释放所有内存
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //收集崩溃信息
    private void collectionException(Throwable ex) {
        final String deviceInfo = Build.DEVICE + Build.VERSION.SDK_INT + Build.MODEL + Build.PRODUCT;
        final String errorInfo = ex.getMessage().toString();
        new Thread() {
            @Override
            public void run() {
                Log.e("qsr", "deviceInfo:" + deviceInfo + "\n" + "errorInfo:" + errorInfo);
            }
        }.start();
    }
}
