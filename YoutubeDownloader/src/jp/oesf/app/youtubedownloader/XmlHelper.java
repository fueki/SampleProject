package jp.oesf.app.youtubedownloader;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jp.oesf.app.youtubedownloader.model.RowModel;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

public class XmlHelper {
	/** 最大行数 */
	private static final int MAX_RESULTS = 20;
	/** ファイル読み取り用バッファサイズ */
	private static final int IO_BUFFER_SIZE = 4 * 1024;
	/** タグ */
	private static final String TAG = "XmlHelper";
	/** Singleton用 */
	private static XmlHelper xmlHelper = new XmlHelper();

	/**
	 * This class is singleton.
	 */
	private XmlHelper() {
	}

	/**
	 * インスタンスの取得。
	 * 
	 * @return このクラスのインスタンス
	 */
	public static XmlHelper getInstance() {
		return xmlHelper;
	}

	/**
	 * レスポンス情報を元に、パースします。(XmlPullParser)
	 * 
	 * @param entity
	 *            ネットワークのentity
	 * @return パース結果
	 * @throws IOException
	 * @throws ParseException
	 * @throws XmlPullParserException
	 */
	public List<RowModel> parseTableModel(final HttpEntity entity)
			throws ParseException, IOException, XmlPullParserException {

		final List<RowModel> result = new ArrayList<RowModel>(MAX_RESULTS);

		InputStream inputStream = entity.getContent();
		// XmlPullParser XmlPullParserを定義する
		final XmlPullParser parser = Xml.newPullParser();
		// XmlPullParser パースする文字列のストリームを渡す
		parser.setInput(new InputStreamReader(inputStream));
		int eventType;
		// XmlPullParser パースしている階層を取得
		final int depth = parser.getDepth();
		// XmlPullParser パースの終了条件
		while (((eventType = parser.next()) != XmlPullParser.END_DOCUMENT || parser
				.getDepth() > depth)) {
			// XmlPullParser eventTypeが開始タグか判定
			if (eventType == XmlPullParser.START_TAG) {
				// XmlPullParser タグの名前を取得する
				String tag = parser.getName();
				// XmlPullParser タグの名前が"entry"なら、parseToRowModelを呼び出す
				if ("entry".equals(tag)) {
					RowModel rowModel = parse2RowModel(parser);
					result.add(rowModel);
				}
			}
		}

		closeStream(inputStream);

		return result;
	}

	/**
	 * XMLPullParserでパースし、RowModelにデータをいれます。
	 * 
	 * @param parser
	 *            パーサー
	 * @return パース後のRowModel
	 * @throws XmlPullParserException
	 *             パースエラー
	 * @throws IOException
	 *             IOエラー
	 */
	private RowModel parse2RowModel(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		RowModel rowModel = new RowModel();
		int eventType;
		String tag = null;
		// パースする階層を取得する
		final int depth = parser.getDepth();
		// パースの終了条件
		while (((eventType = parser.next()) != XmlPullParser.END_DOCUMENT || parser
				.getDepth() > depth)) {
			// eventTypeが開始タグか判定
			if (eventType == XmlPullParser.START_TAG) {
				tag = parser.getName();
				// タグの名前が"id"なら
				if ("id".equals(tag)) {
					// 次に移動する。
					parser.next();
					// parserからテキスト情報を取得し、rowModelのURLにセットする。
					rowModel.setUrl(parser.getText());
				} else if ("description".equals(tag)) {
					// 次に移動する。
					parser.next();
					// parserからテキスト情報を取得する。
					String summary = parser.getText();
					// 入りきらないので20文字でカットする。
					if (summary != null && 0 < summary.length()) {
						if (20 < summary.length()) {
							summary = summary.substring(0, 20);
						}
					}
					// rowModelのSUMMARYにセットする。
					rowModel.setSummary(summary);
					// XmlPullParser タグの名前が"thumbnail"なら
				} else if ("thumbnail".equals(tag)) {
					if (rowModel.getThumbnailImageURL() == null) {
						// parserの属性からurlを取得し、rowModelのThumbnailImageにセットする。
						rowModel.setThumbnailImageURL(parser.getAttributeValue(
								null, "url"));
					}
					// タグの名前が"title"なら
				} else if ("title".equals(tag)) {
					// 次に移動する。
					parser.next();
					// parserからテキスト情報を取得し、rowModelのTitleにセットする。
					rowModel.setTitle(parser.getText());
				}
				// eventTypeが終了タグか判定
			} else if (eventType == XmlPullParser.END_TAG) {
				// タグの名前を取得する
				tag = parser.getName();
				// タグの名前が"entry"ならbreakする
				if ("entry".equals(tag)) {
					break;
				}
			}
		}
		return rowModel;
	}

