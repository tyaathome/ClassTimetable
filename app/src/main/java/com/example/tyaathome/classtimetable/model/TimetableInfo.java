package com.example.tyaathome.classtimetable.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by tyaathome on 2016/5/25.
 */
public class TimetableInfo implements Serializable {
    public String title = "";
    public String color;
    public Calendar startCal = Calendar.getInstance();
    public Calendar endCal = Calendar.getInstance();
    public String info = "";
}
