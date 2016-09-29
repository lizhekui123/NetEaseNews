package com.lanou3g.dllo.neteasenews.model.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.dllo.neteasenews.ui.app.NetEaseNewsApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/9/8.
 * 网络请求类
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
            synchronized (VolleyInstance.class) {
                if (instance == null) {
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;
    }

    // 对外提供请求方法
    public void startRequest(String url, final VolleyResult result) {

        if (url.endsWith(".json") && !url.equals(UrlValues.PHOTONEWSURL)){
            JsonUTF(url, result);
        } else {
            JsonNormal(url, result);
        }

    }

    private void JsonNormal(String url, final VolleyResult result) {
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

    private void JsonUTF(final String url, final VolleyResult result) {
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        Log.d("------", obj.toString());
                        result.success(String.valueOf(obj));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
                result.failure();
            }

        }) {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(new String(response.data, "UTF-8"));
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                }
            }
        };
//        objRequest.setTag("obj");
        requestQueue.add(objRequest);
    }
}

