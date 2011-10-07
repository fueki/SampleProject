package jp.android.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SampleDataBaseUpdate extends Activity {
	
	Intent intent;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.menu1, menu);
    	
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	boolean ret = true;
    	
    	intent = new Intent(this,DataBaseSample.class);
    	startActivity(intent);
    	
    	return ret;
    }
    
	public void onClickResuletListButton(View view){
		
    	intent = new Intent(this,ResultListAvtivity.class);
    	startActivity(intent);
	}
}