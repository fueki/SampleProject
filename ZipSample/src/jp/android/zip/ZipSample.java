package jp.android.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.hishidama.zip.ZipEntry;
import jp.hishidama.zip.ZipOutputStream;
import android.app.Activity;
import android.os.Bundle;

public class ZipSample extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String password = "test";
        ZipOutputStream zos;
		try {
			zos = new ZipOutputStream(new FileOutputStream(new File("/data/data/jp.android.zip/sample.zip")), "MS932");
		
			zos.setPassword(password.getBytes("MS932"));

			ZipEntry ze = new ZipEntry("Test.txt");

			zos.putNextEntry(ze);
			
	        zos.write("PasswordïtÇ´ZipçÏê¨äÆóπÅAÇ®ÇﬂÇ≈Ç∆Ç§ÅI".getBytes());

	        zos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}