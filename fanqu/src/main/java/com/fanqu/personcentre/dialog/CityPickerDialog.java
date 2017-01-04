package com.fanqu.personcentre.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.main.util.ViewHolder;
import com.fanqu.main.widget.CityPickerLayout;


/**
 * 选择城市的wheelview dialo
 */

public class CityPickerDialog extends Dialog implements View.OnClickListener {


    private CityPickerLayout city_picker_text;

    public CityPickerDialog(Context context) {
        super(context);
    }

    public CityPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CityPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Window dialogWindow = this.getWindow();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);// 这个很重要
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.tipsAnimation); // 设置窗口弹出动画
        View view = inflater.inflate(R.layout.citypicker_dialog_layout,
                null);
//        lp.height = (int) activity.getResources().getDimension(
//                R.dimen.dimen_450px);// AutoUtils.getDisplayHeightValue(R.dimen.dimen_198dp);
        DisplayMetrics display = getContext().getResources().getDisplayMetrics();
        int widthPixels = display.widthPixels;
        // lp.height = display.heightPixels - 400;
        lp.width = widthPixels;
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(true);
        AutoUtils.auto(view);

        setContentView(view);
        TextView city_picker_cancel = ViewHolder.get(view, R.id.city_picker_cancel);
        TextView city_picker_sure = ViewHolder.get(view, R.id.city_picker_sure);
        city_picker_text = ViewHolder.get(view, R.id.city_picker_text);
        city_picker_sure.setOnClickListener(this);
        city_picker_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city_picker_cancel:
                dismiss();
                break;
            case R.id.city_picker_sure:
                String province = city_picker_text.getProvince();
                String city = city_picker_text.getCity();
                ToastUtils.showCenterToast(getContext(), province + "省" + city + "市");
                break;
            default:
                break;
        }
    }
}
