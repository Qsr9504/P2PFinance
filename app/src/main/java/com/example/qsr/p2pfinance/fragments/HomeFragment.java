package com.example.qsr.p2pfinance.fragments;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.app.AppNetConfig;
import com.example.qsr.p2pfinance.base.BaseFragment;
import com.example.qsr.p2pfinance.bean.Image;
import com.example.qsr.p2pfinance.bean.Index;
import com.example.qsr.p2pfinance.bean.Product;
import com.example.qsr.p2pfinance.ui.RoundProgress;
import com.example.qsr.p2pfinance.utils.LogUtil;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.fragments
 * Author : qsr
 * Time : 2016/6/6 21:08
 * Description :
 **************************************/
public class HomeFragment extends BaseFragment {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.vp_barner)
    ViewPager vpBarner;
    @Bind(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.p_progresss)
    RoundProgress pProgresss;
    private View view;
    private Index index = new Index();
    private int totalProgress;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initData(String content) {
        if(!TextUtils.isEmpty(content)) {
            LogUtil.MyLog_e(getContext(),"HomeFragment初始化数据\n"+content);
            //解析返回的字符串
            JSONObject jsonObject = JSON.parseObject(content);//转化为jsonObject对象
            String proInfo = jsonObject.getString("proInfo");//获取第一个字段
            Product product = JSON.parseObject(proInfo, Product.class);//将字符串转化直接装进对象中
            String imageArr = jsonObject.getString("imageArr");
            List<Image> imageList = JSON.parseArray(imageArr, Image.class);
            index.imageList = imageList;
            index.product = product;
            //适配数据到ViewPager上
            vpBarner.setAdapter(new HomeViewPagerAdapter());
            //把viewPager交给指示器
            circleBarner.setViewPager(vpBarner);
            totalProgress = Integer.parseInt(index.product.progress);
            //启动刷新自定义进度条
            new Thread(runnable).start();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int tempProgress = 0;
            try {
                while (tempProgress <= totalProgress) {
                    pProgresss.setProgress(tempProgress);
                    tempProgress++;
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void initTitle() {
        //实例化title
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleText.setText(R.string.home);
    }

    @Override
    protected void initEvent() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class HomeViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.imageList == null ? 0 : index.imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View) object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageurl = index.imageList.get(position).IMAURL;
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //实用picasso框架将图片从网络获取并绑定到图片控件imageView上
            Picasso.with(getActivity()).load(imageurl).into(imageView);
            container.addView(imageView);
            return imageView;
        }
    }
}
