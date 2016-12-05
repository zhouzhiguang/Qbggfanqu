package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

/**
 * 已经完成饭局
 */
public class CompletedDInnerPartyActivity extends BaseActivity implements View.OnClickListener {
    private SHSwipeRefreshLayout completed_dinner_party_swiperefreshLayout;

    private TextView completedDinnerPartySerialNumber;//饭局编号
    private TextView completedDinnerPartyOrderNumber;//订单编码
    private TextView completedDinnerPartyAssess;//待评价按钮
    private TextView completedDinnerPartySeatsTime;//饭局开席时间
    private CircleImageView completedDinnerPartyHeadimage;//厨神头像
    private TextView completedDinnerPartyAccountsNickname;//厨神昵称
    private TextView completedDinnerPartyAccountsLocation;//饭局地址
    private TextView completedDinnerPartyNavigation;//导航去厨神家
    private ImageView completedDinnerPartyThemeImage;//饭局主题图片
    private TextView completedDinnerPartyThemeTitle;//饭局标题
    private TextView completedDinnerPartyHowMuch;//多少钱一位
    private TextView completedDinnePartyUnit;// 元/位
    private TextView completedDinnerPartyTotal;//总共多少位
    private TextView completedDinnerPartyMealTicket;//支付方式
    private TextView completedDinnerPartyPlaceOrderTime;//下单时间
    private TextView waitForPayPlaceOrderPhoneNumber;//下单手机号码
    private TextView completedDinnerPartyTotalPay;//付款金额
    private TextView completedDinnerPartyImmediatelyAssessment;//立刻评价


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_completed_dinner_party_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(this);
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        completedDinnerPartySerialNumber = findView(R.id.completed_dinner_party_serial_number);
        completedDinnerPartyOrderNumber = findView(R.id.completed_dinner_party_order_number);
        completedDinnerPartyAssess = findView(R.id.completed_dinner_party_assess);
        completedDinnerPartySeatsTime = findView(R.id.completed_dinner_party_seats_time);
        completedDinnerPartyHeadimage = findView(R.id.completed_dinner_party_headimage);
        completedDinnerPartyAccountsNickname = findView(R.id.completed_dinner_party_accounts_nickname);
        completedDinnerPartyAccountsLocation = findView(R.id.completed_dinner_party_accounts_location);
        completedDinnerPartyNavigation = findView(R.id.completed_dinner_party_navigation);
        completedDinnerPartyThemeImage = findView(R.id.completed_dinner_party_theme_image);
        completedDinnerPartyThemeTitle = findView(R.id.completed_dinner_party_theme_title);
        completedDinnerPartyHowMuch = findView(R.id.completed_dinner_party_how_much);
        completedDinnePartyUnit = findView(R.id.completed_dinne_party_unit);
        completedDinnerPartyTotal = findView(R.id.completed_dinner_party_total);
        completedDinnerPartyMealTicket = (TextView) findView(R.id.completed_dinner_party_meal_ticket);
        completedDinnerPartyPlaceOrderTime = findView(R.id.completed_dinner_party_place_order_time);
        waitForPayPlaceOrderPhoneNumber = findView(R.id.wait_for_pay_place_order_phone_number);
        completedDinnerPartyTotalPay = findView(R.id.completed_dinner_party_total_pay);
        completedDinnerPartyImmediatelyAssessment = (TextView) findView(R.id.completed_dinner_party_immediately_assessment);
        completed_dinner_party_swiperefreshLayout = findView(R.id.completed_dinner_party_swiperefreshLayout);
        completed_dinner_party_swiperefreshLayout.setLoadmoreEnable(false);
        completed_dinner_party_swiperefreshLayout.setRefreshEnable(false);
    }


    private void initDate() {
    }


    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                ToastUtils.showCenterToast(this, "点击返回了哦");
                break;
            default:
                break;
        }
    }
}
