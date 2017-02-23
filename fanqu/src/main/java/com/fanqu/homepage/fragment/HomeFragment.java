package com.fanqu.homepage.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.listener.AppBarStateChangeListener;
import com.fanqu.dinner.listener.State;
import com.fanqu.framework.fragment.LazyFragment;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.homepage.adapter.RecommendedDinnerAdapter;
import com.fanqu.homepage.adapter.SelectionShareAdapter;
import com.fanqu.homepage.model.RecommendedDinnerBean;
import com.fanqu.homepage.model.SelectShareBean;
import com.fanqu.main.acitvities.MainActivity;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.model.ThirdLoginEntity;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.yolanda.nohttp.RequestMethod;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.bgabanner.BGABanner;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


/**

 */
public class HomeFragment extends LazyFragment implements View.OnClickListener {
    private MainActivity activity;

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private View view;
    private TextView textView;

    private RecyclerView fragment_person_recommended_dinner_recyclerView, fragment_person_selection_share_recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SelectionShareAdapter selectionShareAdapter;//精选分享
    private BGABanner bannerMainDefault;

    private TextView fragmentHomeNearbyKitchen;//附近的厨房
    private TextView fragmentHomeQuickSearchKitchen;//快捷找厨
    private TextView fragmentHomeLaiqu;//蹭来蹭去
    private TextView fragmentHomeRecruitKitchener;//厨神招募
    private CircleImageView fragmentHomeSentimentCoffeeImage;//情调咖啡馆广告图片
    private TextView fragmentHomeSentimentCoffeeTile;//情调咖啡馆广告主题
    private TextView fragmentHomeSentimentCoffeeContent;//情调咖啡馆广告宣传文字
    private CircleImageView fragmentHomeEvaluatingAmbassadorImage;//餐厅评测大使广告图片
    private TextView fragmentHomeEvaluatingAmbassadorTile;////餐厅评测大使广告标题
    private TextView fragmentHomeEvaluatingAmbassadorContent;//餐厅评测大使内容
    private CircleImageView fragmentHomeMajorSuitLowPriceImage;//大牌低价广告
    private TextView fragmentHomeMajorSuitLowPriceTile;//大牌低价广告标题
    private TextView fragmentHomeMajorSuitLowPriceContent;//大牌低价广n内容
    private CircleImageView fragmentHomeInvitePolitenessImage;//邀请有礼广告图片
    private TextView fragmentHomeInvitePolitenessTile;/*邀请有礼广告标题*/
    private TextView fragmentHomeInvitePolitenessContent;/*邀请有礼广告内容*/
    private TextView fragmentPersonRecommendedDinner;//推荐饭局下面有recycleview 展示

