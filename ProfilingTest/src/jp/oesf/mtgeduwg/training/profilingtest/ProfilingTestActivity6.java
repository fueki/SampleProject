package jp.oesf.mtgeduwg.training.profilingtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jp.oesf.mtgeduwg.training.profilingtest.PhotoDBHelper.PhotoInfo;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 4.3.7　速度的なパフォーマンス問題の修正-実行
 * @author okubo
 * 
 */
public class ProfilingTestActivity6 extends Activity {
	LinearLayout linearLayout;
	TextView textView;
	ImageView imageView;
	List<PhotoInfo> photoInfos ;
	int currentPhotoIndex;
	Bitmap currentBitmap;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        try {
			preparePhotos();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		getPhotoList();
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        textView = new TextView(this);
        imageView = new ImageView(this);
        linearLayout.addView(textView);
        linearLayout.addView(imageView);
        setContentView(linearLayout);
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		int lastIndex = photoInfos.size()-1;
		if (KeyEvent.KEYCODE_DPAD_LEFT == keyCode
				|| KeyEvent.KEYCODE_DPAD_DOWN == keyCode) {

			currentPhotoIndex = currentPhotoIndex == lastIndex ? 0 : currentPhotoIndex+1; 
			setImage();

		} else if (KeyEvent.KEYCODE_DPAD_RIGHT == keyCode
				|| KeyEvent.KEYCODE_DPAD_UP == keyCode) {

			currentPhotoIndex = currentPhotoIndex == 0 ? lastIndex  : currentPhotoIndex-1; 
			setImage();
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		setImage();
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
	
    @Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentPhotoIndex", currentPhotoIndex);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		currentPhotoIndex = savedInstanceState.getInt("currentPhotoIndex");
	}
	
	public void setImage(){
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
		PhotoInfo photoInfo = photoInfos.get(currentPhotoIndex);
		textView.setText(currentPhotoIndex + ":" + photoInfo.title);
		if(currentBitmap !=null){
			currentBitmap.recycle();
			currentBitmap = null;
		}
		currentBitmap = photoDBHelper.getPhotoBitmap(photoInfo.id);
		imageView.setImageBitmap(currentBitmap);
	}
	

	private void getPhotoList() {
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
		photoInfos = photoDBHelper.getPhotoInfos();
	}

	private void preparePhotos() throws IOException{
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
    	ZipInputStream zipInputStream = null;
    	try {
    		zipInputStream = new ZipInputStream(getResources().openRawResource(R.raw.photos_s));
    		while(true){
    			
    			ZipEntry nextEntry = zipInputStream.getNextEntry();
    			if(nextEntry == null){	break; }
    			if(nextEntry.isDirectory()){ continue;	}
    			photoDBHelper.insertBitMap(nextEntry.getName(), toBytes(zipInputStream) , nextEntry.getTime());
    		}
		}finally{
			if(zipInputStream!=null){ zipInputStream.close(); }
		}
    }
    
	public static byte[] toBytes(InputStream input) throws IOException {
        
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}