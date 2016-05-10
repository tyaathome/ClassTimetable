package com.example.tyaathome.classtimetable.view.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.adapter.AdapterWeekFragment;
import com.example.tyaathome.classtimetable.view.fragment.FragmentClassTimetable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager = null;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private AdapterWeekFragment adapterWeekFragment = null;

    private RelativeLayout rlCurrentTab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        rlCurrentTab = (RelativeLayout) findViewById(R.id.current_tab);
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("position: " + position + ", positionOffset: " + positionOffset + ",positionOffsetPixels: " + positionOffsetPixels);

                //changeViewPosition(rlCurrentTab, positionOffset);

                int[] location = new int[2];
                rlCurrentTab.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                rlCurrentTab.scrollTo(positionOffsetPixels, y);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        FragmentClassTimetable f1 = new FragmentClassTimetable();
        FragmentClassTimetable f2 = new FragmentClassTimetable();
        FragmentClassTimetable f3 = new FragmentClassTimetable();
        f1.setPageName("f11111111111");
        f2.setPageName("f22222222222");
        f3.setPageName("f33333333333");
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        adapterWeekFragment = new AdapterWeekFragment(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterWeekFragment);
    }

    private void changeViewPosition(View view, float percentage) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int viewWidth = layoutParams.width;
        float distance = (x - viewWidth) / 2.f;
        int newX = (int)(distance * (1-percentage));
        view.scrollTo(newX ,y);
        System.out.println(newX+"");
    }
}
