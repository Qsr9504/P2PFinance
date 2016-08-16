package com.example.qsr.p2pfinance.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.qsr.p2pfinance.MainActivity;
import com.example.qsr.p2pfinance.app.AppManager;
import com.example.qsr.p2pfinance.bean.Login;
import com.loopj.android.http.AsyncHttpClient;

import butterknife.ButterKnife;

/**************************************
 * FileName : com.example.qsr.p2pfinance.base
 * Author : qsr
 * Time : 2016/6/6 22:06
 * Description :
 **************************************/
public abstract class BaseActivity extends FragmentActivity {
    protected AsyncHttpClient client = new AsyncHttpClient();
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
    public void saveLogin(Login login){
        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UF_ACC", login.UF_ACC);
        editor.putString("UF_AVATAR_URL", login.UF_AVATAR_URL);
        editor.putString("UF_IS_CERT", login.UF_IS_CERT);
        editor.putString("UF_PHONE", login.UF_PHONE);
        editor.commit();
    }
    /**
     * 获取登录信息
     *
     * @return
     */
    public Login getLogin() {
        Login login = new Login();
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        login.UF_ACC = sp.getString("UF_ACC", "");
        login.UF_AVATAR_URL = sp.getString("UF_AVATAR_URL", "");
        login.UF_IS_CERT = sp.getString("UF_IS_CERT", "");
        login.UF_PHONE = sp.getString("UF_PHONE", "");
        return login;
    }

    /**
     * 跳转到目标activity
     *
     * @param clazz
     * @param bundle
     */
    public void gotoActivity(Class clazz, Bundle bundle) {
        Intent it = new Intent(this, clazz);
        if (bundle != null) {
            it.putExtra("param", bundle);
        }
        startActivity(it);
    }

    /**
     * 退出登录
     */
    public void loginOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出登录");
        builder.setMessage("你确定吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences("user_info", MODE_PRIVATE).edit().clear().commit();
                AppManager.getInstance().removeAll();
                gotoActivity(MainActivity.class, null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
