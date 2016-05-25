package com.example.tyaathome.classtimetable.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.myview.MyCircle;

/**
 * Created by tyaathome on 2016/5/25.
 */
public class AdapterColorPicker extends BaseAdapter {

    private Context context;
    private String[] colors;

    public AdapterColorPicker(Context context, String[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_color_picker, null);
            holder.circle = (MyCircle) convertView.findViewById(R.id.circle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.circle.initPaint(colors[position]);
        //holder.circle.invalidate();


        return convertView;
    }


    private static class ViewHolder {
        MyCircle circle;
    }
}
