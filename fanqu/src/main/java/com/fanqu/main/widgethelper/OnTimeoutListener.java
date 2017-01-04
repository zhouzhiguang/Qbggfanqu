package com.fanqu.main.widgethelper;

public interface OnTimeoutListener {
    void onTimePoint(String hour, String minute, String second);
    void onTimePointDay(String day, String hour, String minute, String second);
    void onTimeout();
}

