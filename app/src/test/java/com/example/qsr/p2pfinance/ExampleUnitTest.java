package com.example.qsr.p2pfinance;

import com.example.qsr.p2pfinance.app.App;
import com.example.qsr.p2pfinance.utils.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.56.1:8080/P2PInvest/index",new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                System.out.print(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                System.out.print(content);
            }
        });
    }
}