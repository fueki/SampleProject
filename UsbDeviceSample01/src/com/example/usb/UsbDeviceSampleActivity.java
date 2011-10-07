package com.example.usb;

import java.io.BufferedWriter;
import java.io.File;
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
import android.os.RemoteException;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import android.os.storage.IMountService; 
import android.os.ServiceManager;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;


public class UsbDeviceSampleActivity extends Activity {

	private EditText edit;
	String status = Environment.getExternalStorageState();
	StorageManager sm = (StorageManager)getSystemService("storage");

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

	}

	public void onClickDataButton(View v) {
		writeFile(getUUID(), false);
	}

	public void onClickSDcardButton(View v) {
		String path = Environment.getExternalStorageDirectory().getPath() + "/" + getUUID();
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
		IMountService mountService =
			IMountService.Stub.asInterface(ServiceManager.getService("mount"));	
		String extStoragePath =
			Environment.getExternalStorageDirectory().toString();	
		
	
		//sm.enableUsbMassStorage();


		try {    
			mountService.mountVolume(extStoragePath);
			//mountService.unmountVolume(extStoragePath, true);
			

			if (status.equalsIgnoreCase(Environment.MEDIA_MOUNTED)){
			    Toast.makeText(this, 
			            "SDカードが装着されている", 
			            Toast.LENGTH_LONG).show();
			    //この状態が返ってきた場合は、読み書きが可能です。
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY)){
			    Toast.makeText(this, 
			            "SDカードが装着されていますが、読み取り専用・書き込み不可です", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_REMOVED)){
			    Toast.makeText(this, 
			            "SDカードが装着されていません", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_SHARED)){
			    Toast.makeText(this, 
			            "SDカードが装着されていますが、USBストレージとしてPCなどに" +
			            "マウント中です", Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_BAD_REMOVAL)){
			    Toast.makeText(this, 
			            "SDカードのアンマウントをする前に、取り外しました", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_CHECKING)){
			    Toast.makeText(this, 
			            "SDカードのチェック中です", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_NOFS)){
			    Toast.makeText(this, 
			            "SDカードは装着されていますが、ブランクであるか、" +
			            "またはサポートされていないファイルシステムを利用しています", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_UNMOUNTABLE)){
			    Toast.makeText(this, 
			            "SDカードは装着されていますが、マウントすることができません", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_UNMOUNTED)){
			    Toast.makeText(this, 
			            "SDカードは存在していますが、マウントすることができません", 
			            Toast.LENGTH_LONG).show();
			}
			else{
			    Toast.makeText(this, 
			            "その他の要因で利用不可能", 
			            Toast.LENGTH_LONG).show();
			}
			
//			Log.v("AAAA", "ret:" + mountService);
//			Toast.makeText(this, "ret:" + isMountEx(), Toast.LENGTH_SHORT).show();
			        
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
		
//		StorageManager manager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
//		Method methodIsUsbMassStorageConnected;
//		try {
//			methodIsUsbMassStorageConnected = manager.getClass().getDeclaredMethod(
//					"isUsbMassStorageConnected", null);
//			methodIsUsbMassStorageConnected.setAccessible(true);
//			boolean ret = (Boolean)methodIsUsbMassStorageConnected.invoke(manager, null);
//			Log.v("AAAA", "ret:" + ret);
//			Toast.makeText(this, "ret:" + ret, Toast.LENGTH_SHORT).show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// boolean isAvilable = manager.isUsbMassStorageConnected();
	}
	
	public void onClickGetUsbStrageUnMount(View v) {
		IMountService mountService =
			IMountService.Stub.asInterface(ServiceManager.getService("mount"));	
		String extStoragePath =
			Environment.getExternalStorageDirectory().toString();
			
		try {    
			//mountService.mountVolume(extStoragePath);
			mountService.unmountVolume(extStoragePath, true);
			
			if (status.equalsIgnoreCase(Environment.MEDIA_MOUNTED)){
			    Toast.makeText(this, 
			            "SDカードが装着されている", 
			            Toast.LENGTH_LONG).show();
			    //この状態が返ってきた場合は、読み書きが可能です。
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY)){
			    Toast.makeText(this, 
			            "SDカードが装着されていますが、読み取り専用・書き込み不可です", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_REMOVED)){
			    Toast.makeText(this, 
			            "SDカードが装着されていません", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_SHARED)){
			    Toast.makeText(this, 
			            "SDカードが装着されていますが、USBストレージとしてPCなどに" +
			            "マウント中です", Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_BAD_REMOVAL)){
			    Toast.makeText(this, 
			            "SDカードのアンマウントをする前に、取り外しました", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_CHECKING)){
			    Toast.makeText(this, 
			            "SDカードのチェック中です", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_NOFS)){
			    Toast.makeText(this, 
			            "SDカードは装着されていますが、ブランクであるか、" +
			            "またはサポートされていないファイルシステムを利用しています", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_UNMOUNTABLE)){
			    Toast.makeText(this, 
			            "SDカードは装着されていますが、マウントすることができません", 
			            Toast.LENGTH_LONG).show();
			}
			else if (status.equalsIgnoreCase(Environment.MEDIA_UNMOUNTED)){
			    Toast.makeText(this, 
			            "SDカードは存在していますが、マウントすることができません", 
			            Toast.LENGTH_LONG).show();
			}
			else{
			    Toast.makeText(this, 
			            "その他の要因で利用不可能", 
			            Toast.LENGTH_LONG).show();
			}

			
//			Log.v("AAAA", "ret:" + mountService);
//			Toast.makeText(this, "ret:" + isMountEx(), Toast.LENGTH_SHORT).show();
			        
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
    /**
	 * @return true:マウントされている false:マウントされていない 
	 */ 
	public boolean isMountEx(){  
		  //内蔵メモリのパス  
		  String pathIn = getSdMountedPath();  
		  
		  //外付けSD用のパス  
		  String pathEx = getSdMountedPath() + "/" + "usb0-disk0";  
		  		  
		  long exAvailableSize = getAvailableSize(pathEx);  
		  long exALLSize = getSize(pathEx);  
		  
		  long inAvailableSize = getAvailableSize(pathIn);  
		  long inALLSize = getSize(pathIn);  
		  
		  if(exAvailableSize != inAvailableSize && exALLSize != inALLSize)  
		    return true;  
		    
		  return false;  
		}  
	
	public String getSdMountedPath(){  
		  File exMountedPath = Environment.getExternalStorageDirectory();  
		  if(exMountedPath == null)  
		    return null;  
		    
		  return exMountedPath.getPath();  
		}  
		  
		/** 
		* path先の使用可能容量を返す 
		* @param path 探索先 
		* @return 0:失敗 0以外:メモリ容量(Byte) 
		*/  
		public long getAvailableSize(String path){  
		  StatFs fs = new StatFs(path);  
		  long bkSize = fs.getBlockSize();  
		  long avaBlocks = fs.getAvailableBlocks();  
		  
		  return bkSize * avaBlocks;  
		}  
		  
		/** 
		* path先の総容量を返す 
		* @param path 探索先 
		* @return 0:失敗 0以外:メモリ容量(Byte) 
		*/  
		public long getSize(String path){  
		  StatFs fs = new StatFs(path);  
		  long bkSize = fs.getBlockSize();  
		  long bkCount = fs.getBlockCount();  
		  
		  return bkSize * bkCount;  
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