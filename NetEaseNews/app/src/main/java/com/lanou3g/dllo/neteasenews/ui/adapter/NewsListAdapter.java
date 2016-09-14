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
import com.lanou3g.dllo.neteasenews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class NewsListAdapter extends BaseAdapter{

    private Context context;
    private List<NewsBean.T1348647909107Bean> datas;

    public void setDatas(List<NewsBean.T1348647909107Bean> datas) {
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
        NewsBean.T1348647909107Bean bean = datas.get(position);
        if (bean != null) {
            holder.textView.setText(bean.getTitle());
            Picasso.with(context).load(bean.getImgsrc()).into(holder.imageView);
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
