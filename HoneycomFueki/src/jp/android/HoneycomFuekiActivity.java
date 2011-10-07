package jp.android;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class HoneycomFuekiActivity extends Activity implements TabListener{
	
	private View actionbar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ActionBar  bar = getActionBar();
        
        bar.addTab(bar.newTab().setText("tab1").setTabListener(this));
        bar.addTab(bar.newTab().setText("tab2").setTabListener(this));
        bar.addTab(bar.newTab().setText("tab3").setTabListener(this));

        actionbar = getLayoutInflater().inflate(R.layout.custom_actionbar, null);
        bar.setCustomView(actionbar);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
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