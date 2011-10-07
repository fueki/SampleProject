package jp.webapi.httpsample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml.Encoding;
import android.view.View;
import android.widget.TextView;

public class HttpSample extends Activity {
    private static final String TAG = "HttpSample";
    // 接続先URL
    private static final String URL = "http://www.oesf.jp/";        //ネットワーク接続可
//      private static final String URL = "http://[PCのIPアドレス]:8080/oesf.html";  //ネットワーク接続不可

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
    }

    public void onClickButton(View v) {
    	//DefaultHttpClientを生成
    	HttpClient httpClient = new DefaultHttpClient(); 

    	//ブログのRSS	
    	StringBuilder uriBuilder = new StringBuilder("http://xiangcai.at.webry.info/rss/index.rdf");
    	//HttpGetを生成する
    	HttpGet request = new HttpGet(uriBuilder.toString()); 
    	HttpResponse response1 = null;

    	//リクエストする
    	try {
    	response1 = httpClient.execute(request);
    	} catch (ClientProtocolException e1) {
    	e1.printStackTrace();
    	} catch (IOException e1) {
    	e1.printStackTrace();
    	}
    	
    }

    
    private String getContents(HttpResponse res) {

            InputStream in = null;
            ByteArrayOutputStream out = null;
            // 一度に読み込むサイズ
            byte[] line = new byte[1024];
            String data = null;
            int size = 0;

            try {
                    // TODO 【HTTP通信②】 No.02 レスポンスからInputStreamを取得する
                    in = res.getEntity().getContent();
                    out = new ByteArrayOutputStream();

                    //  TODO 【HTTP通信②】 No.03 HTTPレスポンスデータを読み込む
                    while (true) {
                            size = in.read(line);
                            if (size <= 0) {
                                    break;
                            }
                            out.write(line, 0, size);
                    }

                    // TODO 【HTTP通信②】  No.04 バイト配列を文字列に変換する
                    data = new String(out.toByteArray(),"EUC-JP");

            } catch (Exception e) {
                    Log.e(TAG, Log.getStackTraceString(e));
            }finally{
                    try {
                            if (in != null) {
                                    in.close();
                            }
                            if (out != null) {
                                    out.close();
                            }
                    } catch (Exception e) {
                            Log.e(TAG, Log.getStackTraceString(e));
                    }
            }
            return data;
    }
}