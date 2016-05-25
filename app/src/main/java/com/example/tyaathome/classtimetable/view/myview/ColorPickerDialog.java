package com.example.tyaathome.classtimetable.view.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.adapter.AdapterColorPicker;

/**
 * Created by tyaathome on 2016/5/25.
 */
public class ColorPickerDialog extends Dialog {

    private AdapterColorPicker adapter;
    private Context context;
    private GridView gridView;
    private ColorPickerItemClickListener listener;

    public ColorPickerDialog(Context context, ColorPickerItemClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    protected ColorPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener, ColorPickerItemClickListener listener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public ColorPickerDialog(Context context, int themeResId, ColorPickerItemClickListener listener) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_color_picker);
        setTitle(R.string.pick_color);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.grid_view);
    }

    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener != null) {
                    listener.onItemClick();
                }
                dismiss();
            }
        });
    }

    private void initData() {
        String[] colors = context.getResources().getStringArray(R.array.colors);
        adapter = new AdapterColorPicker(context, colors);
        gridView.setAdapter(adapter);
    }

    public interface ColorPickerItemClickListener {
        void onItemClick();
    }
}
