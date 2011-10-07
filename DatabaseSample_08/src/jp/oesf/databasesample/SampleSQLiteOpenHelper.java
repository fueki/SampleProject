package jp.oesf.databasesample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String SAMPLE_DATABASE = "SAMPLE_DATABASE";
	public static final String SAMPLE_TABLE = "SAMPLE_TABLE";
	public SampleSQLiteOpenHelper(Context context){
		super(context, "SAMPLE_DATABASE", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("CREATE TABLE SAMPLE_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT" +
													",name TEXT not null" +
													",value INTEGER not null" +
													");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
