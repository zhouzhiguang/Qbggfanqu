package com.qbgg.cenglaicengqu.personcentre.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ViewHolder;

/**
 * 上传头像的dialog
 */

public class UploadPictureDialog extends Dialog implements View.OnClickListener {

    private View view;
    private Activity activity;

    public UploadPictureDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public UploadPictureDialog(Activity activity, int theme) {
        super(activity, theme);
        this.activity = activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(activity);
        Window dialogWindow = this.getWindow();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);// 这个很重要
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.tipsAnimation); // 设置窗口弹出动画
        View view = inflater.inflate(R.layout.upload_picture_dialog_layout,
                null);
//        lp.height = (int) activity.getResources().getDimension(
//                R.dimen.dimen_450px);// AutoUtils.getDisplayHeightValue(R.dimen.dimen_198dp);
        DisplayMetrics display = activity.getResources().getDisplayMetrics();
        int widthPixels = display.widthPixels;
       // lp.height = display.heightPixels - 400;
        lp.width = widthPixels;
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(true);
        AutoUtils.auto(view);

        setContentView(view);
        TextView take_picture_update = ViewHolder.get(view, R.id.take_picture_update);
        TextView photo_album_update = ViewHolder.get(view, R.id.photo_album_update);
        take_picture_update.setOnClickListener(this);
        photo_album_update.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_picture_update:
                //拍照上传
                dismiss();
                break;
            case R.id.photo_album_update:
                //相册选图上传
                dismiss();
                break;
            default:
                break;
        }
    }
}
