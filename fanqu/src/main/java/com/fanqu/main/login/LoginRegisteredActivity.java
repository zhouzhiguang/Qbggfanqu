package com.fanqu.main.login;

/**
 * 登录注册界面
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.main.frgment.LoginFragment;
import com.fanqu.main.frgment.RegisteredFragment;
import com.fanqu.main.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;


public class LoginRegisteredActivity extends BaseActivity {
    private FrameLayout login_container;
    private ViewPagerIndicator mIndicator;


    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas;

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
        initview();
    }

    private void initview() {

        mViewPager = (ViewPager) findViewById(R.id.id_vp);

        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, new LoginFragment()).commit();
        initDatas();
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(getString(R.string.login));
        mDatas.add(getString(R.string.register_account));
        LoginFragment fragment = new LoginFragment();
        mTabContents.add(fragment);
        RegisteredFragment fragment1 = new RegisteredFragment();
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


}
