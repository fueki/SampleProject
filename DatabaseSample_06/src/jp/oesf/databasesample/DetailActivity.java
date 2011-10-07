package jp.oesf.databasesample;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// Intentから値をを取得
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
		
		SampleSQLiteOpenHelper databaseOpenHelper = new SampleSQLiteOpenHelper(this);
		SQLiteDatabase database = null;
		
		// 読込専用のSQLiteDatabaseオブジェクトを取得する
		database = databaseOpenHelper.getReadableDatabase();
		
		// 条件検索
		Cursor cursor = database.query(SampleSQLiteOpenHelper.SAMPLE_TABLE, null, "_id=" + id, null,null, null, null);
		if (cursor != null) {
			startManagingCursor(cursor);
			cursor.moveToFirst();
			// nameをセット
			((TextView) findViewById(R.id.text_name)).setText(cursor.getString(cursor.getColumnIndex("name")));
			// nameをセット
			((TextView) findViewById(R.id.text_value)).setText(cursor.getString(cursor.getColumnIndex("value")));
		}
		// データベースから切断する
		databaseOpenHelper.close();
		Log.v("DatabaseSample", "Succeeded in close the database.");
	}
}