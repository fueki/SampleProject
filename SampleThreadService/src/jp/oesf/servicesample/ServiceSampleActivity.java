package jp.oesf.servicesample;

import java.util.concurrent.atomic.AtomicBoolean;

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

public class ServiceSampleActivity extends Activity {
	Intent intent;
	ServiceSampleService service = new ServiceSampleService();
	// TODO No.03 IServiceSampleService service;
	
	// TODO No.04 Toast表示ボタンの定義（main.xmlにButtonを追加しておくこと）

	// TODO No.05 ServiceConnectionの定義 とメソッドのオーバライド

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// TODO No.06 Toastボタンのインスタンス生成

		// TODO No.07 Toastボタンを無効にする

	}

	public void onClickServiceButton(View v) {
		ToggleButton tb = (ToggleButton) v;
		if (tb.isChecked()) {
			// サービスを開始する
			intent = new Intent(this, ServiceSampleService.class);
			startService(intent);

			// TODO No.08 サービスに接続する
			
			// TODO No.09 Toastボタンを有効にする

		} else {
			// TODO No.10 サービスの接続を解除する

			// サービスを終了する
			stopService(new Intent(this, ServiceSampleService.class));

			// TODO No.11 Toastボタンを無効にする

		}
	}

	public void onClickToastButton(View view) {
		// TODO No.12 Toastを表示する

	}
}