    private TextView fragmentPersonSelectionShare;//精选分享下面有推荐饭局下面有recycleview展示
    private TextView fragment_home_title;//主页主题
    private AppBarLayout kitchen_details_app_bar_layout;
    private ToolBarOptions options;
    private Toolbar toolbar;
    private TextView home_page, city;
    private int whitecolour, textGrayDeep;
    private Drawable downtwhiteDrawable, downblackDrawable;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home_layout);
        toolbar = findView(R.id.toolbar);
        whitecolour = ContextCompat.getColor(activity, R.color.white);
        textGrayDeep = ContextCompat.getColor(activity, R.color.textGrayDeep);
        downtwhiteDrawable = ContextCompat.getDrawable(activity, R.mipmap.ic_down_arrows_white);
        downblackDrawable = ContextCompat.getDrawable(activity, R.mipmap.ic_down_arrows_black);
        assignViews();
        initLitener();

    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.activity = (MainActivity) activity;

    }


    /**
     * 初始化监听事件
     */
    private void initLitener() {
        fragmentHomeQuickSearchKitchen.setOnClickListener(this);
        fragmentHomeNearbyKitchen.setOnClickListener(this);
        kitchen_details_app_bar_layout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.EXPANDED) {
                    city.setTextColor(whitecolour);
                    home_page.setVisibility(View.GONE);
                    ToastUtils.showCenterToast(activity, "展开状态");
                    downtwhiteDrawable.setBounds(0, 0, downtwhiteDrawable.getMinimumWidth(), downtwhiteDrawable.getMinimumHeight());
                    city.setCompoundDrawables(null, null, downtwhiteDrawable, null);
                } else if (state == State.COLLAPSED) {
                    home_page.setVisibility(View.VISIBLE);
                    city.setTextColor(textGrayDeep);
                    downblackDrawable.setBounds(0, 0, downblackDrawable.getMinimumWidth(), downblackDrawable.getMinimumHeight());
                    city.setCompoundDrawables(null, null, downblackDrawable, null);

                } else {


                }
            }

        });
    }

    private void assignViews() {
        city = findView(R.id.city);
        home_page = findView(R.id.home_page);
        kitchen_details_app_bar_layout = findView(R.id.kitchen_details_app_bar_layout);
        bannerMainDefault = findView(R.id.banner_main_default);
        linearLayoutManager = new LinearLayoutManager(activity);
        fragment_person_recommended_dinner_recyclerView = findView(R.id.fragment_person_recommended_dinner_recyclerView);
        fragment_person_selection_share_recyclerView = findView(R.id.fragment_person_selection_share_recyclerView);

        fragmentHomeNearbyKitchen = findView(R.id.fragment_home_nearby_kitchen);
        fragmentHomeQuickSearchKitchen = findView(R.id.fragment_home_quick_search_kitchen);
        fragmentHomeLaiqu = findView(R.id.fragment_home_laiqu);
        fragmentHomeRecruitKitchener = findView(R.id.fragment_home_recruit_kitchener);
        fragmentHomeSentimentCoffeeImage = findView(R.id.fragment_home_sentiment_coffee_image);
        fragmentHomeSentimentCoffeeTile = findView(R.id.fragment_home_sentiment_coffee_tile);
        fragmentHomeSentimentCoffeeContent = findView(R.id.fragment_home_sentiment_coffee_content);
        fragmentHomeEvaluatingAmbassadorImage = findView(R.id.fragment_home_evaluating_ambassador_image);
        fragmentHomeEvaluatingAmbassadorTile = findView(R.id.fragment_home_evaluating_ambassador_tile);
        fragmentHomeEvaluatingAmbassadorContent = findView(R.id.fragment_home_evaluating_ambassador_content);
        fragmentHomeMajorSuitLowPriceImage = findView(R.id.fragment_home_major_suit_low_price_image);
        fragmentHomeMajorSuitLowPriceTile = findView(R.id.fragment_home_major_suit_low_price_tile);
        fragmentHomeMajorSuitLowPriceContent = findView(R.id.fragment_home_major_suit_low_price_content);
        fragmentHomeInvitePolitenessImage = findView(R.id.fragment_home_invite_politeness_image);
        fragmentHomeInvitePolitenessTile = findView(R.id.fragment_home_invite_politeness_tile);
        fragmentHomeInvitePolitenessContent = findView(R.id.fragment_home_invite_politeness_content);
        fragmentPersonRecommendedDinner = findView(R.id.fragment_person_recommended_dinner);
        fragmentPersonSelectionShare = findView(R.id.fragment_person_selection_share);
        initLoadView();
        initDate();
    }


    protected void get() {

        //textView = findView(R.id.testid);

        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = "http://api.nohttp.net/test";
        Observable<TestBean> observable = baseStringProtocol.createObservable(URL, null, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, TestBean.class);
        observable.compose(this.<TestBean>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        // textView.setText(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        //textView.setText(testBean.getData().getBlog() + "博客");
                    }
                });
//        String URL="http://api.nohttp.net/test";
//        Observable<String> observable= baseStringProtocol.createObservable(URL,null,RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
//        observable .compose(this.<String>bindToLifecycle())    //  (2)
//                .observeOn(AndroidSchedulers.mainThread())  //  (3)


