package com.example.tyaathome.classtimetable.view.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.utils.RequestCode;
import com.example.tyaathome.classtimetable.utils.Utils;
import com.example.tyaathome.classtimetable.view.activity.add.ActivityAddTimetable;
import com.example.tyaathome.classtimetable.view.activity.base.ActivityBaseWithTitle;
import com.example.tyaathome.classtimetable.view.activity.settings.ActivitySettings;
import com.example.tyaathome.classtimetable.view.adapter.AdapterWeekFragment;
import com.example.tyaathome.classtimetable.view.fragment.FragmentClassTimetable;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends ActivityBaseWithTitle {

    private ViewPager viewPager = null;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private AdapterWeekFragment adapterWeekFragment = null;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.CODE_SETTINGS:
                    showToast("Settings!");
                    break;
            }
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        initLeftView();
        initRightView();
    }

    private void initLeftView() {
        LinearLayout llLeft = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_week, null);
        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("OnClick!");
            }
        });
        addViewToLeft(llLeft);
    }

    private void initRightView() {
        LinearLayout llRight = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_add_and_settings, null);
        ImageView ivAdd = (ImageView) llRight.findViewById(R.id.iv_add);
        ImageView ivSetting = (ImageView) llRight.findViewById(R.id.iv_settings);
        ivAdd.setOnClickListener(listenerRight);
        ivSetting.setOnClickListener(listenerRight);
        addViewToRight(llRight);
    }

    private View.OnClickListener listenerRight = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_add: {
//                    int index = viewPager.getCurrentItem();
//                    FragmentClassTimetable fragment = (FragmentClassTimetable) fragmentList.get(index);
//                    fragment.addItem("add");
                    Intent intent = new Intent(ActivityHome.this, ActivityAddTimetable.class);
                    startActivityForResult(intent, RequestCode.CODE_ADD_TIMETABLE);
                    break;
                }
                case R.id.iv_settings: {
                    Intent intent = new Intent(ActivityHome.this, ActivitySettings.class);
                    startActivityForResult(intent, RequestCode.CODE_SETTINGS);
                    break;
                }
            }
        }
    };

    private void initEvent() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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

        List<String> listdata = new ArrayList<String>();
        listdata.add("1111111111111");
        listdata.add("2222222222222");
        listdata.add("3333333333333");
        listdata.add("4444444444444");
        listdata.add("5555555555555");

        List<String> listdata2 = new ArrayList<String>();
        listdata2.add("2222222222222");
        listdata2.add("2222222222222");
        listdata2.add("2222222222222");
        listdata2.add("2222222222222");
        listdata2.add("2222222222222");

        List<String> listdata3 = new ArrayList<String>();
        listdata3.add("3333333333333");
        listdata3.add("3333333333333");
        listdata3.add("3333333333333");
        listdata3.add("3333333333333");
        listdata3.add("3333333333333");

        FragmentClassTimetable f1 = new FragmentClassTimetable();
        f1.setData(listdata);
        FragmentClassTimetable f2 = new FragmentClassTimetable();
        f2.setData(listdata2);
        FragmentClassTimetable f3 = new FragmentClassTimetable();
        f3.setData(listdata3);
        f1.setPageName("f11111111111");
        f2.setPageName("f22222222222");
        f3.setPageName("f33333333333");
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);


//        for(int i = 0; i < 28; i++) {
//            FragmentClassTimetable f = new FragmentClassTimetable();
//            f.setData(listdata);
//            f.setPageName(String.valueOf(i));
//            fragmentList.add(f);
//        }

        adapterWeekFragment = new AdapterWeekFragment(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterWeekFragment);
    }
}
