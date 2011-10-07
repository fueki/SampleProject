package jp.rss.rssreaderactivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RegisterService extends Service {
	private static final String TAG = "RegisterService";

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Log.e(TAG, "onStart start");
		new RssFeedRegister(this).registration("http://www.oesf.jp/modules/news/index.php?page=rss");
		Log.e(TAG,"onStart start");
	}
}
