package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.adapter.LiveTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class LiveFragment extends AbsBaseFragment{
    private TabLayout liveTl;
    private ViewPager liveVp;
    private List<Fragment> fragments;
    private List<String> titles;
    private ArrayList<String> hotUrl;
    private ArrayList<String> classifiedUrl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView() {
        liveTl = byView(R.id.live_tl);
        liveVp = byView(R.id.live_vp);
    }

    @Override
    protected void initDatas() {
        buildData();
        LiveTabAdapter adapter = new LiveTabAdapter(getChildFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        liveVp.setAdapter(adapter);
        liveTl.setupWithViewPager(liveVp);
        liveTl.setSelectedTabIndicatorColor(Color.WHITE);
        liveTl.setTabTextColors(Color.rgb(255, 192, 203),Color.WHITE);
    }

    private void buildData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        hotUrl = new ArrayList<>();
        titles.add("热门");
        titles.add("分类");
        hotUrl.add("http://data.live.126.net/livechannel/previewlist.json");
        fragments.add(LiveTabFragment.newInstance(hotUrl));
        classifiedUrl = new ArrayList<>();
        classifiedUrl.add("http://data.live.126.net/livechannel/classifylist.json");
        classifiedUrl.add("http://data.live.126.net/livechannel/classify/3/1.json");
        fragments.add(LiveTabFragment.newInstance(classifiedUrl));
    }
}