	/**
	 * レスポンス情報を元に、パースします。(DOM)
	 * 
	 * @param entity
	 *            ネットワークのentity
	 * @return パース結果
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws ParserConfigurationException
	 */
	public List<RowModel> parseTableModelDom(final HttpEntity entity)
			throws IllegalStateException, IOException,
			ParserConfigurationException, SAXException {

		final List<RowModel> result = new ArrayList<RowModel>(MAX_RESULTS);

		InputStream inputStream = entity.getContent();
		// ドキュメントビルダーファクトリを生成
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		dbfactory.setNamespaceAware(true);
		// ドキュメントビルダーを生成
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		// パースを実行してDocumentオブジェクトを取得
		Document doc = builder.parse(inputStream);
		// ルート要素を取得
		Element root = doc.getDocumentElement();
		// entryタグを取得
		NodeList entryList = root.getElementsByTagName("entry");
		for (int i = 0; i < entryList.getLength(); i++) {
			RowModel rowModel = new RowModel();
			Element entryElement = (Element) entryList.item(i);
			Element idElement = (Element) entryElement.getFirstChild();
			String url = idElement.getFirstChild().getNodeValue();
			rowModel.setUrl(url);
			// NSが"http://search.yahoo.com/mrss/"で"description"タグを取得します。
			NodeList desriptionList = entryElement.getElementsByTagNameNS(
					"http://search.yahoo.com/mrss/", "description");
			// Nodeからテキスト情報を取得。
			String summary = desriptionList.item(0).getFirstChild()
					.getNodeValue();
			// 入りきらないので20文字でカットする。
			if (summary != null && 0 < summary.length()) {
				if (20 < summary.length()) {
					summary = summary.substring(0, 20);
				}
			}
			// 値をRowModelにセットします。
			rowModel.setSummary(summary);
			// NSが"http://search.yahoo.com/mrss/"で"thumbnail"タグを取得します。
			NodeList thumbnailList = entryElement.getElementsByTagNameNS(
					"http://search.yahoo.com/mrss/", "thumbnail");
			// thumbnail elementを取得します。
			Element thumbnailElement = (Element) thumbnailList.item(0);
			// thumbnail elementから"url"属性の値を取得します。
			String thumbnail = thumbnailElement.getAttribute("url");
			// 値をRowModelにセットします。
			rowModel.setThumbnailImageURL(thumbnail);

			// NSが"http://search.yahoo.com/mrss/"で"title"タグを取得します。
			NodeList titleList = entryElement.getElementsByTagNameNS(
					"http://search.yahoo.com/mrss/", "title");
			// Nodeからテキスト情報を取得。
			String title = titleList.item(0).getFirstChild().getNodeValue();
			// 値をRowModelにセットします。
			rowModel.setTitle(title);

			result.add(rowModel);

		}

		closeStream(inputStream);

		return result;
	}

