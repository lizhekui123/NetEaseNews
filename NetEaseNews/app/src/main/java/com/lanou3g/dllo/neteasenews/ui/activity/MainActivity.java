package com.lanou3g.dllo.neteasenews.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.ui.fragment.LiveFragment;
import com.lanou3g.dllo.neteasenews.ui.fragment.MeFragment;
import com.lanou3g.dllo.neteasenews.ui.fragment.NewsFragment;
import com.lanou3g.dllo.neteasenews.ui.fragment.TopicFragment;

public class MainActivity extends AbsBaseActivity {
    private RadioGroup radioGroup;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        radioGroup = byView(R.id.main_rg);
    }

    @Override
    protected void initDatas() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.main_news_rb:
                        transaction.replace(R.id.main_replace_view, new NewsFragment());
                        break;
                    case R.id.main_live_rb:
                        transaction.replace(R.id.main_replace_view, new LiveFragment());
                        break;
                    case R.id.main_topic_rb:
                        transaction.replace(R.id.main_replace_view, new TopicFragment());
                        break;
                    case R.id.main_me_rb:
                        transaction.replace(R.id.main_replace_view, new MeFragment());
                        break;
                }
                transaction.commit();
            }
        });
        radioGroup.check(R.id.main_news_rb);
    }

}
