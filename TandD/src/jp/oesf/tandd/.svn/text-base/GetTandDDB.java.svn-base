package jp.oesf.tandd;

import java.util.ArrayList;
import java.util.List;

import jp.oesf.tandd.model.FeedEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GetTandDDB {
	Context context;
	private TandDSQLiteOpenHelper helper = new TandDSQLiteOpenHelper(context);
	private SQLiteDatabase database = null;

	// DBに入れるもの
	public ContentValues values = new ContentValues();

	public GetTandDDB(Context context) {
		this.context = context;
	}
	
	public List<FeedEntity> GetData(){
		
		List<FeedEntity> list = new ArrayList<FeedEntity>();
		FeedEntity entity = new FeedEntity();
		database = helper.getReadableDatabase();
		Cursor cursor = database.query(TandDSQLiteOpenHelper.RSS_TABLE, null, null, null, null, null, null);
		
		while(cursor.moveToNext()) {
			entity.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("link")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("description")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("pubDate")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("category")));
			
			list.add(entity);
		}
		return list;
	}
	
	
}
