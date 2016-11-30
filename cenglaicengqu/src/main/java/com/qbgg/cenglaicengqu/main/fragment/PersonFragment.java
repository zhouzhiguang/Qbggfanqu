package com.qbgg.cenglaicengqu.main.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.session.audio.MessageAudioControl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.lucene.LuceneService;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.settings.SettingsService;
import com.qbgg.cenglaicengqu.DemoCache;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.common.activities.MainActivity;
import com.qbgg.cenglaicengqu.config.preference.Preferences;
import com.qbgg.cenglaicengqu.config.preference.UserPreferences;
import com.qbgg.cenglaicengqu.contact.activity.UserProfileSettingActivity;
import com.qbgg.cenglaicengqu.login.LoginActivity;
import com.qbgg.cenglaicengqu.main.activity.NoDisturbActivity;
import com.qbgg.cenglaicengqu.main.adapter.SettingsAdapter;
import com.qbgg.cenglaicengqu.main.model.SettingTemplate;
import com.qbgg.cenglaicengqu.main.model.SettingType;

import java.util.ArrayList;
import java.util.List;

import static com.qbgg.cenglaicengqu.config.preference.UserPreferences.setNotificationToggle;


/**
 * 个人主界面
 */
public class PersonFragment extends TFragment implements SettingsAdapter.SwitchChangeListener {

    private static final int TAG_HEAD = 1;
    private static final int TAG_NOTICE = 2;
    private static final int TAG_NO_DISTURBE = 3;
    private static final int TAG_CLEAR = 4;
    private static final int TAG_CUSTOM_NOTIFY = 5;
    private static final int TAG_ABOUT = 6;
    private static final int TAG_SPEAKER = 7;

    private static final int TAG_NRTC_SETTINGS = 8;

    private static final int TAG_MSG_IGNORE = 10;
    private static final int TAG_RING = 11;
    private static final int TAG_LED = 12;
    private static final int TAG_NOTICE_CONTENT = 13; // 通知栏提醒配置
    private static final int TAG_CLEAR_INDEX = 18; // 清空全文检索缓存
    private static final int TAG_MULTIPORT_PUSH = 19; // 桌面端登录，是否推送

