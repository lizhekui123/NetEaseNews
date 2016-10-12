package com.lanou3g.dllo.neteasenews.ui.adapter.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsPhotoBean;

import java.util.List;

/**
 * Created by dllo on 16/10/10.
 * 图片新闻ViewPager适配器
 */
public class NewsPhotoVpAdapter extends PagerAdapter{

    private List<NewsPhotoBean.PhotosBean> datas;
    private Context context;


    public NewsPhotoVpAdapter(Context context) {
        this.context = context;
    }

    public NewsPhotoVpAdapter(List<NewsPhotoBean.PhotosBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<NewsPhotoBean.PhotosBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return datas == null ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_rotate_vp,container,false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_rotate_iv);
        imageView.setAdjustViewBounds(true);
        Glide.with(context).load(datas.get(position).getImgurl()).into(imageView);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
