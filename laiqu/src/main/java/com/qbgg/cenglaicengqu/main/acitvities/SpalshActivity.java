package com.qbgg.cenglaicengqu.main.acitvities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.netease.nim.uikit.common.util.log.LogUtil;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.fragment.TestBean;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.date.DataManager;
import com.qbgg.cenglaicengqu.main.date.UserManager;
import com.qbgg.cenglaicengqu.main.date.UserProfile;
import com.qbgg.cenglaicengqu.main.model.UserToken;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class SpalshActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        final boolean isLogined = UserManager.getInstance(this).isLogined();
        //事件总线登录非登录两种状态
        if (isLogined) {
            // 获取当前帐号的用户信息
            UserToken userToken = UserManager.getInstance(this).getUserToken();
            // 这里请求返回的当前用户javabean
            BeanRequestProtocol beanRequestProtocol = new BeanRequestProtocol();
            DataManager manager = new DataManager(beanRequestProtocol);
            Observable<TestBean> observable = manager.buildRequestBean();
            observable.compose(this.<TestBean>bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TestBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(TestBean testBean) {
                            LogUtil.e("测试数据",testBean.toString()+"****************");
                            Toast.makeText(SpalshActivity.this, "请求成功了"+testBean.toString(), Toast.LENGTH_SHORT).show();
                             //保存当前获取到帐号信息保存起来
                            UserProfile userProfile=new UserProfile();
                            UserManager.getInstance(SpalshActivity.this).setUserProfile(userProfile);

                        }
                    });

        }else {
            //没有登录
//            startActivity(new Intent(SpalshActivity.this, LoginActivity.class));
//            finish();
//            overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
            skip();

        }
        //加背景就需要 setContentView 不加就原来全部注稀
//        setTheme(android.R.style.Theme_Black_NoTitleBar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //     setContentView(R.layout.activity_spalsh_layout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               //skip();
            }
        }, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //skip();
    }

    private void skip() {
        Intent intent=new Intent(SpalshActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }



}
