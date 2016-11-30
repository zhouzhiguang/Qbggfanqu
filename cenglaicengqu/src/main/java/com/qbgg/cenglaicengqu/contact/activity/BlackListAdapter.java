package com.qbgg.cenglaicengqu.contact.activity;

import android.content.Context;

import com.netease.nim.uikit.common.adapter.TAdapter;
import com.netease.nim.uikit.common.adapter.TAdapterDelegate;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import java.util.List;


public class BlackListAdapter extends TAdapter<UserInfoProvider.UserInfo> {

    public BlackListAdapter(Context context, List<UserInfoProvider.UserInfo> items, TAdapterDelegate delegate, ViewHolderEventListener
            viewHolderEventListener) {
        super(context, items, delegate);

        this.viewHolderEventListener = viewHolderEventListener;
    }

    public interface ViewHolderEventListener {
        void onItemClick(UserInfoProvider.UserInfo user);

        void onRemove(UserInfoProvider.UserInfo user);
    }

    private ViewHolderEventListener viewHolderEventListener;

    public ViewHolderEventListener getEventListener() {

        return viewHolderEventListener;
    }
}
