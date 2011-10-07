package net.dabits.android.nicoplayer.procs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class HttpProc {
	
	protected HttpClient httpClient;
	protected HttpResponse httpResponse;
	protected HttpEntity httpEntity;
	protected InputStream inputStream;
	
	public HttpProc(){
		//DefaultHttpClientを生成
		httpClient = new DefaultHttpClient();
	}
	
	public void debug(){
		HttpHost proxy = new HttpHost("192.168.1.2", 8008);
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
	
	public boolean get(String url){
		StringBuilder uriBuilder = new StringBuilder(url);
		HttpGet request = new HttpGet(uriBuilder.toString());
		return connect(request);
	}
	
	public boolean post(String url, ArrayList<NameValuePair> params){
		StringBuilder uriBuilder = new StringBuilder(url);
		HttpPost request = new HttpPost(uriBuilder.toString());
		try {
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
		}
		return connect(request);
	}

	private boolean connect(HttpUriRequest request){

		//リクエストする
		try {
			httpResponse = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		    httpEntity = httpResponse.getEntity();
		    try {
				inputStream = httpEntity.getContent();
			} catch (IllegalStateException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	public void setHeader(String name, String value){
		httpClient.getParams().setParameter(name, value);
	}
	
	public Header[] getHeaders(String name){
		return httpResponse.getHeaders(name);
	}
	
	public void setCookie(Cookie cookie){
		((AbstractHttpClient) httpClient).getCookieStore().addCookie(cookie);
	}
	
	public List<Cookie> getCookies(){
		List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
		return cookies;
	}
	
	public String getString(){
		String line;
		String result = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((line = reader.readLine()) != null) {
			    result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public XmlPullParser getXml(){
	    final XmlPullParser parser = Xml.newPullParser();
	    try {
	        parser.setInput(new InputStreamReader(inputStream));
	        return parser;
	    } catch (XmlPullParserException e) {
	    	return null;
	    }
	}
}
