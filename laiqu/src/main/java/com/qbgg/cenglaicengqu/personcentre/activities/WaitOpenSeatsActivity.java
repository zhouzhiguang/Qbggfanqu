package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;

//待开席饭局
public class WaitOpenSeatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_open_seats_layout);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
