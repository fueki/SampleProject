package net.dabits.android.nicoplayer.procs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.net.Uri;

public class CommentProc {
	
	protected HttpProc httpProc;
	protected ArrayList<HashMap<String, String>> comments;
	protected HashMap<String, String> thread;
	
	public CommentProc(){
		httpProc = new HttpProc();
	}
	
	public boolean parse(String url, String thread){
		Uri.Builder uri = new Uri.Builder();
		uri.appendPath("thread");
		uri.appendQueryParameter("version", "20061206");
		uri.appendQueryParameter("thread", thread);
		
		httpProc.get(url + uri.toString().substring(1));
		comments = parseXml(httpProc.getXml());
		return comments.size() > 0;
	}
	
	public ArrayList<HashMap<String, String>> getComments(){
		return comments;
	}
	
	public HashMap<String, String> getThread(){
		return thread;
	}
	
	private ArrayList<HashMap<String, String>> parseXml(XmlPullParser parser){
		ArrayList<HashMap<String, String>> comments = null;
		int eventType = 0;
		boolean done = false;
		String name;
		HashMap<String, String> currentComment = null;
		try {
			eventType = parser.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT && !done){
				switch(eventType){
					case XmlPullParser.START_DOCUMENT:
						comments = new ArrayList<HashMap<String, String>>();
						break;
					case XmlPullParser.START_TAG:
						name = parser.getName();
						if(name.equalsIgnoreCase("thread")){
							thread = new HashMap<String, String>();
							for(int i = 0 ; i < parser.getAttributeCount() ; i++){
								thread.put(
									parser.getAttributeName(i),
									parser.getAttributeValue(i)
								);
							}
						}
						if(name.equalsIgnoreCase("chat")){
							currentComment = new HashMap<String, String>();
							for(int i = 0 ; i < parser.getAttributeCount() ; i++){
								currentComment.put(
									parser.getAttributeName(i),
									parser.getAttributeValue(i)
								);
							}
							currentComment.put("body", parser.nextText());
						}
						break;
					case XmlPullParser.END_TAG:
						name = parser.getName();
						if(name.equalsIgnoreCase("chat") && currentComment != null){
							comments.add(currentComment);
						}else if(name.equalsIgnoreCase("packet")){
							done = true;
						}
						break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
	}
}
