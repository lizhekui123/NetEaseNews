package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.lanou3g.dllo.neteasenews.R;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsBaseFragment{
    private ImageView newsLiveIv;
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
    }
}
