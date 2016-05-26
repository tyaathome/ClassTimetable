package com.example.tyaathome.classtimetable.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tyaathome on 2016/3/24.
 */
public class ToolSharedPreferences {

    // SharedPreferences name
    public final static String SHARED_PREFERENCES_MAIN = "shared_preferences_main_key";

    // Keys
    /**
     * timetable list
     */
    public final static String KEY_TIMETABLE_LIST = "key_timetable_list";

    public final static String KEY_WEEKEND_DAY = "key_weekend_day";

    /**
     * get int data from cache
     * @param context
     * @param preferencesName
     * @param key
     * @return
     */
    public final static int getInt(Context context, String preferencesName, String key) {
        SharedPreferences shared = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return shared.getInt(key, 0);
    }

    /**
     * set int data from cache
     * @param context
     * @param preferencesName
     * @param key
     * @param value
     */
    public final static void setInt(Context context, String preferencesName, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * get string data from cache
     * @param context
     * @param preferencesName
     * @param key
     * @return
     */
    public final static String getString(Context context, String preferencesName, String key) {
        SharedPreferences shared = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return shared.getString(key, "");
    }

    /**
     * set string data from cache
     * @param context
     * @param preferencesName
     * @param key
     * @param value
     */
    public final static void setString(Context context, String preferencesName, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public final static void setList(Context context, String preferencesName, String key, ArrayList<?> list) {
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        try {
            editor.putString(key, ObjectSerializer.serialize(list));
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static ArrayList<? extends Serializable> getList(Context context, String preferencesName, String key) {
        SharedPreferences shared = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        String result = shared.getString(key, "");
        if(TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            return (ArrayList<? extends Serializable>) ObjectSerializer.deserialize(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static boolean getBoolean(Context context, String preferencesName, String key) {
        SharedPreferences shared = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        return shared.getBoolean(key, false);
    }

    public final static void setBoolean(Context context, String preferencesName, String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * remove single cache
     * @param context
     * @param preferencesName
     */
    public final static void clearCache(Context context, String preferencesName, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
        editor.putString(key, "");
        editor.apply();
    }

    /**
     * remove all cache
     * @param context
     */
    public final static void clearAllCache(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_MAIN, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}
