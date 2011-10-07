package jp.oesf.activitysample.util;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AlertDialogUtil {
	public static void showAlert(Context context, String title, String message){
    	new AlertDialog.Builder(context)
    	.setTitle(title)
    	.setMessage(message)
    	.setPositiveButton(R.string.ok, null)
    	.show();
	}

	public static void showAlert(Context context, String title, String message, String buttonLabel){
    	new AlertDialog.Builder(context)
    	.setTitle(title)
    	.setMessage(message)
    	.setPositiveButton(buttonLabel, null)
    	.show();
	}

	public static void showAlert(Context context, String title, String message, OnClickListener listener){
    	new AlertDialog.Builder(context)
    	.setTitle(title)
    	.setMessage(message)
    	.setPositiveButton(R.string.ok, listener)
    	.show();
	}

	public static void showAlertHasCancel(Context context, String title, String message, OnClickListener listener){
    	new AlertDialog.Builder(context)
    	.setTitle(title)
    	.setMessage(message)
    	.setPositiveButton(R.string.ok, listener)
    	.setNegativeButton(R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
    	.show();
	}

	public static void showAlertHasCancel(Context context, String title, String message, OnClickListener okListener, OnClickListener cancelListner){
    	new AlertDialog.Builder(context)
    	.setTitle(title)
    	.setMessage(message)
    	.setPositiveButton(R.string.ok, okListener)
    	.setNegativeButton(R.string.cancel,cancelListner)
    	.show();
	}

}