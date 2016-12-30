package com.qbgg.cenglaicengqu.personcentre.frgment;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;

/**
 * 认证失败了
 */
public class AuthenticationFailureFragment extends BaseFragment implements View.OnClickListener {

    private TextView authentication_failure_cause;//认证失败原因
    private TextView authenticating_name, certification_identity_card_number, certification_again_authentication;
    private String identity_card = "430000000000000000";
    private String name = "王大锤";
    private Activity activity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_authentication_failure_layout);
        authentication_failure_cause = findView(R.id.authentication_failure_cause);
        activity = getActivity();
        authenticating_name = findView(R.id.authenticating_name);
        certification_again_authentication = findView(R.id.certification_again_authentication);
        certification_identity_card_number = findView(R.id.certification_identity_card_number);
    }

    @Override
    protected void setListener() {
        // 用于显示的加*身份证
        String show_id = identity_card.substring(0, 4) + "*******" + identity_card.substring(14);
        certification_identity_card_number.setText(show_id);
        authenticating_name.setText(name.substring(1));
        certification_again_authentication.setOnClickListener(this);

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
            case R.id.certification_again_authentication:
                //重新认证
                break;
            default:

                break;
        }
    }
}
