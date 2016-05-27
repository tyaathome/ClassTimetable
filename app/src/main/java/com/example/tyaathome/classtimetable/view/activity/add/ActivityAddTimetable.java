package com.example.tyaathome.classtimetable.view.activity.add;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.DayInfo;
import com.example.tyaathome.classtimetable.model.TimetableInfo;
import com.example.tyaathome.classtimetable.utils.ToolSharedPreferences;
import com.example.tyaathome.classtimetable.view.myview.ColorPickerDialog;
import com.example.tyaathome.classtimetable.view.myview.MyOnTimePickerClickListener;
import com.example.tyaathome.classtimetable.view.myview.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by tyaathome on 2016/5/24.
 */
public class ActivityAddTimetable extends Activity implements View.OnClickListener {

    private TextView tvCancel;
    private TextView tvSave;
    private EditText etTitle;
    private EditText etInfo;
    private TextView tvPickColor;
    private TextView tvStart;
    private TextView tvEnd;

    private String[] mColors;
    private int nCurrentColorIndex = -1;
    private int nCurrentPage = -1;

    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);
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
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvSave = (TextView) findViewById(R.id.tv_save);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvEnd = (TextView) findViewById(R.id.tv_end);
        etTitle = (EditText) findViewById(R.id.et_title);
        etInfo = (EditText) findViewById(R.id.et_info);
        tvPickColor = (TextView) findViewById(R.id.tv_pick_color);
    }

    private void initEvent() {
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
        tvPickColor.setOnClickListener(this);
    }

    private void initData() {
        setAmDate(Calendar.getInstance());
        setPmDate(Calendar.getInstance());

        mColors = getResources().getStringArray(R.array.colors);
        Random random = new Random();
        nCurrentColorIndex = random.nextInt(16);
        setPickColorBackground(nCurrentColorIndex);

        nCurrentPage = getIntent().getIntExtra("index", -1);
    }

    private void setPickColorBackground(int index) {
        if(mColors.length > index) {
            nCurrentColorIndex = index;
            tvPickColor.setBackgroundColor(Color.parseColor(mColors[nCurrentColorIndex]));
        }
    }

    private void setAmDate(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        startCalendar.setTime(calendar.getTime());
        String result = format.format(calendar.getTime());
        tvStart.setText(result);
    }

    private void setPmDate(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        endCalendar.setTime(calendar.getTime());
        String result = format.format(calendar.getTime());
        tvEnd.setText(result);
    }

    private TimetableInfo saveInfo() {
        TimetableInfo info = new TimetableInfo();
        info.title = etTitle.getText().toString();
        info.color = mColors[nCurrentColorIndex];
        info.startCal.setTime(startCalendar.getTime());
        info.endCal.setTime(endCalendar.getTime());
        info.info = etInfo.getText().toString();
        ArrayList<DayInfo> list = (ArrayList<DayInfo>) ToolSharedPreferences.getList(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST);
        if(list != null && list.size() > nCurrentPage) {
            list.get(nCurrentPage).infos.add(info);
        }
        ToolSharedPreferences.setList(this, ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST, list);
        return info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel :
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.tv_save :
                Intent intent = new Intent();
                intent.putExtra("info", saveInfo());
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_start: {
                TimePickerDialog dialog = new TimePickerDialog(this, new MyOnTimePickerClickListener() {
                    @Override
                    public void onClick(Calendar calendar, int hourOfDay, int minute) {
                        setAmDate(calendar);
                    }
                });
                dialog.show();
                break;
            }
            case R.id.tv_end: {
                TimePickerDialog dialog = new TimePickerDialog(this, new MyOnTimePickerClickListener() {
                    @Override
                    public void onClick(Calendar calendar, int hourOfDay, int minute) {
                        setPmDate(calendar);
                    }
                });
                dialog.show();
                break;
            }
            case R.id.tv_pick_color : {
                ColorPickerDialog dialog = new ColorPickerDialog(this, new ColorPickerDialog.ColorPickerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        setPickColorBackground(position);
                    }
                });
                dialog.show();
                break;
            }

        }
    }
}
