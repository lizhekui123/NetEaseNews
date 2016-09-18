package com.lanou3g.dllo.neteasenews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.LiveBean;
import com.lanou3g.dllo.neteasenews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 * 直播ListView适配器
 */
public class LiveListAdapter extends BaseAdapter{
    private Context context;
    private String url;
    private List<LiveBean.LiveReviewBean> datas;

    public void setDatas(List<LiveBean.LiveReviewBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public LiveListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LiveItemViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_live_simple,parent,false);
            int height = ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = height / 4;
            convertView.setLayoutParams(params);
            holder = new LiveItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (LiveItemViewHolder) convertView.getTag();
        }
        LiveBean.LiveReviewBean bean = datas.get(position);
        if (bean != null) {
            holder.textView.setText(bean.getRoomName());
            Picasso.with(context).load(bean.getImage()).into(holder.img);
            int liveStatus = bean.getLiveStatus();
            boolean isVideo = bean.isVideo();
            boolean ismutilVideo = bean.isMutilVideo();
            boolean flag = isVideo || ismutilVideo;
            /**
             * liveStatus:
             * 0: 提醒
             * 1: 直播
             * 3: 回顾
             */
            if (liveStatus == 0) {
                holder.stateImg.setImageResource(R.drawable.selector_live_notice_btn);
            } else if (liveStatus == 1) {
                if(flag){
                    holder.stateImg.setImageResource(R.mipmap.a5x);
                } else {
                    holder.stateImg.setImageResource(R.mipmap.a5w);
                }
            } else {
                if(flag){
                    holder.stateImg.setImageResource(R.mipmap.a60);
                } else {
                    holder.stateImg.setImageResource(R.mipmap.a5z);
                }

            }
        }
        return convertView;
    }
    public class LiveItemViewHolder {
        private ImageView img, stateImg;
        private TextView textView;
        public LiveItemViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.item_live_iv);
            stateImg = (ImageView) view.findViewById(R.id.item_live_state_iv);
            textView = (TextView) view.findViewById(R.id.item_live_title_tv);
        }
    }
}
