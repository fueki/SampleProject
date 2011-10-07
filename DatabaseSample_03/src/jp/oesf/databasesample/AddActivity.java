package jp.oesf.databasesample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
	}

	public void onClickAddButton(View v) {
		
		//Nameの取得
		EditText editName = (EditText)findViewById(R.id.edit_name);
		String name = editName.getText().toString();
		
		//Valueの取得
		EditText editValue = (EditText)findViewById(R.id.edit_value);
		String value = editValue.getText().toString();

		if( name != null && name.length() > 0 && value != null && value.length() > 0 ){
			
			SampleSQLiteOpenHelper databaseOpenHelper = new SampleSQLiteOpenHelper(this);
			SQLiteDatabase database = null;
			
			// 読込専用のSQLiteDatabaseオブジェクトを取得する
			database = databaseOpenHelper.getWritableDatabase();
			
			//isnertデータの設定
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("value", value);

			//データを追加する
			long rowId = database.insert(SampleSQLiteOpenHelper.SAMPLE_TABLE, null, values);
			Log.v("AddActivity", "RowID:" + rowId);
			if (databaseOpenHelper != null) {
				// データベースから切断する
				databaseOpenHelper.close();
			}
			if( rowId != -1){
				Toast.makeText(this, "登録完了", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
	

}