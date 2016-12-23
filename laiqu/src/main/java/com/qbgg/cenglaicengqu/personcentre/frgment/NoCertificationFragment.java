package com.qbgg.cenglaicengqu.personcentre.frgment;

import android.app.Activity;
import android.os.Bundle;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.widget.ExpendStepView;

/**
 * 还未认证
 */

public class NoCertificationFragment extends BaseFragment {
    private Activity activity;
    private ExpendStepView certification_stepview;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_no_certification_layout);
        activity = getActivity();
        certification_stepview = findView(R.id.certification_stepview);
        LogUtil.e("测试方法", "initView");
    }

    @Override
    protected void setListener() {
        LogUtil.e("测试方法", "setListener");
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        LogUtil.e("测试方法", "processLogic");
    }

    @Override
    protected void onUserVisible() {
    }
}
