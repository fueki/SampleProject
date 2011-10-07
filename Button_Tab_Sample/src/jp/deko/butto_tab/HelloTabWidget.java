package jp.deko.butto_tab;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class HelloTabWidget extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.main);  
	  
	    Resources res = getResources();   
	  
	    TabHost tabHost = getTabHost();   
	    
	    // 各タブのResusable TabSpec  
	    TabHost.TabSpec spec;  
	  
	    Intent intent;  
	  
	    intent = new Intent().setClass(this, Tab1Activity.class);  
	    spec = tabHost.newTabSpec("tab1")  
	                  .setIndicator("1")  
	                  .setContent(intent);  
	    tabHost.addTab(spec);  
	  
	    intent = new Intent().setClass(this, Tab2Activity.class);  
	    spec = tabHost.newTabSpec("tab2")  
	                  .setIndicator("2")  
	                  .setContent(intent);  
	    tabHost.addTab(spec);  
	  
	    intent = new Intent().setClass(this, Tab3Activity.class);  
	    spec = tabHost.newTabSpec("tab3")  
	                  .setIndicator("3")  
	                  .setContent(intent);  
	    tabHost.addTab(spec);  
	  
	    tabHost.setCurrentTab(0);  
	} 
}
