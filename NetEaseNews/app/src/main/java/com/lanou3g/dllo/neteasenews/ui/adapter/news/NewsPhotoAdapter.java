package com.lanou3g.dllo.neteasenews.ui.adapter.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.NewsBean;
import com.lanou3g.dllo.neteasenews.tools.StringTool;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 新闻-图片适配器
 */
public class NewsPhotoAdapter extends BaseAdapter{
    private Context context;
    private List<NewsBean.PhotoBean> datas;
    private static final int TYPE_ONE_PHOTO = 0;
    private static final int TYPE_THREE_PHOTO = 1;

    public NewsPhotoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<NewsBean.PhotoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
    public int getItemViewType(int position) {
        if (datas.get(position).getPics().size() > 0) {
            return TYPE_THREE_PHOTO;
        } else {
            return TYPE_ONE_PHOTO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnePhotoViewHolder onePhotoViewHolder = null;
        ThreePhotoViewHolder threePhotoViewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null){
            switch (type){
                case TYPE_ONE_PHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_one_photo,parent,false);
                    onePhotoViewHolder = new OnePhotoViewHolder(convertView);
                    convertView.setTag(onePhotoViewHolder);
                    break;
                case TYPE_THREE_PHOTO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_three_photo,parent,false);
                    threePhotoViewHolder = new ThreePhotoViewHolder(convertView);
                    convertView.setTag(threePhotoViewHolder);
                    break;
            }
        } else {
            switch (type){
                case TYPE_ONE_PHOTO:
                    onePhotoViewHolder = (OnePhotoViewHolder) convertView.getTag();
                    break;
                case TYPE_THREE_PHOTO:
                    threePhotoViewHolder = (ThreePhotoViewHolder) convertView.getTag();
                    break;
            }
        }
        NewsBean.PhotoBean bean = datas.get(position);
        switch (type){
            case TYPE_ONE_PHOTO:
                Picasso.with(context).load(bean.getCover()).into(onePhotoViewHolder.imageView);
                onePhotoViewHolder.titleTv.setText(bean.getSetname());
                int a = Integer.valueOf(bean.getReplynum());
                onePhotoViewHolder.replyNumTv.setText(StringTool.doReplyNumText(a));
                break;
            case TYPE_THREE_PHOTO:
                Picasso.with(context).load(bean.getPics().get(0)).into(threePhotoViewHolder.firstIv);
                Picasso.with(context).load(bean.getPics().get(1)).into(threePhotoViewHolder.secondIv);
                Picasso.with(context).load(bean.getPics().get(2)).into(threePhotoViewHolder.thirdIv);
                threePhotoViewHolder.titleTv.setText(bean.getSetname());
                int b = Integer.valueOf(bean.getReplynum());
                threePhotoViewHolder.replyNumTv.setText(StringTool.doReplyNumText(b));
                break;
        }
        return convertView;
    }
    class OnePhotoViewHolder{
        private ImageView imageView;
        private TextView titleTv, replyNumTv;
        public OnePhotoViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_news_one_photo_iv);
            titleTv = (TextView) view.findViewById(R.id.item_news_one_photo_title_tv);
            replyNumTv = (TextView) view.findViewById(R.id.item_news_one_photo_reply_num_tv);
        }
    }
    class ThreePhotoViewHolder{
        private ImageView firstIv,secondIv,thirdIv;
        private TextView titleTv, replyNumTv;
        public ThreePhotoViewHolder(View view) {
            firstIv = (ImageView) view.findViewById(R.id.item_news_three_photo_first_iv);
            secondIv = (ImageView) view.findViewById(R.id.item_news_three_photo_second_iv);
            thirdIv = (ImageView) view.findViewById(R.id.item_news_three_photo_third_iv);
            titleTv = (TextView) view.findViewById(R.id.item_news_three_photo_title_tv);
            replyNumTv = (TextView) view.findViewById(R.id.item_news_three_photo_reply_num_tv);
        }
    }
}
