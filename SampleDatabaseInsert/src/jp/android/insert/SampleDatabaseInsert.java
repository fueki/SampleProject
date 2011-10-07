package jp.android.insert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class SampleDatabaseInsert extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.menu, menu);
    	
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	Intent intent = new Intent(this,InsetView.class);
    	startActivity(intent);
    	
    	return true;
    }
    
    public void onClickSearchButton(View view){
//    	Intent intent = new Intent(this,InsetResult.class);
    	Intent intent = new Intent(this,DataAllList.class);
    	startActivity(intent);
    }
}