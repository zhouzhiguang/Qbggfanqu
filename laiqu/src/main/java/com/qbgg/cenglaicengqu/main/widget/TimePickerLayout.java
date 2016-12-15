package com.qbgg.cenglaicengqu.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.TimeDateUtils;

import java.util.Calendar;
import java.util.List;

import static com.qbgg.cenglaicengqu.R.id.month;

/**
 * 选择生日的wheel picker
 */
public class TimePickerLayout extends LinearLayout {
    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private int year;
    private int mCurryear = -1;//当前选择年的id
    private int curryear;

    private TimeDateUtils utils;

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
        mWheelMonth = (WheelView) findViewById(month);
        mWheelDay = (WheelView) findViewById(R.id.day);
        long time = System.currentTimeMillis();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        year = mCalendar.get(Calendar.YEAR);
        utils = new TimeDateUtils();
        utils.MAX_YEAR = year;
        mWheelYear.setData(getYearData());
//        mWheelYear.setOnSelectListener(new WheelView.OnSelectListener() {
//            @Override
//            public void endSelect(int id, final String text) {
//                android.os.Handler handler=new android.os.Handler();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastUtils.showCenterToast(getContext(), text + "当前年");
//                    }
//                });
//
//                if (text.equals("") || text == null)
//                    return;
//
//                    String selectyear = mWheelYear.getSelectedText();
//
//                    if (selectyear == null || selectyear.equals("")) {
//                        return;
//                    }
//
//                    LogUtil.e("当前年", curryear + "年");
//                }
//            }
//
//            @Override
//            public void selecting(int id, String text) {
//
//            }
//        });
        mWheelYear.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                curryear = Integer.parseInt(text);
                LogUtil.e("当前年份", text + "************年");
                if (mCurryear != id) {
                    mCurryear = id;
                    mWheelMonth.setDefault(0);
                    List<String> daies = utils.createdDay(curryear, 1);
                    mWheelDay.setData(daies);
                    mWheelDay.setDefault(0);
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());
        mWheelMonth.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                List<String> daies = utils.createdDay(curryear, Integer.parseInt(text));
                mWheelDay.setData(daies);
                if (daies.size() > 1) {
                    //if city is more than one,show start index == 1
                    mWheelDay.setDefault(1);
                } else {
                    mWheelDay.setDefault(0);
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

    }


    private List<String> getYearData() {
        return utils.getItemList(TimeDateUtils.TYPE_YEAR);
    }

    private List<String> getMonthData() {
        return utils.getItemList(TimeDateUtils.TYPE_MONTH);

    }

    private List<String> getDayData() {
        return utils.getItemList(TimeDateUtils.TYPE_DAY);

    }

    public String getYear() {
        if (mWheelYear == null) {
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

