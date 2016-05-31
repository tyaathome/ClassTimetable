package com.example.tyaathome.classtimetable.view.fragment;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tyaathome.classtimetable.R;
import com.example.tyaathome.classtimetable.model.DayInfo;
import com.example.tyaathome.classtimetable.model.TimetableInfo;
import com.example.tyaathome.classtimetable.model.inter.MyAnimatorListenerAdapter;
import com.example.tyaathome.classtimetable.utils.AnimationUtils;
import com.example.tyaathome.classtimetable.utils.ToolSharedPreferences;
import com.example.tyaathome.classtimetable.view.adapter.AdapterClassTimeTable;
import com.example.tyaathome.classtimetable.view.myview.ItemClick;
import com.example.tyaathome.classtimetable.view.myview.OnMyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/10.
 */
public class FragmentClassTimetable extends Fragment {

    private String mPageName = "";
    private int mCurrentPage;

    private RecyclerView recyclerView = null;
    private TextView tvNoEvent = null;

    private AdapterClassTimeTable adapter = null;

    private List<TimetableInfo> listdata = new ArrayList<TimetableInfo>();

    private int nCurrentClickedPosition = -1;

    private boolean isAnimationFinish = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.layout_class_timetable, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        tvNoEvent = (TextView) getView().findViewById(R.id.tv_no_event);

        updateUI();
    }

    private void initEvent() {
        recyclerView.addOnItemTouchListener(new OnMyItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh, int position) {

                if(!isAnimationFinish) {
                    return ;
                }

                if(nCurrentClickedPosition != -1) {
                    final View oldView = recyclerView.getLayoutManager().findViewByPosition(nCurrentClickedPosition);
                    if(oldView != null) {
                        initResetAnimation(oldView).start();
                    }
                }

                final View newView = recyclerView.getLayoutManager().findViewByPosition(position);
                initStretchAnimation(newView).start();

                nCurrentClickedPosition = position;
            }
        });
    }

    private void initData() {
        adapter = new AdapterClassTimeTable(getActivity(), listdata);
        adapter.setDeletListener(new ItemClick() {
            @Override
            public void itemClick(Object obj) {
                int position = (int) obj;
                removeItem(position);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public void setPageName(String str) {
        mPageName = str;
    }

    public String getPageName() {
        return mPageName;
    }

    public void setCurrentPage(int page) {
        mCurrentPage = page;
    }

    public void setData(List<TimetableInfo> listdata) {
        this.listdata.clear();
        this.listdata.addAll(listdata);
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
        updateUI();
    }

    public void addItem(TimetableInfo info) {
        this.listdata.add(info);
        if(adapter != null) {
            adapter.notifyItemInserted(this.listdata.size() - 1);
        }
    }

    public void removeItem(int position) {
        if(listdata.size() > position) {
            this.listdata.remove(position);
            if (adapter != null) {
                adapter.notifyItemRemoved(position);
            }
            // update ui
            updateUI();
            // save list
            ArrayList<DayInfo> list = (ArrayList<DayInfo>) ToolSharedPreferences.getList(getActivity(), ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST);
            if(list != null && list.size() > mCurrentPage) {
                list.get(mCurrentPage).infos = listdata;
            }
            ToolSharedPreferences.setList(getActivity(), ToolSharedPreferences.SHARED_PREFERENCES_MAIN, ToolSharedPreferences.KEY_TIMETABLE_LIST, list);
        }
    }

    public void updateUI() {
        if(tvNoEvent == null || recyclerView == null) {
            return;
        }
        if(listdata != null && listdata.size() > 0) {
            tvNoEvent.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            tvNoEvent.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private AnimationUtils initStretchAnimation(final View view) {
        final int height = view.getMeasuredHeight();
        AnimationUtils animationUtils = new AnimationUtils(0, 200, 500L, new MyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                System.out.println("End");
                isAnimationFinish = true;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                System.out.println("Start");
                isAnimationFinish = false;
            }

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                super.onAnimationUpdate(animation);
                view.getLayoutParams().height = (int)(height + (Float) animation.getAnimatedValue());
                view.requestLayout();
            }
        });
        return animationUtils;
    }

    private AnimationUtils initResetAnimation(final View view) {
        final int height = view.getMeasuredHeight();
        AnimationUtils animationUtils = new AnimationUtils(0, 200, 500L, new MyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                System.out.println("End");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                System.out.println("Start");
                isAnimationFinish = false;
            }

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                super.onAnimationUpdate(animation);
                view.getLayoutParams().height = (int)(height - (Float) animation.getAnimatedValue());
                view.requestLayout();
            }
        });
        return animationUtils;
    }
}
