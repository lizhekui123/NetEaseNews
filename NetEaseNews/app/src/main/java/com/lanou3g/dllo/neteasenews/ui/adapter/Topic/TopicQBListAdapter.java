package com.lanou3g.dllo.neteasenews.ui.adapter.Topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.bean.TopicQBBean;
import com.lanou3g.dllo.neteasenews.tools.ScreenSizeUtil;
import com.lanou3g.dllo.neteasenews.tools.StringTool;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/19.
 * 话题问吧listView适配器
 */
public class TopicQBListAdapter extends BaseAdapter{
    private Context context;
    private List<TopicQBBean.DataBean.ExpertListBean> datas;

    public void setDatas(List<TopicQBBean.DataBean.ExpertListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public TopicQBListAdapter(Context context) {
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
        QBItemViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_question_bar_simple,parent,false);
            int height = ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = height / 5 * 3;
            convertView.setLayoutParams(params);
            holder = new QBItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (QBItemViewHolder) convertView.getTag();
        }
        TopicQBBean.DataBean.ExpertListBean bean = datas.get(position);
        String nameTitle = bean.getName() + "/" + bean.getTitle();
        holder.nameTv.setText(nameTitle);
        holder.qbTv.setText(bean.getAlias());
        holder.typeTv.setText(bean.getClassification());
        int followNum = bean.getConcernCount();
        holder.followNumTv.setText(StringTool.doFollowNumText(followNum));
        int quesNum = bean.getQuestionCount();
        holder.quesNumTv.setText(StringTool.doQuesNumText(quesNum));
        Picasso.with(context).load(bean.getHeadpicurl()).into(holder.dpCiv);
        Picasso.with(context).load(bean.getPicurl()).into(holder.bigIv);
        return convertView;
    }
    public class QBItemViewHolder {
        private TextView nameTv, qbTv, typeTv, followNumTv, quesNumTv;
        private ImageView bigIv;
        private CircleImageView dpCiv;

        public QBItemViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_qb_name_tv);
            qbTv = (TextView) view.findViewById(R.id.item_qb_content_tv);
            typeTv = (TextView) view.findViewById(R.id.item_qb_type_tv);
            followNumTv = (TextView) view.findViewById(R.id.item_qb_follow_num_tv);
            quesNumTv = (TextView) view.findViewById(R.id.item_qb_question_num_tv);
            bigIv = (ImageView) view.findViewById(R.id.item_qb_iv);
            dpCiv = (CircleImageView) view.findViewById(R.id.item_qb_display_photo_civ);
        }
    }
}
