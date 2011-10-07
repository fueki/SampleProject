package jp.android.insert;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InsetView extends Activity {
	private static final String TAG = "AddActivity";
	
	ContentValues values = new ContentValues();
	
	SampleSQLiteDataBase ds = new SampleSQLiteDataBase(this);
	SQLiteDatabase database = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
	}
	
	public void onClickInsertButton(View view){
		TextView nameEdit   = (TextView)findViewById(R.id.editText_name);
		TextView valueEdit = (TextView)findViewById(R.id.editText_value);
		
		database = ds.getWritableDatabase();
		
		values.put("name", nameEdit.getText().toString());
		values.put("value", valueEdit.getText().toString());
		
		//ÉfÅ[É^ÇÃí«â¡
		long rowId = database.insert("SAMPLE_TABLE", null, values);
		Log.v(TAG,"RowUD : " + rowId);
		
		if(rowId == -1){
			Log.v(TAG,"insert filed");
		}
		if(ds != null){
			ds.close();
			Toast.makeText(this, R.string.end_db, Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
