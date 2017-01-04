package com.fanqu.personcentre.frgment;

import android.os.Bundle;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;


/**
 * 认证中
 */

public class AuthenticatingFragment extends BaseFragment {
    private TextView authenticating_name, certification_identity_card_number;
    private String identity_card="430000000000000000";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_authenticating_layout);
        authenticating_name = findView(R.id.authenticating_name);
        certification_identity_card_number = findView(R.id.certification_identity_card_number);
    }

    @Override
    protected void setListener() {
        // 用于显示的加*身份证
        String show_id = identity_card.substring(0, 4) + "*******" + identity_card.substring(14);
        certification_identity_card_number.setText(show_id);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }

    /**
     * 验证身份证号码正确性
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}[Xx]";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
}
