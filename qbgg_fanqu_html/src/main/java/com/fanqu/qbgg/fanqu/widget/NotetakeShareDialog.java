package com.fanqu.qbgg.fanqu.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanqu.R;
import com.fanqu.qbgg.fanqu.framework.LogUtil;
import com.fanqu.qbgg.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.qbgg.fanqu.framework.utills.NetworkUtils;
import com.fanqu.qbgg.fanqu.sharebean.ShareBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import com.fanqu.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class NotetakeShareDialog extends Dialog {

    private Context context;

    private ClickListenerInterface clickListenerInterface;
    private String imagepath;
    private boolean istoast;
    private ShareBean bean;

    public interface ClickListenerInterface {
        public void doConfirmsave();// 确定保存

        public void doCancel();// 返回

        public void doclear();// 清楚所有

        public void donotetakelaststep();// 上一步
    }

    public NotetakeShareDialog(Context context) {
        super(context);
        this.context = context;

    }

    public NotetakeShareDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void setTextClicklistener(
            ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    @SuppressLint("NewApi")
    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        Window dialogWindow = this.getWindow();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);// 这个很重要
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setWindowAnimations(R.style.tipsAnimation); // 设置窗口弹出动画
        View view = inflater.inflate(R.layout.share_dialog_from_html_layout,
                null);
        //AutoUtils.auto(view);
        lp.height = (int) context.getResources().getDimension(
                R.dimen.dimen_330dp);// AutoUtils.getDisplayHeightValue(R.dimen.dimen_198dp);
        // int sCREEN_WIDTH = CustomApplication.SCREEN_WIDTH;
        // System.out.println(sCREEN_WIDTH + "寬是：");
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int widthPixels = display.widthPixels;
        lp.width = widthPixels;
        dialogWindow.setAttributes(lp);
        // dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        // WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setCanceledOnTouchOutside(false);
        AutoUtils.auto(view);
        setContentView(view);

        Button note_taking_sharedialog_cancel_button = (Button) view
                .findViewById(R.id.note_taking_sharedialog_cancel_button);
//		TextView share_from_title=(TextView) view.findViewById(R.id.share_from_title);
//		AutoUtils.autoTextSize(share_from_title);
        TextView note_taking_dialogwindow_share_weixing = (TextView) view
                .findViewById(R.id.note_taking_dialogwindow_share_weixing);
        //AutoUtils.autoTextSize(note_taking_dialogwindow_share_weixing);
        TextView note_taking_dialogwindow_share_moments = (TextView) view
                .findViewById(R.id.note_taking_dialogwindow_share_moments);
        //AutoUtils.autoTextSize(note_taking_dialogwindow_share_moments);
        TextView note_taking_dialogwindow_share_qq_friends = (TextView) view
                .findViewById(R.id.note_taking_dialogwindow_share_qq_friends);
        //AutoUtils.autoTextSize(note_taking_dialogwindow_share_qq_friends);
        TextView note_taking_dialogwindow_share_sina = (TextView) view
                .findViewById(R.id.note_taking_dialogwindow_share_sina);
        //AutoUtils.autoTextSize(note_taking_dialogwindow_share_sina);
        TextView note_taking_dialogwindow_share_zone = (TextView) view
                .findViewById(R.id.note_taking_dialogwindow_share_zone);
        //AutoUtils.autoTextSize(note_taking_dialogwindow_share_zone);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch (v.getId()) {
                    case R.id.note_taking_sharedialog_cancel_button:
                        dismiss();
                        break;

                    case R.id.note_taking_dialogwindow_share_weixing:
                        // 分享至微信
                        if (!NetworkUtils.isConnectivityActive(context)) {
                            String string = context.getResources().getString(
                                    R.string.edit_note_no_internet);
                            Toast.makeText(context, string, Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }
                        shareweixing();
                        break;
                    case R.id.note_taking_dialogwindow_share_moments:
                        // 分享至朋友圈
                        if (!NetworkUtils.isConnectivityActive(context)) {
                            String string = context.getResources().getString(
                                    R.string.edit_note_no_internet);
                            Toast.makeText(context, string, Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        sharemoments();
                        break;

                    case R.id.note_taking_dialogwindow_share_qq_friends:
                        if (!NetworkUtils.isConnectivityActive(context)) {
                            String string = context.getResources().getString(
                                    R.string.edit_note_no_internet);
                            Toast.makeText(context, string, Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }
                        shareqqfriends();
                        break;

                    case R.id.note_taking_dialogwindow_share_sina:
                        if (!NetworkUtils.isConnectivityActive(context)) {
                            String string = context.getResources().getString(
                                    R.string.edit_note_no_internet);
                            Toast.makeText(context, string, Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }
                        shareSina();
                        break;
                    case R.id.note_taking_dialogwindow_share_zone:
                        if (!NetworkUtils.isConnectivityActive(context)) {
                            String string = context.getResources().getString(
                                    R.string.edit_note_no_internet);
                            Toast.makeText(context, string, Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }
                        shareZone();
                        break;
                }

                dismiss();
            }


        };
        note_taking_sharedialog_cancel_button.setOnClickListener(listener);
        note_taking_dialogwindow_share_weixing.setOnClickListener(listener);
        note_taking_dialogwindow_share_moments.setOnClickListener(listener);
        note_taking_dialogwindow_share_qq_friends.setOnClickListener(listener);
        note_taking_dialogwindow_share_sina.setOnClickListener(listener);
        note_taking_dialogwindow_share_zone.setOnClickListener(listener);

    }

    /**
     * 分享到新浪微博
     */
    private void shareSina() {
        // TODO Auto-generated method stub
        ShareParams sina = new Platform.ShareParams();
        if (bean != null) {
            sina.setTitle(bean.title);
            sina.setText(bean.desc + " " + bean.url);
            //sina.setUrl(bean.url);
            //sina.setTitleUrl(bean.url);
            sina.setImageUrl(bean.img);

        }
        // sp.shareType = Platform.SHARE_WEBPAGE;
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        platform.setPlatformActionListener(new SharePlatformActionListener());
        platform.share(sina);


    }

    /**
     * 微信分享
     */
    private void shareweixing() {
        // TODO Auto-generated method stub
        Wechat.ShareParams sp = new Wechat.ShareParams();
        if (bean != null) {
            sp.setTitle(bean.title);
            sp.setText(bean.desc);
            sp.setImageUrl(bean.img);
            sp.setUrl(bean.url);
        }
        sp.shareType = Platform.SHARE_WEBPAGE;
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(new SharePlatformActionListener());
        platform.share(sp);
//		ShareParams wechat = new Platform.ShareParams();
//		wechat.setTitle("我是分享标题");
//		wechat.setText("我是分享文本内容");
//		wechat.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg ");
//		wechat.setUrl("http://mob.com ");
//		wechat.setShareType(Platform.SHARE_WEBPAGE);
//		Platform weixin = ShareSDK.getPlatform(context,
//				Wechat.NAME);
//		if(weixin.isClientValid()){
//			System.out.println("安装了微信");
//		}else {
//			System.out.println("没有安装了微信");
//		}
//
//		weixin.setPlatformActionListener(new SharePlatformActionListener());
//		weixin.share(wechat);


    }

    /**
     * 分享到朋友圈
     */
    private void sharemoments() {
        // TODO Auto-generated method stub
        WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
        if (bean != null) {
//            sp.title = bean.title;
//            sp.text = bean.desc;
//            sp.imagePath = bean.img;
//            sp.url = bean.url;
            sp.setTitle(bean.title);
            sp.setText(bean.desc);
            sp.setImageUrl(bean.img);
            sp.setUrl(bean.url);
        }
        sp.shareType = Platform.SHARE_WEBPAGE;
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);

        istoast = false;
        platform.setPlatformActionListener(new SharePlatformActionListener());

        platform.share(sp);
    }

    // 分享到QQ好友
    private void shareqqfriends() {
        /*
		 * site就是分享成功返回的的那个值
		 * 
		 * And技术支持Meng 2015/7/28 15:59:49 siteUrl这个设置了不是您 用的 是个链接来的 分享看不到的
		 */

        ShareParams qq = new Platform.ShareParams();
        if (bean != null) {
            qq.setTitle(bean.title);
            qq.setTitleUrl(bean.url);

            qq.setText(bean.desc);
            qq.setImageUrl(bean.img);
        }
        Platform share_qq = ShareSDK.getPlatform(context, QQ.NAME);
        // 设置监听回调
        share_qq.setPlatformActionListener(new SharePlatformActionListener());
        share_qq.share(qq);

    }

    /**
     * 分享到qq空间
     */
    private void shareZone() {
        // TODO Auto-generated method stub
        ShareParams qqzone = new Platform.ShareParams();
        if (bean != null) {
            qqzone.setTitle(bean.title);
            qqzone.setTitleUrl(bean.url);

            qqzone.setText(bean.desc);
            qqzone.setImageUrl(bean.img);
        }
        Platform share_qqzone = ShareSDK.getPlatform(context, QZone.NAME);
        share_qqzone.setPlatformActionListener(new SharePlatformActionListener());
        share_qqzone.share(qqzone);

    }

    public void setshareImagepath(String path) {
        // TODO Auto-generated method stub
        this.imagepath = path;
    }

    private class SharePlatformActionListener implements PlatformActionListener {
        // 分享取消
        @Override
        public void onCancel(Platform arg0, int arg1) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "取消",
                    Toast.LENGTH_SHORT).show();
        }

        // 分享成功
        @Override
        public void onComplete(Platform platform, int action,
                               HashMap<String, Object> arg2) {
            String name = platform.getName();
            // TODO Auto-generated method stub
            Toast.makeText(context, "成功",
                    Toast.LENGTH_SHORT).show();

            if (istoast) {
                Toast.makeText(context, R.string.share_success,
                        Toast.LENGTH_SHORT).show();
            }
        }

        // 分享失败
        @Override
        public void onError(Platform arg0, int arg1, Throwable throwable) {
            // TODO Auto-generated method stub
            String string = throwable.toString();
            LogUtil.e(string);
            Toast.makeText(context, "错误" + string,
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @param c传人分享的数据 * {"img":
     *                 "http://xlanzi.com/Template/mobile/new/Static/images/voucher_icon.png"
     *                 ,"title":"你的好友给你赠送一张大礼券!598.00元","url":
     *                 "http://test.xlanzi.com/index.php/Mobile/voucher/friend_give_and_get_it/vid/32560/u/833/p/18175861350.html"
     *                 ,"desc":"【鲜篮子】套餐A：澳洲进口 冷鲜上脑牛排 20块200g牛肉!立即领取!"}
     */
    public void setShareDate(String json) {
        // TODO Auto-generated method stub
        try {
            if (!TextUtils.isEmpty(json)) {

                bean = new ShareBean();
                JSONObject object = new JSONObject(json);
                String img = object.getString("img");
                String title = object.getString("title");
                String url = object.getString("url");
                String desc = object.getString("desc");
                bean.img = img;
                bean.title = title;
                bean.url = url;
                bean.desc = desc;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
