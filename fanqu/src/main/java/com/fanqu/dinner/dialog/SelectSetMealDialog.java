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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.DinnerTimeAdapter;
import com.fanqu.dinner.adapter.NumberPeopleAdapter;
import com.fanqu.dinner.date.DateUtill;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ViewHolder;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.fanqu.R.id.close;
import static com.fanqu.R.id.ensure;

/**
 * 选择套餐的dialog
 */

public class SelectSetMealDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private RecyclerView number_people_recyclerview, dinner_time_recyclerview;
    private NumberPeopleAdapter adapter;
    private int temposition, timeposition;
    private TextView last_number_people, last_time_item;
    private DinnerTimeAdapter timeAdapte;
    private RadioGroup meal_price_radiogroup;
    private RadioButton a_set_meal_price;
    private RadioButton b_set_meal_price;
    private int onItemSelect;
    private RadioGroup have_dinner_group;
    private RadioButton lunch_time, dinner_time;

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
                R.dimen.dimen_500px);// AutoUtils.getDisplayHeightValue(R.dimen.dimen_198dp);
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
        lunch_time = ViewHolder.get(view, R.id.lunch_time);
        dinner_time = ViewHolder.get(view, R.id.dinner_time);
        have_dinner_group = ViewHolder.get(view, R.id.have_dinner_group);
        TextView ensure = ViewHolder.get(view, R.id.ensure);
        //ensure.setEnabled(false);
        meal_price_radiogroup = ViewHolder.get(view, R.id.meal_price_radiogroup);
        a_set_meal_price = ViewHolder.get(view, R.id.a_set_meal_price);
        b_set_meal_price = ViewHolder.get(view, R.id.b_set_meal_price);
        ImageView close = ViewHolder.get(view, R.id.close);
        dinner_time_recyclerview = ViewHolder.get(view, R.id.dinner_time_recyclerview);
        number_people_recyclerview = ViewHolder.get(view, R.id.number_people_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        number_people_recyclerview.setLayoutManager(manager);
        manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dinner_time_recyclerview.setLayoutManager(manager);
        List<String> datas = new ArrayList<>();
        //默认最多招待人数
        int maxnumberpeople = 12;
        datas = initDate(datas, maxnumberpeople);
        adapter = new NumberPeopleAdapter(context, R.layout.number_people_recyclerview_item_layout, datas);
        number_people_recyclerview.setAdapter(adapter);
        List<String> timedatas = new ArrayList<>();
        timedatas = getDinnerTimeDatas(timedatas, 10);
        timeAdapte = new DinnerTimeAdapter(context, R.layout.dinner_time_recyclerview_item_layout, timedatas);
        dinner_time_recyclerview.setAdapter(timeAdapte);

//        DinnerTimeAdapter timeadapter=new DinnerTimeAdapter
//        dinner_time_recyclerview.setAdapter(timeadapter);
//        number_people = ViewHolder.get(view, R.id.number_people);
//        initDate();
//        NumberPeopleAdapter adapter=new NumberPeopleAdapter(10,context);
//        number_people.setAdapter(adapter);
//        RecyclerView recyclerView;
//        recyclerView.smoothScrollToPosition(88);
        initListener();
        close.setOnClickListener(this);
        ensure.setOnClickListener(this);
    }

    //可订餐日期 当前日期后面多少天
    private List<String> getDinnerTimeDatas(List<String> timedatas, int future) {
        String date = DateUtill.getcurrentDate();
        String[] da = DateUtill.formartString(date, future);
        StringBuffer buffer;
        if (da != null) {
            for (int i = 0; i < da.length; i++) {
                String result = da[i];
                if (0 < result.length() && result.length() > 11) {
                    //2017年02月27日 星期一
                    buffer = new StringBuffer();
                    String datestrign = result.substring(8, 11);
                    buffer.append(datestrign);
                    String week = context.getString(R.string.week);
                    buffer.append(week);
                    String weekdate = result.substring(result.length() - 1, result.length());
                    buffer.append(weekdate);
                    if (timedatas != null) {
                        timedatas.add(buffer.toString());
                    }
                }
            }
        }

        return timedatas;
    }

    private void initListener() {
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                TextView number_people = ViewHolder.get(view, R.id.number_people);

                if (!number_people.isSelected()) {
                    number_people.setSelected(true);
                    if (last_number_people != null) {
                        if (temposition != position && last_number_people.isSelected()) {
                            last_number_people.setSelected(false);
                        }

                    }
                    last_number_people = number_people;
                    temposition = position;
                    adapter.setOnItemSelect(position);
                } else {
                    number_people.setSelected(false);
                    adapter.setOnItemSelect(-1);
                }


                //adapter.notifyItemChanged(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        timeAdapte.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                TextView time = ViewHolder.get(view, R.id.time);
                if (!time.isSelected()) {
                    time.setSelected(true);
                    if (last_time_item != null) {
                        if (timeposition != position && last_time_item.isSelected()) {
                            last_time_item.setSelected(false);
                        }

                    }
                    last_time_item = time;
                    timeposition = position;
                    timeAdapte.setOnItemSelect(position);
                } else {
                    time.setSelected(false);
                    timeAdapte.setOnItemSelect(-1);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        meal_price_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == a_set_meal_price.getId()) {
                    //A 套餐
                } else if (checkedId == b_set_meal_price.getId()) {
                    // B 套餐
                } else {
                    // C 套餐
                }
            }
        });
        have_dinner_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == lunch_time.getId()) {
                    //中餐
                } else if (checkedId == dinner_time.getId()) {
                    // 晚餐
                } else {

                }
            }
        });
    }

    private List<String> initDate(List<String> datas, int max) {
        if (datas != null) {
            String people = context.getString(R.string.people);
            StringBuffer buffer;
            for (int i = 1; i < max + 1; i++) {
                buffer = new StringBuffer();
                buffer.append(people);
                buffer.append(i);
                datas.add(buffer.toString());
            }
        }
        return datas;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case close:
                dismiss();
                break;
            case ensure:
                int onItemSelect = adapter.getOnItemSelect();
                int time = timeAdapte.getOnItemSelect();
                LogUtil.e("测试点击的是那个", String.valueOf(time));
                break;
            default:
                break;
        }
    }
}
