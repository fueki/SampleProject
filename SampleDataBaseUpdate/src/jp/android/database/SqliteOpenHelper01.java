package jp.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteOpenHelper01 extends SQLiteOpenHelper{
	
	private static final String TAG = "SqliteOpenHelper01";
	public final static String dbName = "SAMPLE_DATABASE";
	private final static String sql = "CREATE TABLE SAMPLE_TABLE02(_id INTEGER PRIMARY KEY AUTOINCREMENT " +
							   		  ",name TEXT not null" +
							   		  ",value INTEGER not null);" ;

	public SqliteOpenHelper01(Context context) {
		super(context, dbName, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO DBアクセス
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
