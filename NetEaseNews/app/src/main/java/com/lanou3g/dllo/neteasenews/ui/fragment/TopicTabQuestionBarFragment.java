package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.util.Log;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/10.
 */
public class TopicTabQuestionBarFragment extends AbsBaseFragment {
    private String menuUrl = "http://c.m.163.com/newstopic/list/classification.html";
    private String dataUrl = "http://c.m.163.com/newstopic/list/expert/YWxs/0-10.html";

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
