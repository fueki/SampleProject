package com.example.gethtml;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class main extends Activity {
	private TextView mView;
	HttpResponse response;
	    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    mView = (TextView) findViewById(R.id.view);
//	    mView.setText(new String(httpGet(createURL())));
	    	
			try {
				xmlPullParser(new String(httpGet(createURL())));
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public String createURL() {
	    String apiURL = "http://news.yahooapis.jp/NewsWebService/V2/topics?";
	    String appid = "xIKkJiWxg67Q6xX1bxyKFokTS8cEMPsvdG1PCyo6jz5K1yYsC5toV3vqkYP061Q2";
	    String category = "top";
	    return String.format("%sappid=%s&pickupcategory=%s", apiURL, appid, category);
	}
	
	public static String httpGet(String strURL) {
	    // (1)try-catchによるエラー処理
	    try {
	        // (2)URLクラスを使用して通信を行う
	        URL url = new URL(strURL);
	        URLConnection connection = url.openConnection();
	        // 動作を入力に設定
	        connection.setDoInput(true);
	        InputStream stream = connection.getInputStream();
	        BufferedReader input = new BufferedReader(new InputStreamReader(stream));
	        // (3)データの取得
	        String data = "";
	        String tmp = "";
	        while ((tmp = input.readLine()) != null) {
	            data += tmp;
	        }
	        // (4)終了処理
	        stream.close();
	        input.close();
	        return data;
	    } catch (Exception e) {
	        // (5)エラー処理
	        return e.toString();
	    }
	}
	
	public void xmlPullParser(String date) throws XmlPullParserException, IOException{
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		//response.getEntity().writeTo(ostream);

		final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		final XmlPullParser parser = factory.newPullParser();

		parser.setInput(new StringReader(date));


		for(int eventType = parser.getEventType();eventType != 
			XmlPullParser.END_DOCUMENT; eventType = parser.next()){
			String tagName;
			String tagText;

			if(eventType == XmlPullParser.START_TAG){
				tagName = parser.getName();
				Log.d("tag",tagName);

				for (int i = 0; i < parser.getAttributeCount(); i++) {
					Log.d("attribute name",parser.getAttributeName(i));
					Log.d("attribute name",parser.getAttributeValue(i));
				}

				//この関数を呼ぶことでタグの中の要素にいく
				parser.next();

				if(parser.getEventType() == XmlPullParser.TEXT){
				  	tagText = parser.getText();
					Log.d("tag text",tagText);
		    		}
			}
		}	
	}
}