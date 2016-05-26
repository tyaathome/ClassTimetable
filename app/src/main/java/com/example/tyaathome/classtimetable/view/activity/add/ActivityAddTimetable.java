package com.example.tyaathome.classtimetable.view.activity.add;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.TimetableInfo;
import com.example.tyaathome.classtimetable.view.myview.ColorPickerDialog;
import com.example.tyaathome.classtimetable.view.myview.MyOnTimePickerClickListener;
import com.example.tyaathome.classtimetable.view.myview.TimePickerDialog;

import java.text.SimpleDateFormat;
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
    private TextView tvAm;
    private TextView tvPm;

    private String[] mColors;
    private int nCurrentColorIndex = -1;

    private Calendar amCalendar = Calendar.getInstance();
    private Calendar pmCalendar = Calendar.getInstance();

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
        tvAm = (TextView) findViewById(R.id.tv_am);
        tvPm = (TextView) findViewById(R.id.tv_pm);
        etTitle = (EditText) findViewById(R.id.et_title);
        etInfo = (EditText) findViewById(R.id.et_info);
        tvPickColor = (TextView) findViewById(R.id.tv_pick_color);
    }

    private void initEvent() {
        tvCancel.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvAm.setOnClickListener(this);
        tvPm.setOnClickListener(this);
        tvPickColor.setOnClickListener(this);
    }

    private void initData() {
        setAmDate(Calendar.getInstance());
        setPmDate(Calendar.getInstance());

        mColors = getResources().getStringArray(R.array.colors);
        Random random = new Random();
        nCurrentColorIndex = random.nextInt(16);
        setPickColorBackground(nCurrentColorIndex);
    }

    private void setPickColorBackground(int index) {
        if(mColors.length > index) {
            nCurrentColorIndex = index;
            tvPickColor.setBackgroundColor(Color.parseColor(mColors[nCurrentColorIndex]));
        }
    }

    private void setAmDate(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        amCalendar.setTime(calendar.getTime());
        String result = format.format(calendar.getTime());
        tvAm.setText(result);
    }

    private void setPmDate(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        pmCalendar.setTime(calendar.getTime());
        String result = format.format(calendar.getTime());
        tvPm.setText(result);
    }

    private TimetableInfo saveInfo() {
        TimetableInfo info = new TimetableInfo();
        info.title = etTitle.getText().toString();
        info.color = mColors[nCurrentColorIndex];
        info.amCal.setTime(amCalendar.getTime());
        info.pmCal.setTime(pmCalendar.getTime());
        info.info = etInfo.getText().toString();
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
            case R.id.tv_am : {
                TimePickerDialog dialog = new TimePickerDialog(this, new MyOnTimePickerClickListener() {
                    @Override
                    public void onClick(Calendar calendar, int hourOfDay, int minute) {
                        setAmDate(calendar);
                    }
                });
                dialog.show();
                break;
            }
            case R.id.tv_pm : {
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
