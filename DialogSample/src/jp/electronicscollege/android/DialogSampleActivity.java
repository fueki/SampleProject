package jp.electronicscollege.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class DialogSampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Dialog();
		return true;
	}
	
	public void Dialog(){
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setIcon(R.drawable.expander_ic_minimized);
		alertDialogBuilder.setTitle("Title");
		alertDialogBuilder.setMessage("Message");
		
		alertDialogBuilder.setPositiveButton("OK", 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogSampleActivity.this, "OK", Toast.LENGTH_LONG).show();
					}
				});
		alertDialogBuilder.setNeutralButton("Neutral", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_LONG).show();
					}
				});
		alertDialogBuilder .setNegativeButton("Cancel", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
		alertDialogBuilder.show();
	}
	
}