package jp.android.database;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DateilsActivity extends Activity {
	
	SqliteOpenHelper01 sqliteOpenHelper = new SqliteOpenHelper01(this);
	SQLiteDatabase database = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	}

	protected void Resume(){
		super.onResume();
		
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
//		String id = extras.getString("id");
		
		database = sqliteOpenHelper.getReadableDatabase();
		
//		Cursor cursor = database.query("SAMPLE_TABLE02", null, "_id=?", new String[]{id}, null, null, null);
		Cursor cursor = database.query("SAMPLE_TABLE02", null, "_id=" + id, null, null, null, null);
		
		if(cursor != null){
			startManagingCursor(cursor);
			cursor.moveToFirst();
			Log.v("DataAllList","‚±‚±‚Ü‚Å‚«‚Ä‚à");
			((TextView)findViewById(R.id.text_name)).setText(cursor.getString(cursor.getColumnIndex("name")));
			((TextView)findViewById(R.id.text_value)).setText(cursor.getString(cursor.getColumnIndex("value")));
		}
		if(database != null){
			sqliteOpenHelper.close();
		}
	}
}
