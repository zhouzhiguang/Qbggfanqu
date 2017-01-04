package com.fanqu.personcentre.frgment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.main.widget.ExpendStepView;
import com.fanqu.personcentre.activities.TakePictureDemandActivity;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;


/**
 * 1.6875倍
 * 还未认证
 */

public class NoCertificationFragment extends BaseFragment implements View.OnClickListener {
    private Activity activity;
    private ExpendStepView certification_stepview;
    private SHSwipeRefreshLayout certification_shswipeRefreshLayout;
    private TextView certification_affirm_uploading, certification_check_take_picture_demand;
    private AppCompatEditText certification_real_name, certifcation_identity_card;//真实姓名 身份证号码
    private FrameLayout certification_add_identity_card_full_face_photo_layout;
    private FrameLayout certification_add_identity_reverse_side_photo_layout;//反面

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_no_certification_layout);
        activity = getActivity();
        certification_add_identity_reverse_side_photo_layout = findView(R.id.certification_add_identity_reverse_side_photo_layout);
        certification_add_identity_card_full_face_photo_layout = findView(R.id.certification_add_identity_card_full_face_photo_layout);
        certification_real_name = findView(R.id.certification_real_name);
        certifcation_identity_card = findView(R.id.certifcation_identity_card);
        certification_check_take_picture_demand = findView(R.id.certification_check_take_picture_demand);
        certification_stepview = findView(R.id.certification_stepview);
        certification_affirm_uploading = findView(R.id.certification_affirm_uploading);
        certification_shswipeRefreshLayout = findView(R.id.certification_shswipeRefreshLayout);
        certification_shswipeRefreshLayout.setLoadmoreEnable(false);
        certification_shswipeRefreshLayout.setRefreshEnable(false);
        certification_affirm_uploading.setSelected(false);
    }

    @Override
    protected void setListener() {
        certification_check_take_picture_demand.setOnClickListener(this);
        // certification_stepview.setCurrentStep(1);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.certification_check_take_picture_demand:
                Intent intent = new Intent(activity, TakePictureDemandActivity.class);
                NoCertificationFragment.this.startActivity(intent);
                activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                break;
            default:
                break;
        }
    }
}
