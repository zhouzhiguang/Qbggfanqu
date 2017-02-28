package com.fanqu.homepage.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanqu.R;
import com.fanqu.dinner.listener.AppBarStateChangeListener;
import com.fanqu.dinner.listener.State;
import com.fanqu.framework.data.UserManager;
import com.fanqu.framework.fragment.LazyFragment;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.framework.model.User;
import com.fanqu.homepage.adapter.RecommendedDinnerAdapter;
import com.fanqu.homepage.adapter.SelectionShareAdapter;
import com.fanqu.homepage.model.RecommendedDinnerBean;
import com.fanqu.homepage.model.SelectShareBean;
import com.fanqu.main.acitvities.MainActivity;
import com.fanqu.main.model.HomePageEntity;
import com.nineoldandroids.animation.ObjectAnimator;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bingoogolapple.bgabanner.BGABanner;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.fanqu.R.id.fragment_home_evaluating_ambassador_image;
import static com.fanqu.R.id.fragment_home_sentiment_coffee_image;
import static com.fanqu.R.id.fragment_home_sentiment_coffee_tile;


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
    private BGABanner bannerMainDefault, banner_slider;

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
    private User user;
    private UserManager usermanager;
    private boolean islogin;
    private RelativeLayout fragment_home_page_adv_1, fragment_home_page_adv_2, fragment_home_page_adv_3, fragment_home_page_adv_4;
    private HomePageEntity.DataBean date;
    private NestedScrollView nestedScrollView;
    private LinearLayout home_page_content_layout;
    private com.fanqu.main.widget.CircularProgress circularProgress;

    public HomeFragment(HomePageEntity date) {
        super();
        this.date = date.getData();
    }

    public HomeFragment() {
        super();
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home_layout);
        usermanager = UserManager.getInstance(activity);
        islogin = usermanager.isLogined();
        if (islogin) {
            user = usermanager.getUser();
        }
        toolbar = findView(R.id.toolbar);
        whitecolour = ContextCompat.getColor(activity, R.color.white);
        textGrayDeep = ContextCompat.getColor(activity, R.color.textGrayDeep);
        downtwhiteDrawable = ContextCompat.getDrawable(activity, R.mipmap.ic_down_arrows_white);
        downblackDrawable = ContextCompat.getDrawable(activity, R.mipmap.ic_down_arrows_black);
        nestedScrollView = findView(R.id.NestedScrollView);
        circularProgress = findView(R.id.CircularProgress);
        kitchen_details_app_bar_layout = findView(R.id.kitchen_details_app_bar_layout);
        home_page_content_layout = findView(R.id.home_page_content_layout);
        //home_page_content_layout.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.GONE);
        kitchen_details_app_bar_layout.setVisibility(View.GONE);
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
        banner_slider.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                //这个方法用来加载的
                if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    //((ImageView) view).setImageResource((int) model);
                    if (model instanceof String) {
                        Glide.with(HomeFragment.this).load(model).into(imageView);
                        // Glide.with(HomeFragment.this).load(model).placeholder(R.mipmap.ic_launcher).into(imageView);
                    }

                }


            }
        });
        bannerMainDefault.setAdapter(new BGABanner.Adapter() {

            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                //这个方法用来加载的
                if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    //((ImageView) view).setImageResource((int) model);
                    if (model instanceof String) {
                        Glide.with(HomeFragment.this).load(model).into(imageView);
                        // Glide.with(HomeFragment.this).load(model).placeholder(R.mipmap.ic_launcher).into(imageView);
                    }

                }

            }

        });

        bannerMainDefault.setOnItemClickListener(new BGABanner.OnItemClickListener() {

            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

            }
        });

        fragmentHomeQuickSearchKitchen.setOnClickListener(this);
        fragmentHomeNearbyKitchen.setOnClickListener(this);
        kitchen_details_app_bar_layout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.EXPANDED) {
                    city.setTextColor(whitecolour);
                    home_page.setVisibility(View.GONE);
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
        banner_slider = findView(R.id.banner_slider);
        fragment_home_page_adv_1 = findView(R.id.fragment_home_page_adv_1);
        fragment_home_page_adv_2 = findView(R.id.fragment_home_page_adv_2);
        fragment_home_page_adv_3 = findView(R.id.fragment_home_page_adv_3);
        fragment_home_page_adv_4 = findView(R.id.fragment_home_page_adv_4);
        city = findView(R.id.city);
        home_page = findView(R.id.home_page);

        bannerMainDefault = findView(R.id.banner_main_default);
        linearLayoutManager = new LinearLayoutManager(activity);
        fragment_person_recommended_dinner_recyclerView = findView(R.id.fragment_person_recommended_dinner_recyclerView);
        fragment_person_selection_share_recyclerView = findView(R.id.fragment_person_selection_share_recyclerView);

        fragmentHomeNearbyKitchen = findView(R.id.fragment_home_nearby_kitchen);
        fragmentHomeQuickSearchKitchen = findView(R.id.fragment_home_quick_search_kitchen);
        fragmentHomeLaiqu = findView(R.id.fragment_home_laiqu);
        fragmentHomeRecruitKitchener = findView(R.id.fragment_home_recruit_kitchener);
        fragmentHomeSentimentCoffeeImage = findView(fragment_home_sentiment_coffee_image);
        fragmentHomeSentimentCoffeeTile = findView(fragment_home_sentiment_coffee_tile);
        fragmentHomeSentimentCoffeeContent = findView(R.id.fragment_home_sentiment_coffee_content);
        fragmentHomeEvaluatingAmbassadorImage = findView(fragment_home_evaluating_ambassador_image);
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

    private void InitShwonDate() {

        List<HomePageEntity.DataBean.TopicBean> tipiclist = date.getTopic();
        List<HomePageEntity.DataBean.AdvBean> advBeanList = date.getAdv();
        List<HomePageEntity.DataBean.SliderBean> slider = date.getSlider();
        if (tipiclist != null && tipiclist.size() > 0) {
            List<String> tiplist = new ArrayList<String>();
            for (HomePageEntity.DataBean.TopicBean bean : tipiclist) {
                tiplist.add(bean.getThumb());
                //bannerMainDefault.setData(Arrays.asList(bean.getThumb(), R.mipmap.ic_cheaper_meal, R.mipmap.ic_chef_in_civil), null);
            }
            if (tipiclist.size() > 0) {
                if (tipiclist.size() == 1) {
                    bannerMainDefault.setIsNeedShowIndicatorOnOnlyOnePage(false);
                    bannerMainDefault.setAutoPlayAble(false);
                }
                bannerMainDefault.setData(tiplist, null);

            }
        }
        if (advBeanList != null && advBeanList.size() > 0) {
            initadvDate(advBeanList);
        }
        if (slider != null && slider.size() > 0) {

            initSlder(slider);
        }
        Observable.timer(500 , TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        // Glide.with(TimerFragment.this).load("http://static.zuchecdn.com/wap/newversion/images/20151225fanli_app.jpg").crossFade().into(iv_welcome);
                        //iv_welcome.setVisibility(View.VISIBLE);

                        circularProgress.setVisibility(View.GONE);
                        nestedScrollView.setVisibility(View.VISIBLE);
                        kitchen_details_app_bar_layout.setVisibility(View.VISIBLE);
                        ObjectAnimator
                                .ofFloat(nestedScrollView, "alpha", 0.0F, 1.0F)
                                .setDuration(500)
                                .start();
                        ObjectAnimator
                                .ofFloat(kitchen_details_app_bar_layout, "alpha", 0.0F, 1.0F)
                                .setDuration(500)
                                .start();
                    }
                });
