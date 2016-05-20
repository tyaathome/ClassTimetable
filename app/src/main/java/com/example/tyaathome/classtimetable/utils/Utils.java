package com.example.tyaathome.classtimetable.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by tyaathome on 2016/5/20.
 */
public class Utils {
    public static int getRandomColor() {
        int a = 0xff;
        int r = getRandomInteger(0, 256);
        int g = getRandomInteger(0, 256);
        int b = getRandomInteger(0, 256);
        return Color.argb(a,r,g,b);
    }

    private static int getRandomInteger(int start, int end) {
        Random random = new Random();
        int result = random.nextInt(end) - start;
        return result;
    }
}
