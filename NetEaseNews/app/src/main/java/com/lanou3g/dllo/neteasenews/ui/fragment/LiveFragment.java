package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

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
        List<Fragment> datas = new ArrayList<>();
        datas.add(new LiveTabHotFragment());
        datas.add(new LiveTabClassifiedFragment());
        LiveTabAdapter adapter = new LiveTabAdapter(getChildFragmentManager(),datas);
        liveVp.setAdapter(adapter);
        liveTl.setupWithViewPager(liveVp);
        View liveTabHotView = getLayoutInflater(null).inflate(R.layout.fragment_live_hot, null);
        liveTl.getTabAt(0).setText("热门");
        View liveTabClassifiedView = getLayoutInflater(null).inflate(R.layout.fragment_live_classified, null);
        liveTl.getTabAt(1).setText("分类");
        liveTl.setSelectedTabIndicatorColor(Color.WHITE);
        liveTl.setTabTextColors(Color.rgb(255, 192, 203),Color.WHITE);
    }
}
