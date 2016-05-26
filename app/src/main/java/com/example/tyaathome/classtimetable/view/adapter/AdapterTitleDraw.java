package com.example.tyaathome.classtimetable.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;

import java.util.List;

public class AdapterTitleDraw extends BaseAdapter {
	private List<String> listData;

	public AdapterTitleDraw(List<String> listData) {
		this.listData = listData;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		TextView tv;
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.item_title_draw, null);
			tv = (TextView) view.findViewById(R.id.title_draw);
			view.setTag(tv);
		} else {
			tv = (TextView) view.getTag();
		}
		tv.setText(listData.get(position));
		return view;
	}
}
