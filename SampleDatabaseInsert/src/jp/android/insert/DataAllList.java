package jp.android.insert;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DataAllList extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-ge	nerated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		SampleSQLiteDataBase ds = new SampleSQLiteDataBase(this);
		SQLiteDatabase database = null;
		
		database = ds.getReadableDatabase();

		Cursor cursor = database.query("SAMPLE_TABLE", null, null, null, null, null, null);
		
		if(cursor != null){
			startManagingCursor(cursor);
			SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.list_row,cursor,
														new String[]{"name"},new int[]{R.id.title});
			setListAdapter(cursorAdapter);
		}
		if(database != null){
			ds.close();
			Log.v("DataAllList","Succeeded in close the database");
		}
	}
	@Override
	protected void onListItemClick(ListView listview,View view,int position,long id){
		Intent intent = new Intent(this,DetailActivity.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
}
