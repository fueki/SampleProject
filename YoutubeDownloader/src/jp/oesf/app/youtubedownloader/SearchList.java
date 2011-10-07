package jp.oesf.app.youtubedownloader;

import java.io.IOException;
import java.util.List;

import jp.oesf.app.youtubedownloader.model.RowModel;
import jp.oesf.app.youtubedownloader.service.DownloadService;
import jp.oesf.app.youtubedownloader.service.IDownloadService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 検索をして、一覧表示を行います。 検索後に動画ファイルのダウンロードを行います。
 * 
 */
public class SearchList extends ListActivity {
	/** タグ */
	private static final String TAG = "SearchList";
	/** 最大取得件数 */
	private static final int MAX_RESULTS = 7;
	/** テーブルデータ */
	private List<RowModel> mTableData;
	/** テーブルアダプタ */
	private TableAdapter tableAdapter;
	/** 非同期のタスク */
	private AsyncTask<?, ?, ?> task;
	/** 接続先ホスト */
	public static final String HOST_URL = "gdata.youtube.com";
	/** XMLのFEED */
	private static final String FEED_URL = "/feeds/api/videos";
	/** QUERY用パラメータ */
	private static final String PARAM_QUERY = "vq";
	/** ORDER BY用パラメータ */
	private static final String PARAM_ORDERBY = "orderby";
	/** MAX-RESULT用パラメータ */
	private static final String PARAM_MAX_RESULTS = "max-results";
	/** サムネイルのFEED */
	private static final String FEED_DATA_URL = "/SampleXML/data";
	/** Download Serviceのインタフェース */
	private IDownloadService downloadServiceIf;

