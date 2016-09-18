package com.lanou3g.dllo.neteasenews.model.net;

/**
 * Created by dllo on 16/9/12.
 * 网络请求结果接口
 */
public interface VolleyResult {
    void success(String resultStr);

    void failure();
}
