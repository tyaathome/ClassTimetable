package com.example.tyaathome.classtimetable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/5/26.
 */
public class DayInfo implements Serializable {
    public String pageName = "";
    public boolean isShow = true;
    public List<TimetableInfo> infos = new ArrayList<TimetableInfo>();
}
