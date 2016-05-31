package com.example.tyaathome.classtimetable.model.inter;

import android.animation.Animator;
import android.animation.ValueAnimator;

/**
 * Created by tyaathome on 2016/5/31.
 */
public abstract class MyAnimatorListenerAdapter implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    public MyAnimatorListenerAdapter() {
        super();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
    }
}