//        BaseStringProtocol baseStringProtocol=new BaseStringProtocol();
//        String URL="http://api.nohttp.net/test";
//        Observable<String> observable= baseStringProtocol.createObservable(URL,null,RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
//        observable .compose(this.<String>bindToLifecycle())    //  (2)
//                .observeOn(AndroidSchedulers.mainThread())  //  (3)
//        .subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                textView.setText(s+"-============================");
//            }
//        });
////                .subscribe(new Action1<String>() {          //  (4)
////
////                    @Override
////                    public void call(String data) {         //  (5)
////
////                        textView.setText(data+"-============================");
////                    }
////                }, new Action1<Throwable>() {
////                    @Override
////                    public void call(Throwable throwable) { //  (6)
////                        LogUtil.e("Get Result:\r\n" , throwable.getMessage());
////                    }
////                });
////        observable .compose(this.<String>bindToLifecycle());    //  (2)
////        observable .observeOn(AndroidSchedulers.mainThread());  //  (3)
////        observable.subscribe(new Action1<String>() {
////                                 @Override
////                                 public void call(String s) {
////
////                                     textView.setText("你爷爷的" + s);
////                                 }
////
////                             }, new Action1<Throwable>() {
////                                 @Override
////                                 public void call(Throwable throwable) {
////                                     textView.setText("你爷爷的报错了");
////                                 }
////                             }
////        );
    }

    protected void initDate() {

        InitShwonDate();
        final List<SelectShareBean> dates = new ArrayList<SelectShareBean>();
        for (int i = 0; i < 6; i++) {
            SelectShareBean bean = new SelectShareBean();
            bean.setSharetiltl("身体被掏空？你需要一锅让身体被填满的小龙虾");
            bean.setSharecontent("快扶朕起来!朕要吃小龙虾");
            dates.add(bean);
        }
        selectionShareAdapter = new SelectionShareAdapter(activity, R.layout.home_selectionshare_recyclview_item_layout, dates);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(selectionShareAdapter);
        final LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.selectionshare_recyclview_loading_layout);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SelectShareBean bean = new SelectShareBean();
                        bean.setSharetiltl("身体被掏空？你需要一锅让身体被填满的小龙虾");
                        bean.setSharecontent("快扶朕起来!朕要吃小龙虾后面添加的");
                        dates.add(bean);
                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, 2000);
                //下拉请求

            }
        });

        selectionShareAdapter.setDates(dates);
        selectionShareAdapter.notifyDataSetChanged();
        fragment_person_selection_share_recyclerView.setAdapter(mLoadMoreWrapper);
        SelectionShareAdapter selectionShareAdapter = new SelectionShareAdapter(activity, R.layout.home_selectionshare_recyclview_item_layout, dates);
        fragment_person_selection_share_recyclerView.setAdapter(selectionShareAdapter);

    }

    /**
     * 初始化首页数据了
     */
    private void InitShwonDate() {
        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = LoginRegisteredFactory.getWeChatLoginUrl();
        Map<String, String> params=new HashMap<>();
        params.put("city_id","305");
        Observable<ThirdLoginEntity> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE,ThirdLoginEntity.class);
        observable.compose(HomeFragment.this.<ThirdLoginEntity>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThirdLoginEntity>() {
                               @Override
                               public void onCompleted() {


                               }

                               @Override
                               public void onError(Throwable e) {
                                   // textView.setText(e.getMessage().toString());

                               }

                               @Override
                               public void onNext(ThirdLoginEntity entity) {


                               }
                           }

                );

    }

    private void initLoadView() {
        bannerMainDefault.setData(Arrays.asList(R.mipmap.ic_theme_activity, R.mipmap.ic_cheaper_meal, R.mipmap.ic_chef_in_civil), null);
        bannerMainDefault.setAdapter(new BGABanner.Adapter() {

            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                //这个方法用来加载的
                ((ImageView) view).setImageResource((int) model);
            }

        });

        bannerMainDefault.setOnItemClickListener(new BGABanner.OnItemClickListener() {

            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

            }
        });

        List<RecommendedDinnerBean> dates = new ArrayList<RecommendedDinnerBean>();
        for (int i = 0; i < 6; i++) {
            RecommendedDinnerBean bean = new RecommendedDinnerBean();
            bean.setRecommendedinnerbeantitle("神秘空中花园");
            bean.setRecommendedinnerbeancontent("炎热的夏天，一碗爽口炸酱面就是你的救星");
            bean.setRecommendedinnerbeanicon(null);
            dates.add(bean);
        }
        RecommendedDinnerAdapter adapter = new RecommendedDinnerAdapter(activity, R.layout.home_recommended_dinner_recyclerview_item_layouy, dates);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_person_recommended_dinner_recyclerView.setLayoutManager(linearLayoutManager);
        //fragment_person_recommended_dinner_recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        fragment_person_recommended_dinner_recyclerView.setAdapter(adapter);


        List<SelectShareBean> beans = new ArrayList<SelectShareBean>();
        selectionShareAdapter = new SelectionShareAdapter(activity, R.layout.home_selectionshare_recyclview_item_layout, beans);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_person_selection_share_recyclerView.setLayoutManager(manager);
        fragment_person_selection_share_recyclerView.setAdapter(selectionShareAdapter);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

//            case fragment_home_city:
//                //切换城市
//                Toast.makeText(activity, "+++", Toast.LENGTH_SHORT).show();
//                intent = new Intent(activity, SwitchoverCityActivity.class);
//                JumpActivity(intent);
//                break;
//            case R.id.fragment_home_message:
//                //销售
//                break;
//            case R.id.fragment_home_search:
//                intent = new Intent(activity, SearchDinnerPartyActivity.class);
//                JumpActivity(intent);
//                break;
//            case R.id.fragment_home_quick_search_kitchen:
//                intent = new Intent(activity, QuickSearchKitchenActivity.class);
//                JumpActivity(intent);
//                break;
//            case R.id.fragment_home_nearby_kitchen:
//                //附近的厨房
//                intent = new Intent(activity, NearbyKitchenActivity.class);
//                JumpActivity(intent);
//                break;
            default:
                break;
        }

    }

    private void JumpActivity(Intent intent) {
        if (intent != null) {
            startActivity(intent);
            activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    }
}