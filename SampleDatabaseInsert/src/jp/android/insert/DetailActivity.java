package jp.android.insert;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		SampleSQLiteDataBase ds = new SampleSQLiteDataBase(this);
		SQLiteDatabase database = null;
		
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
		
		database = ds.getReadableDatabase();
		Cursor cursor = database.query("SAMPLE_TABLE", null, "_id=" + id, null, null, null, null);
		
		if(cursor != null){
			startManagingCursor(cursor);
			cursor.moveToFirst();
			
			((TextView)findViewById(R.id.text_name)).setText(cursor.getString(cursor.getColumnIndex("name")));
			((TextView)findViewById(R.id.text_view)).setText(cursor.getString(cursor.getColumnIndex("value")));
		}
		if(database != null){
			ds.close();
			Log.v("DataAllList","Succeeded in close the database");
		}
	}
}
