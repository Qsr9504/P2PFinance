package com.example.qsr.p2pfinance.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.activities.LoginActivity;
import com.example.qsr.p2pfinance.activities.UserInfoActivity;
import com.example.qsr.p2pfinance.base.BaseActivity;
import com.example.qsr.p2pfinance.base.BaseFragment;
import com.example.qsr.p2pfinance.bean.Login;
import com.example.qsr.p2pfinance.utils.BitMapUtil;
import com.example.qsr.p2pfinance.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.icon_time)
    RelativeLayout iconTime;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @Bind(R.id.chongzhi)
    ImageView chongzhi;
    @Bind(R.id.tixian)
    ImageView tixian;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichang)
    TextView llZichang;
    @Bind(R.id.ll_zhanquan)
    TextView llZhanquan;
    private boolean login;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(String content) {
        isLogin();
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initTitle() {
        //实例化title
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.VISIBLE);
        titleText.setText(R.string.me);
    }
    @OnClick(R.id.title_right)
    public void titleRight(View view){
        ((BaseActivity)getActivity()).gotoActivity(UserInfoActivity.class,null);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void isLogin() {
        String uf_acc = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            //未登录
            showLoginDialog();
        } else {
            //已登录--处理信息
            doUser();
        }
    }

    private void doUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        textView11.setText(login.UF_ACC);
        Picasso.with(getActivity()).load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                //第一步将图片缩放
                Bitmap zoom = BitMapUtil.zoom(source, UIUtils.dpToPx(62), UIUtils.dpToPx(62));
                //画出圆形头像
                Bitmap circleBitMap = BitMapUtil.circleBitMap(zoom);
                //1:transform当中处理完图片之后，需要调用recylce方法回收
                source.recycle();
                return circleBitMap;
            }

            @Override
            public String key() {
                //2:重写key方法的返回值，不能是null
                return "";
            }
        }).into(imageView1);
    }

    private void showLoginDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("必须先登录。。go！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((BaseActivity)getActivity()).gotoActivity(LoginActivity.class,null);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