	/**
	 * レスポンス情報を元に、パースします。(DOM)
	 * 
	 * @param entity
	 *            ネットワークのentity
	 * @return パース結果
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws ParserConfigurationException
	 */
	public List<RowModel> parseTableModelSax(final HttpEntity entity)
			throws IllegalStateException, IOException,
			ParserConfigurationException, SAXException {

		List<RowModel> result = new ArrayList<RowModel>(MAX_RESULTS);
		InputStream inputStream = entity.getContent();
		// パーサのFactoryを作成 名前からしてFactoryパターンで生成する。
		SAXParserFactory saxParaser = SAXParserFactory.newInstance();
		/* パーサを取得 */
		SAXParser sp = saxParaser.newSAXParser();
		/* イベントハンドラを作成 */
		SaxHandler sh = new SaxHandler();

		/* イベントハンドラに入力データとイベントハンドラを渡す */
		sp.parse(inputStream, sh);
		result = sh.getResult();

		closeStream(inputStream);

		return result;
	}

	/**
	 * Sax用のHandlerクラス
	 */
	private class SaxHandler extends DefaultHandler {
		boolean isEntry = false;
		boolean isId = false;
		boolean isSummary = false;
		boolean isTitle = false;
		boolean isThumbnail = false;
		String url = null;
		String summary = null;
		String thumbnail = null;
		String title = null;
		final List<RowModel> result = new ArrayList<RowModel>(MAX_RESULTS);

		/**
		 * 要素の開始タグ読み込み時
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) {
			if ("entry".equals(localName)) {
				// Entry タグの開始
				isEntry = true;
			}
			if (isEntry) {
				if ("id".equals(localName)) {
					isId = true;
				}
				if ("description".equals(localName)
						&& "http://search.yahoo.com/mrss/".equals(uri)) {
					isSummary = true;
				}
				if (!isThumbnail && "thumbnail".equals(localName)
						&& "http://search.yahoo.com/mrss/".equals(uri)) {
					thumbnail = attributes.getValue("url");
					isThumbnail = true;

				}
				if ("title".equals(localName)
						&& "http://search.yahoo.com/mrss/".equals(uri)) {
					isTitle = true;
				}

			}
		}

		/**
		 * テキストデータ読み込み時
		 */
		@Override
		public void characters(char[] ch, int offset, int length) {
			if (isEntry) {
				if (isId) {
					url = new String(ch, offset, length);
					isId = false;
				}
				if (isSummary) {
					summary = new String(ch, offset, length);
					if (summary != null && 0 < summary.length()) {
						if (20 < length) {
							summary = summary.substring(0, 20);
						}
					}
					isSummary = false;
				}
				if (isTitle) {
					title = new String(ch, offset, length);
					isTitle = false;
				}
			}

		}

		/**
		 * 要素の終了タグ読み込み時
		 */
		@Override
		public void endElement(String uri, String localName, String qName) {
			if ("entry".equals(localName)) {
				// Entry タグの終了
				RowModel rowModel = new RowModel();
				rowModel.setUrl(url);
				rowModel.setSummary(summary);
				rowModel.setThumbnailImageURL(thumbnail);
				rowModel.setTitle(title);

				result.add(rowModel);

				url = null;
				summary = null;
				thumbnail = null;
				title = null;
				isThumbnail = false;
			}
		}

		/**
		 * 結果受け渡し用のメソッド
		 * 
		 * @return パース後のデータ
		 */
		public List<RowModel> getResult() {
			return result;
		}
	}

	/**
	 * URLから画像ファイルを取得します。
	 * 
	 * @param url
	 *            画像ファイルのURL
	 * @return 画像ファイル
	 */
	public Bitmap loadImageBitmap(final String url) {

		Bitmap bitmap = null;

		InputStream in = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream(),
					IO_BUFFER_SIZE);

			bitmap = BitmapFactory.decodeStream(in);
		} catch (final IOException e) {
			Log.e(TAG, "Could not load photo: " + this, e);
		} finally {
			closeStream(in);
		}

		return bitmap;
	}

	/**
	 * ストリームのクローズ処理を行います。
	 * 
	 * @param stream
	 *            クローズするStream
	 */
	private void closeStream(final Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (final IOException e) {
				Log.e(TAG, "Could not close stream", e);
			}
		}
	}
}