package com.qbgg.cenglaicengqu.main.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.widgethelper.AreaDataUtil;

import java.util.ArrayList;

/**
 * 选择城市内容的wheelView
 */

public class CityPickerLayout extends LinearLayout {

    private WheelView mProvincePicker;
    private WheelView mCityPicker;

    private int mCurrProvinceIndex = -1;
    private int mCurrCityIndex = -1;

    private AreaDataUtil mAreaDataUtil;
    private ArrayList<String> mProvinceList = new ArrayList<String>();

    public CityPickerLayout(Context context) {

        super(context);
        getAreaInfo();
        initcontent();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CityPickerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAreaInfo();
        initcontent();
    }

    public CityPickerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAreaInfo();
        initcontent();
    }

    public CityPickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAreaInfo();
        initcontent();
    }


    private void getAreaInfo() {
        mAreaDataUtil = new AreaDataUtil(getContext());
        mProvinceList = mAreaDataUtil.getProvinces();
    }

    //初始化显示内容
    private void initcontent() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.city_picker_layout, null);
        addView(view);
        mProvincePicker = (WheelView) findViewById(R.id.province_wv);
        mCityPicker = (WheelView) findViewById(R.id.city_wv);
        mProvincePicker.setData(mProvinceList);
        mProvincePicker.setDefault(0);

        String defaultProvince = mProvinceList.get(0);
        mCityPicker.setData(mAreaDataUtil.getCityByProvince(defaultProvince));
        mCityPicker.setDefault(1);

        mProvincePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                if (mCurrProvinceIndex != id) {
                    mCurrProvinceIndex = id;
                    String selectProvince = mProvincePicker.getSelectedText();
                    if (selectProvince == null || selectProvince.equals(""))
                        return;

                    // get city names by province
                    ArrayList<String> city = mAreaDataUtil.getCityByProvince(mProvinceList.get(id));
                    if (city.size() == 0) {
                        return;
                    }

                    mCityPicker.setData(city);

                    if (city.size() > 1) {
                        //if city is more than one,show start index == 1
                        mCityPicker.setDefault(1);
                    } else {
                        mCityPicker.setDefault(0);
                    }
                }

            }

            @Override
            public void selecting(int id, String text) {
            }
        });

        mCityPicker.setOnSelectListener(new WheelView.OnSelectListener() {

            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                if (mCurrCityIndex != id) {
                    mCurrCityIndex = id;
                    String selectCity = mCityPicker.getSelectedText();
                    if (selectCity == null || selectCity.equals(""))
                        return;
                    int lastIndex = Integer.valueOf(mCityPicker.getListSize());
                    if (id > lastIndex) {
                        mCityPicker.setDefault(lastIndex - 1);
                    }
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    public String getProvince() {
        if (mProvincePicker == null) {
            return null;
        }
        return mProvincePicker.getSelectedText();
    }

    public String getCity() {
        if (mCityPicker == null) {
            return null;
        }
        return mCityPicker.getSelectedText();
    }

}

