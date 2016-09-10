package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.adapter.TopicTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class TopicFragment extends AbsBaseFragment{
    private TabLayout topicTl;
    private ViewPager topicVp;
    @Override
    protected int setLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
        topicTl = byView(R.id.topic_tl);
        topicVp = byView(R.id.topic_vp);
    }

    @Override
    protected void initDatas() {
        List<Fragment> datas = new ArrayList<>();
        datas.add(new TabQuestionBarFragment());
        datas.add(new TabTopicFragment());
        datas.add(new TabFollowFragment());
        TopicTabAdapter adapter = new TopicTabAdapter(getChildFragmentManager(),datas);
        topicVp.setAdapter(adapter);
        topicTl.setupWithViewPager(topicVp);
        View tabQuestionBarView = getLayoutInflater(null).inflate(R.layout.fragment_topic_qb, null);
        topicTl.getTabAt(0).setText("问吧");
        View tabTopicView = getLayoutInflater(null).inflate(R.layout.fragment_topic_topic, null);
        topicTl.getTabAt(1).setText("话题");
        View tabFollowView = getLayoutInflater(null).inflate(R.layout.fragment_topic_follow, null);
        topicTl.getTabAt(2).setText("关注");
        topicTl.setSelectedTabIndicatorColor(Color.WHITE);
        topicTl.setTabTextColors(Color.rgb(255, 192, 203),Color.WHITE);

    }
}
