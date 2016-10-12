package com.lanou3g.dllo.neteasenews.ui.adapter.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;
import com.lanou3g.dllo.neteasenews.model.db.CollectBean;

import java.util.List;

/**
 * Created by dllo on 16/10/12.
 */
public class CollectListAdapter extends BaseAdapter{
    private Context context;
    private List<CollectBean> datas;

    public CollectListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CollectBean> datas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collect_lv,parent,false);
            holder = new CollectViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CollectViewHolder) convertView.getTag();
        }
        holder.textView.setText(datas.get(position).getTitle());
        return convertView;
    }
    class CollectViewHolder {
        private TextView textView;

        public CollectViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.item_collect_lv_tv);
        }
    }
}
