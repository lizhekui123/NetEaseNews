package com.lanou3g.dllo.neteasenews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.db.CollectBean;
import com.lanou3g.dllo.neteasenews.model.db.LiteOrmInstance;
import com.lanou3g.dllo.neteasenews.ui.adapter.common.CollectListAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/11.
 * 收藏界面
 */
public class CollectActivity extends AbsBaseActivity{
    private ImageView collectBackIv;
    private ListView collectLv;
    private TextView collectTv;
    private CollectListAdapter adapter;

    private String username;
    private List<CollectBean> datas;

    @Override
    protected int setLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initViews() {
        collectBackIv = byView(R.id.collect_back_iv);
        collectLv = byView(R.id.collect_lv);
        collectTv = byView(R.id.collect_tv);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        username = intent.getStringExtra("String");

        datas = LiteOrmInstance.getInstance().queryByUser(username);
        if (datas.size() > 0){
            collectTv.setVisibility(View.GONE);
            collectLv.setVisibility(View.VISIBLE);
            adapter = new CollectListAdapter(this);
            collectLv.setAdapter(adapter);
            adapter.setDatas(datas);
            collectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CollectBean collectBean = datas.get(position);
                    String type = collectBean.getUrlType();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("collectBean",collectBean);

                    if (type.equals("photo")){
                        Log.d("CollectActivity", "bundle1:" + bundle);
                        goTo(CollectActivity.this,NewsPhotoActivity.class, bundle);
                    } else {
                        Log.d("CollectActivity", "bundle2:" + bundle);
                        goTo(CollectActivity.this,NewsInfoActivity.class, bundle);
                    }
                }
            });
        } else {
            collectTv.setVisibility(View.VISIBLE);
            collectLv.setVisibility(View.GONE);
        }

        collectBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        datas = LiteOrmInstance.getInstance().queryByUser(username);
        if (datas.size() > 0){
            collectTv.setVisibility(View.GONE);
            collectLv.setVisibility(View.VISIBLE);
            adapter.setDatas(datas);
        } else {
            collectTv.setVisibility(View.VISIBLE);
            collectLv.setVisibility(View.GONE);
        }
    }
}
