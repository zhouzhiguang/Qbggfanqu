package com.fanqu.main.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.main.widgethelper.OnTimeoutListener;
import com.fanqu.main.widgethelper.TimeoutManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 倒计时
 */
public class DayTimeViewComm extends LinearLayout {
    private TextView mDay;
    private TextView mUnit_1, mUnit_2, mUnit_3, mUnit_4;//就是天时分秒单位
    protected TextView mHours;
    protected TextView mMinutes;
    protected TextView mSeconds;
    private int mTextColor = Color.WHITE;
    private int mBackgroundColor = Color.BLACK;
    private int mSpaceColor = Color.BLACK;
    private int mTextSize = 30;
    private int mRadius = 5;
    private int mPaddingHorizontal = 1;
    private int mPaddingVertical = 0;
    private DecimalFormat df = new DecimalFormat("00");
    private TimeoutManager mTimeoutManager;
    private OnTimeoutListener mListener;
    private List<TimePoint> mTimeoutPoints;
    private TextView spaceOne, spaceThree, spaceTwo;

    public DayTimeViewComm(Context context) {
        this(context, null);
    }

    public DayTimeViewComm(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayTimeViewComm(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DayTimeViewComm(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, dm);
        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mRadius, dm);
        mPaddingHorizontal = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mPaddingHorizontal, dm);
        mPaddingVertical = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mPaddingVertical, dm);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeViewComm);
        mTextColor = array.getColor(R.styleable.TimeViewComm_tvc_textColor, mTextColor);
        mBackgroundColor = array.getColor(R.styleable.TimeViewComm_tvc_backgroundColor, mBackgroundColor);
        mSpaceColor = array.getColor(R.styleable.TimeViewComm_tvc_spaceColor, mSpaceColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.TimeViewComm_tvc_textSize, mTextSize);
        mRadius = array.getDimensionPixelSize(R.styleable.TimeViewComm_tvc_radius, mRadius);
        mPaddingHorizontal = array.getDimensionPixelSize(R.styleable.TimeViewComm_tvc_textPaddingHorizantal, mPaddingHorizontal);
        mPaddingVertical = array.getDimensionPixelSize(R.styleable.TimeViewComm_tvc_textPaddingVertical, mPaddingVertical);
        array.recycle();

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;

         mTextColor= ContextCompat.getColor(context,R.color.red_bg);
        int  mUnit_TextColor=  ContextCompat.getColor(context,R.color.textGrayDeep);
        int mUnit_TextSize= (int) context.getResources().getDimension(R.dimen.dimen_40px);
        mUnit_1 = new TextView(context);

        mUnit_1.setLayoutParams(layoutParams);
        mUnit_1.setTextColor(mUnit_TextColor);
        mUnit_1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnit_TextSize);
        mUnit_1.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);

        mUnit_2 = new TextView(context);
        mUnit_2.setLayoutParams(layoutParams);
        mUnit_1.setTextColor(mUnit_TextColor);
        mUnit_1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnit_TextSize);
        mUnit_2.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);

        mUnit_3 = new TextView(context);
        mUnit_3.setLayoutParams(layoutParams);
        mUnit_1.setTextColor(mUnit_TextColor);
        mUnit_1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnit_TextSize);
        mUnit_3.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);

        mUnit_4 = new TextView(context);
        mUnit_4.setLayoutParams(layoutParams);
        mUnit_1.setTextColor(mUnit_TextColor);
        mUnit_1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnit_TextSize);
        mUnit_4.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);

        mDay = new TextView(context);
        mDay.setLayoutParams(layoutParams);
        mDay.setTextColor(mTextColor);
        mDay.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mDay.setText("00");
        mDay.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);


        mHours = new TextView(context);
        mHours.setLayoutParams(layoutParams);
        mHours.setTextColor(mTextColor);
        mHours.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mHours.setText("00");
        mHours.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);
        mMinutes = new TextView(context);
        mMinutes.setLayoutParams(layoutParams);
        mMinutes.setTextColor(mTextColor);
        mMinutes.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mMinutes.setText("00");
        mMinutes.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);

        mSeconds = new TextView(context);
        mSeconds.setLayoutParams(layoutParams);
        mSeconds.setTextColor(mTextColor);
        mSeconds.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mSeconds.setText("00");
        mSeconds.setPadding(mPaddingHorizontal, mPaddingVertical, mPaddingHorizontal, mPaddingVertical);
        spaceOne = new TextView(context);
        spaceOne.setLayoutParams(layoutParams);
        spaceOne.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        spaceOne.setTextColor(mSpaceColor);
        spaceOne.setText(" : ");
        spaceTwo = new TextView(context);
        spaceTwo.setLayoutParams(layoutParams);
        spaceTwo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        spaceTwo.setTextColor(mSpaceColor);
        spaceTwo.setText(" : ");
        spaceThree = new TextView(context);
        spaceThree.setLayoutParams(layoutParams);
        spaceThree.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        spaceThree.setTextColor(mSpaceColor);
        spaceThree.setText(" : ");
        TextView spaceTwo = new TextView(context);
        spaceTwo.setLayoutParams(layoutParams);
        spaceTwo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        spaceTwo.setTextColor(mSpaceColor);
        spaceTwo.setText(" : ");
        String day = getResources().getString(R.string.day);
        String hour = getResources().getString(R.string.hour);
        String minute = getResources().getString(R.string.minute);
        String second = getResources().getString(R.string.second);
        //天
        addView(mDay);
        mUnit_1.setText(day);
        addView(mUnit_1);
        addView(spaceOne);
        //小时
        mUnit_2.setText(hour);
        addView(mHours);
        addView(mUnit_2);
        addView(spaceTwo);
        //分
        mUnit_3.setText(minute);
        addView(mMinutes);
        addView(mUnit_3);
        addView(spaceThree);
        //秒
        mUnit_4.setText(second);
        addView(mSeconds);
        addView(mUnit_4);
        startTime(3, 11, 12, 13);
    }

    public void startTime(final int day, int hour, int minutes, int second) {
        if (null == mTimeoutManager) {
            mTimeoutManager = new TimeoutManager(day,hour, minutes, second, new TimeoutManager.OnTimeRunListener() {
                @Override
                public void onTimeRun(int day,int hour, int minute, int second) {
                    setTime(df.format(day), df.format(hour), df.format(minute), df.format(second));
                }
            });
        } else {
            mTimeoutManager.resetTime( day,hour, minutes, second);
        }
    }


    protected void setTime(String day, String hour, String minute, String second) {
        if (!TextUtils.isEmpty(day)) {
            mDay.setText(day);
        }

        if (!TextUtils.isEmpty(hour)) {

            mHours.setText(hour);
        }
        if (!TextUtils.isEmpty(minute)) {
            mMinutes.setText(minute);
        }
        if (!TextUtils.isEmpty(second)) {
            mSeconds.setText(second);
        }


        if (null != mListener) {
            checkTimeout();
            checkTimeoutPoint();
        }
    }

    private void checkTimeoutPoint() {
        String day = mDay.getText().toString();
        String hour = mHours.getText().toString();
        String minute = mMinutes.getText().toString();
        String second = mSeconds.getText().toString();
        if (TextUtils.isEmpty(hour)) {
            hour = "0";
        }
        for (TimePoint timePoint : mTimeoutPoints) {
            if (timePoint.isEquals(day, hour, minute, second)) {
//                Log.d("DEBUG", "hour = "+hour+"minute = "+minute+"second = "+second);
                mListener.onTimePoint(hour, minute, second);
                mTimeoutPoints.remove(timePoint);
                break;
            }
        }
    }

    private void checkTimeout() {
        String h = mHours.getText().toString();

        if (TextUtils.isEmpty(h)) {
            if (mMinutes.getText().equals("00") && mSeconds.getText().equals("00")) {
                mListener.onTimeout();
            }
        } else {
            if (h.equals("00") && mMinutes.getText().equals("00") && mSeconds.getText().equals("00")) {
                mListener.onTimeout();
            }
        }
    }

    public void setOnTimeoutListener(OnTimeoutListener listener) {
        mListener = listener;
    }

    public void addTimeoutPoint(int day, int hour, int minute, int second) {
        if (null == mTimeoutPoints) {
            mTimeoutPoints = new ArrayList<>();
        }
        TimePoint timePoint = new TimePoint(df.format(day), df.format(hour), df.format(minute), df.format(second));
        mTimeoutPoints.add(timePoint);
    }


    private class TimePoint {
        private String day;
        private String hour;
        private String minute;
        private String second;

        public TimePoint(String day, String hour, String minute, String second) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public boolean isEquals(String day, String hour, String minute, String second) {
            return this.day.equals(day) && this.hour.equals(hour) && this.minute.equals(minute) && this.second.equals(second);
        }
    }

}
