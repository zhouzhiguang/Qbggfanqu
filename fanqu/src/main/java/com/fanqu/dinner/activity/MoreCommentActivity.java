package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.fanqu.R;
import com.fanqu.dinner.adapter.CommentAdapter;
import com.fanqu.dinner.modle.CommentEntity;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ViewHolder;
import com.fanqu.framework.model.ToolBarOptions;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多评论
 */
public class MoreCommentActivity extends BaseActivity {
    private SHSwipeRefreshLayout swiperefreshLayout;
    private RecyclerView recyclerview;
    private List<CommentEntity> datas;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
      //  AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_more_comment_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        inflater = getLayoutInflater();
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
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        datas = new ArrayList<>();
        initDatas(datas);
        CommentAdapter adapter = new CommentAdapter(this, R.layout.comment_list_item_layout, datas);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View layout = inflater.inflate(R.layout.more_comment_recylerview_footview_layout, null);
        LinearLayout footview_main_layout = ViewHolder.get(layout, R.id.footview_main_layout);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) footview_main_layout.getLayoutParams();
        params.width = CustomApplication.SCREEN_WIDTH;
        footview_main_layout.setLayoutParams(params);
        AutoUtils.auto(layout);
        mHeaderAndFooterWrapper.addFootView(layout);
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        recyclerview.setAdapter(mLoadMoreWrapper);
//        HorizontalScrollView horizontalScrollView;
//        ListView listView;
//        listView.setpo;
//
//        horizontalScrollView.s
//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ToastUtils.showCenterToast(MoreCommentActivity.this,"测试---");
////                manager.scrollToPositionWithOffset(6,0);
//                recyclerview.scrollToPosition(16);
//                manager.scrollToPositionWithOffset(16, 0);
//
//            }
//        }, 3000);


    }

    private void initDatas(List<CommentEntity> datas) {
        for (int i = 0; i < 30; i++) {
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
