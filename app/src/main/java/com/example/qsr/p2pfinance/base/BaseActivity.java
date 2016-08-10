package com.example.qsr.p2pfinance.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**************************************
 * FileName : com.example.qsr.p2pfinance.base
 * Author : qsr
 * Time : 2016/6/6 22:06
 * Description :
 **************************************/
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        initData();
        initEvent();
    }
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();
}
