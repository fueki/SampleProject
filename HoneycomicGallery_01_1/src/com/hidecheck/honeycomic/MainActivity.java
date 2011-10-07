package com.hidecheck.honeycomic;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	private View actionvBarView;
	
	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Create ActionBar
        ActionBar bar = getActionBar();

        //TODO add action item lator


        // bar customizition.
        actionvBarView = getLayoutInflater().inflate(R.layout.action_bar_custom, null);
        bar.setCustomView(actionvBarView);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowHomeEnabled(true);
    }
    
}