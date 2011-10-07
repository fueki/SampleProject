package jp.oesf.servicesample;

import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class ServiceSampleService extends Service {

	AtomicBoolean running = new AtomicBoolean(true); 

	// TODO No.01 IServiceSampleService.Stubのサブクラスを定義とshowToastメソッドのオーバライド

	@Override
	public IBinder onBind(Intent intent) {
		// TODO No.02 No.01で定義したStubクラスを返す
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "Service Start", Toast.LENGTH_SHORT).show();
		running.set(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; running.get(); i++) {
					Log.v(getClass().getSimpleName(), "i:" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		Log.v(getClass().getSimpleName(), "onDestroy");
		running.set(false);
		Toast.makeText(this, "Service end", Toast.LENGTH_SHORT).show();
	}
}
