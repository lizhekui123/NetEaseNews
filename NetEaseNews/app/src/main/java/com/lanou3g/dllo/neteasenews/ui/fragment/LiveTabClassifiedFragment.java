package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.util.Log;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/12.
 */
public class LiveTabClassifiedFragment extends AbsBaseFragment{
    private String menuUrl = "http://data.live.126.net/livechannel/classifylist.json";
    private String dataUrl = "http://data.live.126.net/livechannel/classify/3/1.json";
    @Override
    protected int setLayout() {
        return R.layout.fragment_live_classified;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(menuUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("LiveTab","menu" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("LiveTab","menu" + "请求失败");
            }
        });
        VolleyInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("LiveTab", "data" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("LiveTab","data" + "请求失败");
            }
        });
    }
}
