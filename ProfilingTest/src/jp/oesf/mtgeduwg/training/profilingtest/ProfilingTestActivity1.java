package jp.oesf.mtgeduwg.training.profilingtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * for 4.1.4 ZIP読み込みの実行
 * @author okubo
 *
 */
public class ProfilingTestActivity1 extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
        	//とりあえずZipをBitMapのリストで取得する
			@SuppressWarnings("unused")
			List<Bitmap> photos = getPhotos();
       } catch (IOException e) {
            e.printStackTrace();
       }
    }
    
	private List<Bitmap> getPhotos() throws IOException{
    	ZipInputStream zipInputStream = null;
    	List<Bitmap> results = new ArrayList<Bitmap>();
    	try {
    		zipInputStream = new ZipInputStream(getResources().openRawResource(R.raw.photos));
    		while(true){
    			
    			ZipEntry nextEntry = zipInputStream.getNextEntry();
    			if(nextEntry == null){
    				break;
    			}
    			if(nextEntry.isDirectory()){
    				continue;
    			}
    			Bitmap bitmap = BitmapFactory.decodeStream(zipInputStream);
    			results.add(bitmap);
    		}
		}finally{
			if(zipInputStream!=null){
				zipInputStream.close();
			}
		}
		return results;
    }

}