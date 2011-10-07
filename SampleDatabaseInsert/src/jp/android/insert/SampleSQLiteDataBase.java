package jp.android.insert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleSQLiteDataBase extends SQLiteOpenHelper{

	public final static String dbName = "SAMPLE_DATABASE";
	private final static String sql = "CREATE TABLE SAMPLE_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT " +
							   		  ",name TEXT not null" +
							   		  ",value INTEGER not null);" ;
	
	public SampleSQLiteDataBase(Context context) {
		super(context, dbName, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO DBçÏê¨
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	

}
