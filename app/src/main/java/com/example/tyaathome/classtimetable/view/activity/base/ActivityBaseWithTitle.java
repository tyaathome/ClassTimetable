package com.example.tyaathome.classtimetable.view.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;

/**
 * Created by tyaathome on 2016/5/11.
 */
public class ActivityBaseWithTitle extends ActivityBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_with_title);
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
    public void setContentView(@LayoutRes int layoutResID) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View v = LayoutInflater.from(getApplicationContext()).inflate(layoutResID,
                null);
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_content);
        layout.addView(v, lp);
    }

    public void addViewToLeft(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginEnd = (int) getResources().getDimension(R.dimen.margin_normal);
        lp.setMargins(0,0,marginEnd,0);
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_left);
        layout.addView(view, lp);

    }

    public void addViewToRight(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginEnd = (int) getResources().getDimension(R.dimen.margin_normal);
        lp.setMargins(0,0,marginEnd,0);
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_right);
        layout.addView(view, lp);

    }

    public void setActivityTitle(@StringRes int titleId) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getText(titleId));
    }

}
