package com.fanqu.main.login;

/**
 * 登录注册界面
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanqu.R;
import com.fanqu.framework.Constants;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.PreferenceUitl;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.Login;
import com.fanqu.framework.rxbus.RxBus;
import com.fanqu.main.acitvities.BindingMobilePhoneNumberActivity;
import com.fanqu.main.acitvities.MainActivity;
import com.fanqu.main.frgment.LoginFragment;
import com.fanqu.main.frgment.RegisteredFragment;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.model.LoginEntity;
import com.fanqu.main.model.ThirdLoginEntity;
import com.fanqu.main.widget.ImitateIosAlertDialog;
import com.fanqu.main.widget.ViewPagerIndicator;
import com.fanqu.main.widget.WaitProgressDialog;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

import static com.fanqu.R.string.login;


public class LoginRegisteredActivity extends BaseActivity implements Handler.Callback, View.OnClickListener, PlatformActionListener {
    private FrameLayout login_container;
    private ViewPagerIndicator mIndicator;


    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas;
    private Observer mReuseSubscriber;
    private long login_timeout = 15 * 1000;//默认8秒钟请求没有响应就服务器出错了
    private WaitProgressDialog dialog;
    private ImitateIosAlertDialog iosdialog;
    private Handler handler;
    boolean requestsuccessfully = false;
    private TextView wechat_login;
    private static final int MSG_AUTH_CANCEL = 1;// cancel
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;//授权登录成功了
    private boolean isbindingmobilephone = false;//是否已经绑定手机号码
    private Map<String, String> map;
    private RxBus _rxBus;

    @Override
    protected int getLayoutId() {

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        ThemUtils.initthem(this, R.color.color_FFEFEB);
        setContentView(R.layout.activity_login_register_layout);
        AutoUtils.auto(this);
        _rxBus = getRxBusSingleton();
        dialog = WaitProgressDialog.getInstance(LoginRegisteredActivity.this);
        iosdialog = new ImitateIosAlertDialog(this);

        iosdialog.builder();
        handler = new Handler(Looper.getMainLooper(), this);
        initview();
    }

    private void initview() {
        wechat_login = findView(R.id.wechat_login);
        mViewPager = (ViewPager) findViewById(R.id.id_vp);

        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, new LoginFragment()).commit();
        initListener();
        initDatas();
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);

    }

    private void initListener() {
        wechat_login.setOnClickListener(this);
        //订阅者
        mReuseSubscriber = new Observer<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final Object data) {
                if (data != null) {
                    if (data instanceof Login) {
                        Login login = (Login) data;
//                        Message message = new Message();
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
                        ExecuteLoginTask task = new ExecuteLoginTask();
                        task.execute(login);

                        Observable.timer(login_timeout, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .compose(LoginRegisteredActivity.this.<Long>bindToLifecycle())
                                .subscribe(new Action1<Long>() {
                                    @Override
                                    public void call(Long aLong) {
                                        String error = getString(R.string.error);
                                        if (!requestsuccessfully) {
                                            ToastUtils.showToast(LoginRegisteredActivity.this, error);
                                        }
                                    }
                                });
                    }
                }

            }
        };
    }

    //执行登录了哦
    private void executeLogin(Login data) {

        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = LoginRegisteredFactory.getLoginUrl();
        Map<String, String> params = new HashMap<>();
        params.put("username", data.username);
        params.put("password", data.password);
        Observable<LoginEntity> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, LoginEntity.class);
        observable.compose(LoginRegisteredActivity.this.<LoginEntity>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.disMiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // textView.setText(e.getMessage().toString());
                        dialog.disMiss();
                        requestsuccessfully = false;
                    }

                    @Override
                    public void onNext(LoginEntity entity) {
                        if (entity != null) {
                            dialog.disMiss();
                            requestsuccessfully = true;
                            int ErrorId = entity.getErrorId();
                            String ErrorDes = entity.getErrorDes();
                            LoginEntity.DataBean data = entity.getData();
                            if (ErrorId == 0) {
                                //登录成功
                                if (data != null) {
                                    String uid = data.getUid();
                                    String token = data.getToken();
                                    //保存当前登录的信息
                                    PreferenceUitl.getInstance(LoginRegisteredActivity.this).saveString(Constants.USER_ID, uid);
                                    PreferenceUitl.getInstance(LoginRegisteredActivity.this).saveString(Constants.USER_TOKEN, token);
                                    jumpActivity(MainActivity.class);
                                    finish();
                                }

                            } else {
                                if (!TextUtils.isEmpty(ErrorDes)) {
                                    show_Dialog(ErrorDes);
                                }
                            }
                        }

                    }
                });

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(getString(login));
        mDatas.add(getString(R.string.register_account));
        LoginFragment fragment = new LoginFragment(mReuseSubscriber);

        mTabContents.add(fragment);
        RegisteredFragment fragment1 = new RegisteredFragment(mReuseSubscriber);
        mTabContents.add(fragment1);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {

                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {

                return mTabContents.get(position);
            }
        };
    }

    private void show_Dialog(String errorDes) {
        if (iosdialog != null) {
            iosdialog.setMsg(errorDes)
                    .setNegativeButton(getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isbindingmobilephone) {
                                //没有绑定手机号码

                            }
                        }

                    }).show();
        }
    }

    /**
     * 弹出需要绑定手机号码对话框
     */
    private void show_BoundPhoneNumberDialog(final String the3rd_uid) {
        if (iosdialog != null) {
            String tile = getString(R.string.hint);
            String bind_phone_number = getString(R.string.please_bind_phone_number);
            iosdialog.setTitle(tile);
            iosdialog.setMsg(bind_phone_number)
                    .setNegativeButton(getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isbindingmobilephone) {
                                //没有绑定手机号码
                                Intent intent = new Intent(LoginRegisteredActivity.this, BindingMobilePhoneNumberActivity.class);
                                intent.putExtra("the3rd_uid", the3rd_uid);
                                startActivity(intent);
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                                // jumpActivity(BindingMobilePhoneNumberActivity.class);
                            }
                        }


                    }).show();

        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL:
                // 取消
                Toast.makeText(this,
                        R.string.cancel, Toast.LENGTH_SHORT)
                        .show();
                break;
            case MSG_AUTH_COMPLETE:
                // 成功
                Object[] objs = (Object[]) msg.obj;
                String plat = (String) objs[0];
                @SuppressWarnings("unchecked")
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                String openid = msg.getData().getString(Constants.LOGIN_OPENID);
                /*
                * {unionid=osZqVwq2TbYNi0IPSGqSduPLhvk0, country=CN, nickname=旧的回忆(๑Ő௰Ő๑), city=Hengyang, privilege=[], province=Hunan, language=zh_CN, headimgurl=http://wx.qlogo.cn/mmopen/LdQQXa35RIongM4QHIpo9iacAuDaFbP5B8nlcyibjvSnyIf3vXx6A46iaTAbonGgMHUJ7zJias4mbudLuM79HhQ6k4kDcPUMZQVI/0, sex=1, openid=okHZQv9RUKRHDbBzY31O5uGEe-6w}
                * */
                // 动作标志
                int arg2 = msg.arg2;
                if (arg2 == Platform.ACTION_USER_INFOR) {
                    map = new HashMap<>();
                    String unionid = (String) res.get("unionid");
                    String nickname = (String) res.get("nickname");
                    String head_pic = (String) res.get("headimgurl");
                    int sex = (int) res.get("sex");
                    map.put("unionid", unionid);
                    map.put("nickname", nickname);
                    map.put("head_pic", head_pic);
                    map.put("sex", String.valueOf(sex));
                    map.put("openid", openid);
                    if (plat.equals("Wechat")) {
                        ExecuteThirdPartyLoginTask task = new ExecuteThirdPartyLoginTask();
                        task.execute();
                        //executethirdpartyLogin(map);
                    } else if (plat.equals("QQ")) {

                    } else {

                    }

                } else if (arg2 == Platform.ACTION_SHARE) {

                }

                break;
            default:
                break;

        }
        return false;
    }

    /**
     * @param
     * 执行第三方登录了
     */

    private void executethirdpartyLogin(Map<String, String> params) {
        BeanRequestProtocol baseStringProtocol = new BeanRequestProtocol();
        String URL = LoginRegisteredFactory.getWeChatLoginUrl();
        Observable<ThirdLoginEntity> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, ThirdLoginEntity.class);
        observable.compose(LoginRegisteredActivity.this.<ThirdLoginEntity>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThirdLoginEntity>() {
                               @Override
                               public void onCompleted() {
                                   dialog.disMiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   // textView.setText(e.getMessage().toString());
                                   dialog.disMiss();
                                   requestsuccessfully = false;
                               }

                               @Override
                               public void onNext(ThirdLoginEntity entity) {
                                   if (entity != null) {
                                       dialog.disMiss();
                                       requestsuccessfully = true;
                                       int ErrorId = entity.getErrorId();
                                       String ErrorDes = entity.getErrorDes();
                                       if (ErrorId == 0) {
                                           //登录成功
                                           //ToastUtils.showToast(LoginRegisteredActivity.this, "第三方登录成功了");

                                           ThirdLoginEntity.DataBean data = entity.getData();
                                           String uid = data.getUid();
                                           String token = data.getToken();
                                           //保存当前登录的信息
                                           PreferenceUitl.getInstance(LoginRegisteredActivity.this).saveString(Constants.USER_ID, uid);
                                           PreferenceUitl.getInstance(LoginRegisteredActivity.this).saveString(Constants.USER_TOKEN, uid);
                                           jumpActivity(MainActivity.class);
                                           finish();

                                       } else {
                                           ThirdLoginEntity.DataBean data = entity.getData();
                                           // 400011没有绑定手机号ThirdLoginEntity{Data=DataBean{token='null', uid='null', the3rd_uid='6149'}}
                                           LogUtil.e("测试成公了", ErrorId + ErrorDes + entity.toString());
                                           String the3rd_uid = data.getThe3rd_uid();
                                           LogUtil.e("测试the3rd_uid", the3rd_uid);
                                           if (ErrorId == 400011) {
                                               isbindingmobilephone = false;
                                               show_BoundPhoneNumberDialog(the3rd_uid);
                                           } else {
                                               isbindingmobilephone = true;
                                               if (!TextUtils.isEmpty(ErrorDes)) {
                                                   show_Dialog(ErrorDes);
                                               }
                                           }
                                       }
                                   }

                               }
                           }

                );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_login:
                //微信登录
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                } else {
                    wxLogin();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        LogUtil.e("测试方法", "onComplete成功了");
        Message msg = new Message();
        msg.what = MSG_AUTH_COMPLETE;
        msg.arg2 = action;
        // 获取用户OpenID
        String openid = platform.getDb().getUserId();
        msg.obj = new Object[]{platform.getName(), res};
        Bundle bundle = new Bundle();
        bundle.putString(Constants.LOGIN_OPENID, openid);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.what = MSG_AUTH_CANCEL;
        handler.sendMessage(msg);
        LogUtil.e("测试方法", "onCancel");
    }

    private class ExecuteLoginTask extends AsyncTask<Login, Void, Void> {

        @Override
        protected Void doInBackground(Login... voids) {
            Login login = voids[0];
            executeLogin(login);
            return null;
        }

        @Override
        protected void onPreExecute() {
            String login = getString(R.string.login);
            dialog.showWaitPrompt(login + "中", false);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }


    }

    /**
     * 微信授权登录
     */

    private void wxLogin() {
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        if (wechat.isAuthValid()) {
            wechat.removeAccount(true);
            return;
        }
        // 使用SSO授权，通过客户单授权
        wechat.SSOSetting(false);
        wechat.setPlatformActionListener(this);
        wechat.showUser(null);
        // Toast.makeText(droidGap.getActivity(), "微信登录了", Toast.LENGTH_SHORT)
        // .show();
    }

    private class ExecuteThirdPartyLoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (map != null) {
                executethirdpartyLogin(map);
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            String login = getString(R.string.login);
            dialog.showWaitPrompt(login + "中", false);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        //将普通的Observable转换为可连接的Observable
        ConnectableObservable<Object> tapEventEmitter = _rxBus.toObserverable().publish();

        tapEventEmitter
                .compose(this.bindToLifecycle())
                .subscribe(new Action1<Object>() { //一个一旦被触发就会显示TapText的监听者
                    @Override
                    public void call(Object event) {
                        if (event instanceof CloseEvent) {
                            finish();
                        }
                    }
                });
        tapEventEmitter.connect();  //可连接的Observable并不在订阅时触发，而需手动调用connect()方法
    }

    /**
     * 手机绑定成功后结束当前登录的activity
     */
    public static class CloseEvent {
    }
}