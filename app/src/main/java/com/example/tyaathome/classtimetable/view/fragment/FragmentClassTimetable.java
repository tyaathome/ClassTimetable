package com.example.tyaathome.classtimetable.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.adapter.AdapterClassTimeTable;
import com.example.tyaathome.classtimetable.view.myview.OnMyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/10.
 */
public class FragmentClassTimetable extends Fragment {

    private String mPageName = "";

    private RecyclerView recyclerView = null;
    private AdapterClassTimeTable adapter = null;

    private List<String> listdata = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.layout_class_timetable, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        recyclerView.addOnItemTouchListener(new OnMyItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh, int position) {
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
                adapter.notifyItemChanged(position);
            }
        });
    }

    private void initData() {
        adapter = new AdapterClassTimeTable(getActivity(), listdata);
        recyclerView.setAdapter(adapter);
    }

    public void setPageName(String str) {
        mPageName = str;
    }

    public String getPageName() {
        return mPageName;
    }

    public void setData(List<String> listdata) {
        this.listdata.clear();
        this.listdata.addAll(listdata);
    }

    public void addItem(String str) {
        this.listdata.add(str);
        adapter.notifyItemInserted(this.listdata.size()-1);
    }

    public void removeItem(int position) {
        this.listdata.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
