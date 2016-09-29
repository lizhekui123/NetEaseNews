package com.lanou3g.dllo.neteasenews.ui.fragment.news;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.news.NewsListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 新闻解释实体类
 */
public class NewsTabFragment extends AbsBaseFragment {
    private ListView listView;
    private NewsListAdapter adapter;
    private String strUrl;

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
        strUrl = bundle.getString("url");
        VolleyInstance.getInstance().startRequest(strUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                if (strUrl.equals(UrlValues.PHOTONEWSURL)){
                    String str = "{\"photo\":" + resultStr + "}";
                    NewsBean newsBean = gson.fromJson(str, NewsBean.class);
                    List<NewsBean.PhotoBean> datas = newsBean.getPhoto();
                    adapter.setDatas(strUrl,datas);
                } else {
                    NewsBean newsBean = gson.fromJson(resultStr, NewsBean.class);
                    if (strUrl.equals(UrlValues.HEADLINENEWSURL)) {
                        List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.SELECTEDNEWSURL)) {
                        List<NewsBean.T1467284926140Bean> datas = newsBean.getT1467284926140();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.ENTERTAINMENTNEWSURL)){
                        List<NewsBean.T1348648517839Bean> datas = newsBean.getT1348648517839();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.SPORTSNEWSURL)){
                        List<NewsBean.T1348649079062Bean> datas = newsBean.getT1348649079062();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.NETEASENUMNEWSURL)){
                        List<NewsBean.推荐Bean> datas = newsBean.get推荐();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.VIDEONEWSURL)){
                        List<NewsBean.视频Bean> datas = newsBean.get视频();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.FINANCIALNEWSURL)){
                        List<NewsBean.T1348648756099Bean> datas = newsBean.getT1348648756099();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.TECHNEWSURL)){
                        List<NewsBean.T1348649580692Bean> datas = newsBean.getT1348649580692();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.AUTONEWSURL)){
                        List<NewsBean.ListBean> datas = newsBean.getList();
                        adapter.setDatas(strUrl, datas);
                    } else if (strUrl.equals(UrlValues.FASHIONNEWSURL)){
                        List<NewsBean.T1348650593803Bean> datas = newsBean.getT1348650593803();
                        adapter.setDatas(strUrl, datas);
                    } else {
                        List<NewsBean.T1348647909107Bean> datas = newsBean.getT1348647909107();
                        adapter.setDatas(strUrl, datas);
                    }
                }
            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
            }
        });
    }
}
