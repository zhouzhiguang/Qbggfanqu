package com.qbgg.cenglaicengqu.addconference.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.addconference.activitiies.EditPublisAddActivity;
import com.qbgg.cenglaicengqu.main.acitvities.MainActivity;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;
import com.qbgg.cenglaicengqu.main.widget.AnFQNumEditText;
import com.qbgg.cenglaicengqu.main.widget.SwitchButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment extends BaseFragment implements View.OnClickListener{


    private View view;
    private AnFQNumEditText publish_edit_content;
    //import 输入最大字个数才能发表
    private final static int INPORT_MAX_LENGTH = 100;

    //import 输入最小字个数才能发表
    private final static int INPORT_MIN_LENGTH = 10;
    private MainActivity activity;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private RelativeLayout publishHeadLayout;
    private ImageView publishBacktrack;
    private AnFQNumEditText publishEditContent;
    private View publishEditLine;
    private LinearLayout publishAddImageLayout;
    private ImageView publishAddImage;
    private ImageView publishAddImageFirst;
    private ImageView publishAddImageTwo;
    private ImageView publishAddImageThird;
    private LinearLayout publishAddLinearlayout;
    private SwitchButton publishAddInvitePasswordSwitchButton;
    private EditText publishAddInvitePassword;
    private TextView publishAddNextstep;


    public PublishFragment() {
        // Required empty public constructor

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_publish_layout);
        activity = (MainActivity) getActivity();
        publishHeadLayout = findView(R.id.publish_head_layout);
        publishBacktrack = findView(R.id.publish_backtrack);
        publishEditContent = findView(R.id.publish_edit_content);
        publishEditLine = findView(R.id.publish_edit_line);
        publishAddImageLayout = findView(R.id.publish_add_image_layout);
        publishAddImage = findView(R.id.publish_add_image);
        publishAddImageFirst = findView(R.id.publish_add_image_first);
        publishAddImageTwo = findView(R.id.publish_add_image_two);
        publishAddImageThird = findView(R.id.publish_add_image_third);
        publishAddLinearlayout = findView(R.id.publish_add_linearlayout);
        publishAddInvitePasswordSwitchButton = findView(R.id.publish_add_invite_password_switch_button);
        publishAddInvitePassword = findView(R.id.publish_add_invite_password);
        publishAddNextstep = findView(R.id.publish_add_nextstep);
        publish_edit_content = findView(R.id.publish_edit_content);
        publish_edit_content.setType(AnFQNumEditText.PERCENTAGE);
        publish_edit_content.setLength(INPORT_MAX_LENGTH);
        publish_edit_content.setminLength(INPORT_MIN_LENGTH);
        publish_edit_content.show();
        //获取输入的内容
        String input = publish_edit_content.getInputText();


    }

    @Override
    protected void setListener() {
        publishAddNextstep.setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }


    @Override
    public void onStart() {
        super.onStart();
        isPrepared = true;
    }

//    @Override
//    protected void onInit() {
//
//    }
//        @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//            View view = getFragmentView(inflater, container, savedInstanceState);
//            AutoUtils.auto(view);
//        return view;
//    }


    private View getFragmentView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(
                    R.layout.fragment_publish_layout,
                    container, false);
        } else {
            /**
             * 为了保险起见 注意：onCreateView中返回的视图是要添加到viewTree中去的，
             * 而rootview在上次已经添加到里面去了。(onDestroyview的时候又移除掉了，有可能会有坑)
             * 一个视图实例不能被add多次，不然就会报错。 所以，我们针对这情况，要县从viewTree里面移除掉该view already
             * has a parent
             */
            ((ViewGroup) view.getParent()).removeView(view);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.publish_add_nextstep:
                Intent intent=new Intent(activity,EditPublisAddActivity.class);
                PublishFragment.this.startActivity(intent);
                activity.overridePendingTransition(R.anim.activity_out,R.anim.activity_in);
            break;
         default:
             break;
        }
    }
}
