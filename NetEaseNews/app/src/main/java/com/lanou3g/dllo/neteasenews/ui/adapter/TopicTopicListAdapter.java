package com.lanou3g.dllo.neteasenews.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicTpBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/19.
 * 话题-话题ListView适配器
 */
public class TopicTopicListAdapter extends BaseAdapter {

    private TopicTpBean bean;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList datas = new ArrayList();
    private List<TopicTpItemType> types = new ArrayList<>();


    public enum TopicTpItemType {
        HEADER, DP, TEXT, THREEPIC
    }

    public void setDatas(ArrayList datas, List<TopicTpItemType> types) {
        this.datas = datas;
        this.types = types;
        Log.d("TopicTopicListAdapter", "datas:" + datas);
        Log.d("TopicTopicListAdapter", "types:" + types);
        notifyDataSetChanged();
    }

    public TopicTopicListAdapter(Context context) {
        this.context = context;
    }


//    private boolean hasData() {
//        return datas.size() > 0 && datas != null;
//    }


    @Override
    public int getCount() {
//        return hasData() ? datas.size() : 0;
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
//        return hasData() ? datas.get(position) : null;
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeaderHolder headerHolder = null;
        DPHolder dpHolder = null;
        ContentTextHolder textHolder = null;
        ContentThreePicHolder threePicHolder = null;
        TopicTpItemType type = types.get(position);

        if (convertView == null) {
            switch (type) {
                case HEADER:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_header, parent, false);
                    headerHolder = new HeaderHolder(convertView);
                    convertView.setTag(headerHolder);
                    break;
                case DP:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_dp, parent, false);
                    dpHolder = new DPHolder(convertView);
                    convertView.setTag(dpHolder);
                    break;
                case TEXT:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_simple,parent,false);
                    textHolder = new ContentTextHolder(convertView);
                    convertView.setTag(textHolder);
                    break;
                case THREEPIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_three_pic, parent, false);
                    threePicHolder = new ContentThreePicHolder(convertView);
                    convertView.setTag(threePicHolder);
                    break;
            }
        } else {
            switch (type) {
                case HEADER:
                    headerHolder = (HeaderHolder) convertView.getTag();
                    break;
                case DP:
                    dpHolder = (DPHolder) convertView.getTag();
                    break;
                case TEXT:
                    textHolder = (ContentTextHolder) convertView.getTag();
                    break;
                case THREEPIC:
                    threePicHolder = (ContentThreePicHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case HEADER:
                List<TopicTpBean.话题Bean> headList = (List<TopicTpBean.话题Bean>) datas.get(position);
                Log.d("TopicTopicListAdapter", "headList:" + headList);
                Picasso.with(context).load(headList.get(0).getPicUrl()).into(headerHolder.headFirstIv);
                headerHolder.headFirstTv.setText(doTitleText(headList.get(0).getTopicName()));
                Picasso.with(context).load(headList.get(1).getPicUrl()).into(headerHolder.headSecondIv);
                headerHolder.headSecondTv.setText(doTitleText(headList.get(1).getTopicName()));
                Picasso.with(context).load(headList.get(2).getPicUrl()).into(headerHolder.headThirdIv);
                headerHolder.headThirdTv.setText(doTitleText(headList.get(2).getTopicName()));
                Picasso.with(context).load(headList.get(3).getPicUrl()).into(headerHolder.headFourthIv);
                headerHolder.headFourthTv.setText(doTitleText(headList.get(3).getTopicName()));
                Picasso.with(context).load(headList.get(4).getPicUrl()).into(headerHolder.headFifthIv);
                headerHolder.headFifthTv.setText(doTitleText(headList.get(4).getTopicName()));
                break;
            case DP:
                List<TopicTpBean.DataBean.RecomendExpertBean.ExpertListBean> dpList =
                        (List<TopicTpBean.DataBean.RecomendExpertBean.ExpertListBean>) datas.get(position);
                Log.d("TopicTopicListAdapter", "dpList:" + dpList);
                Picasso.with(context).load(dpList.get(0).getHeadpicurl()).into(dpHolder.dpFirstDpCiv);
                dpHolder.dpFirstNameTv.setText(dpList.get(0).getName());
                dpHolder.dpFirstFollowNumTv.setText(doFollowNumText(dpList.get(0).getConcernCount()));
                Picasso.with(context).load(dpList.get(1).getHeadpicurl()).into(dpHolder.dpSecondDpCiv);
                dpHolder.dpSecondNameTv.setText(dpList.get(1).getName());
                dpHolder.dpSecondFollowNumTv.setText(doFollowNumText(dpList.get(1).getConcernCount()));
                Picasso.with(context).load(dpList.get(2).getHeadpicurl()).into(dpHolder.dpThirdDpCiv);
                dpHolder.dpThirdNameTv.setText(dpList.get(2).getName());
                dpHolder.dpThirdFollowNumTv.setText(doFollowNumText(dpList.get(2).getConcernCount()));
                Picasso.with(context).load(dpList.get(3).getHeadpicurl()).into(dpHolder.dpFourthDpCiv);
                dpHolder.dpFourthNameTv.setText(dpList.get(3).getName());
                dpHolder.dpFourthFollowNumTv.setText(doFollowNumText(dpList.get(3).getConcernCount()));
                break;
            case TEXT:
                TopicTpBean.DataBean.SubjectListBean textBean = (TopicTpBean.DataBean.SubjectListBean) datas.get(position);
                Log.d("TopicTopicListAdapter", "textBean:" + textBean);
                textHolder.textTitleTv.setText(doTitleText(textBean.getName()));
                String firstDpUrl = textBean.getTalkContent().get(0).getUserHeadPicUrl();
                if (!firstDpUrl.isEmpty()){
//                if (firstDpUrl != null){
                    Picasso.with(context).load(firstDpUrl).into(textHolder.textFirstDpCiv);
                }
                textHolder.textFirstContentTv.setText(textBean.getTalkContent().get(0).getContent());
                String secondDpUrl = textBean.getTalkContent().get(1).getUserHeadPicUrl();
                if (!secondDpUrl.isEmpty()){
//                if (secondDpUrl != null){
                    Picasso.with(context).load(secondDpUrl).into(textHolder.textSecondDpCiv);
                }
                textHolder.textSecondContentTv.setText(textBean.getTalkContent().get(1).getContent());
                textHolder.textTypeTv.setText(textBean.getClassification());
                textHolder.textFollowNumTv.setText(doFollowNumText(textBean.getConcernCount()));
                textHolder.textDiscussionNumTv.setText(doDiscussionNumText(textBean.getTalkCount()));
                break;
            case THREEPIC:
                TopicTpBean.DataBean.SubjectListBean threePicBean = (TopicTpBean.DataBean.SubjectListBean) datas.get(position);
                Log.d("TopicTopicListAdapter", "threePicBean:" + threePicBean);
                threePicHolder.threePicTitleTv.setText(doTitleText(threePicBean.getName()));
                Picasso.with(context).load(threePicBean.getTalkPicture().get(0)).into(threePicHolder.threePicFirstIv);
                Picasso.with(context).load(threePicBean.getTalkPicture().get(1)).into(threePicHolder.threePicSecondIv);
                Picasso.with(context).load(threePicBean.getTalkPicture().get(2)).into(threePicHolder.threePicThirdIv);
                threePicHolder.threePicTypeTv.setText(threePicBean.getClassification());
                threePicHolder.threePicFollowNumTv.setText(doFollowNumText(threePicBean.getConcernCount()));
                threePicHolder.threePicDiscussionNumTv.setText(doDiscussionNumText(threePicBean.getTalkCount()));
                break;
        }
        return convertView;
    }

    private String doTitleText (String title) {
        String str  = "#" + title + "#";
        return str;
    }

    private String doFollowNumText(int concernCount) {
        String str;
        if (concernCount >= 10000) {
            double temp = concernCount/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万关注";
        } else {
            str = concernCount + "关注";
        }
        return str;
    }
    private String doDiscussionNumText(int concernCount) {
        String str;
        if (concernCount >= 10000) {
            double temp = concernCount/10000.0;
            double finalNum = (double) (Math.round(temp*10))/10;
            str = finalNum + "万讨论";
        } else {
            str = concernCount + "讨论";
        }
        return str;
    }

    class HeaderHolder {
        private ImageView headFirstIv;
        private TextView headFirstTv;
        private ImageView headSecondIv;
        private TextView headSecondTv;
        private ImageView headThirdIv;
        private TextView headThirdTv;
        private ImageView headFourthIv;
        private TextView headFourthTv;
        private ImageView headFifthIv;
        private TextView headFifthTv;

        public HeaderHolder(View view) {
            headFirstIv = (ImageView) view.findViewById(R.id.item_topic_head_first_iv);
            headFirstTv = (TextView) view.findViewById(R.id.item_topic_head_first_tv);
            headSecondIv = (ImageView) view.findViewById(R.id.item_topic_head_second_iv);
            headSecondTv = (TextView) view.findViewById(R.id.item_topic_head_second_tv);
            headThirdIv = (ImageView) view.findViewById(R.id.item_topic_head_third_iv);
            headThirdTv = (TextView) view.findViewById(R.id.item_topic_head_third_tv);
            headFourthIv = (ImageView) view.findViewById(R.id.item_topic_head_fourth_iv);
            headFourthTv = (TextView) view.findViewById(R.id.item_topic_head_fourth_tv);
            headFifthIv = (ImageView) view.findViewById(R.id.item_topic_head_fifth_iv);
            headFifthTv = (TextView) view.findViewById(R.id.item_topic_head_fifth_tv);
        }
    }

    class DPHolder {
        private CircleImageView dpFirstDpCiv;
        private TextView dpFirstNameTv;
        private TextView dpFirstFollowNumTv;
        private CircleImageView dpSecondDpCiv;
        private TextView dpSecondNameTv;
        private TextView dpSecondFollowNumTv;
        private CircleImageView dpThirdDpCiv;
        private TextView dpThirdNameTv;
        private TextView dpThirdFollowNumTv;
        private CircleImageView dpFourthDpCiv;
        private TextView dpFourthNameTv;
        private TextView dpFourthFollowNumTv;

        public DPHolder(View view) {
            dpFirstDpCiv = (CircleImageView) view.findViewById(R.id.item_topic_dp_first_dp_civ);
            dpFirstNameTv = (TextView) view.findViewById(R.id.item_topic_dp_first_name_tv);
            dpFirstFollowNumTv = (TextView) view.findViewById(R.id.item_topic_dp_first_follow_num_tv);
            dpSecondDpCiv = (CircleImageView) view.findViewById(R.id.item_topic_dp_second_dp_civ);
            dpSecondNameTv = (TextView) view.findViewById(R.id.item_topic_dp_second_name_tv);
            dpSecondFollowNumTv = (TextView) view.findViewById(R.id.item_topic_dp_second_follow_num_tv);
            dpThirdDpCiv = (CircleImageView) view.findViewById(R.id.item_topic_dp_third_dp_civ);
            dpThirdNameTv = (TextView) view.findViewById(R.id.item_topic_dp_third_name_tv);
            dpThirdFollowNumTv = (TextView) view.findViewById(R.id.item_topic_dp_third_follow_num_tv);
            dpFourthDpCiv = (CircleImageView) view.findViewById(R.id.item_topic_dp_fourth_dp_civ);
            dpFourthNameTv = (TextView) view.findViewById(R.id.item_topic_dp_fourth_name_tv);
            dpFourthFollowNumTv = (TextView) view.findViewById(R.id.item_topic_dp_fourth_follow_num_tv);
        }
    }

    class ContentTextHolder {
        private TextView textTitleTv;
        private ImageView textFirstDpCiv;
        private TextView textFirstContentTv;
        private ImageView textSecondDpCiv;
        private TextView textSecondContentTv;
        private TextView textTypeTv;
        private TextView textFollowNumTv;
        private TextView textDiscussionNumTv;

        public ContentTextHolder(View view) {
            textTitleTv = (TextView) view.findViewById(R.id.item_topic_text_title_tv);
            textFirstDpCiv = (ImageView) view.findViewById(R.id.item_topic_text_first_dp_civ);
            textFirstContentTv = (TextView) view.findViewById(R.id.item_topic_text_first_content_tv);
            textSecondDpCiv = (ImageView) view.findViewById(R.id.item_topic_text_second_dp_civ);
            textSecondContentTv = (TextView) view.findViewById(R.id.item_topic_text_second_content_tv);
            textTypeTv = (TextView) view.findViewById(R.id.item_topic_text_type_tv);
            textFollowNumTv = (TextView) view.findViewById(R.id.item_topic_text_follow_num_tv);
            textDiscussionNumTv = (TextView) view.findViewById(R.id.item_topic_text_discussion_num_tv);
        }
    }

    class ContentThreePicHolder {
        private TextView threePicTitleTv;
        private ImageView threePicFirstIv;
        private ImageView threePicSecondIv;
        private ImageView threePicThirdIv;
        private TextView threePicTypeTv;
        private TextView threePicFollowNumTv;
        private TextView threePicDiscussionNumTv;

        public ContentThreePicHolder(View view) {
            threePicTitleTv = (TextView) view.findViewById(R.id.item_topic_three_pic_title_tv);
            threePicFirstIv = (ImageView) view.findViewById(R.id.item_topic_three_pic_first_iv);
            threePicSecondIv = (ImageView) view.findViewById(R.id.item_topic_three_pic_second_iv);
            threePicThirdIv = (ImageView) view.findViewById(R.id.item_topic_three_pic_third_iv);
            threePicTypeTv = (TextView) view.findViewById(R.id.item_topic_three_pic_type_tv);
            threePicFollowNumTv = (TextView) view.findViewById(R.id.item_topic_three_pic_follow_num_tv);
            threePicDiscussionNumTv = (TextView) view.findViewById(R.id.item_topic_three_pic_discussion_num_tv);
        }
    }

}
