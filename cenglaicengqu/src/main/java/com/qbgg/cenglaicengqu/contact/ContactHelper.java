package com.qbgg.cenglaicengqu.contact;

import android.content.Context;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.contact.ContactEventListener;
import com.qbgg.cenglaicengqu.contact.activity.UserProfileActivity;

/**
 * UIKit联系人列表定制展示类

 */
public class ContactHelper {

    public static void init() {
        setContactEventListener();
    }

    private static void setContactEventListener() {
        NimUIKit.setContactEventListener(new ContactEventListener() {
            @Override
            public void onItemClick(Context context, String account) {
                //个人名片界面
                UserProfileActivity.start(context, account);
            }

            @Override
            public void onItemLongClick(Context context, String account) {

            }

            @Override
            public void onAvatarClick(Context context, String account) {
                //个人名片界面
                UserProfileActivity.start(context, account);
            }
        });
    }

}
