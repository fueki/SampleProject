package jp.samplecamera.android;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

public class SampleView extends SurfaceView implements Callback,PictureCallback{
	private static final String TAG = "SampleView"; 
	private static final String APPLICATION_NAME = "map_google_image";  
	private static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  
	private static final String PATH = Environment.getExternalStorageDirectory().toString() + "/" + APPLICATION_NAME;  

	private Camera camera;

//	private Context context;
	/**
	 * Callbackメソッドをセットして、SurfaceHolderのタイプをPUSH_BUFFERSにする
	 * @param context
	 */
	public SampleView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.e(TAG, "SampleView");
//		this.context = context;
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.e(TAG, "surfaceChanged");
		/**
		 * 【ここ重要ね！！】
		 * OS 2.1 以上の端末では、setPreviewSize メソッドは使わないほうが良い。
		 * Camera.Parameters parameters=camera.getParameters()を使用するよろし！
		 */
		//Camera.Parameters p = camera.getParameters();
		//p.setPreviewSize(width, height);
		Camera.Parameters parameters=camera.getParameters();
		camera.setParameters(parameters);
		camera.startPreview();
	}

	/**
	 * サーフェイスホルダーが作られた時に、カメラオブジェクトを生成
	 * サーフェイスホルダーが捨てられた時に、カメラオブジェクトを開放
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		try {
			Log.e(TAG, "surfaceCreated");
			camera = Camera.open();
			camera.setPreviewDisplay(holder);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.e(TAG, "surfaceDestroyed");
		camera.stopPreview();
		camera.release();
	}

	/**
	 * 別フォルダとして保存する
	 * @param data
	 * @param camera
	 */
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, null);
//		MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bmp, "", null);
//		camera.startPreview();
	    long dateTaken = System.currentTimeMillis();  
	    String name = createName(dateTaken) + ".jpg";  
	    addImageAsApplication(getContext().getContentResolver(), name, dateTaken, PATH, name, bmp, null);  

	    Toast.makeText(getContext(), "保存しました。", Toast.LENGTH_LONG).show();
	    camera.startPreview();
	}
	
	private static String createName(long dateTaken) {  
        return DateFormat.format("yyyy-MM-dd_kk.mm.ss", dateTaken).toString();  
    }
	
	public static Uri addImageAsApplication(ContentResolver cr, String name,  
	        long dateTaken, String directory,  
	        String filename, Bitmap source, byte[] jpegData) {  
	  
	    OutputStream outputStream = null;  
	    String filePath = directory + "/" + filename;  
	    try {  
	        File dir = new File(directory);  
	        if (!dir.exists()) {  
	            dir.mkdirs();  
	            Log.d(TAG, dir.toString() + " create");  
	        }  
	        File file = new File(directory, filename);  
	        if (file.createNewFile()) {  
	            outputStream = new FileOutputStream(file);  
	            if (source != null) {  
	                source.compress(CompressFormat.JPEG, 75, outputStream);  
	            } else {  
	                outputStream.write(jpegData);  
	            }  
	        }  
	  
	    } catch (FileNotFoundException ex) {  
	        Log.w(TAG, ex);  
	        return null;  
	    } catch (IOException ex) {  
	        Log.w(TAG, ex);  
	        return null;  
	    } finally {  
	        if (outputStream != null) {  
	            try {  
	                outputStream.close();  
	            } catch (Throwable t) {  
	            }  
	        }  
	    }  
	      
	    ContentValues values = new ContentValues(7);  
	    values.put(Images.Media.TITLE, name);  
	    values.put(Images.Media.DISPLAY_NAME, filename);  
	    values.put(Images.Media.DATE_TAKEN, dateTaken);  
	    values.put(Images.Media.MIME_TYPE, "image/jpeg");  
	    values.put(Images.Media.DATA, filePath);  
	    return cr.insert(IMAGE_URI, values);  
	}  
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		if(me.getAction() == MotionEvent.ACTION_DOWN){
			camera.takePicture(null, null, this);
		}
		return true;
	}
}
