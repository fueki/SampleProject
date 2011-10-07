package jp.android.insert;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InsetResult extends Activity {
	private static final String TAG = "InsetResult";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		TextView textCount = (TextView)findViewById(R.id.textView1);
		
		SampleSQLiteDataBase ds = new SampleSQLiteDataBase(this);
		SQLiteDatabase database = null;

		try{
			database = ds.getReadableDatabase();
			Log.v(TAG, "Succeeded in open the database");
			
			Cursor cursor = database.query("SAMPLE_TABLE", null, null, null, null, null, null);
			
			if(cursor != null){
				startManagingCursor(cursor);
				textCount.setText("［データ件数：" + cursor.getCount() + "件］");
				while(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("name"));
					int value = cursor.getInt(cursor.getColumnIndex("value"));
					
					Log.v(TAG,"name : " + name + " value : " + value);
				}
			}
		} finally {
			if(database != null){
				database.close();
				Log.v(TAG,"Succeeded in close the database");
			}
		}
	}
}
