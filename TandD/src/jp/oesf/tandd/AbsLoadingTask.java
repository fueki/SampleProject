package jp.oesf.tandd;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * ストリーミングダウンロード
 * 
 */
public abstract class AbsLoadingTask extends AsyncTask<String, Void, Boolean> {
	protected ProgressDialog progressDialog;

	public AbsLoadingTask(Context context) {
		progressDialog = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		// TODO string
		progressDialog.setMessage("読込中...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(true);
		progressDialog.show();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		progressDialog.dismiss();
	}
}
