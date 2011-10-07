package com.hidecheck.honeycomic;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements ActionBar.TabListener {

	private View actionvBarView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Create ActionBar
		ActionBar bar = getActionBar();

		// TODO add action item and detail lator
		bar.addTab(bar.newTab().setText("tab1").setTabListener(this));
		bar.addTab(bar.newTab().setText("tab2").setTabListener(this));
		bar.addTab(bar.newTab().setText("tab3").setTabListener(this));

		// bar customizition.
		actionvBarView = getLayoutInflater().inflate(R.layout.action_bar_custom, null);
		bar.setCustomView(actionvBarView);
		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO);
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayShowHomeEnabled(true);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}