//        circularProgress.setVisibility(View.GONE);
//        nestedScrollView.setVisibility(View.VISIBLE);
//        kitchen_details_app_bar_layout.setVisibility(View.VISIBLE);
    }


    /**
     * 初始化首页数据了
     */
//    private void InitShwonDate() {
//        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
//        String URL = HomePageFactory.getHomepageDateurl();
//        Map<String, String> params = new HashMap<>();
//        if (islogin) {
//            params.put("token", user.getUser_token());
//        }
//        params.put("city_id", "305");
//        Observable<HomePageEntity> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.GET, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, HomePageEntity.class);
//        observable.compose(HomeFragment.this.<HomePageEntity>bindToLifecycle())    //  (2)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HomePageEntity>() {
//                               @Override
//                               public void onCompleted() {
//
//
//                               }
//
//                               @Override
//                               public void onError(Throwable e) {
//                                   // textView.setText(e.getMessage().toString());
//
//                               }
//
//                               @Override
//                               public void onNext(HomePageEntity entity) {
//                                   HomePageEntity.DataBean date = entity.getData();
//                                   List<HomePageEntity.DataBean.TopicBean> tipiclist = date.getTopic();
//                                   List<HomePageEntity.DataBean.AdvBean> advBeanList = date.getAdv();
//                                   List<HomePageEntity.DataBean.SliderBean> slider = date.getSlider();
//                                   if (tipiclist != null && tipiclist.size() > 0) {
//                                       List<String> tiplist = new ArrayList<String>();
//                                       for (HomePageEntity.DataBean.TopicBean bean : tipiclist) {
//                                           tiplist.add(bean.getThumb());
//                                           //bannerMainDefault.setData(Arrays.asList(bean.getThumb(), R.mipmap.ic_cheaper_meal, R.mipmap.ic_chef_in_civil), null);
//                                       }
//                                       if (tipiclist.size() > 0) {
//                                           if (tipiclist.size() == 1) {
//                                               bannerMainDefault.setIsNeedShowIndicatorOnOnlyOnePage(false);
//                                               bannerMainDefault.setAutoPlayAble(false);
//                                           }
//                                           bannerMainDefault.setData(tiplist, null);
//
//                                       }
//                                   }
//                                   if (advBeanList != null && advBeanList.size() > 0) {
//                                       initadvDate(advBeanList);
//                                   }
//                                   if (slider != null && slider.size() > 0) {
//                                       initSlder(slider);
//                                   }
//                               }
//                           }
//
//                );
//
//    }
    private void initSlder(List<HomePageEntity.DataBean.SliderBean> slider) {
        LogUtil.e("轮播", slider.toString());
        List<String> sliderimage = new ArrayList<>();
        for (HomePageEntity.DataBean.SliderBean sliderBean : slider) {
            sliderimage.add(sliderBean.getPicture());
        }
        if (sliderimage.size() > 0) {
            if (sliderimage.size() == 1) {
                banner_slider.setIsNeedShowIndicatorOnOnlyOnePage(false);
                banner_slider.setAutoPlayAble(false);
            }
            banner_slider.setData(sliderimage, null);
        }
    }

    /**
     * @param advBeanList 初始化广告数据
     *                    <p>
     *                    [AdvBean{id='1', province_id='0', city_id='0', category=null, title='儿时的味道', intro='细品香醇时光细品香醇时', thumb='http://o8uz2td92.bkt.clouddn.com/2016-12-29_586469070acdd.jpg', picture='http://o8uz2td92.bkt.clouddn.com/2016-11-10_5824366146a7e.jpg', link='http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17', sort='1', end_time='0', status='1', create_time='1478155357', modify_time='1478156653'}, AdvBean{id='2', province_id='0', city_id='0', category=null, title='澳洲龙虾主题餐馆', intro='得50元优惠礼包', thumb='http://o8uz2td92.bkt.clouddn.com/2016-12-29_58646992033d4.jpg', picture='http://o8uz2td92.bkt.clouddn.com/2016-11-10_58243530ad802.jpg', link='http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=4', sort='2', end_time='0', status='1', create_time='1478156804', modify_time='0'}, AdvBean{id='4', province_id='0', city_id='0', category=null, title='元气早餐', intro='
     *                    带你走进这些温柔的店里，完美的一天才要刚刚开始', thumb='http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635d8c120dd.png', picture='http://o8uz2td92.bkt.clouddn.com/2016-12-28_58635dfa75e60.png', link='http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=article&id=17', sort='3', end_time='0', status='1', create_time='1478766047', modify_time='1482908045'}, AdvBean{id='5', province_id='0', city_id='0', category=null, title='暖暖火锅季', intro='寒秋的美食慰藉', thumb='null', picture='http://o8uz2td92.bkt.clouddn.com/2016-11-10_582432677fa85.jpg', link='http://test.cengfan7.cn/wap.php?m=Wap&c=Index&a=kitchen_intr&id=7', sort='4', end_time='0', status='1', create_time='1478767218', modify_time='0'}]
     */
    private void initadvDate(List<HomePageEntity.DataBean.AdvBean> advBeanList) {

        if (advBeanList.size() == 1) {
            fragment_home_page_adv_1.setVisibility(View.VISIBLE);
            fragment_home_page_adv_2.setVisibility(View.GONE);
            fragment_home_page_adv_3.setVisibility(View.GONE);
            fragment_home_page_adv_4.setVisibility(View.GONE);
            HomePageEntity.DataBean.AdvBean advbean1 = advBeanList.get(0);
            Glide.with(HomeFragment.this).load(advbean1.getPicture()).into(fragmentHomeSentimentCoffeeImage);
            String title = advbean1.getTitle();
            String intro = advbean1.getIntro();
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeSentimentCoffeeContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeSentimentCoffeeTile.setText(title);
            }

        } else if (advBeanList.size() == 2) {
            fragment_home_page_adv_1.setVisibility(View.VISIBLE);
            fragment_home_page_adv_2.setVisibility(View.VISIBLE);
            fragment_home_page_adv_3.setVisibility(View.GONE);
            fragment_home_page_adv_4.setVisibility(View.GONE);
            HomePageEntity.DataBean.AdvBean advbean1 = advBeanList.get(0);
            HomePageEntity.DataBean.AdvBean advbean2 = advBeanList.get(1);
            Glide.with(HomeFragment.this).load(advbean1.getPicture()).into(fragmentHomeSentimentCoffeeImage);
            String title = advbean1.getTitle();
            String intro = advbean1.getIntro();
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeSentimentCoffeeContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeSentimentCoffeeTile.setText(title);
            }
            title = advbean2.getTitle();
            intro = advbean2.getIntro();
            Glide.with(HomeFragment.this).load(advbean2.getPicture()).into(fragmentHomeEvaluatingAmbassadorImage);
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeEvaluatingAmbassadorContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeEvaluatingAmbassadorTile.setText(title);
            }


        } else if (advBeanList.size() == 3) {
            fragment_home_page_adv_1.setVisibility(View.VISIBLE);
            fragment_home_page_adv_2.setVisibility(View.VISIBLE);
            fragment_home_page_adv_3.setVisibility(View.VISIBLE);
            fragment_home_page_adv_4.setVisibility(View.GONE);
            HomePageEntity.DataBean.AdvBean advbean1 = advBeanList.get(0);
            HomePageEntity.DataBean.AdvBean advbean2 = advBeanList.get(1);
            HomePageEntity.DataBean.AdvBean advbean3 = advBeanList.get(2);
            Glide.with(HomeFragment.this).load(advbean1.getPicture()).into(fragmentHomeSentimentCoffeeImage);
            String title = advbean1.getTitle();
            String intro = advbean1.getIntro();
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeSentimentCoffeeContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeSentimentCoffeeTile.setText(title);
            }
            title = advbean2.getTitle();
            intro = advbean2.getIntro();
            Glide.with(HomeFragment.this).load(advbean2.getPicture()).into(fragmentHomeEvaluatingAmbassadorImage);
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeEvaluatingAmbassadorContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeEvaluatingAmbassadorTile.setText(title);
            }

            title = advbean3.getTitle();
            intro = advbean3.getIntro();
            Glide.with(HomeFragment.this).load(advbean3.getPicture()).into(fragmentHomeMajorSuitLowPriceImage);
            if (!TextUtils.isEmpty(intro)) {
//                if (intro.contains("\n")) {
//                    //intro.replaceAll("[\\pP‘’“”]", "");
//                    intro.replaceAll("\n", "");
//                }
                fragmentHomeMajorSuitLowPriceContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeMajorSuitLowPriceTile.setText(title);
            }

        } else if (advBeanList.size() == 4) {
            fragment_home_page_adv_1.setVisibility(View.VISIBLE);
            fragment_home_page_adv_2.setVisibility(View.VISIBLE);
            fragment_home_page_adv_3.setVisibility(View.VISIBLE);
            fragment_home_page_adv_4.setVisibility(View.VISIBLE);
            HomePageEntity.DataBean.AdvBean advbean1 = advBeanList.get(0);
            HomePageEntity.DataBean.AdvBean advbean2 = advBeanList.get(1);
            HomePageEntity.DataBean.AdvBean advbean3 = advBeanList.get(2);
            HomePageEntity.DataBean.AdvBean advbean4 = advBeanList.get(3);
            Glide.with(HomeFragment.this).load(advbean1.getPicture()).into(fragmentHomeSentimentCoffeeImage);
            String title = advbean1.getTitle();
            String intro = advbean1.getIntro();
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeSentimentCoffeeContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeSentimentCoffeeTile.setText(title);
            }
            title = advbean2.getTitle();
            intro = advbean2.getIntro();
            Glide.with(HomeFragment.this).load(advbean2.getPicture()).into(fragmentHomeEvaluatingAmbassadorImage);
            if (!TextUtils.isEmpty(intro)) {
                //intro.replaceAll("[\\pP‘’“”]", "");
                fragmentHomeEvaluatingAmbassadorContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeEvaluatingAmbassadorTile.setText(title);
            }

            title = advbean3.getTitle();
            intro = advbean3.getIntro();
            Glide.with(HomeFragment.this).load(advbean3.getPicture()).into(fragmentHomeMajorSuitLowPriceImage);
            if (!TextUtils.isEmpty(intro)) {
//                if (intro.contains("\n")) {
//                    //intro.replaceAll("[\\pP‘’“”]", "");
//                    intro.replaceAll("\n", "");
//                }
                fragmentHomeMajorSuitLowPriceContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeMajorSuitLowPriceTile.setText(title);
            }

            title = advbean4.getTitle();
            intro = advbean4.getIntro().replaceAll("\n", "");;
            Glide.with(HomeFragment.this).load(advbean4.getPicture()).into(fragmentHomeInvitePolitenessImage);
            if (!TextUtils.isEmpty(intro)) {
                fragmentHomeInvitePolitenessContent.setText(intro);
            }
            if (!TextUtils.isEmpty(title)) {
                fragmentHomeInvitePolitenessTile.setText(title);
            }
        }


    }

    private void initLoadView() {


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