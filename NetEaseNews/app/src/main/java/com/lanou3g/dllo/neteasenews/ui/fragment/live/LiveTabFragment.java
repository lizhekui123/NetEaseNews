package com.lanou3g.dllo.neteasenews.ui.fragment.live;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.LiveBean;
import com.lanou3g.dllo.neteasenews.model.net.VolleyInstance;
import com.lanou3g.dllo.neteasenews.model.net.VolleyResult;
import com.lanou3g.dllo.neteasenews.ui.adapter.LiveListAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 直播解析实体类
 */
public class LiveTabFragment extends AbsBaseFragment {
    private ListView listView;
    private String strUrl;
    private LiveListAdapter adapter;

    public static LiveTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        LiveTabFragment fragment = new LiveTabFragment();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_live_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.live_lv);
        adapter = new LiveListAdapter(context);
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
                LiveBean bean = gson.fromJson(resultStr,LiveBean.class);
                List<LiveBean.LiveReviewBean> datas = bean.getLive_review();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
