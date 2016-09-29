package com.lanou3g.dllo.neteasenews.ui.adapter.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.dllo.neteasenews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 * 轮播图适配器
 */
public class RotateVpAdapter extends PagerAdapter{

    private List<String> datas;
    private Context context;


    public RotateVpAdapter(Context context) {
        this.context = context;
    }

    public RotateVpAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        /**
         * 为了ViewPager能一直滑动到下一页,设置int最大值
         */
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /**
         * 为避免position数组越界,取余
         */
        int newPosition = position % datas.size();
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_rotate_vp,container,false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_rotate_iv);
        Picasso.with(context).load(datas.get(newPosition)).into(imageView);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
