package com.example.tyaathome.classtimetable.view.activity.add;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.myview.ColorPickerDialog;
import com.example.tyaathome.classtimetable.view.myview.MyOnTimePickerClickListener;
import com.example.tyaathome.classtimetable.view.myview.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        setDate(tvAm, Calendar.getInstance());
        setDate(tvPm, Calendar.getInstance());
    }

    private void setDate(TextView tv, Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("a h:mm");
        String result = format.format(calendar.getTime());
        tv.setText(result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel :
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.tv_save :
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.tv_am : {
                TimePickerDialog dialog = new TimePickerDialog(this, new MyOnTimePickerClickListener() {
                    @Override
                    public void onClick(Calendar calendar, int hourOfDay, int minute) {
                        setDate(tvAm, calendar);
                    }
                });
                dialog.show();
                break;
            }
            case R.id.tv_pm : {
                TimePickerDialog dialog = new TimePickerDialog(this, new MyOnTimePickerClickListener() {
                    @Override
                    public void onClick(Calendar calendar, int hourOfDay, int minute) {
                        setDate(tvPm, calendar);
                    }
                });
                dialog.show();
                break;
            }
            case R.id.tv_pick_color : {
                ColorPickerDialog dialog = new ColorPickerDialog(this, new ColorPickerDialog.ColorPickerItemClickListener() {
                    @Override
                    public void onItemClick() {

                    }
                });
                dialog.show();
                break;
            }

        }
    }
}
