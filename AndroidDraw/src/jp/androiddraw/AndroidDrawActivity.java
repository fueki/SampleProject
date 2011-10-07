package jp.androiddraw;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import jp.oesf.app.youtubedownloader.XmlHelper;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;


public class AndroidDrawActivity extends Activity {


	    private static final String LogTag = "AndroidDrawActivity:";
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
				XmlHelper xmlHelper = XmlHelper.getInstance();
				StringBuffer url = new StringBuffer();
				url.append(rowModel.getThumbnailImageURL());
				final Bitmap bitmap = xmlHelper.loadImageBitmap(url
						.toString());
				if (bitmap != null) {
					rowModel.setThumbnailImage(bitmap);
				}
				publishProgress((Void) null);
	        
	        
	        try {
	            requestWindowFeature(Window.FEATURE_NO_TITLE);
	            ImageView iv = new ImageView(this);
	            
	            URL url = new URL("http://hinode.nao.ac.jp/news/071207PressRelease/okamoto-fig.png");
	            
	            InputStream is = url.openStream();
	            Bitmap bm = BitmapFactory.decodeStream(is);
	            iv.setImageBitmap(bm);
	            setContentView(iv);
	            
	        } catch (Exception e) {
	           Log.v(LogTag, e.toString());
	        }
	    }
}