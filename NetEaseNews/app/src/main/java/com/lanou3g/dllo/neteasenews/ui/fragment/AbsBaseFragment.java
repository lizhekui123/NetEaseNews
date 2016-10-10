package com.lanou3g.dllo.neteasenews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou3g.dllo.neteasenews.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/9/8.
 * Fragment的基类
 */
public abstract class AbsBaseFragment extends Fragment{
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }

    /**
     * 设置布局文件
     * @return R.layout.xx
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 简化findViewById
     */
    protected <T extends View> T byView(int resId){
        return (T) getView().findViewById(resId);
    }

    /**
     * 跳转不传值
     */
    protected void goTo(Class<? extends AbsBaseActivity> to){
        context.startActivity(new Intent(context, to));
    }

    /**
     * 跳转传值
     */
    protected void goTo(Class<? extends AbsBaseActivity> to, Bundle extras){
        Intent intent = new Intent(context, to);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    protected void goTo(Class<? extends AbsBaseActivity> to,String url,Bundle extras){
        Intent intent = new Intent(context, to);
        intent.putExtra("url",url);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}
