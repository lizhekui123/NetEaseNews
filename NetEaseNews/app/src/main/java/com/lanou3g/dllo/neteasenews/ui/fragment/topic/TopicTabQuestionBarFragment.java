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
 * 话题-问吧页面
 */
public class TopicTabQuestionBarFragment extends AbsBaseFragment {
    private String menuUrl = UrlValues.TOPICQUESTIONBARMENUURL;
    private String dataUrl = UrlValues.TOPICQUESTIONBARCONTENTURL;

    public static TopicTabQuestionBarFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopicTabQuestionBarFragment fragment = new TopicTabQuestionBarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_qb;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(menuUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("QuestionBar","menu" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("QuestionBar","menu" + "请求失败");
            }
        });
        VolleyInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("QuestionBar", "data" + resultStr);
            }

            @Override
            public void failure() {
                Log.d("QuestionBar","data" + "请求失败");
            }
        });
    }
}
