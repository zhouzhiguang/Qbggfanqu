package com.fanqu.dinner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 在订单确定时候人数可以滑动的viewpage
 */

public class NumberPeopleAdapter extends PagerAdapter {

    /**
     * @param i       传人可以接待的最多人数
     * @param context 上下文对象
     */
    private int maxnumber;
    private Context context;
    private LayoutInflater inflater;
    private List<View> viewList;

    public NumberPeopleAdapter(int maxnumber, Context context) {
        this.maxnumber = maxnumber;
        this.context = context;
        inflater = LayoutInflater.from(context);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        for (int i = 1; i < maxnumber + 1; i++) {
            View view = inflater.inflate(R.layout.number_people_viewpage_item_layout, null, false);
            AutoUtils.auto(view);
            viewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(viewList.get(position));
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
//        ToastUtils.showCenterToast(context, position + "位置执行了吗");
//        //返回每个item 注意这里的都是单选的.
//        View view = null;
//        for (int i = 1; i < maxnumber + 1; i++) {
//            view = inflater.inflate(R.layout.number_people_viewpage_item_layout, null, false);
//        }
//        RadioGroup peoplegroup = ViewHolder.get(view, R.id.number_people_group);
//        int height = (int) context.getResources().getDimension(R.dimen.dimen_95px);
//        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, height);
//        layoutParams.setMargins(10, 10, 10, 10);
//        String people = context.getString(R.string.people);
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(people);
//        float size = context.getResources().getDimension(R.dimen.dimen_48px);
//        int color = ContextCompat.getColor(context, R.color.textGrayMiddle);
//        //Drawable drawable=ContextCompat.getDrawable(context,R.drawable.select_set_meal_selector);
//        for (int i = 1; i < maxnumber + 1; i++) {
//            RadioButton radiobutton = new RadioButton(context);
//            radiobutton.setLayoutParams(layoutParams);
//            // 这里不能用null，必需采用以下方式设置
//            radiobutton.setButtonDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
//            buffer.append(i);
//            radiobutton.setText(buffer.toString());
//            radiobutton.setTextSize(size);
//            radiobutton.setTextColor(color);
//            radiobutton.setBackgroundResource(R.drawable.select_set_meal_selector);
//            peoplegroup.addView(radiobutton);//将单选按钮添加到RadioGroup中
//        }
        View view=viewList.get(position);
        int width=500;
        int height=300;
        ViewGroup.LayoutParams params= new ViewGroup.LayoutParams(width,height);
        view.setLayoutParams(params);
        container.addView(view);


        return viewList.get(position);

    }
}
