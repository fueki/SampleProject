package jp.oesf.databasesample;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {

	private EditText editName;
	private EditText editValue;
	private long id;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		editName = (EditText) findViewById(R.id.edit_name);
		editValue = (EditText) findViewById(R.id.edit_value);

		// Intentから値をを取得
		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
		String name = extras.getString("name");
		String value = extras.getString("value");
		// nameをセット
		editName.setText(name);
		// valueをセット
		editValue.setText(value);
	}

	public void onClickUpdateButton(View v) {
		String name = editName.getText().toString();
		String value = editValue.getText().toString();

		if (name != null && name.length() > 0 && value != null
				&& value.length() > 0) {
			SampleSQLiteOpenHelper databaseOpenHelper = new SampleSQLiteOpenHelper(
					this);
			SQLiteDatabase database = null;
			database = databaseOpenHelper.getWritableDatabase();

			// updateデータの設定
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("value", value);

			// データを更新する
			long rowId = database.update(SampleSQLiteOpenHelper.SAMPLE_TABLE,
					values, "_id=" + id, null);

			if (databaseOpenHelper != null) {
				databaseOpenHelper.close();
			}
			if (rowId != -1) {
				Toast.makeText(this, "更新完了", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}
}