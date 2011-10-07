package jp.oesf.tandd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TandDSQLiteOpenHelper extends SQLiteOpenHelper{
    public static final String RSS_DATABASE = "rss_database";
    public static final String RSS_TABLE = "rss_table";

	
	public TandDSQLiteOpenHelper(Context context) {
		super(context, "RSS_DATABASE", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE RSS_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT not null,description TEXT,link TEXT,pubDate TEXT,category TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
