package jp.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class HttpsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickHttp(View view){
    	SchemeRegistry schemeRegistry = new SchemeRegistry();
    	schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

    	SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
    	schemeRegistry.register(new Scheme("https", (SocketFactory) sslSocketFactory, 443));

    	HttpParams params = new BasicHttpParams();
    	HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
    	HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

    	Uri.Builder uriBuilder = new Uri.Builder();
    	uriBuilder.path("/login");

    	HttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
    	HttpPost post = new HttpPost(uriBuilder.build().toString());

    	List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
    	postParams.add(new BasicNameValuePair("id", "fueki"));
    	postParams.add(new BasicNameValuePair("password", "12345"));

    	try {
    	    post.setEntity(new UrlEncodedFormEntity(postParams, HTTP.UTF_8));
    	    HttpResponse httpResponse = client.execute(new HttpHost("example.com", 443, "https"), post);
    	    Log.e("ここまで", "来てる１");
    	    // レスポンスコードなど取得
    	} catch (ClientProtocolException e) {
    	    throw new RuntimeException(e);
    	} catch (IOException e) {
    	    throw new RuntimeException(e);
    	}
    }
}