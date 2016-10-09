package com.lanou3g.dllo.neteasenews.ui.fragment.news;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.tools.ToastTool;
import com.lanou3g.dllo.neteasenews.ui.adapter.news.NewsPhotoAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 新闻-图片页
 */
public class NewsPhotoFragment extends AbsBaseFragment {
    private ListView listView;
    private NewsPhotoAdapter adapter;
    private String strUrl;

    public static NewsPhotoFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        NewsPhotoFragment fragment = new NewsPhotoFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_photo;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.news_photo_lv);
        adapter = new NewsPhotoAdapter(context);
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
                String str = "{\"photo\":" + resultStr + "}";
                NewsBean newsBean = gson.fromJson(str, NewsBean.class);
                List<NewsBean.PhotoBean> datas = newsBean.getPhoto();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {
                ToastTool.shortMsg("请求失败");
            }
        });
    }
}
