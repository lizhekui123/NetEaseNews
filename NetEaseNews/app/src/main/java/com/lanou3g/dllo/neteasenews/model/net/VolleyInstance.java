package com.lanou3g.dllo.neteasenews.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.dllo.neteasenews.ui.app.NetEaseNewsApp;

/**
 * Created by dllo on 16/9/8.
 */
public class VolleyInstance {

    private static VolleyInstance instance;
    private RequestQueue requestQueue;

    private VolleyInstance() {
        requestQueue = Volley.newRequestQueue(NetEaseNewsApp.getContext());
    }
    // 双重校验锁
    public static VolleyInstance getInstance() {
        if (instance == null) {
            synchronized (VolleyInstance.class){
                if (instance == null) {
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;
    }

    // 对外提供请求方法
    public void startRequest(String url, final VolleyResult result) {
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 请求成功,返回数据
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 请求失败,通过接口通知调用者请求失败
                result.failure();
            }
        });
        requestQueue.add(sr);
    }
}
