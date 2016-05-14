package com.example.tyaathome.classtimetable.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;

import java.util.List;

/**
 * Created by tyaathome on 2016/5/14.
 */
public class AdapterSettings extends BaseAdapter {

    private Context context;
    private List<String> listdata;

    public AdapterSettings(Context context, List<String> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_settings, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(listdata.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView tv;
    }
}
