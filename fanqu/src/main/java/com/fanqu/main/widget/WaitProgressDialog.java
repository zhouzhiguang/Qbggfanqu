package com.fanqu.main.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 等待不确定进度条
 */

public class WaitProgressDialog {

    private static final int DEFAULT_WAIT_TIME_OUT = 10 * 1000;//默认三秒钟
    private static final String COMMON_PROMPT = "正在卖力加载中";

    private static WaitProgressDialog progressHUD;
    private Context context;
    private Dialog progressDialog;
    private final Timer timer = new Timer();
    private TimerTask timerTask;

    private WaitProgressDialog(Context context) {
        this.context = context;
    }

    public static WaitProgressDialog getInstance(Context context) {

        if (progressHUD == null) {
            progressHUD = new WaitProgressDialog(context);
        }
        return progressHUD;
    }

    public void showWaitPrompt() {
        showWaitPrompt(COMMON_PROMPT);
    }

    public void showWaitPrompt(String prompt) {
        showWaitPrompt(prompt, true);
    }

    public void showWaitPrompt(String prompt, Boolean cancelAble) {
        showWaitPrompt(prompt, cancelAble, DEFAULT_WAIT_TIME_OUT);
    }

    public void showWaitPrompt(String prompt, Boolean cancelAble, int waitTime) {
        progressDialog = new Dialog(context, R.style.progress_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.wait_progress_dialog_layout, null);
        AutoUtils.auto(view);
        progressDialog.setContentView(view);
        progressDialog.setCancelable(cancelAble);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        if (TextUtils.isEmpty(prompt)) {
            msg.setVisibility(View.GONE);
        } else {
            msg.setVisibility(View.VISIBLE);
            msg.setText(prompt);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                disMiss();
            }
        };
        timer.schedule(timerTask, waitTime);
    }

    public void disMiss() {
        if (progressDialog != null ) {
            progressDialog.dismiss();
            timer.purge();
        }
    }

}
