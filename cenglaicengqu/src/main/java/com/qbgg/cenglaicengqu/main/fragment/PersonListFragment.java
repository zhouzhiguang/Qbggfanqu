package com.qbgg.cenglaicengqu.main.fragment;


import android.widget.FrameLayout;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.model.MainTab;

/**
 * 个人中心主页
 */
public class PersonListFragment extends MainTabFragment {
    private PersonFragment fragment;

    public void PersonListFragment() {

        setContainerId(MainTab.CHAT_ROOM.fragmentId);
    }


    @Override
    protected void onInit() {
        // 采用静态集成，这里不需要做什么了
        FrameLayout person_list_container = findView(R.id.person_list_container);
        fragment = new PersonFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.person_list_container, fragment).commit();
    }

    @Override
    public void onCurrent() {
        super.onCurrent();
        if (fragment != null) {
            fragment.onCurrent();
        }
    }
}
