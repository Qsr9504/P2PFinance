package com.example.qsr.p2pfinance;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qsr.p2pfinance.app.AppManager;
import com.example.qsr.p2pfinance.base.BaseActivity;
import com.example.qsr.p2pfinance.fragments.HomeFragment;
import com.example.qsr.p2pfinance.fragments.InvestFragment;
import com.example.qsr.p2pfinance.fragments.MeFragment;
import com.example.qsr.p2pfinance.fragments.MoreFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 底部导航栏  的点击效果等一系列实现
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.iv_touzi)
    ImageView ivTouzi;
    @Bind(R.id.tv_touzi)
    TextView tvTouzi;
    @Bind(R.id.ll_touzi)
    LinearLayout llTouzi;
    @Bind(R.id.iv_me)
    ImageView ivMe;
    @Bind(R.id.tv_me)
    TextView tvMe;
    @Bind(R.id.ll_me)
    LinearLayout llMe;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.ll_more)
    LinearLayout llMore;
    @Bind(R.id.main_content)
    FrameLayout mainContent;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction ft;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //将当前activity管理起来
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void initData() {
        //设置首页
        setSelect(0);
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.ll_home, R.id.ll_me, R.id.ll_more, R.id.ll_touzi})
    public void changTab(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_touzi:
                setSelect(1);
                break;
            case R.id.ll_me:
                setSelect(2);
                break;
            case R.id.ll_more:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        reSetTab();
        //隐藏当前的fragment
        hideFragment();
        switch (i) {
            case 0://首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_content,homeFragment);
                }
                ft.show(homeFragment);
                //更改Tab的点击样式
                ivHome.setImageResource(R.drawable.bid01);
                tvHome.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 1://投资
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.main_content,investFragment);
                }
                ft.show(investFragment);
                ivTouzi.setImageResource(R.drawable.bid03);
                tvTouzi.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 2://资产
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.main_content,meFragment);
                }
                ft.show(meFragment);
                ivMe.setImageResource(R.drawable.bid05);
                tvMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 3://更多
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.main_content,moreFragment);
                }
                ft.show(moreFragment);
                ivMore.setImageResource(R.drawable.bid07);
                tvMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
        }
        ft.commit();
    }

    private void reSetTab() {
        //将底部导航栏全部重置
        ivHome.setImageResource(R.drawable.bid02);
        ivMe.setImageResource(R.drawable.bid06);
        ivMore.setImageResource(R.drawable.bid08);
        ivTouzi.setImageResource(R.drawable.bid04);

        tvHome.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMe.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMore.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(getResources().getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
