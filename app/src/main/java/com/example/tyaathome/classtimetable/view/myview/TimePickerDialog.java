package com.example.tyaathome.classtimetable.view.myview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.tyaathome.classtimetable.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tyaathome on 2016/5/24.
 */
public class TimePickerDialog extends Dialog implements View.OnClickListener {

    private TextView tvDone = null;
    private MyOnTimePickerClickListener listener;
    private TimePicker timePicker;
    private int HourOfDay;
    private int Minute;
    private Calendar nowCalendar;

    public TimePickerDialog(Context context, MyOnTimePickerClickListener listener) {
        super(context);
        this.listener = listener;
    }

    public TimePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected TimePickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time_picker);
        initView();
        initEvent();
        initData();

    }

    private void initView() {
        tvDone = (TextView) findViewById(R.id.tv_done);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    private void initEvent() {
        tvDone.setOnClickListener(this);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                HourOfDay = hourOfDay;
                Minute = minute;
            }
        });
    }

    private void initData() {
        nowCalendar = Calendar.getInstance();
        HourOfDay = nowCalendar.get(Calendar.HOUR_OF_DAY);
        Minute = nowCalendar.get(Calendar.MINUTE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_done:
                nowCalendar.set(Calendar.HOUR_OF_DAY, HourOfDay);
                nowCalendar.set(Calendar.MINUTE, Minute);
                listener.onClick(nowCalendar, HourOfDay, Minute);
                dismiss();
                break;
        }
    }
}
