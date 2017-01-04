package com.fanqu.homepage.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.main.widget.SearchBarLayout;


/**
 * 搜索附近的厨房饭局
 */
public class SearchDinnerPartyActivity extends BaseActivity {

    private SearchBarLayout search_dinner_party_search_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_search_dinner_party_layout);
       // AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        initView();
        innitDate();
        initListener();
    }

    private void initListener() {
    }

    private void innitDate() {
    }

    private void initView() {
        search_dinner_party_search_layout = findView(R.id.search_dinner_party_search_layout);
        int color = ContextCompat.getColor(this, R.color.search_background_color);
        search_dinner_party_search_layout.setBackgroundColor(color);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
