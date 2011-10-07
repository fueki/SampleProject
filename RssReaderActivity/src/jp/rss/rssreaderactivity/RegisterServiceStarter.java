package jp.rss.rssreaderactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RegisterServiceStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
			Intent serviceIntent = new Intent(context,RegisterService.class);
			context.startService(serviceIntent);
		}
	}

}
