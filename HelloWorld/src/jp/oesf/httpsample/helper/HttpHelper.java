package jp.oesf.httpsample.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import jp.oesf.httpsample.constant.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpHelper extends DefaultHttpClient {
	
	private static final String TAG = "HttpHelper";

    public static String getResponseContent(String requestJsonString) throws HttpHelperException , ClientProtocolException, IOException{
		Log.v(TAG,">>>>>>>> Start HttpRequest <<<<<<<<");

		final HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Constants.URL);
		StringEntity se;
		try {
			se = new StringEntity(requestJsonString, "UTF-8");
			httpPost.setEntity(se);
			Log.v(TAG, se.toString() + ": " + se.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Log.v(TAG,"__Request:" + requestJsonString);
		//HTTP通信開始
		final HttpResponse response = client.execute(httpPost);

		//レスポンスチェック
		int statusCode = response.getStatusLine().getStatusCode();
		Log.v(TAG, "__Status: " + statusCode);
		HttpEntity entity = null;
		entity = response.getEntity();
		String responceString = EntityUtils.toString(entity);
		Log.v(TAG,"__Responce:" + responceString);
		
		if (statusCode == HttpStatus.SC_OK) {
			Log.v(TAG,">>>>>>>> End HttpRequest <<<<<<<<");
			return responceString;

		}else{
			Log.v(TAG,">>>>>>>> End HttpRequest <<<<<<<<");
			throw new HttpHelperException("Connection Failed");
		}
    }
}