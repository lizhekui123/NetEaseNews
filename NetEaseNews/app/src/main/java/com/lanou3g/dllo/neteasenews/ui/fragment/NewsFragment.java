package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.adapter.NewsTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsBaseFragment{
    private ImageView newsLiveIv;

    private String menuUrl = "http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html";

    private ViewPager newsVp;
    private TabLayout newsTl;
    private List<Fragment> fragments;
    private List<String> titles;
    private ArrayList<String> tabUrl;
    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        newsLiveIv = byView(R.id.news_live_iv);
        newsTl = byView(R.id.news_tl);
        newsVp = byView(R.id.news_vp);

    }

    @Override
    protected void initDatas() {
        AnimationDrawable drawable = (AnimationDrawable) newsLiveIv.getBackground();
        drawable.start();
        buildData();
        NewsTabAdapter adapter = new NewsTabAdapter(getChildFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        newsVp.setAdapter(adapter);
        newsTl.setupWithViewPager(newsVp);
        newsTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        newsTl.setSelectedTabIndicatorColor(Color.RED);
        newsTl.setTabTextColors(Color.GRAY,Color.RED);
    }
    private void buildData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        tabUrl = new ArrayList<>();
        titles.add("头条");
        tabUrl.add("http://c.3g.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&size=20");
        titles.add("精选");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1467284926140/0-20.html");
        titles.add("娱乐");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html");
        titles.add("体育");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html");
        titles.add("网易号");
        tabUrl.add("http://c.3g.163.com/recommend/getSubDocPic?from=netease_h&size=20");
        titles.add("视频");
        tabUrl.add("http://c.3g.163.com/recommend/getChanListNews?channel=T1457068979049&size=10");
        titles.add("财经");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1348648756099/0-20.html");
        titles.add("科技");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1348649580692/0-20.html");
        titles.add("汽车");
        tabUrl.add("http://c.3g.163.com/nc/auto/list/5YWo5Zu9/0-20.html");
        titles.add("时尚");
        tabUrl.add("http://c.3g.163.com/nc/article/list/T1348650593803/0-20.html");
        titles.add("图片");
        tabUrl.add("http://c.3g.163.com/photo/api/list/0096/4GJ60096.json");
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(NewsTabFragment.newInstance(tabUrl.get(i)));
        }
    }
}
