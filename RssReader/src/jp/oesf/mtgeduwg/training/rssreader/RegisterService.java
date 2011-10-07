/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader;

import jp.oesf.mtgeduwg.training.rssreader.helper.RssFeedRegister;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RegisterService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.v("RegisterService", "onStart start");
		new RssFeedRegister(this).registration("http://www.oesf.jp/modules/news/index.php?page=rss");
		
//		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification(R.drawable.icon,getText(R.string.notification_ticker_text), System.currentTimeMillis());
//		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,RssListActivity.class), 0);
//		notification.setLatestEventInfo(this, getText(R.string.notification_title), getText(R.string.notification_text), pendingIntent);
//		nm.notify(0,notification);
		Log.v("RegisterService", "onStart end");

		Toast.makeText(this, "終了", Toast.LENGTH_SHORT).show();
	}
}
