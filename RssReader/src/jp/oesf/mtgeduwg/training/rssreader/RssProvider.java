package jp.oesf.mtgeduwg.training.rssreader;

import jp.oesf.mtgeduwg.training.rssreader.helper.DatabaseOpenHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class RssProvider extends ContentProvider {
	
	private DatabaseOpenHelper databaseOpenHelper;
	private SQLiteDatabase db;
	
	private static final UriMatcher uriMatcher;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("jp.oesf.mtgeduwg.training.rssreader", "RSS_FEED", 1);
		
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return uri;
	}

	@Override
	public boolean onCreate() {

		databaseOpenHelper = new DatabaseOpenHelper(getContext());
		 return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {	
				
		db = databaseOpenHelper.getReadableDatabase();
		
		return db.query("RSS_FEED", projection, selection, selectionArgs, null, null, null);
	}
			

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
