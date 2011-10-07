package jp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

public class SampleOptionMenu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        builder.setTitle("Titleƒ^ƒCƒgƒ‹");
        builder.setMessage("Message");
        
        builder.setPositiveButton("Button", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(SampleOptionMenu.this, "Hello,Android", Toast.LENGTH_LONG).show();
			}
		});	
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.main_menu, menu);
		return true;
    }
}