package net.dabits.android.nicoplayer.activities;

import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.R;
import net.dabits.android.nicoplayer.tasks.LoginTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	
    	LoginTask loginTask = new LoginTask(this);
        loginTask.execute();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	switch(requestCode){
    		case Config.requestCode.ACCOUNT:
    	    	LoginTask loginTask = new LoginTask(this);
    	        loginTask.execute();
    			break;
    		default:
    			finish();
    	}
    }
}