package jp.oesf.databasesample;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);

		SampleSQLiteOpenHelper databaseOpenHelper = new SampleSQLiteOpenHelper(
				this);
		SQLiteDatabase database = null;
		TextView textCount = (TextView)findViewById(R.id.text_count);

		// 読込専用のSQLiteDatabaseオブジェクトを取得する
		database = databaseOpenHelper.getReadableDatabase();
		Log.v("ResultActivity", "Succeeded in open the database.");

		//全件検索する
		Cursor cursor = database.query(SampleSQLiteOpenHelper.SAMPLE_TABLE, null, null, null, null, null, null);
		
		if(cursor != null ){
			textCount.setText("[データ件数：" + cursor.getCount() + "件]");			
			//データの数だけループする
			while(cursor.moveToNext()){
				//nameを取得
				String name = cursor.getString(cursor.getColumnIndex("name"));
				//valueを取得
				int value = cursor.getInt(cursor.getColumnIndex("value"));
				
				Log.v("ResultActivity", "name:" + name + " value:" + value);
			}
		}

		if (databaseOpenHelper != null) {
			// データベースから切断する
			databaseOpenHelper.close();
			Log.v("DatabaseSample", "Succeeded in close the database.");
		}


	}
}