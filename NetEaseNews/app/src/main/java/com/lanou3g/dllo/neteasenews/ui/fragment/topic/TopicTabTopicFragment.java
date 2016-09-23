package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicTpBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.ui.adapter.TopicTopicListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 话题-话题页面
 */
public class TopicTabTopicFragment extends AbsBaseFragment {

    private ListView listView;
    private TopicTopicListAdapter adapter;

    private String headUrl = UrlValues.TOPICRECOMURL;
    private String dataUrl = UrlValues.TOPICCONTENTURL;
    private ArrayList datas = new ArrayList();
    private List<TopicTopicListAdapter.TopicTpItemType> types = new ArrayList<>();
    private boolean hasheader = false;
    private boolean hascontent = false;

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
        listView = byView(R.id.topic_topic_lv);
        adapter = new TopicTopicListAdapter(context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(headUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("TopicTabTopicFragment", "head" + resultStr);
                Gson gson = new Gson();
                TopicTpBean bean = gson.fromJson(resultStr, TopicTpBean.class);
                Log.d("TopicTabTopicFragment", "bean:" + bean.get话题().get(1).getTopicName());
                datas.add(0,bean.get话题());
                types.add(0,TopicTopicListAdapter.TopicTpItemType.HEADER);
                hasheader = true;
                if (hascontent) {
                    adapter.setDatas(datas, types);
                    hascontent = false;
                }
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
                Gson gson = new Gson();
                TopicTpBean tpbean = gson.fromJson(resultStr, TopicTpBean.class);
                List<TopicTpBean.DataBean.SubjectListBean> list = tpbean.getData().getSubjectList();
                for (int i = 0; i < list.size(); i++) {
                    TopicTpBean.DataBean.SubjectListBean bean = list.get(i);
                    /**
                     * type == 0,无图
                     * type == 1,三图
                     */
                    if (bean.getType() == 0) {
                        datas.add(bean);
                        types.add(TopicTopicListAdapter.TopicTpItemType.TEXT);
                    } else if (bean.getType() == 1) {
                        datas.add(bean);
                        types.add(TopicTopicListAdapter.TopicTpItemType.THREEPIC);
                    }
                }
                List<TopicTpBean.DataBean.RecomendExpertBean.ExpertListBean> dpList = tpbean.getData().getRecomendExpert().getExpertList();
                datas.add(4,dpList);
                types.add(4,TopicTopicListAdapter.TopicTpItemType.DP);
                hascontent = true;
                if (hasheader) {
                    adapter.setDatas(datas, types);
                    hasheader = false;
                }
            }

            @Override
            public void failure() {
                Log.d("TopicTabTopicFragment", "data:请求失败");
            }
        });
    }
}

