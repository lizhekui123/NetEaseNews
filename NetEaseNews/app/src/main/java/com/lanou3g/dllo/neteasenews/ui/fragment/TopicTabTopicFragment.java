package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.util.Log;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/10.
 */
public class TopicTabTopicFragment extends AbsBaseFragment{
    private String headUrl = "http://c.m.163.com/recommend/getChanRecomNews?channel=T1460094487214&size=5";
    private String dataUrl = "http://topic.comment.163.com/topic/list/subject/0-10.html";
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
