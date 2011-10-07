package jp.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleSQLiteOprenHelper extends SQLiteOpenHelper{
	
	private final static String sql = "CREATE TABLE SAMPLE_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT " +
		  							  ",name TEXT not null" +
		  							  ",value INTEGER not null);" ;

	public SampleSQLiteOprenHelper(Context context){
		super(context,"SAMPLE_LE_DB",null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
