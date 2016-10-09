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
import com.lanou3g.dllo.neteasenews.model.net.UrlValues;
import com.lanou3g.dllo.neteasenews.tools.ScreenSizeUtil;
import com.lanou3g.dllo.neteasenews.tools.StringTool;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 * 新闻ListView适配器
 */
public class NewsListAdapter<T> extends BaseAdapter {

    private Context context;
    private String url;
    private List<T> datas;
    private static final int TYPE_SIMPLE = 0;
    private static final int TYPE_LIVE = 1;
    private static final int TYPE_PHOTOSET = 2;

    public void setDatas(String url, List<T> datas) {
        this.url = url;
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return datas;
    }

    public NewsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size() - 1;
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
        String skipType = null;
        if (url.equals(UrlValues.HEADLINENEWSURL)) {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.SELECTEDNEWSURL)) {
            NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
            NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.SPORTSNEWSURL)) {
            NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.FINANCIALNEWSURL)) {
            NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean) datas.get(position + 1);
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.TECHNEWSURL)) {
            NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean) datas.get(position + 1);
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.AUTONEWSURL)) {
            NewsBean.ListBean bean = (NewsBean.ListBean) datas.get(position + 1);
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else if (url.equals(UrlValues.FASHIONNEWSURL)) {
            NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        } else {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) datas.get(position + 1);
            skipType = bean.getSkipType();
            if (bean.getImgType() == 1) {
                skipType = "live";
            }
        }
        if (skipType != null) {
            if (skipType.equals("photoset")) {
                return TYPE_PHOTOSET;
            } else if (skipType.equals("live")) {
                return TYPE_LIVE;
            } else {
                return TYPE_SIMPLE;
            }
        } else {
            return TYPE_SIMPLE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemSimpleViewHolder simpleViewHolder = null;
        NewsItemLiveViewHolder liveViewHolder = null;
        NewsItemPhotoSetViewHolder photoSetViewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            int height = ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT);
            switch (type) {
                case TYPE_SIMPLE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_simple, parent, false);
                    ViewGroup.LayoutParams paramsSimple = convertView.getLayoutParams();
                    paramsSimple.height = height / 6;
                    convertView.setLayoutParams(paramsSimple);
                    simpleViewHolder = new NewsItemSimpleViewHolder(convertView);
                    convertView.setTag(simpleViewHolder);
                    break;
                case TYPE_LIVE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_live, parent, false);
                    ViewGroup.LayoutParams paramsLive = convertView.getLayoutParams();
                    paramsLive.height = height / 3;
                    convertView.setLayoutParams(paramsLive);
                    liveViewHolder = new NewsItemLiveViewHolder(convertView);
                    convertView.setTag(liveViewHolder);
                    break;
                case TYPE_PHOTOSET:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_photoset, parent, false);
                    ViewGroup.LayoutParams paramsPS = convertView.getLayoutParams();
                    paramsPS.height = height / 7 * 2;
                    convertView.setLayoutParams(paramsPS);
                    photoSetViewHolder = new NewsItemPhotoSetViewHolder(convertView);
                    convertView.setTag(photoSetViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_SIMPLE:
                    simpleViewHolder = (NewsItemSimpleViewHolder) convertView.getTag();
                    break;
                case TYPE_LIVE:
                    liveViewHolder = (NewsItemLiveViewHolder) convertView.getTag();
                    break;
                case TYPE_PHOTOSET:
                    photoSetViewHolder = (NewsItemPhotoSetViewHolder) convertView.getTag();
                    break;
            }
        }
        if (url.equals(UrlValues.HEADLINENEWSURL)) {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }

            }
        } else if (url.equals(UrlValues.SELECTEDNEWSURL)) {
            NewsBean.T1467284926140Bean bean = (NewsBean.T1467284926140Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.ENTERTAINMENTNEWSURL)) {
            NewsBean.T1348648517839Bean bean = (NewsBean.T1348648517839Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.SPORTSNEWSURL)) {
            NewsBean.T1348649079062Bean bean = (NewsBean.T1348649079062Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.VIDEONEWSURL)) {
            NewsBean.视频Bean bean = (NewsBean.视频Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getTopicImg()).into(simpleViewHolder.imageView);
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getTopicImg()).into(liveViewHolder.imageView);
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getTopicImg()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        break;
                }
                // 暂留,后删除
            }
        } else if (url.equals(UrlValues.FINANCIALNEWSURL)) {
            NewsBean.T1348648756099Bean bean = (NewsBean.T1348648756099Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.TECHNEWSURL)) {
            NewsBean.T1348649580692Bean bean = (NewsBean.T1348649580692Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.AUTONEWSURL)) {
            NewsBean.ListBean bean = (NewsBean.ListBean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.FASHIONNEWSURL)) {
            NewsBean.T1348650593803Bean bean = (NewsBean.T1348650593803Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }
        } else if (url.equals(UrlValues.PHOTONEWSURL)) {
            NewsBean.PhotoBean bean = (NewsBean.PhotoBean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getSetname());
                        Picasso.with(context).load(bean.getCover()).into(simpleViewHolder.imageView);
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getSetname());
                        Picasso.with(context).load(bean.getCover()).into(liveViewHolder.imageView);
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getSetname());
                        Picasso.with(context).load(bean.getCover()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        break;
                }
                // 暂留,后删除
            }
        } else {
            NewsBean.T1348647909107Bean bean = (NewsBean.T1348647909107Bean) datas.get(position + 1);
            if (bean != null) {
                switch (type) {
                    case TYPE_SIMPLE:
                        simpleViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(simpleViewHolder.imageView);
                        simpleViewHolder.fromTv.setText(bean.getSource());
                        simpleViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                    case TYPE_LIVE:
                        liveViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(liveViewHolder.imageView);
                        liveViewHolder.fromTv.setText(bean.getSource());
                        liveViewHolder.startTimeTv.setText(bean.getTAG());
                        break;
                    case TYPE_PHOTOSET:
                        photoSetViewHolder.titleTv.setText(bean.getTitle());
                        Picasso.with(context).load(bean.getImgsrc()).into(photoSetViewHolder.firstIv);
                        Picasso.with(context).load(bean.getImgextra().get(0).getImgsrc()).into(photoSetViewHolder.secondIv);
                        Picasso.with(context).load(bean.getImgextra().get(1).getImgsrc()).into(photoSetViewHolder.thirdIv);
                        photoSetViewHolder.fromTv.setText(bean.getSource());
                        photoSetViewHolder.replyNumTv.setText(StringTool.doReplyNumText(bean.getReplyCount()));
                        break;
                }
            }

        }
        return convertView;
    }

    public class NewsItemSimpleViewHolder {
        ImageView imageView;
        TextView titleTv, fromTv, replyNumTv;

        public NewsItemSimpleViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_news_simple_title_tv);
            imageView = (ImageView) view.findViewById(R.id.item_news_simple_img);
            fromTv = (TextView) view.findViewById(R.id.item_news_simple_from_tv);
            replyNumTv = (TextView) view.findViewById(R.id.item_news_simple_reply_num_tv);
        }
    }

    public class NewsItemLiveViewHolder {
        ImageView imageView;
        TextView titleTv, fromTv, startTimeTv;

        public NewsItemLiveViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_news_live_title_tv);
            imageView = (ImageView) view.findViewById(R.id.item_news_live_iv);
            fromTv = (TextView) view.findViewById(R.id.item_news_live_from_tv);
            startTimeTv = (TextView) view.findViewById(R.id.item_news_live_start_time_tv);
        }
    }

    public class NewsItemPhotoSetViewHolder {
        ImageView firstIv, secondIv, thirdIv;
        TextView titleTv, fromTv, replyNumTv;

        public NewsItemPhotoSetViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_news_photoset_title_tv);
            firstIv = (ImageView) view.findViewById(R.id.item_news_photoset_first_iv);
            secondIv = (ImageView) view.findViewById(R.id.item_news_photoset_second_iv);
            thirdIv = (ImageView) view.findViewById(R.id.item_news_photoset_third_iv);
            fromTv = (TextView) view.findViewById(R.id.item_news_photoset_from_tv);
            replyNumTv = (TextView) view.findViewById(R.id.item_news_photoset_reply_num_tv);
        }
    }
}
