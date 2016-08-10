package com.example.qsr.p2pfinance.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.base.BaseFragment;
import com.example.qsr.p2pfinance.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.fragments
 * Author : qsr
 * Time : 2016/6/6 21:09
 * Description :
 **************************************/
public class InvestFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tab_indicator)
    TabPageIndicator tabIndicator;
    @Bind(R.id.pager)
    ViewPager pager;
    //viewPager中fragment集合
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
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
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String content) {
        initFragments();//初始化fragment
        pager.setAdapter(new MyAdapter(getFragmentManager()));
        tabIndicator.setViewPager(pager);//将viewpager交给pager指示器
    }

    private void initFragments() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductCommonFragment productCommonFragment = new ProductCommonFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        fragmentList.add(productListFragment);
        fragmentList.add(productHotFragment);
        fragmentList.add(productCommonFragment);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initTitle() {
        //实例化title
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleText.setText(R.string.invest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.invest_title)[position];
        }
    }
}
