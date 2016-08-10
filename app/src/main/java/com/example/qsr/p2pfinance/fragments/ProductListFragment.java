package com.example.qsr.p2pfinance.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.app.AppNetConfig;
import com.example.qsr.p2pfinance.base.BaseHolder;
import com.example.qsr.p2pfinance.base.MyBaseAdapter2;
import com.example.qsr.p2pfinance.bean.Product;
import com.example.qsr.p2pfinance.ui.RoundProgress;
import com.example.qsr.p2pfinance.utils.LogUtil;
import com.example.qsr.p2pfinance.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.fragments
 * Author : qsr
 * Time : 2016/7/19 14:19
 * Description :
 **************************************/
public class ProductListFragment extends Fragment {
    @Bind(R.id.lv)
    ListView lv;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        client.post(AppNetConfig.PRODUCT, new RequestParams(),new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                LogUtil.MyLog_e(getContext(),"---------"+content);
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> productList = JSON.parseArray(data, Product.class);
                    lv.setAdapter(new MyAdapter3(productList));
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private class MyAdapter3 extends MyBaseAdapter2<Product> {

        public MyAdapter3(List<Product> list) {
            super(list);
        }

        @Override
        protected BaseHolder getHolder() {
            return new MyViewHolder();
        }
    }

    class MyViewHolder extends BaseHolder<Product> {

        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        @Override
        public View initView() {
            View inflate = View.inflate(getActivity(), R.layout.item_product_list, null);
            return inflate;
        }

        @Override
        protected void refreshView() {
            Product product = getData();
            //设置数据
            pMinzouzi.setText(product.minTouMoney);
            pMoney.setText(product.money);
            pName.setText(product.name);
            pSuodingdays.setText(product.suodingDays);
            pYearlv.setText(product.yearLv);
            pProgresss.setProgress(Integer.parseInt(product.progress));
        }
    }
//    private class Myadapter2 extends SimpleBaseAdapter<Product>{
//        public Myadapter2(List<Product> list) {
//            super(list);
//        }
//        @Override
//        public View getYourView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            Product product = list.get(position);
//            if (convertView == null) {
//                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
//                viewHolder = new ViewHolder(convertView);
//                convertView.setTag(viewHolder);
//            }else{
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            //设置数据
//            viewHolder.pMinzouzi.setText(product.minTouMoney);
//            viewHolder.pMoney.setText(product.money);
//            viewHolder.pName.setText(product.name);
//            viewHolder.pSuodingdays.setText(product.suodingDays);
//            viewHolder.pYearlv.setText(product.yearLv);
//            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
//            return convertView;
//        }
//    }
//    private class MyAdapter extends BaseAdapter {
//        private List<Product> productList;
//
//        public MyAdapter(List<Product> productList) {
//            this.productList = productList;
//        }
//
//        @Override
//        public int getCount() {
//            return productList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return productList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Product product = productList.get(position);
//            if (convertView == null) {
//                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
//                viewHolder = new ViewHolder(convertView);
//                convertView.setTag(viewHolder);
//            }else{
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            //设置数据
//            viewHolder.pMinzouzi.setText(product.minTouMoney);
//            viewHolder.pMoney.setText(product.money);
//            viewHolder.pName.setText(product.name);
//            viewHolder.pSuodingdays.setText(product.suodingDays);
//            viewHolder.pYearlv.setText(product.yearLv);
//            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
//            return convertView;
//        }
//    }
//    static class ViewHolder {
//        @Bind(R.id.p_name)
//        TextView pName;
//        @Bind(R.id.p_money)
//        TextView pMoney;
//        @Bind(R.id.p_yearlv)
//        TextView pYearlv;
//        @Bind(R.id.p_suodingdays)
//        TextView pSuodingdays;
//        @Bind(R.id.p_minzouzi)
//        TextView pMinzouzi;
//        @Bind(R.id.p_progresss)
//        RoundProgress pProgresss;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
}
