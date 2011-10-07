package jp.android.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DataBaseSample extends Activity {
	
	private static final String TAG = "DataBaseSample"; 
	
	SqliteOpenHelper01 sqliteOpenHelper = new SqliteOpenHelper01(this);
	SQLiteDatabase database = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databaseinsert);
	
	}
	
	public void onClickDbInsert(View view){
		
		TextView name = (TextView)findViewById(R.id.edit_name);
		TextView value = (TextView)findViewById(R.id.edit_value);
		
		database = sqliteOpenHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("name", name.getText().toString());
		values.put("value", value.getText().toString());
		
		long rowId = database.insert("SAMPLE_TABLE02", null, values);
		Log.v(TAG, "RowID : " + rowId);
		
		if(rowId == -1){
			Log.v(TAG,"insert filed");
		}
		if(sqliteOpenHelper != null){
			sqliteOpenHelper.close();
			
			Toast.makeText(this, R.string.ok_insert, Toast.LENGTH_LONG).show();
			
			finish();
		}
	}
	
}
