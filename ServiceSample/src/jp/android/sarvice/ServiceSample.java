package jp.android.sarvice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class ServiceSample extends Activity {
	
	Intent intent;
	private IMyService service;
	Button button;
	
	private ServiceConnection connection = new ServiceConnection(){
		@Override
		public void onServiceConnected(ComponentName name, IBinder ibinder) {
			// TODO Auto-generated method stub
			service = IMyService.Stub.asInterface(ibinder);
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			service = null;
			
		}
	}; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button)findViewById(R.id.button_toast);
        this.button.setEnabled(false); 
        
    }
    
    public void onClickServiceButton(View view){
    	ToggleButton td = (ToggleButton)view;
    	
    	if(td.isChecked()){
    		intent = new Intent(this,ServiceSampleService.class);
    		startService(intent);
    		
    		bindService(intent,connection,BIND_AUTO_CREATE);
    		this.button.setEnabled(true); 
    		
    	} else {
    		unbindService(connection);
    		intent = new Intent(this,ServiceSampleService.class);
    		stopService(intent);

    		this.button.setEnabled(false); 
    	}
    }
    
    public void onClickToastButton(View view){
    	try{
    		service.showToast();
    	} catch(RemoteException e) {
    		e.printStackTrace();
    	}
    }
}