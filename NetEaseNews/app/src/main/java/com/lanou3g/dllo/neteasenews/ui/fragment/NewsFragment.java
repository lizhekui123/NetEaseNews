package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsBaseFragment{
    private ImageView newsLiveIv;

    private String menuUrl = "http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html";

//    private ViewPager newsVp;
//    private TabLayout newsTl;
    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        newsLiveIv = byView(R.id.news_live_iv);
//        newsTl = byView(R.id.news_tl);
//        newsVp = byView(R.id.news_vp);

    }

    @Override
    protected void initDatas() {
        AnimationDrawable drawable = (AnimationDrawable) newsLiveIv.getBackground();
        drawable.start();
        VolleyInstance.getInstance().startRequest(menuUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("NewsFragment", "menu:" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("NewsFragment", "menu:请求失败");
            }
        });
    }
}
