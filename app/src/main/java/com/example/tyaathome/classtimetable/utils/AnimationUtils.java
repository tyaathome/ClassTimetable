package com.example.tyaathome.classtimetable.utils;

import android.animation.ValueAnimator;

import com.example.tyaathome.classtimetable.model.inter.MyAnimatorListenerAdapter;

/**
 * Created by tyaathome on 2016/5/31.
 */
public class AnimationUtils {
    private ValueAnimator valueAnimator;

    public AnimationUtils(int start, int end, long duration, MyAnimatorListenerAdapter adapter) {
        valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(adapter);
        valueAnimator.addListener(adapter);
        valueAnimator.setDuration(duration);
    }

    public void start() {
        if(valueAnimator != null) {
            valueAnimator.start();
        }
    }
}
