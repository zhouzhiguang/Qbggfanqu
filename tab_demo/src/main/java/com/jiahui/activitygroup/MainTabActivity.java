package com.jiahui.activitygroup;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainTabActivity extends TabActivity {

	private TabHost tabHost;

	private FrameLayout layout1, layout2;

	private ImageView tab_home, tab_home_click, tab_bang, tab_bang_click;
	private TextView tab_home_text_click, tab_home_text, tab_bang_text,
			tab_bang_text_click;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();


	}

	private void initView() {
		// TODO Auto-generated method stub
		tabHost = getTabHost();
		Intent intent1 = new Intent();
		intent1.setClass(MainTabActivity.this, HomeActivity.class);
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1")
				.setContent(intent1));
		Intent intent2 = new Intent();
		intent2.setClass(MainTabActivity.this, SecondActivity.class);
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2")
				.setContent(intent2));

		layout1 = (FrameLayout) findViewById(R.id.frame_home);
		layout2 = (FrameLayout) findViewById(R.id.frame_forum);

		layout1.setOnClickListener(l);
		layout2.setOnClickListener(l);

		tab_home = (ImageView) findViewById(R.id.tab_home_click);

		tab_bang = (ImageView) findViewById(R.id.tab_bang);

	}

	OnClickListener l = new OnClickListener() {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (arg0 == layout1) {
				tabHost.setCurrentTabByTag("1");

				tab_home.setImageResource(R.drawable.tab_home_click);
				tab_bang.setImageResource(R.drawable.tab_bang);

			} else if (arg0 == layout2) {

				tab_home.setImageResource(R.drawable.tab_home);
				tab_bang.setImageResource(R.drawable.tab_bang_click);

				tabHost.setCurrentTabByTag("2");

			}

		}
	};
}