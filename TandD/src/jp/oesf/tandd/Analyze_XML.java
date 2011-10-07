package jp.oesf.tandd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jp.oesf.tandd.model.FeedEntity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.widget.Toast;

public class Analyze_XML {
	ArrayList<FeedEntity> rssList;
	
	public ArrayList<FeedEntity> parseSax(Context context, String xml) {
		rssList = new ArrayList<FeedEntity>();
		InputStream is = null;
		try {
			// 解析対象のストリームを取得する
//			is = context.getAssets().open("oesf_rss.xml");
			is = new ByteArrayInputStream(xml.getBytes());
			// パーサのFactoryを作成
			SAXParserFactory saxParaser = SAXParserFactory.newInstance();

			// パーサを取得
			SAXParser sp = saxParaser.newSAXParser();
			// イベントハンドラを作成
			SaxHandler sh = new SaxHandler();

			// イベントハンドラに入力データとイベントハンドラを渡す
			sp.parse(is, sh);
			
			return rssList;
			
		} catch (Exception e) {
			Toast.makeText(context, "xml解析失敗", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	class SaxHandler extends DefaultHandler {
		
		//タグ開始フラグ
		boolean isEntry;
		boolean isLink = false;
		boolean isDescription = false;
		boolean isTitle = false;
		boolean isPubDate = false;
		String link = "";
		String description = "";
		String pubdate = "";
		String title = "";
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			if("item".equals(localName)) {
				//entryタグの場合はisEntryをtrueにする
				isEntry = true;
			}
			//entryタグの中にいるか判定
			if(isEntry) {
				if ("pubDate".equals(localName)) {
					isPubDate = true;
				}
				if ("description".equals(localName)){
					isDescription = true;
				}
				if (!isLink && "link".equals(localName)){
					isLink = true;
					
				}
				if ("title".equals(localName)){
					isTitle = true;
				}
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
		throws SAXException {
			// TODO Auto-generated method stub
			if (isEntry) {
				if (isPubDate) {
					//PubDateを取得する
					pubdate += new String(ch, start, length);
//					isPubDate = false;
				}
				if (isDescription) {
					//descriptionを取得する
					description += new String(ch, start, length);
//					isDescription = false;
				}
				if (isTitle) {
					// titleを取得する

					title += new String(ch, start, length);
//					isTitle = false;
				}
				if (isLink) {
					link += new String(ch, start, length);
//					isLink = false;
				}
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName)
		throws SAXException {
			// TODO Auto-generated method stub
			if("link".equals(localName)) {
				isLink = false;
			}
			
			if ("title".equals(localName)) {
				isTitle = false;
			}
			
			if ("description".equals(localName)) {
				isDescription = false;
			}
			
			if("pubDate".equals(localName)) {
				isPubDate = false;
			}
			
			if ("item".equals(localName)) {
				// feedEntityに値を設定する 
				FeedEntity feedEntity = new FeedEntity();
				feedEntity.setTitle(title);
				feedEntity.setLink(link);
				feedEntity.setDescription(description);
				feedEntity.setPubDate(pubdate);

				rssList.add(feedEntity);
				
				//値をクリア
				title = "";
				link = "";
				description = "";
				pubdate = "";
				isLink = false;
				isEntry = false;
			}
		}
	}
}
