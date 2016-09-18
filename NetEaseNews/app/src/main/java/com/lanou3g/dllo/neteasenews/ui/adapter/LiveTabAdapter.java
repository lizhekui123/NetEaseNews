package com.lanou3g.dllo.neteasenews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 直播Tab适配器
 */
public class LiveTabAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private List<String> titles;

    public LiveTabAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
