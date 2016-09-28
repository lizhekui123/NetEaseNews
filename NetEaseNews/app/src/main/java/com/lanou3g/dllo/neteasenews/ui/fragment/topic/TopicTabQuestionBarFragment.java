package com.lanou3g.dllo.neteasenews.ui.fragment.topic;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicQBBean;
import com.lanou3g.dllo.neteasenews.model.bean.TopicQBHeaderBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.ui.adapter.TopicQBHeaderAdapter;
import com.lanou3g.dllo.neteasenews.ui.adapter.TopicQBListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 话题-问吧页面
 */
public class TopicTabQuestionBarFragment extends AbsBaseFragment {

    private ListView listView;
    private RecyclerView recyclerView;

    private ImageView menuIv;
    private boolean menuIsSelected = false;

    private TopicQBListAdapter adapter;
    private TopicQBHeaderAdapter headerAdapter;

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
        listView = byView(R.id.topic_qb_lv);
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_bar_header, null);
        listView.addHeaderView(view);
        recyclerView = (RecyclerView) listView.findViewById(R.id.item_qb_header_rv);
        menuIv = (ImageView) listView.findViewById(R.id.item_qb_header_menu_iv);
        adapter = new TopicQBListAdapter(context);
        headerAdapter = new TopicQBHeaderAdapter(context);
        recyclerView.setAdapter(headerAdapter);
        GridLayoutManager manager = new GridLayoutManager(context, 5);
        recyclerView.setLayoutManager(manager);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(menuUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                TopicQBHeaderBean bean = gson.fromJson(resultStr, TopicQBHeaderBean.class);
                List<TopicQBHeaderBean.DataBean> datas =  bean.getData();
                headerAdapter.setDatas(datas);
            }

            @Override
            public void failure() {
                Log.d("QuestionBar", "menu" + "请求失败");
            }
        });
        VolleyInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                TopicQBBean bean = gson.fromJson(resultStr, TopicQBBean.class);
                Log.d("TopicTabQuestionBarFrag", "bean:" + bean);
                List<TopicQBBean.DataBean.ExpertListBean> datas = bean.getData().getExpertList();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {
                Log.d("QuestionBar", "data" + "请求失败");
            }
        });
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuIsSelected = !menuIsSelected;
                menuIv.setSelected(menuIsSelected);
                headerAdapter.setMenuIsSelected(menuIsSelected);
            }
        });
    }
}