	/**
	 * 検索結果画面を生成します。
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.list);

		String query = this.getIntent().getStringExtra("key1");

		if (query == null) {
			finish();
		}

		tableAdapter = new TableAdapter(this);
		getListView().setAdapter(tableAdapter);

		tableAdapter.clear();

		Log.d(TAG, "search task start. query=" + query);

		task = new SearchTask().execute(new Object[] { query });

		// Intent intent = new Intent(IDownloadService.class.getName());
		Intent intent = new Intent(this, DownloadService.class);
		bindService(intent, downloadServiceConn, BIND_AUTO_CREATE);
	}

	/**
	 * 各行の押下時の処理。
	 * 
	 * @param listView
	 *            リスト
	 * @param view
	 *            ROWのView
	 * @param position
	 *            押下された行のポジション
	 * @param id
	 *            ROWのID
	 */
	@Override
	protected void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		String dialogTitle = getString(R.string.dialog_tilte);
		final RowModel rowModel = mTableData.get(position);
		if (rowModel.isDownloadFlag()) {
			dialogTitle = getString(R.string.play_message);
		}
		final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(
				dialogTitle).setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						if (rowModel.isDownloadFlag()) {
							// TODO VideoActivity
							Intent intent = new Intent(getApplicationContext(),
									VideoActivity.class);
							intent.putExtra("key1", rowModel.getFileName());
							startActivity(intent);
						} else {
							new DownloadTask()
									.execute(new RowModel[] { rowModel });
						}
					}

				}).setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Nothing
					}
				}).create();

		alertDialog.show();

	}

	/**
	 * アクティビティが終了した場合の処理。 非同期タスクが動いている場合、終了させます。
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (task != null && task.getStatus() == AsyncTask.Status.RUNNING) {
			Log.d(TAG, "Task cancelled. " + task);
			task.cancel(true);
			task = null;
		}
	}

	/**
	 * テーブルアダプタクラス。
	 */
	private class TableAdapter extends ArrayAdapter<RowModel> {
		/** Context (Activity) */
		Activity context;

		/**
		 * テーブルアダプタ。 コンテキストを取得します。
		 * 
		 * @param context
		 *            親のActivity
		 */
		TableAdapter(final Activity context) {
			super(context, R.layout.list_row);

			this.context = context;
		}

		/**
		 * 行を表示します。
		 * 
		 * @param convertView
		 *            行のView
		 * @param parent
		 *            行の親View
		 * @return RowのView
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewWrapper wrapper = null;

			if (row == null) {
				LayoutInflater inflater = context.getLayoutInflater();

				row = inflater.inflate(R.layout.list_row, null);

				wrapper = new ViewWrapper(row);

				row.setTag(wrapper);
			} else {
				wrapper = (ViewWrapper) row.getTag();
			}

			wrapper.getTitle().setText(getItem(position).getTitle());
			wrapper.getSummary().setText(getItem(position).getSummary());
			wrapper.getImageView().setImageBitmap(
					getItem(position).getThumbnailImage());
			return row;
		}
	}

	/**
	 * 検索処理を行います。
	 */
	private class SearchTask extends AsyncTask<Object, Void, List<RowModel>> {
		/**
		 * タスク実行時の処理を行います。 右上にインジゲータを表示します。
		 */
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}

		/**
		 * Http通信を使って一覧に表示する情報を取得します。
		 * 
		 * @param params
		 *            クエリ情報
		 * @return 取得結果
		 */
		@Override
		protected List<RowModel> doInBackground(final Object... params) {
			final String query = (String) params[0];
			Builder builder = new Builder();
			builder.path(FEED_URL);
			builder.appendQueryParameter(PARAM_QUERY, query);
			builder.appendQueryParameter(PARAM_ORDERBY, "relevance_lang_ja");
			builder.appendQueryParameter(PARAM_MAX_RESULTS, "" + MAX_RESULTS);

			Log.e(" @@@@@@ @@@@@@", " * " + builder);
			
			HttpHelper httpHelper = HttpHelper.getInstance();
			final HttpGet httpGet = new HttpGet(builder.build().toString());
			final HttpHost httpHost = new HttpHost(HOST_URL, 80, "http");
			// String url = "http://gdata.youtube.com:80/SampleXML/httpsample?vq="
			// +
			// query + "&orderby=relevance_lang_ja&max-results=" + MAX_RESULTS;

			try {
				HttpEntity entity = httpHelper.getResponseContent(httpHost,
						httpGet);

				try {
					XmlHelper xmlHelper = XmlHelper.getInstance();
					// start tracing to "/sdcard/xml.trace"
//					Debug.startMethodTracing("xml");
					// XMLPullParser
					// tableData = xmlHelper.parseTableModel(entity);
					// DOM
					// tableData = xmlHelper.parseTableModelDom(entity);
					// SAX
					mTableData = xmlHelper.parseTableModelSax(entity);
					// stop tracing
//					Debug.stopMethodTracing();

				} catch (final ParseException e) {
					final IOException ioe = new IOException(
							"Could not parser the response");
					ioe.initCause(e);
					throw ioe;
				}

			} catch (Exception e) {
				Log.e(TAG, "", e);
			}
			return mTableData;
		}

		/**
		 * タスク終了時の処理
		 */
		@Override
		protected void onPostExecute(final List<RowModel> result) {
			setProgressBarIndeterminateVisibility(false);
			task = null;

			if (isCancelled()) {
				return;
			}

			if (result != null) {
				tableAdapter.setNotifyOnChange(false);
				// final int length = result.size();
				final int length = MAX_RESULTS;
				final RowModel[] rowModels = new RowModel[length];
				for (int i = 0; i < length; i++) {
					rowModels[i] = result.get(i);
					tableAdapter.add(result.get(i));
				}
				tableAdapter.setNotifyOnChange(true);
				tableAdapter.notifyDataSetChanged();

				task = new LoadImageTask().execute(rowModels);
			}

		}
	}

	/**
	 * サムネイル情報の取得を行います。
	 */
	class LoadImageTask extends AsyncTask<RowModel, Void, Void> {
		/**
		 * タスク実行時の処理を行います。 右上にインジゲータを表示します。
		 */
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}

		/**
		 * Http通信を使って一覧に表示する情報を取得します。
		 * 
		 * @param params
		 *            クエリ情報
		 * @return 取得結果
		 */
		@Override
		protected Void doInBackground(final RowModel... params) {
			// Thread優先度はstandard
			android.os.Process
					.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

			for (final RowModel rowModel : params) {
				if (isCancelled()) {
					Log.d(TAG, "LoadImageTask#doInBackground() cancelled.");
					break;
				}

				if (!TextUtils.isEmpty(rowModel.getThumbnailImageURL())) {
					XmlHelper xmlHelper = XmlHelper.getInstance();
					StringBuffer url = new StringBuffer();
					url.append(rowModel.getThumbnailImageURL());
					final Bitmap bitmap = xmlHelper.loadImageBitmap(url
							.toString());
					if (bitmap != null) {
						rowModel.setThumbnailImage(bitmap);
					}
					publishProgress((Void) null);
				}
			}
			return null;
		}

		/**
		 * 取得時にテーブル情報を更新します。
		 * 
		 * @param values
		 */
		@Override
		protected void onProgressUpdate(final Void... values) {
			tableAdapter.notifyDataSetChanged();
		}

		/**
		 * タスク終了時の処理。 右上のインジゲータを止めます。
		 */
		@Override
		protected void onPostExecute(final Void result) {
			setProgressBarIndeterminateVisibility(false);
		}
	}

	/**
	 * ダウンロード用のタスクです。
	 */
	class DownloadTask extends AsyncTask<RowModel, Void, Void> {
		/**
		 * コンストラクタ。 特に何もしません。
		 */
		public DownloadTask() {
			super();
		}

		/**
		 * タスク実行時の処理を行います。 右上にインジゲータを表示します。
		 */
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}

		/**
		 * ダウンロードサービスを起動します。
		 * 
		 * @param params
		 *            行のデータ
		 */
		@Override
		protected Void doInBackground(final RowModel... params) {
			// Thread優先度はstandard
			android.os.Process
					.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

			for (final RowModel rowModel : params) {
				if (isCancelled()) {
					Log.d(TAG, "LoadImageTask#doInBackground() cancelled.");
					break;
				}
				rowModel.setSummary("Download start...");
				publishProgress((Void) null);
				// download(rowModel);
				try {
					String fileName = downloadServiceIf.downloadFile(rowModel
							.getUrl(), rowModel.getTitle());
					rowModel.setFileName(fileName);

				} catch (RemoteException e) {
					e.printStackTrace();
				}
				rowModel.setSummary("Download end...");
				rowModel.setDownloadFlag(true);
				publishProgress((Void) null);

			}
			return null;
		}

		/**
		 * 取得時にテーブル情報を更新します。
		 * 
		 * @param values
		 */
		@Override
		protected void onProgressUpdate(final Void... values) {
			tableAdapter.notifyDataSetChanged();
		}

		/**
		 * タスク終了時の処理。 右上のインジゲータを止めます。
		 */
		@Override
		protected void onPostExecute(final Void result) {
			setProgressBarIndeterminateVisibility(false);
		}
	}

	/**
	 * ダウンロードサービスのコネクションを確立します。
	 */
	private final ServiceConnection downloadServiceConn = new ServiceConnection() {

		/**
		 * ダウンロードサービスのコネクションを確立します。
		 * 
		 * @param name
		 * @param service
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			downloadServiceIf = IDownloadService.Stub.asInterface(service);
		}

		/**
		 * ダウンロードサービスのコネクションを外します。
		 * 
		 * @param name
		 */
		@Override
		public void onServiceDisconnected(ComponentName name) {
			downloadServiceIf = null;
		}
	};

}
