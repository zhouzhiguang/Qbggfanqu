package com.qbgg.cenglaicengqu.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.qbgg.cenglaicengqu.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 选择生日的wheel picker
 */
public class TimePickerLayout extends LinearLayout {
    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private int year;

    public TimePickerLayout(Context context) {
        this(context, null);
    }

    public TimePickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.time_picker_layout, this);
        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);
        mWheelDay = (WheelView) findViewById(R.id.day);
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        year = mCalendar.get(Calendar.YEAR);
        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());
  //((((19|20)\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-(0?[13578]|1[02])-31)|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$
        mWheelMonth
    }


    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = year; i > 1949; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder builder;
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                builder = new StringBuilder();
                builder.append("0");
                builder.append(i);
                list.add(builder.toString());
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    private ArrayList<String> getDayData() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        StringBuilder builder;
        for (int i = 1; i < 31; i++) {
            if (i < 10) {
                builder = new StringBuilder();
                builder.append("0");
                builder.append(i);
                list.add(builder.toString());
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public String getYear() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    public String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    public String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }
}

