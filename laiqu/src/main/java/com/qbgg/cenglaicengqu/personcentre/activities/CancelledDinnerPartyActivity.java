package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;


public class CancelledDinnerPartyActivity extends BaseActivity {
    private SHSwipeRefreshLayout cancelled_dinner_party_swiperefreshLayout;
    private SHSwipeRefreshLayout cancelledDinnerPartySwiperefreshLayout;
    private TextView dinnerPartySerialNumber;//饭局编号
    private TextView dinnerPartyOrderNumber;//订单编码
    private TextView cancelledDinnerPartyCanceled;//已取消
    private TextView waitForPayOpenSeatsTime;//开席时间
    private CircleImageView waitForPayHeadimage;//头像
    private TextView waitForPayAccountsNickname;//厨神昵称
    private TextView waitForPayAccountsLocation;//饭局位置
    private TextView waitForPayNavigation;//导航去厨神家
    private ImageView waitForPayDinnerPartyThemeImage;//饭局主题图片
    private TextView waitForPayDinnerPartyThemeTitle;//饭局标题
    private TextView waitForPayDinnerPartyUnitSize;//饭局人数hu
    private TextView waitForPayLocation;//饭局地址
    private TextView waitForPayOpenSeatsTimes;//饭局开席时间
    private TextView waitForPayMealTicket;//支付方式
    private TextView waitForPayPlaceOrderTime;//下单时间注意和支付时间区别
    private TextView waitForPayPlaceOrderPhoneNumber;//下单手机号码
    private TextView waitForPayAmountSheets;//付款张数


    /**
     * 已经取消饭局
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_cancelled_dinner_party_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        initView();
        initListener();
    }


    private void initView() {
        dinnerPartySerialNumber = findView(R.id.dinner_party_serial_number);
        dinnerPartyOrderNumber = findView(R.id.dinner_party_order_number);
        cancelledDinnerPartyCanceled = findView(R.id.cancelled_dinner_party_canceled);
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
        cancelled_dinner_party_swiperefreshLayout = findView(R.id.cancelled_dinner_party_swiperefreshLayout);
        cancelled_dinner_party_swiperefreshLayout.setLoadmoreEnable(false);
        cancelled_dinner_party_swiperefreshLayout.setRefreshEnable(false);
    }

    private void initListener() {
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
