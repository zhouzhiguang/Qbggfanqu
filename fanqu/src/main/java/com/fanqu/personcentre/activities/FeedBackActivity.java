package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;

import static com.fanqu.main.widget.AnFQNumEditText.calculateLength;


public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    private EditText feed_back_notice;
    private TextView feed_back_text, submit;
    private int MaxNum = 150;//最大字符
    private TextWatcher mTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);

        setContentView(R.layout.activity_feed_back_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
//        Toolbar toolbar = getToolBar();
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showCenterToast(AboutFanquActivity.this, "点击返回了哦");
//            }
//        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        feed_back_notice = findView(R.id.feed_back_notice);
        feed_back_text = findView(R.id.feed_back_text);
        submit = findView(R.id.submit);
    }

    private void initDate() {
        // 先去掉监听器，否则会出现栈溢出
// 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
// 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
// 当输入字符个数超过限制的大小时，进行截断操作
// 恢复监听器
        mTextWatcher = new TextWatcher() {
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = feed_back_notice.getSelectionStart();
                editEnd = feed_back_notice.getSelectionEnd();
                // 先去掉监听器，否则会出现栈溢出
                feed_back_notice.removeTextChangedListener(mTextWatcher);
                // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
                // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
                while (calculateLength(s.toString()) > MaxNum) { // 当输入字符个数超过限制的大小时，进行截断操作
                    s.delete(editStart - 1, editEnd);
                    editStart--;
                    editEnd--;
                }
                // 恢复监听器
                feed_back_notice.addTextChangedListener(mTextWatcher);
                setLeftCount();
            }
        };
    }

    /**
     * 刷新剩余输入字数
     */
    private void setLeftCount() {
        String person_edit_signature = feed_back_notice.getText().toString();
        if (!TextUtils.isEmpty(person_edit_signature)) {
            int inputcount = (int) calculateLength(person_edit_signature);

            feed_back_text.setText(String.valueOf(inputcount + "/" + MaxNum));

        } else {
            feed_back_text.setText(String.valueOf(0 + "/" + MaxNum));
        }

    }

    private void initListener() {
        feed_back_notice.addTextChangedListener(mTextWatcher);
        submit.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                String inputcontent = feed_back_notice.getText().toString();
                if (!TextUtils.isEmpty(inputcontent)) {
                    //提交了
                }
                break;
            default:
                break;
        }
    }
}
