package com.qbgg.cenglaicengqu.login;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.uikit.cache.DataCacheManager;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.ui.dialog.EasyAlertDialogHelper;
import com.netease.nim.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.netease.nim.uikit.permission.MPermission;
import com.netease.nim.uikit.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.permission.annotation.OnMPermissionGranted;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.ClientType;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.qbgg.cenglaicengqu.BaseActivity;
import com.qbgg.cenglaicengqu.DemoCache;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.common.activities.MainActivity;
import com.qbgg.cenglaicengqu.config.preference.Preferences;
import com.qbgg.cenglaicengqu.config.preference.UserPreferences;
import com.qbgg.cenglaicengqu.contact.ContactHttpClient;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, View.OnKeyListener {
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;
    private static final String KICK_OUT = "KICK_OUT";
    private TextView login_btn;
    private boolean registerMode = false; // 注册模式
    private AbortableFuture<LoginInfo> loginRequest;
    private ClearableEditTextWithIcon loginAccountEdit, register_AccountEdit, register_PasswordEdit;
    private ClearableEditTextWithIcon loginPasswordEdit, register_nickname;
    private TextView register_btn, register_login_tip, has_account_login;
    private LinearLayout loginLayout;
    private LinearLayout register_layout;
    private TextView login_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = false;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);

        init();
        requestBasicPermission();
        super.initthem();
        //super.initStatusbar(LoginActivity.this,R.color.white);
    }

    /**
     * 初始化
     */
    private void init() {

        has_account_login = findView(R.id.has_account_login);
        register_nickname = findView(R.id.edit_register_nickname);
        register_AccountEdit = findView(R.id.edit_register_account);
        register_PasswordEdit = findView(R.id.edit_register_password);
        register_login_tip = findView(R.id.register_login_tip);
        login_title = findView(R.id.login_layout_title);
        loginLayout = findView(R.id.login_layout);
        register_layout = findView(R.id.register_layout);
        login_btn = findView(R.id.login_btn);
        loginAccountEdit = findView(R.id.phone_edit_text);
        loginPasswordEdit = findView(R.id.password_edit_text);
        register_btn = findView(R.id.register_btn);
        loginAccountEdit.setIconResource(R.mipmap.user_account_icon);
        loginPasswordEdit.setIconResource(R.mipmap.user_pwd_lock_icon);

        loginAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        loginPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        loginAccountEdit.addTextChangedListener(textWatcher);
        loginPasswordEdit.addTextChangedListener(textWatcher);
        loginPasswordEdit.setOnKeyListener(this);

        register_AccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        register_nickname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        register_PasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

//        ToolBarOptions options = new ToolBarOptions();
//        options.isNeedNavigate = false;
//        setToolBar(R.id.toolbar, options);
//        requestBasicPermission();
//        onParseIntent();

        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        //已经有账号登录
        has_account_login.setOnClickListener(this);
        register_login_tip.setOnClickListener(this);
        setDeletedIcon(loginAccountEdit);
        setDeletedIcon(loginPasswordEdit);
    }

    private void onParseIntent() {
        if (getIntent().getBooleanExtra(KICK_OUT, false)) {
            int type = NIMClient.getService(AuthService.class).getKickedClientType();
            String client;
            switch (type) {
                case ClientType.Web:
                    client = "网页端";
                    break;
                case ClientType.Windows:
                    client = "电脑端";
                    break;
                case ClientType.REST:
                    client = "服务端";
                    break;
                default:
                    client = "移动端";
                    break;
            }
            EasyAlertDialogHelper.showOneButtonDiolag(LoginActivity.this, getString(R.string.kickout_notify),
                    String.format(getString(R.string.kickout_content), client), getString(R.string.ok), true, null);
        }
    }

    /**
     * 请求权限
     */
    private void requestBasicPermission() {
        MPermission.with(LoginActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login_btn();
                break;
            case R.id.register_btn:
                changestate(true);


                break;
            case R.id.has_account_login:
                //已经有账号登录了
                changestate(false);
                break;
            case R.id.register_login_tip:
                Toast.makeText(this, "客户端安全问题暂时不支持客户端直接注册", Toast.LENGTH_SHORT).show();

                // register();
                break;
            default:
                break;
        }
    }

    /**
     * 已经有账号了改变状态
     */
    private void changestate(boolean isregister) {
        if (isregister) {
            loginLayout.setVisibility(View.GONE);
            register_layout.setVisibility(View.VISIBLE);
            login_title.setText(R.string.register);
        } else {
            loginLayout.setVisibility(View.VISIBLE);
            register_layout.setVisibility(View.GONE);
            login_title.setText(R.string.login);
        }

    }

    /**
     * 注册代码
     */
    private void register() {

        if (!checkRegisterContentValid()) {
            return;
        }
        if (!NetworkUtil.isNetAvailable(LoginActivity.this)) {
            Toast.makeText(LoginActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        DialogMaker.showProgressDialog(this, getString(R.string.registering), false);
        // 注册流程
        final String account = register_AccountEdit.getText().toString();
        final String nickName = register_nickname.getText().toString();
        final String password = register_PasswordEdit.getText().toString();

        ContactHttpClient.getInstance().register(account, nickName, password, new ContactHttpClient.ContactHttpCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(LoginActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();

                loginAccountEdit.setText(account);
                loginPasswordEdit.setText(password);

                register_AccountEdit.setText("");
                register_nickname.setText("");
                register_PasswordEdit.setText("");

                DialogMaker.dismissProgressDialog();
            }

            @Override
            public void onFailed(int code, String errorMsg) {
                Toast.makeText(LoginActivity.this, getString(R.string.register_failed, code, errorMsg), Toast.LENGTH_SHORT)
                        .show();
                LogUtil.e(KICK_OUT, errorMsg);
                DialogMaker.dismissProgressDialog();
            }
        });
    }

    /**
     * 登录了
     */

    private void login_btn() {
        {
            //noinspection deprecation
            DialogMaker.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (loginRequest != null) {
                        loginRequest.abort();
                        onLoginDone();
                    }
                }
            }).setCanceledOnTouchOutside(false);

            // 云信只提供消息通道，并不包含用户资料逻辑。开发者需要在管理后台或通过服务器接口将用户帐号和token同步到云信服务器。
            // 在这里直接使用同步到云信服务器的帐号和token登录。
            // 这里为了简便起见，demo就直接使用了密码的md5作为token。
            // 如果开发者直接使用这个demo，只更改appkey，然后就登入自己的账户体系的话，需要传入同步到云信服务器的token，而不是用户密码。
            final String account = loginAccountEdit.getEditableText().toString().toLowerCase();
            //开始不加密登录的密码
            final String token = loginPasswordEdit.getEditableText().toString();// tokenFromPassword(loginPasswordEdit.getEditableText().toString());
            // 登录
            loginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo(account, token));
            loginRequest.setCallback(new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo param) {

                    onLoginDone();
                    DemoCache.setAccount(account.toLowerCase());
                    saveLoginInfo(account, token.toLowerCase());

                    // 初始化消息提醒
                    NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

                    // 初始化免打扰
                    if (UserPreferences.getStatusConfig() == null) {
                        UserPreferences.setStatusConfig(DemoCache.getNotificationConfig());
                    }
                    NIMClient.updateStatusBarNotificationConfig(UserPreferences.getStatusConfig());

                    // 构建缓存
                    DataCacheManager.buildDataCacheAsync();

                    // 进入主界面
                    //  MainActivity.start(LoginActivity.this, null);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (intent != null) {
                        intent.putExtras(intent);
                    }
                    intent.putExtras(intent);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    finish();
                }

                @Override
                public void onFailed(int code) {
                    onLoginDone();
                    if (code == 302 || code == 404) {
                        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onException(Throwable exception) {
                    Toast.makeText(LoginActivity.this, R.string.login_exception, Toast.LENGTH_LONG).show();
                    onLoginDone();
                }
            });
        }
    }

    private void onLoginDone() {
        loginRequest = null;
        DialogMaker.dismissProgressDialog();
    }

    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
    private String tokenFromPassword(String password) {
        String appKey = readAppKey(getApplicationContext());
        boolean isDemo = "45c6af3c98409b18a84451215d0bdd6e".equals(appKey)
                || "fe416640c8e8a72734219e1847ad2547".equals(appKey);

        return isDemo ? MD5.getStringMD5(password) : password;
    }

    private String readAppKey(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存到首选项
     *
     * @param account
     * @param token
     */
    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 更新右上角按钮状态
//            if (!registerMode) {
//                // 登录模式
//                boolean isEnable = loginAccountEdit.getText().length() > 0
//                        && loginPasswordEdit.getText().length() > 0;
//                updateRightTopBtn(LoginActivity.this, rightTopBtn, isEnable);
//            }
        }
    };

