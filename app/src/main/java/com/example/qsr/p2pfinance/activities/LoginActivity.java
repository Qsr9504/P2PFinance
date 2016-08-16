package com.example.qsr.p2pfinance.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qsr.p2pfinance.MainActivity;
import com.example.qsr.p2pfinance.R;
import com.example.qsr.p2pfinance.app.AppManager;
import com.example.qsr.p2pfinance.app.AppNetConfig;
import com.example.qsr.p2pfinance.base.BaseActivity;
import com.example.qsr.p2pfinance.bean.Login;
import com.example.qsr.p2pfinance.utils.MD5Utils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.log_ed_mob)
    EditText logEdMob;
    @Bind(R.id.about_com)
    RelativeLayout aboutCom;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.log_ed_pad)
    EditText logEdPad;
    @Bind(R.id.log_log_btn)
    Button logLogBtn;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
        titleText.setText("用户登录");
    }

    @Override
    protected void initData() {
    }

    private void login() {
        String username = logEdMob.getText().toString();
        String pwd = logEdPad.getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)){
            RequestParams params = new RequestParams();
            params.put("username",username);
            params.put("password", MD5Utils.MD5(pwd));
            client.post(AppNetConfig.LOGIN,params,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    JSONObject jsonObject = JSON.parseObject(content);
                    if(jsonObject.getBoolean("success")){
                        String data = jsonObject.getString("data");
                        Login login = JSON.parseObject(data, Login.class);
                        //保存到sp中
                        saveLogin(login);
                        //跳转到首页去
                        gotoActivity(MainActivity.class,null);
                    }

                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }
            });
        }
    }

    @OnClick(R.id.log_log_btn)
    public void log_log_btn(View view){
        login();
    }

    @Override
    protected void initEvent() {

    }
    @OnClick(R.id.title_left)
    public void back(View view){
        closeCurrent();
    }
    /**
     * 关闭当前activity
     */
    public void closeCurrent(){
        AppManager.getInstance().removeCurrent();
    }
}
