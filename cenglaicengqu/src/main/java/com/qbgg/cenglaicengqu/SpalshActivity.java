package com.qbgg.cenglaicengqu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.qbgg.cenglaicengqu.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.common.activities.MainActivity;
import com.qbgg.cenglaicengqu.common.util.sys.SysInfoUtil;
import com.qbgg.cenglaicengqu.config.preference.Preferences;
import com.qbgg.cenglaicengqu.login.LoginActivity;
import com.qbgg.cenglaicengqu.main.model.Extras;

import java.util.ArrayList;


/**
 * 开启欢迎页面
 */
public class SpalshActivity extends BaseActivity {
    private String account;//账号
    private String token;
    private static boolean firstEnter = true; // 是否首次进入
    private boolean customSplash = false;
    private  String TAG="spalshactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_layout);
        AutoUtils.auto(this);
        account = Preferences.getUserAccount();
        token = Preferences.getUserToken();
        if (savedInstanceState !=null){
            setIntent(new Intent()); // 从堆栈恢复，不再重复解析之前的intent
        }
        if (!firstEnter) {
            onIntent();
        } else {
            showSplashView();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(){
            @Override
            public void run() {
                com.netease.nim.uikit.contact.core.query.PinYin.init(getApplicationContext());
                 com.netease.nim.uikit.contact.core.query.PinYin.validate();
            }
        }.start();

    }

    private void showSplashView() {
        //getWindow().setBackgroundDrawableResource(R.drawable.splash_bg);
        customSplash = true;
    }

    private void onIntent() {

        if (TextUtils.isEmpty(DemoCache.getAccount())) {
            // 判断当前app是否正在运行
            if (!SysInfoUtil.stackResumed(this)) {
                Intent intent=new Intent(this, com.qbgg.cenglaicengqu.login.LoginActivity.class);
                intent.putExtra("KICK_OUT",false);
                startActivity(intent);

            }
            finish();
            overridePendingTransition(R.anim.activity_fade_anim,R.anim.activity_hold_anim);
            
        } else {
            // 已经登录过了，处理过来的请求
            Intent intent = getIntent();
            if (intent != null) {
              Bundle bundle=  intent.getExtras();
                if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
                    parseNotifyIntent(intent);
                    return;
                } else if (intent.hasExtra(Extras.EXTRA_JUMP_P2P) ) {
                    parseNormalIntent(intent);
                }
            }else{

            }

            if (!firstEnter && intent == null) {
                finish();
                overridePendingTransition(R.anim.activity_fade_anim,R.anim.activity_hold_anim);
            } else {
                showMainActivity(null);
            }
        }

    }

    private void parseNotifyIntent(Intent intent) {
        ArrayList<IMMessage> messages = (ArrayList<IMMessage>) intent.getSerializableExtra(NimIntent.EXTRA_NOTIFY_CONTENT);
        if (messages == null || messages.size() > 1) {
            showMainActivity();
        } else {
            showMainActivity(new Intent().putExtra(NimIntent.EXTRA_NOTIFY_CONTENT, messages.get(0)));
        }
    }
    private void parseNormalIntent(Intent intent) {
        showMainActivity(intent);
    }

    private void showMainActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        //这里不带Extra

        showMainActivity(intent);
    }

    private void showMainActivity(Intent  intent) {
        Intent intents = new Intent();
        intents.setClass(SpalshActivity.this, MainActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intents.putExtras(intents);
            startActivity(intents);
            finish();
            overridePendingTransition(R.anim.activity_fade_anim,R.anim.activity_hold_anim);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (firstEnter) {
            firstEnter = false;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (canAutoLogin()) {
                       onIntent();
                    } else {
                        Intent intent=new Intent(SpalshActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.activity_fade_anim,R.anim.activity_hold_anim);
                    }
                }
            };
            if (customSplash) {
                new Handler().postDelayed(runnable, 2000);
            } else {
                runnable.run();
            }
        }
    }
    /**
     * 已经登陆过，自动登陆
     */
    private boolean canAutoLogin() {
        String account = Preferences.getUserAccount();
        String token = Preferences.getUserToken();
        return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(token);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.clear();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
