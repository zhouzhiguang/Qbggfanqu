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
import com.fanqu.main.widget.TimePickerLayout;


public class BirthDayPickerDialog extends Dialog implements View.OnClickListener {

    private TimePickerLayout time_picker_text;

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
        time_picker_text = ViewHolder.get(view, R.id.time_picker_text);
        TextView time_picker_cancel = ViewHolder.get(view, R.id.time_picker_cancel);
        TextView time_picker_sure = ViewHolder.get(view, R.id.time_picker_sure);
        time_picker_cancel.setOnClickListener(this);
        time_picker_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_picker_cancel:
                dismiss();
                break;
            case R.id.time_picker_sure:
                String year = time_picker_text.getYear();
                String month = time_picker_text.getMonth();
                String day = time_picker_text.getDay();
                dismiss();
                ToastUtils.showCenterToast(getContext(), year + "年" + month + "月" + day + "日");
                break;
            default:
                break;
        }
    }
}
