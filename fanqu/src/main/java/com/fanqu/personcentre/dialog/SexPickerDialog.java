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
import com.fanqu.main.widget.WheelView;

import java.util.ArrayList;

import static com.fanqu.R.id.sex_picker_cancel;
import static com.fanqu.R.id.sex_picker_sure;


/**
 * 选择性别的wheel
 */

public class SexPickerDialog extends Dialog implements View.OnClickListener {

    private WheelView sex_wheelView;

    public SexPickerDialog(Context context) {
        super(context);
    }

    public SexPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SexPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        Window dialogWindow = this.getWindow();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);// 这个很重要
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.tipsAnimation); // 设置窗口弹出动画
        View view = inflater.inflate(R.layout.sexpicker_dialog_layout,
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
        TextView sex_picker_sure = ViewHolder.get(view, R.id.sex_picker_sure);
        TextView sex_picker_cancel = ViewHolder.get(view, R.id.sex_picker_cancel);
        sex_wheelView = ViewHolder.get(view, R.id.sex_wheelView);
        ArrayList<String> sex = new ArrayList<String>();
        String male = getContext().getResources().getString(R.string.male);
        String female = getContext().getResources().getString(R.string.female);
        sex.add(male);
        sex.add(female);
        sex_wheelView.setData(sex);
        sex_wheelView.setDefault(0);
        sex_picker_cancel.setOnClickListener(this);
        sex_picker_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case sex_picker_cancel:
                dismiss();
                break;
            case sex_picker_sure:
                String sex = sex_wheelView.getSelectedText();
                ToastUtils.showCenterToast(getContext(), sex);
                dismiss();
                break;
            default:
                break;
        }
    }
}
