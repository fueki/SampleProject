package jp.android;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DateAllList extends Activity{
	
	private static final String TAG = "DateAllList";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_all);
		
		TextView contentText = (TextView)findViewById(R.id.textView1);
		
		SampleSQLiteOprenHelper db = new SampleSQLiteOprenHelper(this);
		SQLiteDatabase database = null;
		try{
			database = db.getReadableDatabase();
			
			Cursor cursor = database.query("SAMPLE_TABLE", columns, selection, selectionArgs, groupBy, having, orderBy)
		} finally {
			
		}
		
	}
}
