package com.qbgg.cenglaicengqu.main.model;


import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.fragment.ContactListFragment;
import com.qbgg.cenglaicengqu.main.fragment.MainTabFragment;
import com.qbgg.cenglaicengqu.main.fragment.PersonListFragment;
import com.qbgg.cenglaicengqu.main.fragment.SessionListFragment;
import com.qbgg.cenglaicengqu.main.reminder.ReminderId;

public enum MainTab {
    RECENT_CONTACTS(0, ReminderId.SESSION, SessionListFragment.class, R.string.main_tab_session, R.layout.session_list),
    CONTACT(1, ReminderId.CONTACT, ContactListFragment.class, R.string.main_tab_contact, R.layout.contacts_list),
    CHAT_ROOM(2, ReminderId.INVALID, PersonListFragment.class, R.string.chat_room, R.layout.person_list_fragment_layout);

    public final int tabIndex;

    public final int reminderId;

    public final Class<? extends MainTabFragment> clazz;

    public final int resId;

    public final int fragmentId;

    public final int layoutId;

    MainTab(int index, int reminderId, Class<? extends MainTabFragment> clazz, int resId, int layoutId) {
        this.tabIndex = index;
        this.reminderId = reminderId;
        this.clazz = clazz;
        this.resId = resId;
        this.fragmentId = index;
        this.layoutId = layoutId;
    }

    public static final MainTab fromReminderId(int reminderId) {
        for (MainTab value : MainTab.values()) {
            if (value.reminderId == reminderId) {
                return value;
            }
        }

        return null;
    }

    public static final MainTab fromTabIndex(int tabIndex) {
        for (MainTab value : MainTab.values()) {
            if (value.tabIndex == tabIndex) {
                return value;
            }
        }

        return null;
    }
}
