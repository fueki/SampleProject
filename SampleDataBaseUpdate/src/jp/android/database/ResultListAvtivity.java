package jp.android.database;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ResultListAvtivity extends ListActivity {

	private static final String TAG = "ResultListAvtivity";
	SqliteOpenHelper01 sqliteOpenHelper = new SqliteOpenHelper01(this);
	SQLiteDatabase database = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		database = sqliteOpenHelper.getReadableDatabase();
		
		Cursor cursor = database.query("SAMPLE_TABLE02", null, null, null, null, null, null);
		
		if(cursor != null){
			startManagingCursor(cursor);
			
			SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list_row,
														cursor,new String[]{"name"},new int[]{R.id.title});
		
			setListAdapter(cursorAdapter);
		}
		if(sqliteOpenHelper != null){
			sqliteOpenHelper.close();
			Log.v(TAG,"Succeeded in close the database");
		}
	}
	
	protected void onListItemClick(ListView listView, View view, int position, long id){
		super.onListItemClick(listView, view, position, id);
		
		Intent intent = new Intent(this,DateilsActivity.class);
		intent.putExtra("id", id);
		Log.v("DataAllList","" 	+ id);
		startActivity(intent);
	}
}
