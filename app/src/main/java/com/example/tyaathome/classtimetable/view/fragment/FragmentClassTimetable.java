package com.example.tyaathome.classtimetable.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tyaathome.classtimetable.R;

/**
 * Created by tyaathome on 2016/5/10.
 */
public class FragmentClassTimetable extends Fragment {

    private String mPageName = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.layout_class_timetable, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setPageName(String str) {
        mPageName = str;
    }

    public String getPageName() {
        return mPageName;
    }
}
