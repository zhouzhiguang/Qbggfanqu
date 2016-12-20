package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

/**
 * 个性签名
 */
public class PersonalizedSignatureActivity extends BaseActivity implements View.OnClickListener {
    private TextView personalized_signature_submit;
    private EditText fragment_person_edit_signature;
    private TextView fragment_person_edit_signature_text;
    private int MaxNum = 15;//最大字符

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_personalized_signature_layout);
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
                ToastUtils.showCenterToast(PersonalizedSignatureActivity.this, "点击返回了哦");
                finish();
                overridePendingTransition(R.anim.activity_out,R.anim.activity_in);
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        personalized_signature_submit = findView(R.id.personalized_signature_submit);
        fragment_person_edit_signature = findView(R.id.fragment_person_edit_signature);
        fragment_person_edit_signature_text = findView(R.id.fragment_person_edit_signature_text);

    }

    private void initDate() {

    }

    private void initListener() {
        personalized_signature_submit.setOnClickListener(this);
        fragment_person_edit_signature.addTextChangedListener(mTextWatcher);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personalized_signature_submit:
                //提交我的个性签名
                String signature = fragment_person_edit_signature.getText().toString();
                if (!TextUtils.isEmpty(signature)){

                }
                break;
            default:
                break;
        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        private int editStart;
        private int editEnd;

        public void afterTextChanged(Editable s) {
            editStart = fragment_person_edit_signature.getSelectionStart();
            editEnd = fragment_person_edit_signature.getSelectionEnd();
            // 先去掉监听器，否则会出现栈溢出
            fragment_person_edit_signature.removeTextChangedListener(mTextWatcher);
            // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(s.toString()) > MaxNum) { // 当输入字符个数超过限制的大小时，进行截断操作
                s.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
            }
            // 恢复监听器
            fragment_person_edit_signature.addTextChangedListener(mTextWatcher);
            setLeftCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };

    /**
     * 刷新剩余输入字数
     */
    private void setLeftCount() {
        String person_edit_signature = fragment_person_edit_signature.getText().toString();
        if (!TextUtils.isEmpty(person_edit_signature)) {
            int inputcount = (int) calculateLength(person_edit_signature);

            fragment_person_edit_signature_text.setText(String.valueOf(inputcount + "/" + MaxNum));

        } else {
            fragment_person_edit_signature_text.setText(String.valueOf(0 + "/" + MaxNum));
        }

    }

    /**
     * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点
     * 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
     *
     * @param cs
     * @return
     */
    public long calculateLength(CharSequence cs) {
        double len = 0;
        for (int i = 0; i < cs.length(); i++) {
            int tmp = (int) cs.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 1;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }
}
