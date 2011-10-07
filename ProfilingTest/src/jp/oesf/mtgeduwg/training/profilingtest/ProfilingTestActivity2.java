package jp.oesf.mtgeduwg.training.profilingtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * for 4.1.8 データベースへの保存実行
 * @author okubo
 * 
 */
 public class ProfilingTestActivity2 extends Activity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
			preparePhotos();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	private void preparePhotos() throws IOException{
		Log.v(getClass().getName(), "preparePhotos start");
		PhotoDBHelper photoDBHelper = new PhotoDBHelper(this);
    	ZipInputStream zipInputStream = null;
    	try {
    		zipInputStream = new ZipInputStream(getResources().openRawResource(R.raw.photos));
    		int i = 1;
    		while(true){
    			
    			ZipEntry nextEntry = zipInputStream.getNextEntry();
    			if(nextEntry == null){	break; }
    			if(nextEntry.isDirectory()){ continue;	}
    			photoDBHelper.insertBitMap(nextEntry.getName(), toBytes(zipInputStream),nextEntry.getTime());
    			Log.v(getClass().getName(), "proccessing insert :" + i);
    			i++;
    			
    		}
		}finally{
			if(zipInputStream!=null){ zipInputStream.close(); }
		}
		Log.v(getClass().getName(), "end of " + new Throwable().getStackTrace()[0].getMethodName());
		
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