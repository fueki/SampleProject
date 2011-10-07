package jp.android.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssXMLSample extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //XMLの取得
        String uri = "http://www.5ive.info/blog/feed";
        
        //httpクライアントの設定
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet();
        
        try{
        	get.setURI(new URI(uri));
        	HttpResponse res = client.execute(get);
        	InputStream in = res.getEntity().getContent();
        	
        	//パーサーの設定
        	XmlPullParser parser = Xml.newPullParser();
        	parser.setInput(in, "UTF-8");
        	
        	//アダプターの設定
        	ArrayAdapter adapter;
        	adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        	
        	int eventType = parser.getEventType();
        	
        	while(eventType != XmlPullParser.END_DOCUMENT){
        		switch(eventType){
        		case XmlPullParser.START_TAG:
        			String tag = parser.getName();
        			if("title".equals(tag)){
        				//深度が４のtitleだけ取得
        				int depth = parser.getDepth();
        				if(depth == 4){
        					String txt = parser.nextText();
        					adapter.add(txt);
        				}
        			}
        			break;
        		}
        		eventType = parser.next();
        	}
        	//リストビューにアダプターをセット
        	ListView lv = (ListView)findViewById(R.id.listView1);
        	lv.setAdapter(adapter);
        } catch (URISyntaxException e) {
        	e.printStackTrace();
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}