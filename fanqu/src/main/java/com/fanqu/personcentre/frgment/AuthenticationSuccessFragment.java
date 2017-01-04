package com.fanqu.personcentre.frgment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;


/**
 * 认证成功
 */

public class AuthenticationSuccessFragment extends BaseFragment implements View.OnClickListener {
    private TextView authenticating_name, certification_identity_card_number, certification_finish;
    private String identity_card = "430000000000000000";
    private Activity activity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_authenticating_layout);
        activity = getActivity();
        authenticating_name = findView(R.id.authenticating_name);
        certification_finish = findView(R.id.certification_finish);
        certification_identity_card_number = findView(R.id.certification_identity_card_number);
    }

    @Override
    protected void setListener() {
        // 用于显示的加*身份证
        String show_id = identity_card.substring(0, 4) + "*******" + identity_card.substring(14);
        certification_identity_card_number.setText(show_id);
        certification_finish.setOnClickListener(this);
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
            case R.id.certification_finish:
                activity.finish();
                activity.overridePendingTransition(R.anim.activity_out, R.anim.activity_in);

                break;
            default:
                break;
        }
    }
}
