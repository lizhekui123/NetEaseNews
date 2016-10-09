package com.lanou3g.dllo.neteasenews.ui.adapter.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.tools.StringTool;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 新闻-视频适配器
 */
public class NewsVideoAdapter extends RecyclerView.Adapter<NewsVideoAdapter.VideoViewHolder>{
    private Context context;
    private List<NewsBean.视频Bean> datas;

    public NewsVideoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<NewsBean.视频Bean> datas) {
        this.datas = datas;
        Log.d("NewsVideoAdapter", "datas:" + datas);
        notifyDataSetChanged();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_video,parent,false);
        VideoViewHolder holder = new VideoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        NewsBean.视频Bean bean = datas.get(position);
        Log.d("NewsVideoAdapter", "bean:" + bean);
        Picasso.with(context).load(bean.getCover()).into(holder.videoIv);
        Picasso.with(context).load(bean.getTopicImg()).into(holder.dpIv);
        holder.timeTv.setText(StringTool.doVideoText(bean.getLength(),bean.getPlayCount()));
        holder.titleTv.setText(bean.getTitle());
        holder.fromTv.setText(bean.getTopicName());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView videoIv, dpIv;
        private TextView timeTv, titleTv, fromTv;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoIv = (ImageView) itemView.findViewById(R.id.item_news_video_iv);
            dpIv = (ImageView) itemView.findViewById(R.id.item_news_video_dp_iv);
            timeTv = (TextView) itemView.findViewById(R.id.item_news_video_time_tv);
            titleTv = (TextView) itemView.findViewById(R.id.item_news_video_title_tv);
            fromTv = (TextView) itemView.findViewById(R.id.item_news_video_from_tv);
        }
    }
}
