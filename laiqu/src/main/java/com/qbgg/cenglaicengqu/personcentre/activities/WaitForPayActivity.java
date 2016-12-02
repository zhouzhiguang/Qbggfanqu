package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.main.widget.TimeViewComm;
import com.qbgg.cenglaicengqu.main.widgethelper.OnTimeoutListener;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

public class WaitForPayActivity extends BaseActivity implements View.OnClickListener {
    private TimeViewComm wait_for_pay_timeview;
    private TextView wait_for_pay_total_how_much, wait_for_pay_total_meal_ticket;
    private TextView dinnerPartySerialNumber;//饭局编号
    private TextView dinnerPartyOrderNumber;//订单编码
    private TextView waitForPayOrder;//待支付
    private TextView waitForPayOpenSeatsTime;//开席时间
    private CircleImageView waitForPayHeadimage;//厨神头像
    private TextView waitForPayAccountsNickname;//昵称
    private TextView waitForPayAccountsLocation;//厨神地址
    private TextView waitForPayNavigation;//导航去厨神家
    private ImageView waitForPayDinnerPartyThemeImage;//饭局主题图片
    private TextView waitForPayDinnerPartyThemeTitle;//饭局主题
    private TextView waitForPayDinnerPartyUnitSize;//饭局最多位数
    private TextView waitForPayLocation;//饭局发布位置
    private TextView waitForPayOpenSeatsTimes;//饭局开席时间
    private TextView waitForPayMealTicket;//饭局支付方式
    private TextView waitForPayPlaceOrderTime;//饭局下单时间
    private TextView waitForPayPlaceOrderPhoneNumber;//下单手机号码
    private TextView waitForPayAmountSheets;//饭票张数
    //立即支付
    private TextView waitForPayNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_wait_for_pay_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        initView();
        initListener();
    }


    /**
     * 初始化view
     */
    private void initView() {
        dinnerPartySerialNumber = findView(R.id.dinner_party_serial_number);
        dinnerPartyOrderNumber = findView(R.id.dinner_party_order_number);
        waitForPayOrder = findView(R.id.wait_for_pay_order);
        waitForPayOpenSeatsTime = findView(R.id.wait_for_pay_open_seats_time);
        waitForPayHeadimage = findView(R.id.wait_for_pay_headimage);
        waitForPayAccountsNickname = findView(R.id.wait_for_pay_accounts_nickname);
        waitForPayAccountsLocation = findView(R.id.wait_for_pay_accounts_location);
        waitForPayNavigation = findView(R.id.wait_for_pay_navigation);
        waitForPayDinnerPartyThemeImage = findView(R.id.wait_for_pay_dinner_party_theme_image);
        waitForPayDinnerPartyThemeTitle = findView(R.id.wait_for_pay_dinner_party_theme_title);
        waitForPayDinnerPartyUnitSize = findView(R.id.wait_for_pay_dinner_party_unit_size);
        waitForPayLocation = findView(R.id.wait_for_pay_location);
        waitForPayOpenSeatsTimes = findView(R.id.wait_for_pay_open_seats_times);
        waitForPayMealTicket = findView(R.id.wait_for_pay_meal_ticket);
        waitForPayPlaceOrderTime = findView(R.id.wait_for_pay_place_order_time);
        waitForPayPlaceOrderPhoneNumber = findView(R.id.wait_for_pay_place_order_phone_number);
        waitForPayAmountSheets = findView(R.id.wait_for_pay_amount_sheets);
        waitForPayNow = (TextView) findView(R.id.wait_for_pay_now);
        SHSwipeRefreshLayout wait_for_pay_swiperefreshLayout = findView(R.id.wait_for_pay_swiperefreshLayout);
        //共多少位
        wait_for_pay_total_how_much = findView(R.id.wait_for_pay_total_how_much);
        //多少张饭票
        wait_for_pay_total_meal_ticket = findView(R.id.wait_for_pay_total_meal_ticket);
        String s = getResources().getText(R.string.total_how_much).toString();
        String format = String.format(s, 2);
        wait_for_pay_total_how_much.setText(format);
        s = getResources().getText(R.string.total_how_many_pieces).toString();
        String text = String.format(s, 2);
        wait_for_pay_total_meal_ticket.setText(text);
        wait_for_pay_swiperefreshLayout.setRefreshEnable(false);
        wait_for_pay_swiperefreshLayout.setLoadmoreEnable(false);
        wait_for_pay_timeview = findView(R.id.wait_for_pay_timeview);
        wait_for_pay_timeview.startTime(0, 10, 00);
        wait_for_pay_timeview.addTimeoutPoint(0, 0, 0);
        wait_for_pay_timeview.setOnTimeoutListener(new OnTimeoutListener() {
            @Override
            public void onTimePoint(String hour, String minute, String second) {
                // ToastUtils.showCenterToast(WaitForPayActivity.this,"超时了 "+second);
            }

            @Override
            public void onTimeout() {
                ToastUtils.showCenterToast(WaitForPayActivity.this, "超时了没有了");
            }
        });
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        waitForPayNow.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wait_for_pay_now:
                //立即支付
                break;
            default:
                break;
        }
    }
}
