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
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.main.widget.DayTimeViewComm;
import com.qbgg.cenglaicengqu.main.widgethelper.OnTimeoutListener;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

//待开席饭局
public class WaitOpenSeatsActivity extends BaseActivity {
    private SHSwipeRefreshLayout wait_open_seats_swiperefreshLayout;
    private DayTimeViewComm wait_open_seats_timeview;
    private TextView waitOpenSeatsOrderAuthCode;//订单验证码
    private TextView waitOpenSeatsSerialNumber;//饭局编码
    private TextView waitOpenSeatsOrderNumber;//订单编码
    private TextView waitOpenSeatsSeatsTime;//开席时间
    private CircleImageView waitOpenSeatsHeadimage;//厨神头像
    private TextView waitOpenSeatsAccountsNickname;//厨神昵称
    private TextView waitOpenSeatsAccountsLocation;//饭局地址
    private TextView waitOpenSeatsNavigation;//导航去厨神家里
    private ImageView waitOpenSeatsPartyThemeImage;//主题饭局图片
    private TextView waitOpenSeatsPartyThemeTitle;//饭局标题
    private TextView waitOpenSeatsPartyHowMuch;//饭局多少钱一位
    private TextView waitOpenSeatsPartyUnit;
    private TextView waitOpenSeatsPartyTotal;//总共多少钱
    private TextView waitForPayMealTicket;//支付方式
    private TextView waitForPayPlaceOrderTime;//下单时间
    private TextView waitForPayPlaceOrderPhoneNumber;//下单手机号码
    private TextView waitOpenSeatsTotalPay;//总共花费多少钱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_wait_open_seats_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        initView();

        initDate();
        initListener();
    }


    private void initListener() {
        wait_open_seats_timeview.setOnTimeoutListener(new OnTimeoutListener() {
            @Override
            public void onTimePoint(String hour, String minute, String second) {

            }

            @Override
            public void onTimePointDay(String day, String hour, String minute, String second) {

            }

            @Override
            public void onTimeout() {
                ToastUtils.showCenterToast(WaitOpenSeatsActivity.this, "已经超时");
            }
        });
    }

    private void initView() {
        waitOpenSeatsOrderAuthCode = findView(R.id.wait_open_seats_order_auth_code);
        waitOpenSeatsSerialNumber = findView(R.id.wait_open_seats_serial_number);
        waitOpenSeatsOrderNumber = findView(R.id.wait_open_seats_order_number);
        waitOpenSeatsSeatsTime = findView(R.id.wait_open_seats_seats_time);
        waitOpenSeatsHeadimage = findView(R.id.wait_open_seats_headimage);
        waitOpenSeatsAccountsNickname = findView(R.id.wait_open_seats_accounts_nickname);
        waitOpenSeatsAccountsLocation = findView(R.id.wait_open_seats_accounts_location);
        waitOpenSeatsNavigation = findView(R.id.wait_open_seats_navigation);
        waitOpenSeatsPartyThemeImage = findView(R.id.wait_open_seats_party_theme_image);
        waitOpenSeatsPartyThemeTitle = findView(R.id.wait_open_seats_party_theme_title);
        waitOpenSeatsPartyHowMuch = findView(R.id.wait_open_seats_party_how_much);
        waitOpenSeatsPartyTotal = findView(R.id.wait_open_seats_party_total);
        waitForPayMealTicket = findView(R.id.wait_for_pay_meal_ticket);
        waitForPayPlaceOrderTime = findView(R.id.wait_for_pay_place_order_time);
        waitForPayPlaceOrderPhoneNumber = findView(R.id.wait_for_pay_place_order_phone_number);
        waitOpenSeatsTotalPay = findView(R.id.wait_open_seats_total_pay);
        wait_open_seats_timeview = findView(R.id.wait_open_seats_timeview);
        wait_open_seats_swiperefreshLayout = findView(R.id.wait_open_seats_swiperefreshLayout);
        wait_open_seats_swiperefreshLayout.setRefreshEnable(false);
        wait_open_seats_swiperefreshLayout.setLoadmoreEnable(false);

    }

    /**
     * 初始化数据
     */
    private void initDate() {
        wait_open_seats_timeview.startTime(1, 1, 1, 1);
        wait_open_seats_timeview.addTimeoutPoint(0, 0, 0, 0);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
