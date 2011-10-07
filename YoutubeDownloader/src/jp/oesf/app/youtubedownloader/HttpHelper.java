package jp.oesf.app.youtubedownloader;

import jp.oesf.app.youtubedownloader.exception.HttpHelperException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * HttpHelperクラスです。 Http通信を行います。
 */
public class HttpHelper extends DefaultHttpClient {
	/**
	 * HttpHelperクラス(Singleton)。
	 */
	private static HttpHelper httpHelper = new HttpHelper();

	/**
	 * Singletonにするためにprivate。
	 */
	private HttpHelper() {
	}

	/**
	 * このクラスのインスタンスを返します。
	 * 
	 * @return このクラスのインスタンス
	 */
	public static HttpHelper getInstance() {
		return httpHelper;
	}

	/**
	 * Http通信をしてコンテンツを取得します。
	 * 
	 * @param url
	 *            取得先のURL
	 * @return コンテンツ
	 * @throws HttpHelperException
	 *             HTTP通信時でエラー
	 */
	public HttpEntity getResponseContent(String url) throws HttpHelperException {

		HttpResponse response;

		try {

			HttpGet httpGet = new HttpGet(url);

			response = execute(httpGet);

		} catch (Exception exception) {

			throw new HttpHelperException(exception);

		}

		return statusCheck(response);

	}

	/**
	 * Http通信後のステータスチェックを行います。
	 * 
	 * @param response
	 *            レスポンス
	 * @return 取得したコンテンツ
	 * @throws HttpHelperException
	 *             Http通信のエラー
	 */
	private HttpEntity statusCheck(HttpResponse response)
			throws HttpHelperException {
		// 200 OK and 201 CREATED
		int statusCode = response.getStatusLine().getStatusCode();
		if (HttpStatus.SC_OK == statusCode
				|| HttpStatus.SC_CREATED == statusCode) {

			try {

				Log.v("HttpHelper", "Suceeded in retriving the InputStream");

				return response.getEntity();

			} catch (IllegalStateException e) {

				throw new HttpHelperException(e);

			}

		} else {

			Log.e("HttpHelper", "Connection Failed");
			Log.e("HttpHelper", "Status code = " + statusCode);

			throw new HttpHelperException("Connection Failed");

		}
	}

	/**
	 * Http通信を行い、コンテンツ情報を取得します。
	 * 
	 * @param httpHost
	 *            取得先Host情報
	 * @param httpGet
	 *            HttpGetのインスタンス
	 * @return 取得したコンテンツ情報
	 * @throws HttpHelperException
	 *             Http通信のエラー
	 */
	public HttpEntity getResponseContent(HttpHost httpHost, HttpGet httpGet)
			throws HttpHelperException {
		HttpResponse response;

		try {

			response = execute(httpHost, httpGet);

		} catch (Exception exception) {

			throw new HttpHelperException(exception);

		}

		return statusCheck(response);
	}
}
