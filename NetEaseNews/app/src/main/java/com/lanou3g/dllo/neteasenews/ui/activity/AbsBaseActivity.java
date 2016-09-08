package com.lanou3g.dllo.neteasenews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/8.
 * Activity的基类
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setLayout());
        initViews();
        initDatas();
    }

    /**
     * 设置布局文件
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 简化findViewById
     */
    protected <T extends View> T byView(int resId){
        return (T) findViewById(resId);
    }

    /**
     * 跳转不传值
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to){
        startActivity(new Intent(from, to));
    }

    /**
     * 跳转传值
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle extras){
        Intent intent = new Intent(from, to);
        intent.putExtras(extras);
        startActivity(intent);

    }

    /**
     * Activity结束动画
     */

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.xx, R.anim.xx);
    }


}
