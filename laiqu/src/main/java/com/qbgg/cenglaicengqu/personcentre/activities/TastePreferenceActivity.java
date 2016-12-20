package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class TastePreferenceActivity extends BaseActivity {
    private MultiLineChooseLayout taste_preference_flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_taste_preference_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showCenterToast(TastePreferenceActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        taste_preference_flowLayout = findView(R.id.taste_preference_flowLayout);
    }

    private void initDate() {
        List<String> mColorData = new ArrayList<>();
        String[] items = this.getResources().getStringArray(R.array.taste_preference_string_array);
        for (String item : items) {
            mColorData.add(item);

        }


        //设置数据源
        taste_preference_flowLayout.setList(mColorData);


//取消选中项
        //    singleChoose.cancelAllSelectedItems();

    }

    private void initListener() {
        //单选
        taste_preference_flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int position, String text) {
//                singleChooseTv.setText("结果：position: " + position + "   " + text);
                LogUtil.e("测试", position + "位置是多少");
                ToastUtils.showCenterToast(TastePreferenceActivity.this, "结果：position: " + position + "   " + text);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
