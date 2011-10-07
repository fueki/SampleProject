package com.example.usb;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usb.util.RefrectionUtil;


public class UsbDeviceSampleActivity extends Activity {

	private EditText edit;
	String status = Environment.getExternalStorageState();
    private StorageManager mStorageManager = null;

//    StorageEventListener mStorageListener = new StorageEventListener() {
//        @Override
//        public void onStorageStateChanged(String path, String oldState, String newState) {
//            Log.v("UsbDeviceSample", "-------Received storage state changed notification that " +
//                    path + " changed state from " + oldState +
//                    " to " + newState);
//
//        }
//    };


	class UsbDetachedReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "recive", Toast.LENGTH_SHORT).show();
			Log.v(getClass().getName(), "receive");
			String action = intent.getAction();

			if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
				UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
				Toast.makeText(context, device.getDeviceName(), Toast.LENGTH_SHORT).show();
				Log.v(getClass().getName(), "receive usb detached");
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edit = (EditText) findViewById(R.id.editText1);

		
        if (mStorageManager == null) {
            mStorageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
//            mStorageManager.registerListener(mStorageListener);
        }

	}

	public void onClickDataButton(View v) {
		writeFile(getUUID(), false);
	}

	public void onClickSDcardButton(View v) {
		String path = Environment.getExternalStorageDirectory().getPath() + "/" + getUUID();
		//REGZA
//		String path = Environment.getExternalStorageDirectory().getPath() + "/sdcard-disk0/" + getUUID();
		
		
		
		Log.v(getClass().getName(), "path:" + path);
		writeFile(path, true);
	}

	public void onClickUsbButton(View v) {
		String path = Environment.getExternalStorageDirectory().getPath() + "/usb0-disk0/"
				+ getUUID();
		Log.v(getClass().getName(), "onClickUsbButton path:" + path);
		writeFile(path, true);

	}

	public void onClicGetDeviceButton(View v) {
		Log.v(getClass().getName(), " onClickDevice");
		UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
		Log.v(getClass().getName(), " devices:" + deviceList.size());
		Toast.makeText(this, "count:" + deviceList.size(), Toast.LENGTH_SHORT).show();

		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

		while (deviceIterator.hasNext()) {
			UsbDevice device = deviceIterator.next();
			// your code
			Log.v(getClass().getName(), " device :" + device.getDeviceName());
			String venderId = "" + device.getVendorId();
			String product = "" + device.getProductId();
			Log.v(getClass().getName(), " venderid :" + venderId + " productId:" + product);

		}
	}

	public void onClickGetUsbStrageMount(View v) {
		Method method;
		try {
			method = RefrectionUtil.getMethod("disableUsbMassStorage", mStorageManager.getClass());
			method.invoke(mStorageManager, null);
			Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e("UsbDeviceSample", "onClickGetUsgStrageMount", e);
		}
		
//		try {
//			methodIsUsbMassStorageConnected = manager.getClass().getDeclaredMethod(
//					"disableUsbMassStorage", null);
//			methodIsUsbMassStorageConnected.setAccessible(true);
//			boolean ret = (Boolean)methodIsUsbMassStorageConnected.invoke(manager, null);
//			Log.v("AAAA", "ret:" + ret);
//			Toast.makeText(this, "ret:" + ret, Toast.LENGTH_SHORT).show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	public void onClickGetUsbStrageUnMount(View view){
		Method method;
		try {
			method = RefrectionUtil.getMethod("enableUsbMassStorage", mStorageManager.getClass());
			method.invoke(mStorageManager, null);
			Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e("UsbDeviceSample", "onClickGetUsgStrageUnMount", e);
		}
	}
	
	
	private static String getUUID() {
		return UUID.randomUUID().toString();
	}

	private void writeFile(String path, boolean isSd) {

		String message = edit.getText().toString();
		FileOutputStream fos = null;
		BufferedWriter out = null;

		try {
			if (isSd) {
				fos = new FileOutputStream(path, true);
			} else {
				fos = this.openFileOutput(path, MODE_PRIVATE);
			}
			out = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			out.write(message);
			out.flush();

			Toast.makeText(this, "完了", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "writeFile", e);
			Toast.makeText(this, "失敗", Toast.LENGTH_SHORT).show();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}