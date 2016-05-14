package com.example.tyaathome.classtimetable.view.activity.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.activity.base.ActivityBaseWithTitle;
import com.example.tyaathome.classtimetable.view.adapter.AdapterSettings;
import com.example.tyaathome.classtimetable.view.myview.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/11.
 */
public class ActivitySettings extends ActivityBaseWithTitle{

    private AdapterSettings adapterSettings;
    private MyListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.setActivityTitle(R.string.settings);
        initView();
        initEvent();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }

    private void initView() {
        initBackView();
        listView = (MyListView) findViewById(R.id.listview);
    }

    private void initBackView() {
        ImageView ivBack = (ImageView) LayoutInflater.from(this).inflate(R.layout.layout_back, null);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addViewToLeft(ivBack);
    }

    private void initEvent() {

    }

    private void initData() {
        List<String> listdata = new ArrayList<String>();
        listdata.add("Number of Weeks");
        listdata.add("Weekend Days");
        listdata.add("Notifications");
        adapterSettings = new AdapterSettings(this, listdata);
        listView.setAdapter(adapterSettings);
    }

}
