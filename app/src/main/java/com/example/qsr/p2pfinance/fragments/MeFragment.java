package com.example.qsr.p2pfinance.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.base.BaseFragment;
import com.example.qsr.p2pfinance.utils.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.fragments
 * Author : qsr
 * Time : 2016/6/6 21:08
 * Description :
 **************************************/
public class MeFragment extends BaseFragment {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_right)
    ImageView titleRight;


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(String content) {

    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initTitle() {
        //实例化title
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleText.setText(R.string.me);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
