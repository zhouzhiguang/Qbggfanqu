package com.qbgg.cenglaicengqu.main.widgethelper;

public interface OnTimeoutListener {
    void onTimePoint(String hour, String minute, String second);
    void onTimePointDay( String day,String hour, String minute, String second);
    void onTimeout();
}

