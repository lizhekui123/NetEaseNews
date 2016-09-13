package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;

/**
 * Created by dllo on 16/9/12.
 */
public class NewsTabFragment extends AbsBaseFragment{
    private TextView textView;
    public static NewsTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        NewsTabFragment fragment = new NewsTabFragment();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_live_tab;
    }

    @Override
    protected void initView() {
        textView = byView(R.id.fragment_live_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String str = bundle.getString("url");
        textView.setText(str);
    }
}
