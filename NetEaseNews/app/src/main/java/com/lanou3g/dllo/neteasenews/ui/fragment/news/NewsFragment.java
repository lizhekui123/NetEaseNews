package com.lanou3g.dllo.neteasenews.ui.fragment.news;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.ui.adapter.NewsMenuGVAdapter;
import com.lanou3g.dllo.neteasenews.ui.adapter.NewsTabAdapter;
import com.lanou3g.dllo.neteasenews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 新闻页面
 */
public class NewsFragment extends AbsBaseFragment {
    private ImageView newsLiveIv;
    private ViewPager newsVp;
    private TabLayout newsTl;
    private List<Fragment> fragments;
    private List<String> titles;
    private ArrayList<String> tabUrl;

    private ImageView menuIv;
    private TextView changeTv;
    private GridView gridView;
    private NewsMenuGVAdapter gvAdapter;

    private PopupWindow window;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        newsLiveIv = byView(R.id.news_live_iv);
        newsTl = byView(R.id.news_tl);
        newsVp = byView(R.id.news_vp);

        menuIv = byView(R.id.news_menu_iv);
        changeTv = byView(R.id.news_change_tv);

    }

    @Override
    protected void initDatas() {
        AnimationDrawable drawable = (AnimationDrawable) newsLiveIv.getBackground();
        drawable.start();
        buildData();
        NewsTabAdapter adapter = new NewsTabAdapter(getChildFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);
        newsVp.setAdapter(adapter);
        newsTl.setupWithViewPager(newsVp);
        newsTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        newsTl.setSelectedTabIndicatorColor(Color.RED);
        newsTl.setTabTextColors(Color.GRAY,Color.RED);
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    menuIv.setSelected(true);
                    newsTl.setVisibility(View.GONE);
                    changeTv.setVisibility(View.VISIBLE);
                    showPopWindow();
            }
        });
    }


    private void showPopWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_popwindow,null);
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        window.setFocusable(true);
        window.setTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        gridView = (GridView) view.findViewById(R.id.item_news_menu_gv);
        gvAdapter = new NewsMenuGVAdapter(context);
        gvAdapter.setDatas(titles);
        gvAdapter.setSelectedItem(newsVp.getCurrentItem());
        gridView.setAdapter(gvAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newsVp.setCurrentItem(position);
                gvAdapter.setSelectedItem(position);
                window.dismiss();
            }
        });

        window.setOutsideTouchable(true);
//        window.setAnimationStyle(R.style.news_popwindow_anim_style);
        window.showAsDropDown(menuIv);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                menuIv.setSelected(false);
                newsTl.setVisibility(View.VISIBLE);
                changeTv.setVisibility(View.GONE);
            }
        });
    }

    private void buildData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        tabUrl = new ArrayList<>();
        titles.add("头条");
        tabUrl.add(UrlValues.HEADLINENEWSURL);
        titles.add("精选");
        tabUrl.add(UrlValues.SELECTEDNEWSURL);
        titles.add("娱乐");
        tabUrl.add(UrlValues.ENTERTAINMENTNEWSURL);
        titles.add("体育");
        tabUrl.add(UrlValues.SPORTSNEWSURL);
        titles.add("网易号");
        tabUrl.add(UrlValues.NETEASENUMNEWSURL);
        titles.add("视频");
        tabUrl.add(UrlValues.VIDEONEWSURL);
        titles.add("财经");
        tabUrl.add(UrlValues.FINANCIALNEWSURL);
        titles.add("科技");
        tabUrl.add(UrlValues.TECHNEWSURL);
        titles.add("汽车");
        tabUrl.add(UrlValues.AUTONEWSURL);
        titles.add("时尚");
        tabUrl.add(UrlValues.FASHIONNEWSURL);
        titles.add("图片");
        tabUrl.add(UrlValues.PHOTONEWSURL);
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(NewsTabFragment.newInstance(tabUrl.get(i)));
        }
    }
}
