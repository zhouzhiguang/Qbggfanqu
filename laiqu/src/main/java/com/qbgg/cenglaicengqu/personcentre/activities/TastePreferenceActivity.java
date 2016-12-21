package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.main.util.ViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.qbgg.cenglaicengqu.R.layout.taste_preference_item_layout;

public class TastePreferenceActivity extends BaseActivity implements View.OnClickListener {
    private TagFlowLayout taste_preference_label, taste_preference_select_label;
    private TextView taste_preference_custom, taste_preference_confirm;
    private static final int TASTE_PREFERENCE_ADD_LABLE = 101;
    private static final int TASTE_PREFERENCE_REMOVE_LABLE = 102;

    /**
     * 选中标签
     */
    private List<String> lableSelected;
    private List<String> lableDate;//所有标签的集合
    private Handler handler = new Handler() {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TASTE_PREFERENCE_ADD_LABLE:
                    TagAdapter adapter = taste_preference_select_label.getAdapter();
                    adapter.notifyDataChanged();
                    break;
                case TASTE_PREFERENCE_REMOVE_LABLE:
                    TagAdapter TagAdapter = taste_preference_select_label.getAdapter();
                    TagAdapter.notifyDataChanged();
                    break;
                default:

                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_taste_preference_layout);
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
                ToastUtils.showCenterToast(TastePreferenceActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        taste_preference_label = findView(R.id.taste_preference_label);
        taste_preference_custom = findView(R.id.taste_preference_custom);
        taste_preference_select_label = findView(R.id.taste_preference_select_label);
        taste_preference_confirm = findView(R.id.taste_preference_confirm);
    }

    private void initDate() {
        lableSelected = new ArrayList<String>();
        lableDate = new ArrayList<>();
        String[] items = this.getResources().getStringArray(R.array.taste_preference_string_array);
        for (String item : items) {
            lableDate.add(item);

        }
        //  TastePreferenceLabeladapter adapte=new TastePreferenceLabeladapter(TastePreferenceActivity.this,mColorData);
        final LayoutInflater inflater = getLayoutInflater();

        taste_preference_label.setAdapter(new TagAdapter<String>(lableDate) {
            @Override
            public View getView(FlowLayout parent, int position, String string) {

                TextView textView = (TextView) inflater.inflate(taste_preference_item_layout, taste_preference_label, false);
                textView.setGravity(Gravity.CENTER);
                textView.setText(string);
                //设置默认选择的标签
                if (lableSelected != null && lableSelected.size() > 0) {
                    if (lableSelected.contains(string)) {
                        textView.setSelected(true);
                        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textGrayDeep));
                    }
                }
                return textView;
            }
        });
        // lableSelected.addAll(lableDate);
        taste_preference_select_label.setAdapter(new TagAdapter<String>(lableSelected) {
            @Override
            public View getView(FlowLayout parent, int position, String labe) {
                FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.taste_preference_select_item_layout, taste_preference_select_label, false);
                TextView textview = ViewHolder.get(layout, R.id.taste_preference_select_item_text);
                textview.setText(labe);
                return layout;
            }
        });
    }

    private void initListener() {
        taste_preference_confirm.setOnClickListener(this);
        taste_preference_custom.setOnClickListener(this);
        taste_preference_label.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });

        taste_preference_label.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {


            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (view instanceof TextView) {
                    if (lableSelected == null) {
                        lableSelected = new ArrayList<String>();
                    }
                    TextView textView = (TextView) view;
                    String lable = lableDate.get(position);
                    //设置选中效果
                    if (!lableSelected.contains(lable)) {
                        //选中
                        textView.setSelected(true);
                        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textGrayDeep));
                    } else {
                        //未选中

                        textView.setSelected(false);
                        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textGrayLight));
                    }
                    Message msg = new Message();
                    msg.obj = lable;
                    if (textView.isSelected()) {
                        //将选中的标签加入到lableSelected中 TastePreference
                        lableSelected.add(lable);
                        msg.what = TASTE_PREFERENCE_ADD_LABLE;

                    } else {
                        lableSelected.remove(lable);
                        msg.what = TASTE_PREFERENCE_REMOVE_LABLE;

                    }
                    handler.sendMessage(msg);
                }
                return false;
            }
        });
        taste_preference_select_label.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (view instanceof FrameLayout) {
                    TextView text = (TextView) view.findViewById(R.id.taste_preference_select_item_text);
                    String string = text.getText().toString();
                    if (!TextUtils.isEmpty(string)) {
                        lableSelected.remove(string);
                        LogUtil.e("测试点击下面删除的", string);
                        Message msg = new Message();
                        msg.what = TASTE_PREFERENCE_REMOVE_LABLE;
                        handler.sendMessage(msg);
                        taste_preference_label.getAdapter().notifyDataChanged();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.taste_preference_custom:
                if (lableSelected != null) {
                    LogUtil.e("选择哦了多少啊", lableSelected.toString());
                }
                //自定义添加标签了
                break;
            case R.id.taste_preference_confirm:
                //确定我的口味偏好
                LogUtil.e("测试选择完成", lableSelected.toString());
                 finish();
                overridePendingTransition(R.anim.activity_out,R.anim.activity_in);
                break;
            default:

                break;
        }
    }
}
