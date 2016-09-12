package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.util.Log;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/12.
 */
public class LiveTabHotFragment extends AbsBaseFragment{
    private String dataUrl = "http://data.live.126.net/livechannel/previewlist.json";
    @Override
    protected int setLayout() {
        return R.layout.fragment_live_hot;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("LiveTabHot", "data:" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("LiveTabHot", "data:请求失败");
            }
        });
    }
}
