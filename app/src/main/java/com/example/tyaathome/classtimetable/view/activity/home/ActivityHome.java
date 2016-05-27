package com.example.tyaathome.classtimetable.view.activity.home;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.DayInfo;
import com.example.tyaathome.classtimetable.utils.RequestCode;
import com.example.tyaathome.classtimetable.utils.ToolSharedPreferences;
import com.example.tyaathome.classtimetable.view.activity.add.ActivityAddTimetable;
import com.example.tyaathome.classtimetable.view.activity.base.ActivityBaseWithTitle;
import com.example.tyaathome.classtimetable.view.activity.settings.ActivitySettings;
import com.example.tyaathome.classtimetable.view.adapter.AdapterWeekFragment;
import com.example.tyaathome.classtimetable.view.fragment.FragmentClassTimetable;
import com.example.tyaathome.classtimetable.view.myview.ItemClick;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityHome extends ActivityBaseWithTitle {

    private ViewPager viewPager = null;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private AdapterWeekFragment adapterWeekFragment = null;

    private int nCurrentPage = 0;

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
                    break;
                case RequestCode.CODE_ADD_TIMETABLE:
                    updateData();
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
        final LinearLayout llLeft = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_week, null);

        final TextView tv = (TextView) llLeft.findViewById(R.id.tv_week);

        String[] weekNumber = getResources().getStringArray(R.array.week_number);
        int weekIndex = getCurrentWeekIndex();
        if(weekNumber.length > weekIndex) {
            tv.setText(weekNumber[weekIndex]);
        }

        final List<String> weekList = Arrays.asList(weekNumber);

        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Rect bounds = new Rect();
                Paint textPaint = tv.getPaint();
                String str = "Week Three";
                textPaint.getTextBounds(str ,0,str.length(),bounds);
                int height = bounds.height();
                int width = bounds.width() + 10;


                showPopupWindow(tv, weekList, width, new ItemClick() {
                    @Override
                    public void itemClick(Object obj) {
                        int position = (int) obj;
                        tv.setText(weekList.get(position));
                        int page = getWeekPageIndex(position);
                        viewPager.setCurrentItem(page);

                    }
                });
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
                    Intent intent = new Intent(ActivityHome.this, ActivityAddTimetable.class);
                    intent.putExtra("index", getCurrentPage(nCurrentPage));
                    startActivityForResult(intent, RequestCode.CODE_ADD_TIMETABLE);
                    break;
                }
                case R.id.iv_settings: {
//                    Intent intent = new Intent(ActivityHome.this, ActivitySettings.class);
//                    startActivityForResult(intent, RequestCode.CODE_SETTINGS);
                    showToast(getResources().getString(R.string.comming_soon));
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
                nCurrentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        ArrayList<DayInfo> infos = initTimetable();
        boolean isWeekendDays = isWeekendDays();

        for(DayInfo info : infos) {
            int i = parseDayOfWeek(info.pageName);
            if(!isWeekendDays && (i == 1 || i == 7)) {
                continue;
            }
            FragmentClassTimetable f = new FragmentClassTimetable();
            f.setCurrentPage(getCurrentPage(infos.indexOf(info)));
            f.setData(info.infos);
            f.setPageName(info.pageName);
            fragmentList.add(f);
        }

        adapterWeekFragment = new AdapterWeekFragment(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterWeekFragment);

        updateData();
    }

    private ArrayList<DayInfo> initTimetable() {
        ArrayList<DayInfo> infos = (ArrayList<DayInfo>) ToolSharedPreferences.getList(ActivityHome.this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST);
        if(infos == null) {
            infos = new ArrayList<DayInfo>();
            for(int i = 0; i < 4; i++) {
                String[] weekDay = getWeekdays();
                for (int j = 0; j < weekDay.length; j++) {
                    DayInfo dayInfo = new DayInfo();
                    dayInfo.pageName = weekDay[j];
                    infos.add(dayInfo);
                }
            }
        } else {
            for(int i = 0; i < 4; i++) {
                String[] weekDay = getWeekdays();
                for(int j = 0; j < weekDay.length; j++) {
                    infos.get(i*7 + j).pageName = weekDay[j];
                }
            }
        }
        ToolSharedPreferences.setList(ActivityHome.this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST, infos);
        return infos;
    }

    private boolean isWeekendDays() {
        return ToolSharedPreferences.getBoolean(ActivityHome.this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_WEEKEND_DAY);
    }

    private String[] getWeekdays() {
        String[] weekDay = DateFormatSymbols.getInstance().getWeekdays();
        String[] result = new String[7];
        for(int i = 2; i < weekDay.length; i++) {
            result[i-2] = weekDay[i];
        }
        result[6] = weekDay[1];
        return result;
    }

    private int parseDayOfWeek(String day) {
        Locale locale = getResources().getConfiguration().locale;
        SimpleDateFormat dayFormat = new SimpleDateFormat("E", locale);
        try {
            Date date = dayFormat.parse(day);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private int getCurrentPage(int index) {
        boolean isWeekend = ToolSharedPreferences.getBoolean(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_WEEKEND_DAY);
        if(!isWeekend) {
            //index = index + 1;
            int weekCount = index / 5;
            int week = index % 5;
            return weekCount*7 + week;
        }
        return index;
    }

    private int getWeekPageIndex(int weekindex) {
        boolean isWeekend = ToolSharedPreferences.getBoolean(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_WEEKEND_DAY);
        if(isWeekend) {
            return weekindex * 7;
        } else {
            return weekindex * 5;
        }
    }

    private int getCurrentWeekIndex() {
        boolean isWeekend = ToolSharedPreferences.getBoolean(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_WEEKEND_DAY);
        if(isWeekend) {
            return nCurrentPage / 7;
        } else {
            return nCurrentPage / 5;
        }
    }

    private List<DayInfo> getDayInfo() {
        return (List<DayInfo>) ToolSharedPreferences.getList(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST);
    }

    private void updateData() {
        ArrayList<DayInfo> list = (ArrayList<DayInfo>) getDayInfo();
        int index = getCurrentPage(nCurrentPage);
        DayInfo info = list.get(index);
        FragmentClassTimetable fragment = (FragmentClassTimetable) fragmentList.get(nCurrentPage);
        fragment.setData(info.infos);
    }
}
