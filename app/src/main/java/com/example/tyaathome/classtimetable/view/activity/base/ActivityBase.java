package com.example.tyaathome.classtimetable.view.activity.base;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.view.adapter.AdapterTitleDraw;
import com.example.tyaathome.classtimetable.view.myview.ItemClick;

import java.util.List;

/**
 * Created by tyaathome on 2016/5/11.
 */
public class ActivityBase extends FragmentActivity {

    private Toast mToast;

    private PopupWindow topChangerpopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();

        // another way
        /*if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.setText(msg);
        mToast.show();*/
    }

    public void showPopupWindow(final TextView tvView, final List<String> listData, final ItemClick listener) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_main_layout, null);
        // 设置按钮的点击事件
        ListView chose_listview = (ListView) contentView.findViewById(R.id.chose_listview);
        AdapterTitleDraw adapter = new AdapterTitleDraw(listData);
        chose_listview.setAdapter(adapter);
        chose_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dimissPop();
                tvView.setText(listData.get(position));
                if (listener != null) {
                    listener.itemClick(position);
                }
            }
        });
        topChangerpopupWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar
                .LayoutParams.WRAP_CONTENT, true);
        topChangerpopupWindow.setTouchable(true);
        topChangerpopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.alpha100));
        int width = tvView.getWidth();
        // 设置好参数之后再show
        int off = 0;
//        int off = tvView.getWidth() / 4;
        topChangerpopupWindow.setWidth(width);
        topChangerpopupWindow.showAsDropDown(tvView, -off, 0);
    }

    private void dimissPop() {
        if (topChangerpopupWindow != null && topChangerpopupWindow.isShowing()) {
            topChangerpopupWindow.dismiss();

        }
    }
}
