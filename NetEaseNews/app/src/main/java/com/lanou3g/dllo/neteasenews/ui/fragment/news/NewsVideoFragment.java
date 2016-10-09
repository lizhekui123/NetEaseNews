package com.lanou3g.dllo.neteasenews.ui.fragment.news;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.news.NewsVideoAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 新闻-视频页
 */
public class NewsVideoFragment extends AbsBaseFragment {
    private RecyclerView recyclerView;
    private NewsVideoAdapter adapter;
    private String strUrl;

    public static NewsVideoFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        NewsVideoFragment fragment = new NewsVideoFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_video;
    }

    @Override
    protected void initView() {
        recyclerView = byView(R.id.news_video_rv);
        adapter = new NewsVideoAdapter(context);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        strUrl = bundle.getString("url");
        VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                List<NewsBean.视频Bean> datas = newsBean.get视频();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
            }
        });
    }
}
