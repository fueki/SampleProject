/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RegisterServiceStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){

			Intent serviceIntent = new Intent(context, RegisterService.class);
			context.startService(serviceIntent);
		}
		if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
			Intent intent1 = new Intent("a.b.c.d.E");
			context.sendBroadcast(intent1);
		}
	}
}
