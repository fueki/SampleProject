package jp.oesf.databasesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class DatabaseSample extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}
	
	public void onClickSearchButton(View v){
		Intent intent = new Intent(this, ResultListActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        MenuInflater menuInfalter = getMenuInflater();
        menuInfalter.inflate(R.menu.menu, menu);
        return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent = new Intent(this, AddActivity.class);
		startActivity(intent);
		return true;
	}
	
	
}