//    private void updateRightTopBtn(Context context, TextView rightTopBtn, boolean isEnable) {
//        rightTopBtn.setText(R.string.done);
//        rightTopBtn.setBackgroundResource(R.drawable.g_white_btn_selector);
//        rightTopBtn.setEnabled(isEnable);
//        rightTopBtn.setTextColor(context.getResources().getColor(R.color.color_blue_0888ff));
//        rightTopBtn.setPadding(ScreenUtil.dip2px(10), 0, ScreenUtil.dip2px(10), 0);
//    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    private boolean checkRegisterContentValid() {
        {
            // 帐号检查
            String account = register_AccountEdit.getText().toString().trim();
            if (account.length() <= 0 || account.length() > 20) {
                Toast.makeText(this, R.string.register_account_tip, Toast.LENGTH_SHORT).show();

                return false;
            }

            // 昵称检查
            String nick = register_nickname.getText().toString().trim();
            if (nick.length() <= 0 || nick.length() > 10) {
                Toast.makeText(this, R.string.register_nick_name_tip, Toast.LENGTH_SHORT).show();

                return false;
            }

            // 密码检查
            String password = register_PasswordEdit.getText().toString().trim();
            if (password.length() < 6 || password.length() > 20) {
                Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();

                return false;
            }

            return true;
        }
    }

    /**
     * 设置删除的icon
     */
    private void setDeletedIcon(ClearableEditTextWithIcon deletedIcon) {
        deletedIcon.setDeleteImage(R.mipmap.nim_grey_delete_icon);
    }
}
