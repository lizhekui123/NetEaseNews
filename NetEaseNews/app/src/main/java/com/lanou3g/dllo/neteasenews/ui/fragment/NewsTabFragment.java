package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.NewsListAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class NewsTabFragment extends AbsBaseFragment{
    private ListView listView;
    private NewsListAdapter adapter;
    public static NewsTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        NewsTabFragment fragment = new NewsTabFragment();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_news_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.news_tab_lv);
        adapter = new NewsListAdapter(context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String str = bundle.getString("url");
        VolleyInstance.getInstance().startRequest(str, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(resultStr,NewsBean.class);
                List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
            }
        });
    }
}
