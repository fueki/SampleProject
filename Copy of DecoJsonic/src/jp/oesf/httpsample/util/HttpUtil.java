package jp.oesf.httpsample.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

import jp.oesf.httpsample.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpUtil {
	public static byte[] getByteArrayFromURL(String strUrl) {
		byte[] line = new byte[1024];
		byte[] result = null;
		HttpURLConnection con = null;
		InputStream in = null;
		ByteArrayOutputStream out = null;
		int size = 0;
		try {
			// HTTP接続のオープン
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			in = con.getInputStream();

			// バイト配列の読み込み
			out = new ByteArrayOutputStream();
			while (true) {
				size = in.read(line);
				if (size <= 0) {
					break;
				}
				out.write(line, 0, size);
			}
			result = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.disconnect();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static Bitmap getImage(String url) {
		byte[] byteArray = getByteArrayFromURL(url);
		if(byteArray == null){
			//Log.e("@@@@@@@@@@@", "キタコレ" + BitmapFactory.decodeByteArray(R.drawable.icon, 0, byteArray.length));
//			byteArray = 
		}
		return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
	}
}