    ListView listView;
    SettingsAdapter adapter;
    private List<SettingTemplate> items = new ArrayList<SettingTemplate>();
    private String noDisturbTime;
    private SettingTemplate disturbItem;
    private SettingTemplate clearIndexItem;
    private Activity activity;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        // android2.3以下版本 布局混乱的问题
        if (Build.VERSION.SDK_INT <= 10) {
            adapter = null;
            initAdapter();
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化UI、
     */
    private void initUI() {
        initItems();
        listView = (ListView) view.findViewById(R.id.settings_listview);
        View footer = LayoutInflater.from(activity).inflate(R.layout.person_logout_footer, null);
        AutoUtils.auto(footer);
        listView.addFooterView(footer);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SettingTemplate item = items.get(position);
                onListItemClick(item);
            }
        });
        View logoutBtn = footer.findViewById(R.id.settings_button_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    //初始化item
    private void initItems() {
        items.clear();
        //编辑个人资料
        items.add(new SettingTemplate(TAG_HEAD, SettingType.TYPE_HEAD));
        items.add(new SettingTemplate(TAG_NOTICE, getString(R.string.msg_notice), SettingType.TYPE_TOGGLE,
                UserPreferences.getNotificationToggle()));
        items.add(SettingTemplate.addLine());
        items.add(new SettingTemplate(TAG_SPEAKER, getString(R.string.msg_speaker), SettingType.TYPE_TOGGLE,
                com.netease.nim.uikit.UserPreferences.isEarPhoneModeEnable()));
        items.add(SettingTemplate.makeSeperator());
        items.add(new SettingTemplate(TAG_RING, getString(R.string.ring), SettingType.TYPE_TOGGLE,
                UserPreferences.getRingToggle()));
        items.add(new SettingTemplate(TAG_LED, getString(R.string.led), SettingType.TYPE_TOGGLE,
                UserPreferences.getLedToggle()));
        items.add(SettingTemplate.makeSeperator());

        items.add(new SettingTemplate(TAG_NOTICE_CONTENT, getString(R.string.notice_content), SettingType.TYPE_TOGGLE,
                UserPreferences.getNoticeContentToggle()));
        items.add(SettingTemplate.makeSeperator());

        disturbItem = new SettingTemplate(TAG_NO_DISTURBE, getString(R.string.no_disturb), noDisturbTime);
        items.add(disturbItem);
        items.add(SettingTemplate.addLine());
        items.add(new SettingTemplate(TAG_MULTIPORT_PUSH, getString(R.string.multiport_push), SettingType.TYPE_TOGGLE,
                !NIMClient.getService(SettingsService.class).isMultiportPushOpen()));
        items.add(SettingTemplate.makeSeperator());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            //网络通话设置
//            items.add(new SettingTemplate(TAG_NRTC_SETTINGS, getString(R.string.nrtc_settings)));
//            items.add(SettingTemplate.makeSeperator());
//        }

        items.add(new SettingTemplate(TAG_MSG_IGNORE, "过滤通知",
                SettingType.TYPE_TOGGLE, UserPreferences.getMsgIgnore()));
        items.add(SettingTemplate.addLine());
        items.add(new SettingTemplate(TAG_CLEAR, getString(R.string.about_clear_msg_history)));
        items.add(SettingTemplate.addLine());

        clearIndexItem = new SettingTemplate(TAG_CLEAR_INDEX, getString(R.string.clear_index), getIndexCacheSize() + " M");
        items.add(clearIndexItem);
        items.add(SettingTemplate.addLine());
        //自定义通知
        items.add(new SettingTemplate(TAG_CUSTOM_NOTIFY, getString(R.string.custom_notification)));
        items.add(SettingTemplate.addLine());
        items.add(SettingTemplate.addLine());
        items.add(new SettingTemplate(TAG_ABOUT, getString(R.string.setting_about)));
    }


    private String getIndexCacheSize() {
        long size = NIMClient.getService(LuceneService.class).getCacheSize();
        return String.format("%.2f", size / (1024.0f * 1024.0f));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (UserPreferences.getStatusConfig() == null || !UserPreferences.getStatusConfig().downTimeToggle) {
            noDisturbTime = getString(R.string.setting_close);
        } else {
            noDisturbTime = String.format("%s到%s", UserPreferences.getStatusConfig().downTimeBegin,
                    UserPreferences.getStatusConfig().downTimeEnd);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        view = inflater.inflate(R.layout.person_fragment_layout, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initUI();
        initAdapter();
    }

    /**
     * 填充数据了这也是懒加载
     */
    public void onCurrent() {
        // initAdapter();
    }

    private void initAdapter() {
        adapter = new SettingsAdapter(activity, this, items);
        listView.setAdapter(adapter);
    }

    @Override
    public void onSwitchChange(SettingTemplate item, boolean checkState) {

        switch (item.getId()) {
            case TAG_NOTICE:
                try {
                    setNotificationToggle(checkState);
                    //通知栏
                    NIMClient.toggleNotification(checkState);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TAG_SPEAKER:
                //听筒模式
                com.netease.nim.uikit.UserPreferences.setEarPhoneModeEnable(checkState);
                MessageAudioControl.getInstance(activity).setEarPhoneModeEnable(checkState);
                break;
            case TAG_MSG_IGNORE:
                //
                UserPreferences.setMsgIgnore(checkState);
                break;
            case TAG_RING:
                //响铃设置
                UserPreferences.setRingToggle(checkState);
                StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
                config.ring = checkState;
                UserPreferences.setStatusConfig(config);
                NIMClient.updateStatusBarNotificationConfig(config);
                break;
            case TAG_LED:
                //呼吸灯
                UserPreferences.setLedToggle(checkState);
                StatusBarNotificationConfig config1 = UserPreferences.getStatusConfig();
                if (checkState) {
                    config1.ledARGB = Color.GREEN;
                    config1.ledOnMs = 1000;
                    config1.ledOffMs = 1500;
                } else {
                    config1.ledARGB = -1;
                    config1.ledOnMs = -1;
                    config1.ledOffMs = -1;
                }
                UserPreferences.setStatusConfig(config1);
                NIMClient.updateStatusBarNotificationConfig(config1);
                break;
            case TAG_NOTICE_CONTENT:
                //通知栏
                UserPreferences.setNoticeContentToggle(checkState);
                StatusBarNotificationConfig config2 = UserPreferences.getStatusConfig();
                config2.titleOnlyShowAppName = checkState;
                UserPreferences.setStatusConfig(config2);
                NIMClient.updateStatusBarNotificationConfig(config2);
                break;
            case TAG_MULTIPORT_PUSH:
                updateMultiportPushConfig(!checkState);
                break;
            default:
                break;
        }
        item.setChecked(checkState);
    }

    /**
     * @param checkState 设置pc web 在线推送
     */
    private void updateMultiportPushConfig(final boolean checkState) {
        NIMClient.getService(SettingsService.class).updateMultiportPushConfig(checkState).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                Toast.makeText(activity, "设置成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(activity, "设置失败,code:" + code, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onException(Throwable exception) {

            }
        });
    }

    /**
     * @param item条目点击事件
     */
    private void onListItemClick(SettingTemplate item) {
        if (item == null) return;

        switch (item.getId()) {
            case TAG_HEAD:
                UserProfileSettingActivity.start(activity, DemoCache.getAccount());
                break;
            case TAG_NO_DISTURBE:
                startNoDisturb();
                break;
            case TAG_CUSTOM_NOTIFY:
                //自定义消息发送
                //CustomNotificationActivity.start(SettingsActivity.this);
                break;
            case TAG_ABOUT:
                //关于
                //startActivity(new Intent(activity, AboutActivity.class));
                break;
            case TAG_CLEAR:
                NIMClient.getService(MsgService.class).clearMsgDatabase(true);
                Toast.makeText(activity, R.string.clear_msg_history_success, Toast.LENGTH_SHORT).show();
                break;
            case TAG_CLEAR_INDEX:
                clearIndex();
                break;
            case TAG_NRTC_SETTINGS:
                //网络通话设置
                //startActivity(new Intent(activity, AVChatSettingsActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 设置免打扰模式
     */
    private void startNoDisturb() {
        NoDisturbActivity.startActivityForResult(activity, UserPreferences.getStatusConfig(), noDisturbTime, NoDisturbActivity.NO_DISTURB_REQ);
    }

    private void clearIndex() {
        NIMClient.getService(LuceneService.class).clearCache();
        clearIndexItem.setDetail("0.00 M");
        adapter.notifyDataSetChanged();
    }

    /**
     * 注销
     */
    private void logout() {
        removeLoginState();
        Intent intent=new Intent(activity, LoginActivity.class);
        MainActivity.logout(activity, false);
        NIMClient.getService(AuthService.class).logout();
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_out,R.anim.activity_in);
    }

    private void removeLoginState() {
        Preferences.saveUserToken("");
        DemoCache.setAccount("");
    }
}
