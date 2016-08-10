package com.example.qsr.p2pfinance.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qsr.p2pfinance.bean.ResultState;
import com.example.qsr.p2pfinance.ui.LoadingPage;
import com.example.qsr.p2pfinance.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.base
 * Author : qsr
 * Time : 2016/6/6 21:11
 * Description :
 **************************************/
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //声明加载界面
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                ButterKnife.bind(BaseFragment.this, successView);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                //返回需要网络访问需要的参数
                return getParams();
            }

            @Override
            protected String url() {
                //返回需要网络请求的地址
                return getUrl();
            }
        };
        initEvent();
        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);
    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    protected abstract int getLayoutId();
    protected abstract void initData(String content);
    protected abstract void initEvent();
    protected abstract void initTitle();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    public void show() {
        loadingPage.show();
    }
}
