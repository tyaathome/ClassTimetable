package com.example.tyaathome.classtimetable.view.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tyaathome on 2016/5/25.
 */
public class MyCircle extends View {

    public String mColor;

    private Paint mPaint;

    public MyCircle(Context context) {
        super(context);
    }

    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //initPaint();
        if(mPaint != null) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            int radius = width / 2;
            canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        }
    }

    public void initPaint(String color) {
        mColor = color;
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor(mColor));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        invalidate();
    }

    private void initPaint() {
        //mColor = color;
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#123456"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }
}
