package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.adapter.Topic.TopicTabAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 话题页面
 */
public class TopicFragment extends AbsBaseFragment {
    private TabLayout topicTl;
    private ViewPager topicVp;

    public static TopicFragment newInstance() {

        Bundle args = new Bundle();

        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        datas.add(TopicTabQuestionBarFragment.newInstance());
        datas.add(TopicTabTopicFragment.newInstance());
        datas.add(TopicTabFollowFragment.newInstance());
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
