package com.fanqu.main.acitvities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.data.UserManager;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.User;
import com.fanqu.framework.model.UserInfoBean;
import com.fanqu.main.location.BaseUrlFactory;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.login.LoginRegisteredActivity;
import com.fanqu.main.model.UserInfoEntity;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.yolanda.nohttp.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SpalshActivity extends BaseActivity {

    private String interfaceBaseUrl;
    private CustomApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageTransparent(this);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        ShareSDK.initSDK(this);
        application = CustomApplication.getInstance();
        ThemUtils.initthem(this, R.color.transparent);
        // setContentView(R.layout.activity_spalsh_lyout);
//        Glide.with(this).load(imageUrl).fitCenter().into(imageView);
        boolean islogined = UserManager.getInstance(this).isLogined();
        //判断当前登录状态 不管什么都跳转到主页
        interfaceBaseUrl = BaseUrlFactory.getInterfaceBaseUrl();
        //http://test-skapi-v1-3.cengfan7.com/Session
        if (islogined) {
            User user = UserManager.getInstance(this).getUser();
            getUsinfoRequest(user.getUser_token(), user.getUser_id());
//            jumpActivity(MainActivity.class);
//            finish();
        } else {
            jumpActivity(LoginRegisteredActivity.class);
            finish();
        }
        // jumpActivity(LoginRegisteredActivity.class);
    }


    @Override
    protected int getLayoutId() {

        return 0;
    }

    /**
     * 图片全屏透明状态栏（图片位于状态栏下面）
     *
     * @param activity
     */
    public static void setImageTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * D登录了获取当前
     */
    private void getUsinfoRequest(String token, String uid) {
        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = LoginRegisteredFactory.getUsinfoUrl();
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        Observable<UserInfoEntity> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.GET, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, UserInfoEntity.class);
        observable.compose(SpalshActivity.this.<UserInfoEntity>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfoEntity>() {
                               @Override
                               public void onCompleted() {
                                   LogUtil.e("测onCompleted试", "'==");
                                   //dialog.disMiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   LogUtil.e("测onError试", e.toString());
                                   CustomApplication.getInstance().setUserInfoBean(null);
                               }

                               @Override
                               public void onNext(UserInfoEntity entity) {
                                   LogUtil.e("测onNext试", entity.toString());
                                   if (entity != null) {
                                       UserInfoBean bean = entity.getData();
                                       application.setUserInfoBean(bean);
                                       LogUtil.e("测试", entity.getData().getBind_phone() + entity.getData().getNickname());
                                       //application.setUserInfoBean(entity);
                                   }


                               }
                           }

                );
    }
}