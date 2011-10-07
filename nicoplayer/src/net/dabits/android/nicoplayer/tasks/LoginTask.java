package net.dabits.android.nicoplayer.tasks;

import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.activities.AccountActivity;
import net.dabits.android.nicoplayer.procs.LoginProc;
import net.dabits.android.nicoplayer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;


public class LoginTask extends AsyncTask <String, Integer, Integer> {
	
	private Activity activity;
	
	public LoginTask(Activity activity){
		this.activity = activity;
	}
	
	@Override
	protected Integer doInBackground(String... args){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
		String email = sp.getString("email", "");
		String pass = sp.getString("pass", "");
		if(email.length() <= 0 || pass.length() <= 0){
			return Config.login.NOCONFIG;
		}
		
		LoginProc loginProc = new LoginProc();
		if(!loginProc.login(email, pass)){
			return Config.login.FAILED;
		}
		
		Editor ed = sp.edit();
		ed.putString("cookie", loginProc.getCookie().getValue());
		ed.commit();
		return Config.login.SUCCESS;
	}
	
    @Override
    protected void onPostExecute(Integer result) {
    	switch(result){
    		case Config.login.SUCCESS:
    			startFirstVideoList();
    			break;
    		case Config.login.NOCONFIG:
    			showAccountDialog(activity.getString(R.string.logintask_noconfig));
    			break;
    		case Config.login.FAILED:
    			showAccountDialog(activity.getString(R.string.logintask_loginfailed));
    			break;
    	}
    }
    
    protected void startFirstVideoList(){
    	FirstVideoListTask firstVideoListTask = new FirstVideoListTask(activity);
    	firstVideoListTask.execute();
    }
    
    protected void startAccount(){
		Intent intent = new Intent(activity, AccountActivity.class);
		activity.startActivityForResult(intent, Config.requestCode.ACCOUNT);
    }
    
    protected void showAccountDialog(String text){
    	AlertDialog.Builder ad=new AlertDialog.Builder(activity);
        ad.setTitle(activity.getString(R.string.logintask_account_dialog_title));
        ad.setMessage(text);
        ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
            	startAccount();
            }
        });
        ad.create();
        ad.show();
    }
}