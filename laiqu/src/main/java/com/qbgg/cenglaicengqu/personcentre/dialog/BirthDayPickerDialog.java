package com.qbgg.cenglaicengqu.personcentre.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;

/**
 * Created by Administrator on 2016/12/14.
 */

public class BirthDayPickerDialog extends Dialog {
    public BirthDayPickerDialog(Context context) {
        super(context);
    }

    protected BirthDayPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public BirthDayPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
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
        View view = inflater.inflate(R.layout.birthday_picker_dialog_layout,
                null);
        DisplayMetrics display = getContext().getResources().getDisplayMetrics();
        int widthPixels = display.widthPixels;
        lp.width = widthPixels;
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(true);
        AutoUtils.auto(view);
        setContentView(view);
    }
}
