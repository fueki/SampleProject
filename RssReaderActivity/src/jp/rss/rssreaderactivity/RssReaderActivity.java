package jp.rss.rssreaderactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RssReaderActivity extends Activity {
	private static final String TAG = "RssReaderActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	Log.e(TAG, "onOptionsLtemSelected start");
    	
    	if(item.getItemId() == R.id.main_menu_add){
    		Intent i = new Intent(this,RegisterService.class);
    		startService(i);
    		Log.e(TAG, "onOptionsItemSelected end");
    	}
		return false;    	
    }
}