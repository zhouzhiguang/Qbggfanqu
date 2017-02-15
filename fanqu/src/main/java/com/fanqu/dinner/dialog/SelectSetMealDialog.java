package com.fanqu.dinner.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fanqu.R;
import com.fanqu.dinner.adapter.NumberPeopleAdapter;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择套餐的dialog
 */

public class SelectSetMealDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private RecyclerView number_people_recyclerview;

    public SelectSetMealDialog(Context context) {
        super(context);
        this.context = context;
    }

    protected SelectSetMealDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public SelectSetMealDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        Window dialogWindow = this.getWindow();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);// 这个很重要
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.tipsAnimation); // 设置窗口弹出动画
        View view = inflater.inflate(R.layout.select_set_meal_dialog_layout,
                null);
        //AutoUtils.auto(view);
        int height = (int) context.getResources().getDimension(
                R.dimen.dimen_330px);// AutoUtils.getDisplayHeightValue(R.dimen.dimen_198dp);
        // int sCREEN_WIDTH = CustomApplication.SCREEN_WIDTH;
        // System.out.println(sCREEN_WIDTH + "寬是：");
        //DisplayMetrics display = context.getResources().getDisplayMetrics();
        int widthPixels = CustomApplication.SCREEN_WIDTH;
        lp.width = widthPixels;
        lp.height = CustomApplication.SCREEN_HEIGHT - height;
        dialogWindow.setAttributes(lp);
        // dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        // WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setCanceledOnTouchOutside(false);
        setContentView(view);
        AutoUtils.auto(view);
        number_people_recyclerview = ViewHolder.get(view, R.id.number_people_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        number_people_recyclerview.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        //默认最多招待人数
        int maxnumberpeople = 12;
        datas = initDate(datas, maxnumberpeople);
        NumberPeopleAdapter adapter = new NumberPeopleAdapter(context, R.layout.number_people_recyclerview_item_layout, datas);
        number_people_recyclerview.setAdapter(adapter);
//        number_people = ViewHolder.get(view, R.id.number_people);
//        initDate();
//        NumberPeopleAdapter adapter=new NumberPeopleAdapter(10,context);
//        number_people.setAdapter(adapter);
//        RecyclerView recyclerView;
//        recyclerView.smoothScrollToPosition(88);

    }

    private List<String> initDate(List<String> datas, int max) {
        if (datas != null) {
            String people = context.getString(R.string.people);
            StringBuffer buffer = new StringBuffer();
            buffer.append(people);
            for (int i = 1; i < max; i++) {
                buffer.append(i);
                datas.add(buffer.toString());
            }
        }
        return datas;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }
}
