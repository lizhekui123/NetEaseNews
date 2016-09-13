package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/12.
 */
public class LiveTabFragment extends AbsBaseFragment{
    private TextView textView;
    public static LiveTabFragment newInstance(ArrayList<String> urls) {
        Bundle args = new Bundle();
        args.putStringArrayList("url", urls);
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
        textView = byView(R.id.fragment_live_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        ArrayList<String> list = bundle.getStringArrayList("url");
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i) + "\n";
        }
        textView.setText(str);
    }
}
