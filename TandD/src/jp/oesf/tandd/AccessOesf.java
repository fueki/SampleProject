package jp.oesf.tandd;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class AccessOesf {

	public String conectOesf(Context context) {
		// 接続先URL
		String url = "http://www.oesf.jp/modules/news/index.php?page=rss";
		// DefaultHttpClientオブジェクトの生成
		DefaultHttpClient client = new DefaultHttpClient();
		// GETメソッドで接続するリクエストオブジェクトの生成
		HttpGet getRss = new HttpGet(url);

		try {
			// リクエストを発行してレスポンスを取得する
			HttpResponse res = client.execute(getRss);
			// ステータスコードのチェック
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// レスポンス情報を取得する
				String xml = getContents(res);
				return xml;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "タイムアウトです", Toast.LENGTH_LONG).show();
		}

		return null;
	}

	public String getContents(HttpResponse res) {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		// 一度に読み込むサイズ
		byte[] line = new byte[1024];
		String data = null;
		int size = 0;
		try {
			// レスポンスからInputStreamを取得する
			in = res.getEntity().getContent();
			out = new ByteArrayOutputStream();
			// HTTPレスポンスデータを読み込む
			while (true) {
				size = in.read(line);
				if (size <= 0) {
					break;
				}
				out.write(line, 0, size);
			}
			// バイト配列を文字列に変換する
			data = new String(out.toByteArray());
		} catch (Exception e) {
			Log.e("TAG", Log.getStackTraceString(e));
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				Log.e("TAG", Log.getStackTraceString(e));
			}
		}
		return data;
	}
}
