package com.lanou3g.dllo.neteasenews.ui.adapter.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.dllo.neteasenews.R;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 直播-分类界面头布局中GridView的适配器
 */
public class LiveHeaderGVAdapter extends BaseAdapter{
    private Context context;
    private List<String> datas;
    private int selectedItem;

    public LiveHeaderGVAdapter(Context context) {
        this.context = context;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }

    public void setDatas(List<String> datas) {
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
        GridViewViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_live_gridview,parent,false);
            holder = new GridViewViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GridViewViewHolder) convertView.getTag();
        }
        holder.textView.setText(datas.get(position));
        if(position == selectedItem){
            holder.textView.setSelected(true);
        } else {
            holder.textView.setSelected(false);
        }
        return convertView;
    }
    public class GridViewViewHolder {
        private TextView textView;
        public GridViewViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.item_live_grid_tv);
        }
    }
}
