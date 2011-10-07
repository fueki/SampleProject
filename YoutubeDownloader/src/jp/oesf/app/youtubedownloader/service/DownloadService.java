package jp.oesf.app.youtubedownloader.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jp.oesf.app.youtubedownloader.HttpHelper;
import jp.oesf.app.youtubedownloader.VideoActivity;
import jp.oesf.app.youtubedownloader.exception.HttpHelperException;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * ダウンロードのサービスクラス。 このクラスは動画ファイルのダウンロードを行い、ステータスバーに通知します。
 */
public class DownloadService extends Service {
	/** タグ */
	private static final String TAG = "DownloadService";
	/** download directory */
	private static final String DOWNLOAD_DIR = "/sdcard/video/";
	/** notification manager */
	private NotificationManager notificationManager;
	/** notifiy ID */
	private int notifikationId = 0;
	/** アクティブなID管理用 */
	private final List<Integer> activeIdList = new ArrayList<Integer>();
	/** intent */
	private Intent intent;
	private static final String HOST_URL = "www.youtube.com";
	private static final String FEED_INFO_URL = "/get_video_info";
	private static final String FEED_VIDEO_URL = "/get_video";
	private static final String PARAM_ID = "video_id";
	private static final String PARAM_TOKEN = "t";
	private static final String PARAM_FMT = "fmt";


	/**
	 * Notification managerの初期設定を行います。
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * サービスのバインドを行います。
	 * 
	 * @param intent
	 *            インテント
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// IDownloadService
		return downloadServiceIf;
	}

	/**
	 * Download Serviceインタフェース
	 */
	private final IDownloadService.Stub downloadServiceIf = new IDownloadService.Stub() {
		/**
		 * ファイルのダウンロードを行います。
		 * 
		 * @param url
		 *            ダウンロードするURL
		 * @param title ビデオタイトル ファイル名になります。
		 */
		@Override
		public String downloadFile(String url, String title) throws RemoteException {
			Notification notification = new Notification();
			notification.icon = android.R.drawable.btn_default;
			notification.tickerText = "Download start...";
			notification.when = System.currentTimeMillis();
			notification.setLatestEventInfo(getApplicationContext(),
					"YoutubeDownloader", "Download start...", dammyIntent());
			int notificationId = createNotificationId();
			notificationManager.notify(notificationId, notification);
			String[] split = url.split("/");
			String id = split[split.length - 1];
			String filename = download(parseURL(id), title, id);
			notification.tickerText = "Download end.";
			notification.setLatestEventInfo(getApplicationContext(),
					"YoutubeDownloader", "Download end.", pendingIntent(filename));
			notificationManager.notify(notificationId, notification);

			return filename;
		}

	};

	/**
	 * Downloadを行います。
	 * 
	 * @param url
	 *            ダウンロードするURL
	 */
	private String download(String url, String title, String id) {
		HttpHelper httpHelper = HttpHelper.getInstance();
		OutputStream out = null;
		String fileName = "";
		try {
			String token = getVideoInfo(url);
//			Builder builder = new Builder();
//			builder.path(FEED_VIDEO_URL);
//			builder.appendQueryParameter("el", "detailpage");
//			builder.appendQueryParameter("asv", "3");
//			builder.appendQueryParameter(PARAM_ID, id);
//			builder.appendQueryParameter(PARAM_TOKEN, token);
//			builder.appendQueryParameter(PARAM_FMT, "18");

			StringBuilder builder = new StringBuilder();
			builder.append(FEED_VIDEO_URL + "?");
//			builder.append("el=detailpage&asv=3&");
			builder.append("asv=2&");
			builder.append(PARAM_ID + "=");
			builder.append(id + "&");
			builder.append(PARAM_TOKEN + "=");
			builder.append(token + "&");
			builder.append(PARAM_FMT + "=");
			builder.append("18");

			String downloadUrl = builder.toString();
			Log.v(TAG, "downrload url = " + downloadUrl);

			final HttpGet httpGet = new HttpGet(downloadUrl);
			final HttpHost httpHost = new HttpHost(HOST_URL, 80, "http");
			InputStream inputStream = httpHelper.getResponseContent(httpHost,
					httpGet).getContent();
			File videoDirectory = new File(DOWNLOAD_DIR);
			if (!videoDirectory.exists()) {
				videoDirectory.mkdir();
			}
			
			fileName = DOWNLOAD_DIR + title + ".mp4";

			out = new BufferedOutputStream(new FileOutputStream(fileName));
			byte[] buffer = new byte[8192];
			int wsize;
			while ((wsize = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, wsize);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (HttpHelperException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				Log.e(TAG, "Error occur", e);
			}
		}
		return fileName;
	}
	/**
	 * Get token from videoInfo
	 * 
	 * @param url
	 * @return token
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws HttpHelperException
	 */
	private String getVideoInfo(String url) throws IllegalStateException,
			IOException, HttpHelperException {
		HttpHelper httpHelper = HttpHelper.getInstance();
		final HttpGet httpGet = new HttpGet(url);
		final HttpHost httpHost = new HttpHost(HOST_URL, 80, "http");
		InputStream inputStream = httpHelper.getResponseContent(httpHost,
				httpGet).getContent();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String line;
		String token = null;
		while ((line = bufferedReader.readLine()) != null) {
			int index = line.indexOf("&token=");
			if (index == -1) {
				break;
			}
			index += "&token=".length();
			int lastIndex = line.indexOf("&", index);
			token = line.substring(index, lastIndex);
			if (token.length() != 0) {
				break;
			}
		}

		return token;
	}

	private int createNotificationId() {
		int id = ++notifikationId;
		activeIdList.add(id);
		return id;
	}
	private String parseURL(String id) {
		Builder builder = new Builder();
		builder.path(FEED_INFO_URL);
		builder.appendQueryParameter(PARAM_ID, id);

		return builder.build().toString();
	}

	/**
	 * ダウンロード後にNotificationが押されたときの処理を行います。 この場合は、ビデオプレイヤーが起動します。
	 * 
	 * @param fileName
	 *            ビデオのファイルです。
	 * @return intent
	 */
	private PendingIntent pendingIntent(String fileName) {
		intent = new Intent(getApplicationContext(), VideoActivity.class);
		intent.putExtra("key1", fileName);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}

	/**
	 * ダウンロード中にNotificationが押されたときの処理を行います。 この場合は、このプログラムに戻ります。
	 * 
	 * @return intent
	 */
	private PendingIntent dammyIntent() {
		intent = null;
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		return pendingIntent;
	}
}
