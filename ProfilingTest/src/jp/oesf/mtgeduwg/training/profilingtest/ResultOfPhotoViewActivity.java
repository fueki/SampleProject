package jp.oesf.mtgeduwg.training.profilingtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jp.oesf.mtgeduwg.training.profilingtest.PhotoDBHelper.PhotoInfo;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultOfPhotoViewActivity extends Activity{
	LinearLayout linearLayout;
	TextView textView;
	ImageView imageView;
	List<PhotoInfo> photoInfos ;
	int currentPhotoIndex;
	Bitmap currentBitmap;

	
	
    @Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentPhotoIndex", currentPhotoIndex);
	}
    

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		currentPhotoIndex = savedInstanceState.getInt("currentPhotoIndex");
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        try {
			preparePhotos();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		getPhotoList();
		Context applicationContext = getApplicationContext();
        linearLayout = new LinearLayout(applicationContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(applicationContext);
        imageView = new ImageView(applicationContext);
        linearLayout.addView(textView);
        linearLayout.addView(imageView);
        setContentView(linearLayout);
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		setImage();
	}
	private void preparePhotos() throws IOException{
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
    	ZipInputStream zipInputStream = null;
    	try {
    		zipInputStream = new ZipInputStream(getResources().openRawResource(R.raw.photos));
    		while(true){
    			
    			ZipEntry nextEntry = zipInputStream.getNextEntry();
    			if(nextEntry == null){	break; }
    			if(nextEntry.isDirectory()){ continue;	}
    			photoDBHelper.insertBitMap(nextEntry.getName(), toBytes(zipInputStream),nextEntry.getTime());
    		}
		}finally{
			if(zipInputStream!=null){ zipInputStream.close(); }
		}
    }

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (KeyEvent.KEYCODE_DPAD_LEFT == keyCode
				|| KeyEvent.KEYCODE_DPAD_DOWN == keyCode) {

			currentPhotoIndex = currentPhotoIndex == photoInfos.size()-1 ? 0 : currentPhotoIndex+1; 
			setImage();

		} else if (KeyEvent.KEYCODE_DPAD_RIGHT == keyCode
				|| KeyEvent.KEYCODE_DPAD_UP == keyCode) {

			currentPhotoIndex = currentPhotoIndex == 0 ? photoInfos.size()-1  : currentPhotoIndex-1; 
			setImage();
		}
		return true;
	}




	public void setImage(){
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
		PhotoInfo photoInfo = photoInfos.get(currentPhotoIndex);
//		textView = new TextView(this);
//		imageView = new ImageView(this);
//		linearLayout.addView(textView);
//		linearLayout.addView(imageView);
//		setContentView(linearLayout);
		textView.setText(currentPhotoIndex + ":" + photoInfo.title);
		if(currentBitmap !=null){
			currentBitmap.recycle();
			currentBitmap = null;
		}
//		System.gc();
		currentBitmap = photoDBHelper.getPhotoBitmap(photoInfo.id);
		imageView.setImageBitmap(currentBitmap);
//		imageView.invalidate();
	}


	

	@Override
	protected void onPause() {
		super.onPause();
		if(currentBitmap!=null){
			currentBitmap.recycle();
		}
		currentBitmap = null;
		linearLayout = null;
		textView= null;
		imageView= null;
		photoInfos= null ;
		currentBitmap= null;
	}

	private void getPhotoList() {
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
		photoInfos = photoDBHelper.getPhotoInfos();
	}
	
	private static byte[] toBytes(InputStream input) throws IOException {
        
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }



}
