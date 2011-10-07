package jp.oesf.databasesample;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ResultListActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

	}

	@Override
	protected void onResume() {

		super.onResume();
		SampleSQLiteOpenHelper databaseOpenHelper = new SampleSQLiteOpenHelper(
				this);
		SQLiteDatabase database = null;

		// 読込専用のSQLiteDatabaseオブジェクトを取得する
		database = databaseOpenHelper.getReadableDatabase();
		Log.v("ResultActivity", "Succeeded in open the database.");

		// 全件検索する
		Cursor cursor = database.query(SampleSQLiteOpenHelper.SAMPLE_TABLE,
				null, null, null, null, null, null);

		if (cursor != null) {
			startManagingCursor(cursor);
			SimpleCursorAdapter rssFeedCursorAdapter = new SimpleCursorAdapter(
					this, R.layout.list_row, cursor, new String[] { "name" },
					new int[] { R.id.title });
			setListAdapter(rssFeedCursorAdapter);

		}

		if (databaseOpenHelper != null) {
			// データベースから切断する
			databaseOpenHelper.close();
			Log.v("DatabaseSample", "Succeeded in close the database.");
		}
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position,
			long id) {

		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra("id", id);
		startActivity(intent);

	}

}