package com.qbgg.cenglaicengqu.common.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.netease.nim.uikit.LoginSyncDataStatusObserver;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.permission.MPermission;
import com.netease.nim.uikit.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.permission.annotation.OnMPermissionGranted;
import com.netease.nimlib.sdk.NimIntent;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.qbgg.cenglaicengqu.BaseActivity;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.contact.activity.AddFriendActivity;
import com.qbgg.cenglaicengqu.login.LoginActivity;
import com.qbgg.cenglaicengqu.login.LogoutHelper;
import com.qbgg.cenglaicengqu.main.fragment.HomeFragment;
import com.qbgg.cenglaicengqu.main.model.Extras;
import com.qbgg.cenglaicengqu.session.SessionHelper;



public class MainActivity extends BaseActivity {
    private final int BASIC_PERMISSION_REQUEST_CODE = 100;

    private static final String EXTRA_APP_QUIT = "APP_QUIT";

    private HomeFragment mainFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window dialogWindow = this.getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        setTranslucentStatus(true);
        setContentView(R.layout.activity_main_tab);
        AutoUtils.auto(this);
        initthem();
        requestBasicPermission();

        onParseIntent();

        // 等待同步数据完成
        boolean syncCompleted = LoginSyncDataStatusObserver.getInstance().observeSyncDataCompletedEvent(new Observer<Void>() {
            @Override
            public void onEvent(Void v) {
                DialogMaker.dismissProgressDialog();
            }
        });

        if (!syncCompleted) {
            DialogMaker.showProgressDialog(MainActivity.this, getString(R.string.prepare_data)).setCanceledOnTouchOutside(false);
        }

        onInit();

    }

    private void onInit() {

        // 加载主页面
        showMainFragment();

        // 聊天室初始化
        //ChatRoomHelper.init();


    }

    private void showMainFragment() {
        if (mainFragment == null && !isDestroyedCompatible()) {
            mainFragment = new HomeFragment();
            switchFragmentContent(mainFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_menu_add_friends:
                AddFriendActivity.start(MainActivity.this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void onParseIntent() {

        Intent intent = getIntent();
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            IMMessage message = (IMMessage) getIntent().getSerializableExtra(NimIntent.EXTRA_NOTIFY_CONTENT);
            switch (message.getSessionType()) {
                case P2P:
                    SessionHelper.startP2PSession(this, message.getSessionId());
                    break;
                case Team:
                    SessionHelper.startTeamSession(this, message.getSessionId());
                    break;
                default:
                    break;
            }
        } else if (intent.hasExtra(EXTRA_APP_QUIT)) {
            onLogout();
            return;
        }
        //音视频界面
//        else if (intent.hasExtra(AVChatActivity.INTENT_ACTION_AVCHAT)) {
//            if (AVChatProfile.getInstance().isAVChatting()) {
//                Intent localIntent = new Intent();
//                localIntent.setClass(this, AVChatActivity.class);
//                startActivity(localIntent);
//            }
        // }
        else if (intent.hasExtra(com.qbgg.cenglaicengqu.main.model.Extras.EXTRA_JUMP_P2P)) {
            Intent data = intent.getParcelableExtra(Extras.EXTRA_DATA);
            String account = data.getStringExtra(Extras.EXTRA_ACCOUNT);
            if (!TextUtils.isEmpty(account)) {
                SessionHelper.startP2PSession(this, account);
            }
        }
    }

    /**
     * 请求权限
     */
    private void requestBasicPermission() {

        MPermission.with(MainActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
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

    // 注销
    private void onLogout() {
        // 清理缓存&注销监听
        LogoutHelper.logout();

        // 启动登录
        Intent intent = new Intent(this, LoginActivity.class);
        // KICK_OUT
        intent.putExtra("KICK_OUT", false);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
    }
    // 注销
    public static void logout(Context context, boolean quit) {
//        Intent intent = new Intent();
//        extra.putExtra(EXTRA_APP_QUIT, quit);
//        extra.setClass(context, MainActivity.class);
//        extra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        if (extra != null) {
//            extra.putExtras(extra);
//        }
//        context.startActivity(extra);
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }
    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
}
