package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.NumberButton;

//确认订单activity
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    private NumberButton buy_number_people;
    private LinearLayout activityQuickSearchKitchenLayout;
    private ImageView orderImage;
    private TextView setMealPrice;
    private TextView canOrderNumberPeople;
    private TextView openSeatsDate;
    private TextView openSeatsWork;
    private TextView openSeatsTime;
    private NumberButton buyNumberPeople;
    private TextView ordersPhoneNumber;
    private TextView orderTotalAmount;
    private TextView orderFavorable;
    private TextView submitOrder;
    private TextView totalMoney;
    private TextView totalNumberPeople;
    private RelativeLayout user_discount_coupon_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_confirm_order_layout);
        //StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.titleString = "";
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        setToolBar(R.id.toolbar, options);
        initView();
        initDate();
        initListener();
    }

    private void initDate() {
        buy_number_people.setBuyMax(12);
        int color = ContextCompat.getColor(this, R.color.textGrayDeep);
        buy_number_people.setaddButtonColor(color);
        buy_number_people.setInventory(12);
        buy_number_people.setCurrentNumber(1);
    }

    private void initListener() {
        user_discount_coupon_layout.setOnClickListener(this);
    }

    private void initView() {

        buy_number_people = findView(R.id.buy_number_people);
        orderImage = findView(R.id.order_image);
        setMealPrice = findView(R.id.set_meal_price);
        canOrderNumberPeople = findView(R.id.can_order_number_people);
        openSeatsDate = findView(R.id.open_seats_date);
        openSeatsWork = findView(R.id.open_seats_work);
        openSeatsTime = findView(R.id.open_seats_time);
        ordersPhoneNumber = findView(R.id.orders_phone_number);
        orderTotalAmount = findView(R.id.order_total_amount);
        orderFavorable = findView(R.id.order_favorable);
        submitOrder = findView(R.id.submit_order);
        totalMoney = findView(R.id.total_money);
        totalNumberPeople = findView(R.id.total_number_people);
        user_discount_coupon_layout=findView(R.id.user_discount_coupon_layout);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_discount_coupon_layout:
                //使用优惠券
                jumpActivity(AvailableDiscountCouponActivity.class);
            break;
            default:
                break;
        }
    }
}
