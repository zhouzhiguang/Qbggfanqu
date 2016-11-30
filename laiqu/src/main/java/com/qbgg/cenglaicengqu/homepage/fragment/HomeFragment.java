package com.qbgg.cenglaicengqu.homepage.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.adapter.RecommendedDinnerAdapter;
import com.qbgg.cenglaicengqu.homepage.adapter.SelectionShareAdapter;
import com.qbgg.cenglaicengqu.homepage.model.RecommendedDinnerBean;
import com.qbgg.cenglaicengqu.homepage.model.SelectShareBean;
import com.qbgg.cenglaicengqu.main.acitvities.MainActivity;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;
import com.qbgg.cenglaicengqu.main.widget.SearchBarLayout;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.yolanda.nohttp.RequestMethod;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import static com.qbgg.cenglaicengqu.R.id.fragment_home_city;


/**

 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private MainActivity activity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private View view;
    private TextView textView;
    private BGABanner banner_main_default;
    private SHSwipeRefreshLayout fragment_home_shswipeRefreshLayout;
    private RecyclerView fragment_person_recommended_dinner_recyclerView,fragment_person_selection_share_recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SelectionShareAdapter selectionShareAdapter;//精选分享

    private BGABanner bannerMainDefault;
    private ImageView fragmentHomeMessage;//主页消息
    private TextView fragmentHomeCity;//主页切换城市
    private SearchBarLayout fragmentHomeSearch;//主页搜索附近主题饭局
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



    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home_layout);
        assignViews();
    }

    @Override
    protected void setListener() {
        initLitener();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }
    /**
     * 初始化监听事件
     */
    private void initLitener() {
        fragmentHomeMessage.setOnClickListener(this);
        fragmentHomeCity.setOnClickListener(this);
    }

    private void assignViews() {
        fragment_home_title = findView(R.id.fragment_home_title);
        fragmentHomeMessage =  findView(R.id.fragment_home_message);
         fragmentHomeCity =  findView(fragment_home_city);
        fragmentHomeSearch =  findView(R.id.fragment_home_search);
        fragmentHomeNearbyKitchen =  findView(R.id.fragment_home_nearby_kitchen);
        fragmentHomeQuickSearchKitchen =  findView(R.id.fragment_home_quick_search_kitchen);
        fragmentHomeLaiqu =  findView(R.id.fragment_home_laiqu);
        fragmentHomeRecruitKitchener = findView(R.id.fragment_home_recruit_kitchener);
        fragmentHomeSentimentCoffeeImage =  findView(R.id.fragment_home_sentiment_coffee_image);
        fragmentHomeSentimentCoffeeTile =  findView(R.id.fragment_home_sentiment_coffee_tile);
        fragmentHomeSentimentCoffeeContent =  findView(R.id.fragment_home_sentiment_coffee_content);
        fragmentHomeEvaluatingAmbassadorImage =  findView(R.id.fragment_home_evaluating_ambassador_image);
        fragmentHomeEvaluatingAmbassadorTile =  findView(R.id.fragment_home_evaluating_ambassador_tile);
        fragmentHomeEvaluatingAmbassadorContent =  findView(R.id.fragment_home_evaluating_ambassador_content);
        fragmentHomeMajorSuitLowPriceImage =  findView(R.id.fragment_home_major_suit_low_price_image);
        fragmentHomeMajorSuitLowPriceTile =  findView(R.id.fragment_home_major_suit_low_price_tile);
        fragmentHomeMajorSuitLowPriceContent =  findView(R.id.fragment_home_major_suit_low_price_content);
        fragmentHomeInvitePolitenessImage =  findView(R.id.fragment_home_invite_politeness_image);
        fragmentHomeInvitePolitenessTile =  findView(R.id.fragment_home_invite_politeness_tile);
        fragmentHomeInvitePolitenessContent =  findView(R.id.fragment_home_invite_politeness_content);
        fragmentPersonRecommendedDinner =  findView(R.id.fragment_person_recommended_dinner);
        fragmentPersonSelectionShare =  findView(R.id.fragment_person_selection_share);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        linearLayoutManager = new LinearLayoutManager(activity);
        banner_main_default = findView(R.id.banner_main_default);
        fragment_person_recommended_dinner_recyclerView = findView(R.id.fragment_person_recommended_dinner_recyclerView);
        fragment_person_selection_share_recyclerView=findView(R.id.fragment_person_selection_share_recyclerView);
        fragment_home_shswipeRefreshLayout = findView(R.id.fragment_home_shswipeRefreshLayout);
        //关闭下拉刷新
        fragment_home_shswipeRefreshLayout.setLoadmoreEnable(false);
        fragment_home_shswipeRefreshLayout.setRefreshEnable(false);

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

       final   List<SelectShareBean>dates=new ArrayList<SelectShareBean>();
        for(int i=0;i<6;i++){
            SelectShareBean bean=new SelectShareBean();
            bean.setSharetiltl("身体被掏空？你需要一锅让身体被填满的小龙虾");
            bean.setSharecontent("快扶朕起来!朕要吃小龙虾");
            dates.add(bean);
        }
        selectionShareAdapter = new SelectionShareAdapter(activity, R.layout.home_selectionshare_recyclview_item_layout,dates);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(selectionShareAdapter);
      final   LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.selectionshare_recyclview_loading_layout);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SelectShareBean bean=new SelectShareBean();
                        bean.setSharetiltl("身体被掏空？你需要一锅让身体被填满的小龙虾");
                        bean.setSharecontent("快扶朕起来!朕要吃小龙虾后面添加的");
                        dates.add(bean);
                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                },2000);
                //下拉请求

            }
        });

