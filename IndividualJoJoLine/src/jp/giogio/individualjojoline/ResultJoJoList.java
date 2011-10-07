package jp.giogio.individualjojoline;


import jp.giogio.individualjojoline.database.DatabaseOpenHelper;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ResultJoJoList extends ListActivity {

	private static final String TAG = "ResultJoJoList";
	String s = null;
	IndividualJoJoLineActivity mm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		Bundle extras = getIntent().getExtras();
		int id = extras.getInt("id");
		
		DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
		SQLiteDatabase database = null;
		
		database = databaseOpenHelper.getReadableDatabase();
		Log.v(TAG, "open the database.");
		
		Cursor cursor = database.query(TableCheck(id), null, null, null,null, null, null);
		SimpleCursorAdapter simpleCursorAdapter = null;
		if(cursor != null){
			startManagingCursor(cursor);
			simpleCursorAdapter = new SimpleCursorAdapter(this,
					R.layout.list_row,cursor,new String[]{"WORDS"},new int[]{R.id.feed_title});
			setListAdapter(simpleCursorAdapter);
		}
        // データベースから切断する
        databaseOpenHelper.close();
        Log.v("DatabaseSample", "close the database.");
	}
	

	@Override
	protected void onListItemClick(ListView listView, View v, int position, long id) {
		super.onListItemClick(listView, v, position, id);
		
//		String item = (String) listView.getItemAtPosition(position);
		TextView t = (TextView) v.findViewById(R.id.feed_title);
		s = t.getText().toString();

		
		AlertDialog dlg = new AlertDialog.Builder(this)
		.setIcon(R.drawable.icon)
		.setTitle("Copy to Clipboard")
		.setMessage(s)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				// OKの処理
				ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cm.setText(s);
				CharSequence cs = cm.getText().toString();
				
				mm.mReplaceList = s;
				
				finish();
			}
		})
		.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// CANCELの処理
			}
		})
		.show();
	}

	public String TableCheck(int id){
		String table = null;
		
		switch(id){
		case 0:
			table = DatabaseOpenHelper.ARAKI_TABLE;
			break;
		case 1:
			table = DatabaseOpenHelper.JONATHAN_TABLE;
			break;
		case 2:
			table = DatabaseOpenHelper.JOSEF_TABLE;
			break;
		case 3:
			table = DatabaseOpenHelper.JOUTAROU_TABLE;
			break;
		case 4:
			table = DatabaseOpenHelper.JOUSUKE_TABLE;
			break;
		case 5:
			table = DatabaseOpenHelper.GIORNO_TABLE;
			break;
		case 6:
			table = DatabaseOpenHelper.JORIN_TABLE;
			break;
		case 7:
			table = DatabaseOpenHelper.SPEEDWAGON_TABLE;
			break;
		case 8:
			table = DatabaseOpenHelper.STROHEIM_TABLE;
			break;
		case 9:
			table = DatabaseOpenHelper.KAKYUUIN_TABLE;
			break;
		case 10:
			table = DatabaseOpenHelper.POLNAREFF_TABLE;
			break;
		case 11:
			table = DatabaseOpenHelper.DIO_TABLE;
			break;
		case 12:
			table = DatabaseOpenHelper.KIRA_TABLE;
			break;
		case 13:
			table = DatabaseOpenHelper.OKUYASU_TABLE;
			break;
		case 14:
			table = DatabaseOpenHelper.ROHAN_TABLE;
			break;
		case 15:
			table = DatabaseOpenHelper.BUCHARATHI_TABLE;
			break;
		case 16:
			table = DatabaseOpenHelper.MISTA_TABLE;
			break;
		case 17:
			table = DatabaseOpenHelper.PROSCIUTTO_TABLE;
			break;
		case 18:
			table = DatabaseOpenHelper.FF_TABLE;
			break;
		case 19:
			table = DatabaseOpenHelper.ANNASUI_TABLE;
			break;
		}
		return table;
	}
}
