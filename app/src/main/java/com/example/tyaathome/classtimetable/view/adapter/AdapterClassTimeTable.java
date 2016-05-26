package com.example.tyaathome.classtimetable.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.TimetableInfo;
import com.example.tyaathome.classtimetable.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/20.
 */
public class AdapterClassTimeTable extends RecyclerView.Adapter<AdapterClassTimeTable.ViewHolder> {

    private Context context;
    private List<TimetableInfo> listdata = new ArrayList<TimetableInfo>();

    public AdapterClassTimeTable(Context context, List<TimetableInfo> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_table_time, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(listdata.get(position).title);

        if(holder.itemView != null) {
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT));
            //holder.itemView.setBackgroundColor(Utils.getRandomColor());
        }
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void setData(List<TimetableInfo> listdata) {
        this.listdata.clear();
        this.listdata.addAll(listdata);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
