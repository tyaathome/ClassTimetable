package com.example.tyaathome.classtimetable.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tyaathome.classtimetable.view.fragment.FragmentClassTimetable;

import java.util.List;

/**
 * Created by tyaathome on 2016/5/10.
 */
public class AdapterWeekFragment extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public AdapterWeekFragment(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragmentList != null) {
            return fragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        FragmentClassTimetable fragment = (FragmentClassTimetable) fragmentList.get(position);
        return fragment.getPageName();
    }
}
