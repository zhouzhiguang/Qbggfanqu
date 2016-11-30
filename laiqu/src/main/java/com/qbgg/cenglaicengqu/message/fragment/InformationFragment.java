package com.qbgg.cenglaicengqu.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends BaseFragment {


    private  boolean isPrepared;
    private View view;

    public InformationFragment() {
        // Required empty public constructor
//        setContainerId(R.layout.fragment_information_layout);
//        isPrepared = true;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_information_layout);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }





}
