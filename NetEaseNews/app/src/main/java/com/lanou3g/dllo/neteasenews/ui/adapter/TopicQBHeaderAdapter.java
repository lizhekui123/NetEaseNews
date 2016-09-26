package com.lanou3g.dllo.neteasenews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicQBHeaderBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class TopicQBHeaderAdapter extends RecyclerView.Adapter<TopicQBHeaderAdapter.HeaderViewHolder>{
    private Context context;
    private List<TopicQBHeaderBean.DataBean> datas;
    private boolean menuIsSelected;

    public TopicQBHeaderAdapter(Context context) {
        this.context = context;
    }

    public void setMenuIsSelected(boolean menuIsSelected) {
        this.menuIsSelected = menuIsSelected;
        notifyDataSetChanged();
    }

    public void setDatas(List<TopicQBHeaderBean.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_qb_header_recyclerview,parent,false);
        HeaderViewHolder holder = new HeaderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getIcon()).into(holder.headerIv);
        holder.headerTv.setText(datas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (menuIsSelected) {
            return datas == null ? 0 : datas.size();
        } else {
            return datas == null ? 0 : 5;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{
        private ImageView headerIv;
        private TextView headerTv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerIv = (ImageView) itemView.findViewById(R.id.item_qb_header_rv_iv);
            headerTv = (TextView) itemView.findViewById(R.id.item_qb_header_rv_tv);
        }
    }
}
