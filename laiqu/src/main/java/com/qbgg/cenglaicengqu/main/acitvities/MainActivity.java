package com.qbgg.cenglaicengqu.main.acitvities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.update.UpdateHelper;
import com.dou361.update.type.UpdateType;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.addconference.fragment.PublishFragment;
import com.qbgg.cenglaicengqu.dinner.fragment.DinnerFragment;
import com.qbgg.cenglaicengqu.homepage.fragment.HomeFragment;
import com.qbgg.cenglaicengqu.main.managestatus.SystemBarTintManager;
import com.qbgg.cenglaicengqu.main.widget.MainNavigateTabBar;
import com.qbgg.cenglaicengqu.message.fragment.InformationFragment;
import com.qbgg.cenglaicengqu.personcentre.frgment.PersonFragment;

/**
 * 主页加载太慢了我先添加
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String TAG_PAGE_HOME = "首页";
    private String TAG_PAGE_DINNER = "饭局";
    private String TAG_PAGE_PUBLISH = "发布";
    private String TAG_PAGE_MESSAGE = "消息";
    private String TAG_PAGE_PERSON = "我的";


    private MainNavigateTabBar mNavigateTabBar;
    private ImageView adddinner;
    //状态栏沉浸模式使用
    /*statusbar view*/
    private ViewGroup view;
    /*沉浸颜色*/
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_layout);
        // StatusBarUtil.setColorNoTranslucent(MainActivity.this,R.color.red_bg);
        mNavigateTabBar = (MainNavigateTabBar) findViewById(R.id.main_navigate_TabBar);
        adddinner = (ImageView) findViewById(R.id.tab_post_icon);
        adddinner.setOnClickListener(this);
        if (savedInstanceState != null) {
            mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        }
        initdate();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //默认是自动检测更新
        UpdateHelper.getInstance()
                .setUpdateType(UpdateType.autoupdate)
                .check(MainActivity.this);

//        String url = "http://api.fir.im/apps/latest/" + "57d3a808ca87a87e01000834"
//                + "?api_token=" + "8e1bb6d08a1dda6bb1f4f196fe5e8e35";
//        LogUtil.e("地址",url);
    }

    /**
     * 初始化显示的内容
     */
    private void initdate() {

        mNavigateTabBar.addTab(new HomeFragment(), new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_home, R.mipmap.comui_tab_home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(new DinnerFragment(), new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_dinner, R.mipmap.comui_tab_dinner_select, TAG_PAGE_DINNER));
        mNavigateTabBar.addTab(new PublishFragment(), new MainNavigateTabBar.TabParam(0, 0, TAG_PAGE_PUBLISH));
        mNavigateTabBar.addTab(new InformationFragment(), new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_message, R.mipmap.comui_tab_message_selected, TAG_PAGE_MESSAGE));
        mNavigateTabBar.addTab(new PersonFragment(), new MainNavigateTabBar.TabParam(R.mipmap.comui_tab_person, R.mipmap.comui_tab_person_selected, TAG_PAGE_PERSON));

        mNavigateTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(MainNavigateTabBar.ViewHolder holder) {
                int tabIndex = holder.tabIndex;
                if (tabIndex == 4) {
                    //设置颜色
                    //Color.parseColor("#D81D24");
                    //StatusBarUtil.setTransparent(MainActivity.this);
                    // StatusBarUtil.setColor(MainActivity.this, Color.BLACK);
                    //initStatusbar(MainActivity.this,R.color.red_bg);
                    initthem(R.color.red_bg);
                   // initStatusbar(MainActivity.this, R.color.red_bg);
                } else if (tabIndex == 0) {
                    //透明的状态栏了
                    //  initthem(R.color.black);
                   // initStatusbar(MainActivity.this, R.color.transparent);
                    initthem(R.color.black);

                    // StatusBarUtil.setTransparent(MainActivity.this);
                    // StatusBarUtil.setColor(MainActivity.this,R.color.white);
                } else {
                    //透明的状态栏了
                    // StatusBarUtil.setTransparent(MainActivity.this);
                    initthem(R.color.white);
                }
            }
        });
        //默认第一个首页显示
        mNavigateTabBar.showTabFragment(0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_post_icon:
                Toast.makeText(this, "改显示添加发布饭局了", Toast.LENGTH_SHORT).show();
                mNavigateTabBar.showTabFragment(2);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }


    /**
     * 沉浸模式状态栏初始化
     *
     * @param context       上下文
     * @param
     * @return沉浸颜色
     */
    @SuppressLint("NewApi")
    public void initStatusbar(Context context, int statusbar_bg) {
        //4.4版本及以上可用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏沉浸效果
            Window window = ((Activity) context).getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //decorview实际上就是activity的外层容器，是一层framlayout
            view = (ViewGroup) ((Activity) context).getWindow().getDecorView();
            LayoutParams lParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT, getStatusBarHeight());
            //textview是实际添加的状态栏view，颜色可以设置成纯色，也可以加上shape，添加gradient属性设置渐变色
            textView = new TextView(this);
            textView.setBackgroundResource(statusbar_bg);
            textView.setLayoutParams(lParams);
            view.addView(textView);
        }
    }

    /**
     * 初始化沉浸式狀態欄
     */
    protected void initthem(int color) {
        // TODO Auto-generated method stub
        /**
         * 沉浸式状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
        //tintManager.setStatusBarTintResource(R.color.white);
        tintManager.setStatusBarTintResource(color);
        // tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        tintManager.setStatusBarDarkMode(false, this);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, this);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
}
