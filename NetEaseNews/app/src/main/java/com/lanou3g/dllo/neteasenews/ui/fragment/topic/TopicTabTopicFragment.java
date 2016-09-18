package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.os.Bundle;
import android.util.Log;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/10.
 * 话题-话题页面
 */
public class TopicTabTopicFragment extends AbsBaseFragment {
    private String headUrl = UrlValues.TOPICRECOMURL;
    private String dataUrl = UrlValues.TOPICCONTENTURL;

    public static TopicTabTopicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopicTabTopicFragment fragment = new TopicTabTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_topic;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(headUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("TopicTabTopicFragment", "head" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("TopicTabTopicFragment", "head:请求失败");
            }
        });
        VolleyInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("TopicTabTopicFragment", "data" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("TopicTabTopicFragment", "data:请求失败");
            }
        });
    }
}
