package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.CommentAdapter;
import com.fanqu.dinner.modle.CommentEntity;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MoreCommentActivity extends BaseActivity {
    private SHSwipeRefreshLayout swiperefreshLayout;
    private RecyclerView recyclerview;
    private List<CommentEntity> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_more_comment_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        initView();
        initDate();
        initListener();
        // StatusBarUtil.setColor(MoreCommentActivity.this, Color.WHITE);
    }

    private void initView() {
        recyclerview = findView(R.id.comment_recyclerview);
        swiperefreshLayout = findView(R.id.swiperefreshLayout);
        swiperefreshLayout.setRefreshEnable(false);
        swiperefreshLayout.setLoadmoreEnable(false);

    }


    private void initDate() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        datas = new ArrayList<>();
        initDatas(datas);
        CommentAdapter adapter = new CommentAdapter(this, R.layout.comment_list_item_layout, datas);
        recyclerview.setAdapter(adapter);
    }

    private void initDatas(List<CommentEntity> datas) {
        for (int i = 0; i < 10; i++) {
            CommentEntity entity = new CommentEntity();
            datas.add(entity);
        }
    }

    private void initListener() {


    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
