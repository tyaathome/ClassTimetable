package com.example.tyaathome.classtimetable.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.TimetableInfo;
import com.example.tyaathome.classtimetable.view.myview.ItemClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/20.
 */
public class AdapterClassTimeTable extends RecyclerView.Adapter<AdapterClassTimeTable.ViewHolder> {

    private Context context;
    private List<TimetableInfo> listdata = new ArrayList<TimetableInfo>();

    private ItemClick delListener;
    private ItemClick editListener;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TimetableInfo info = listdata.get(position);
        holder.tvTitle.setText(info.title);

        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        String startTime = format.format(info.startCal.getTime());
        String endTime = format.format(info.endCal.getTime());
        holder.tvStartTime.setText(startTime);
        holder.tvTime.setText(startTime + " - " + endTime);
        holder.tvInfo.setText(info.info);
        holder.itemView.setBackgroundColor(Color.parseColor(info.color));


        if(holder.itemView != null) {
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            //holder.itemView.setBackgroundColor(Utils.getRandomColor());
        }

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editListener != null) {
                    editListener.itemClick(position);
                }
            }
        });

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delListener != null) {
                    delListener.itemClick(position);
                }
            }
        });
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

    public void setDeletListener(ItemClick listener) {
        delListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public View itemView;
        public TextView tvStartTime;
        public TextView tvTime;
        public TextView tvInfo;
        public ImageView ivEdit;
        public ImageView ivDel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvStartTime = (TextView) itemView.findViewById(R.id.tv_start_time);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info);
            ivEdit = (ImageView) itemView.findViewById(R.id.iv_edit);
            ivDel = (ImageView) itemView.findViewById(R.id.iv_del);
        }
    }
}