//        selectionShareAdapter.setDates(dates);
//        selectionShareAdapter.notifyDataSetChanged();
        fragment_person_selection_share_recyclerView.setAdapter(mLoadMoreWrapper);
//        SelectionShareAdapter selectionShareAdapter=new SelectionShareAdapter(activity,R.layout.home_selectionshare_recyclview_item_layout,dates);
//        fragment_person_selection_share_recyclerView.setAdapter(selectionShareAdapter);

    }

    private void initLoadView() {
        banner_main_default.setData(Arrays.asList(R.mipmap.ic_theme_activity, R.mipmap.ic_cheaper_meal, R.mipmap.ic_chef_in_civil), null);
        banner_main_default.setAdapter(new BGABanner.Adapter() {

            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                //这个方法用来加载的
                ((ImageView) view).setImageResource((int) model);
            }

        });

        banner_main_default.setOnItemClickListener(new BGABanner.OnItemClickListener() {

            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

            }
        });

    List<RecommendedDinnerBean> dates=new ArrayList<RecommendedDinnerBean>();
    for (int i=0;i<6;i++){
        RecommendedDinnerBean bean=new RecommendedDinnerBean();
        bean.setRecommendedinnerbeantitle("神秘空中花园");
        bean.setRecommendedinnerbeancontent("炎热的夏天，一碗爽口炸酱面就是你的救星");
        bean.setRecommendedinnerbeanicon(null);
        dates.add(bean);
    }
    RecommendedDinnerAdapter adapter=new RecommendedDinnerAdapter(activity,R.layout.home_recommended_dinner_recyclerview_item_layouy,dates);linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    fragment_person_recommended_dinner_recyclerView.setLayoutManager(linearLayoutManager);
    //fragment_person_recommended_dinner_recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
    fragment_person_recommended_dinner_recyclerView.setAdapter(adapter);


        List<SelectShareBean>beans=new ArrayList<SelectShareBean>();
        selectionShareAdapter = new SelectionShareAdapter(activity, R.layout.home_selectionshare_recyclview_item_layout,beans);
        LinearLayoutManager  manager= new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_person_selection_share_recyclerView.setLayoutManager(manager);
        fragment_person_selection_share_recyclerView.setAdapter(selectionShareAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_home_city:
                //切换城市
                Toast.makeText(activity, "+++", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_home_message:
                //销售
            break;
            default:
                break;
        }

    }

}