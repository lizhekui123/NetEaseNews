package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.os.Bundle;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 话题-关注页面
 */
public class TopicTabFollowFragment extends AbsBaseFragment {

    public static TopicTabFollowFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopicTabFollowFragment fragment = new TopicTabFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_follow;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
