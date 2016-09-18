package com.lanou3g.dllo.neteasenews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 * 新闻ListView适配器
 */
public class NewsListAdapter<T> extends BaseAdapter{

    private Context context;
    private String url;
    private List<T> datas;

    public void setDatas(String url,List<T> datas) {
        this.url = url;
        this.datas = datas;
        notifyDataSetChanged();
    }

    public NewsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null :datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_simple,parent,false);
            int height = ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = height/ 6;
            convertView.setLayoutParams(params);
            holder = new NewsItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewsItemViewHolder) convertView.getTag();
        }
        if (url.equals(UrlValues.HEADLINENEWSURL)){
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.SELECTEDNEWSURL)){
            NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.ENTERTAINMENTNEWSURL)){
            NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.SPORTSNEWSURL)){
            NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.NETEASENUMNEWSURL)){
            NewsBean.推荐Bean bean = (NewsBean.推荐Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.VIDEONEWSURL)){
            NewsBean.视频Bean bean = (NewsBean.视频Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getTopicImg()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.FINANCIALNEWSURL)){
            NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.TECHNEWSURL)){
            NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.AUTONEWSURL)){
            NewsBean.ListBean bean = (NewsBean.ListBean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.FASHIONNEWSURL)){
            NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }
        } else if (url.equals(UrlValues.PHOTONEWSURL)){
            NewsBean.PhotoBean bean = (NewsBean.PhotoBean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getSetname());
                Picasso.with(context).load(bean.getCover()).into(holder.imageView);
            }
        } else {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean)datas.get(position);
            if (bean != null) {
                holder.textView.setText(bean.getTitle());
                Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
            }

        }
        return convertView;
    }
    public class NewsItemViewHolder{
        ImageView imageView;
        TextView textView;

        public NewsItemViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.item_news_simple_tv);
            imageView = (ImageView) view.findViewById(R.id.item_news_simple_img);
        }
    }
}
