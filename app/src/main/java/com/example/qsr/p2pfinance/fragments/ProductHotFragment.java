package com.example.qsr.p2pfinance.fragments;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.ui.FlowLayout;
import com.example.qsr.p2pfinance.utils.DrawableUtil;
import com.example.qsr.p2pfinance.utils.UIUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**************************************
 * FileName : com.example.qsr.p2pfinance.fragments
 * Author : qsr
 * Time : 2016/7/19 14:23
 * Description :
 **************************************/
public class ProductHotFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.flow)
    FlowLayout flow;

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private Random random;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_hot);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        random = new Random();
        for (String data : datas) {
            TextView tv = new TextView(getActivity());
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dpToPx(10);
            mp.rightMargin = UIUtils.dpToPx(10);
            mp.topMargin = UIUtils.dpToPx(10);
            mp.bottomMargin = UIUtils.dpToPx(10);
            tv.setLayoutParams(mp);
            tv.setText(data);
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tv.setBackground(
                        //设置背景以及按下时的特效
                        DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(r, g, b), UIUtils.dpToPx(5)),
                                DrawableUtil.getDrawable(Color.WHITE, UIUtils.dpToPx(5))));
            }
            int padding = UIUtils.dpToPx(5);
            tv.setPadding(padding, padding, padding, padding);
            flow.addView(tv);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView)v;
        Toast.makeText(getContext(),"你猜猜你点了个啥东西",Toast.LENGTH_SHORT).show();
    }
}
