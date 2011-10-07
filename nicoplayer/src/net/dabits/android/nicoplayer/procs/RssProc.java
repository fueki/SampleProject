package net.dabits.android.nicoplayer.procs;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class RssProc {
	
	protected HttpProc httpProc;
	protected ArrayList<HashMap<String, String>> videoItems;
	
	public RssProc(){
		httpProc = new HttpProc();
	}
	
	public boolean parse(String url){
		httpProc.get(url);
		videoItems = parseXml(httpProc.getXml());
		return videoItems.size() > 0;
	}
	
	public ArrayList<HashMap<String, String>> getVideoItems(){
		return videoItems;
	}
	
	private ArrayList<HashMap<String, String>> parseXml(XmlPullParser parser){
		ArrayList<HashMap<String, String>> videoItems = null;
		int eventType = 0;
		boolean done = false;
		String name;
		HashMap<String, String> currentVideoItem = null;
		final XmlPullParser descParser = Xml.newPullParser();
		try {
			eventType = parser.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT && !done){
				switch(eventType){
					case XmlPullParser.START_DOCUMENT:
						videoItems = new ArrayList<HashMap<String, String>>();
						break;
					case XmlPullParser.START_TAG:
						name = parser.getName();
						if(name.equalsIgnoreCase("item")){
							currentVideoItem = new HashMap<String, String>();
						}else if(currentVideoItem != null){
							if(name.equalsIgnoreCase("title")){
								currentVideoItem.put("title", parser.nextText());
							}else if(name.equalsIgnoreCase("link")){
								currentVideoItem.put("url", parser.nextText());
							}else if(name.equalsIgnoreCase("description")){
								String[] descs = parser.nextText().split("\n");
								for(int i = 0 ; descs.length > i ; i++){
									descParser.setInput(new StringReader(descs[i]));
									parseDescription(currentVideoItem, descParser);
								}
							}
						}
						break;
					case XmlPullParser.END_TAG:
						name = parser.getName();
						if(name.equalsIgnoreCase("item") && currentVideoItem != null){
							videoItems.add(currentVideoItem);
						}else if(name.equalsIgnoreCase("channel")){
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
		
		return videoItems;
	}
	
	private void parseDescription(HashMap<String, String> videoItem, XmlPullParser parser){
		int eventType = 0;
		String name;
		String attrName;
		try {
			eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch(eventType){
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
						name = parser.getName();
						if(name.equalsIgnoreCase("p")){
							String cl = null;
							for(int i=0 ; parser.getAttributeCount() > i ; i++){
								attrName = parser.getAttributeName(i);
								if(attrName == "class"){
									cl = parser.getAttributeValue(i);
								}
							}
							if(cl.equalsIgnoreCase("nico-description")){
								String text = parser.nextText();
								if(text.length() > 36){
									text = text.substring(0, 36) + "...";
								}
								videoItem.put("description", text);
							}
						}else if(name.equalsIgnoreCase("img")){
							for(int i=0 ; parser.getAttributeCount() > i ; i++){
								attrName = parser.getAttributeName(i);
								if(attrName == "src"){
									videoItem.put("thumbnailUrl", parser.getAttributeValue(i));
								}
							}
						}else if(name.equalsIgnoreCase("strong")){
							String cl = "";
							for(int i=0 ; parser.getAttributeCount() > i ; i++){
								attrName = parser.getAttributeName(i);
								if(attrName == "class"){
									cl = parser.getAttributeValue(i);
								}
							}
							if(cl.equalsIgnoreCase("nico-info-number")){
								videoItem.put("number", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-length")){
									videoItem.put("length", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-date")){
								videoItem.put("date", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-total-view")){
								videoItem.put("totalView", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-total-res")){
								videoItem.put("totalRes", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-total-mylist")){
								videoItem.put("totalMylist", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-daily-view")){
								videoItem.put("dailyView", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-daily-res")){
								videoItem.put("dailyRes", parser.nextText());
							}else if(cl.equalsIgnoreCase("nico-info-daily-mylist")){
								videoItem.put("dailyMylist", parser.nextText());
							}
						}
						break;
					case XmlPullParser.END_TAG:
						break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
