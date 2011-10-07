package jp.android.sarvice;

import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ServiceSampleService extends Service{
	
	private static final String TAG = "ServiceSampleService";
	AtomicBoolean running = new AtomicBoolean(true);
	LogThread thread = new LogThread();
	ToggleButton tb;
	
	private IMyService.Stub binder = new IMyService.Stub() {
		@Override
		public void showToast() throws RemoteException {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Service Running", Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
//		super.onDestroy();
		Log.v(getClass().getSimpleName(), "onDestroy");
		running.set(false);
		Toast.makeText(this, "Service End", Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onStart(Intent intent, int flags) {
		// TODO Auto-generated method stub
		Toast.makeText(this, R.string.service_on, Toast.LENGTH_LONG).show();
		
		running.set(true);
		new Thread(new Runnable() {
			@Override
			public void run(){
				for(int i = 0; running.get(); i++){
					Log.v(TAG, "i : " + i);
					try{
						Thread.sleep(1000);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	
	class LogThread extends Thread{
		@Override
		public void run(){
			for(int i = 0;running.get(); i++){
				Log.v(TAG,"" + i);
				try{
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
