package jp.oesf.tandd;

import java.util.ArrayList;
import java.util.List;

import jp.oesf.tandd.model.FeedEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TandDDB {
	Context context;
	
	private TandDSQLiteOpenHelper helper = null;
	
	SQLiteDatabase database = null;
	
	public TandDDB(Context context) {
		this.context = context;
		helper = new TandDSQLiteOpenHelper(context);
	}
	

	
	public void addAll(List<FeedEntity> list)
	{
		// XMLから取得したデータ
		ContentValues values = new ContentValues();
		
		try {
			database = helper.getWritableDatabase();
			Log.v("TandGdatabase", "Succeeded in open the database.");
			
			for (FeedEntity entity:list) {
				values.put("title", entity.getTitle());
				values.put("link", entity.getLink());
				values.put("pubDate", entity.getPubDate());
				values.put("category", entity.getCategory());
				values.put("description", entity.getDescription());
				values.put("_id", entity.getId());

				database.insert(TandDSQLiteOpenHelper.RSS_TABLE, null, values);
			}
		} catch(Exception e) {
			Log.e("AddTandDDB", "addAll:" + e.toString(),e);
		}finally {
			helper.close();
			Log.v("TandDdatabase", "Succeeded in close the database.");
		}
	}
	
	public List<FeedEntity> getData(){
		
		List<FeedEntity> list = new ArrayList<FeedEntity>();
		database = helper.getReadableDatabase();
		Cursor cursor = database.query(TandDSQLiteOpenHelper.RSS_TABLE, null, null, null, null, null, null);
		
		while(cursor.moveToNext()) {
			FeedEntity entity = new FeedEntity();
			entity.setId(cursor.getString(cursor.getColumnIndex("_id")));
			entity.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			entity.setLink(cursor.getString(cursor.getColumnIndex("link")));
			entity.setDescription(cursor.getString(cursor.getColumnIndex("description")));
			entity.setPubDate(cursor.getString(cursor.getColumnIndex("pubDate")));
			entity.setCategory(cursor.getString(cursor.getColumnIndex("category")));
			
			list.add(entity);
		}
		
		helper.close();
		return list;
	}
	
	public void deleteALLDBdata()
	{
		try {
			database = helper.getWritableDatabase();
			Log.v("TandGdatabase", "Succeeded in open the database.");
			
				database.delete(TandDSQLiteOpenHelper.RSS_TABLE, null, null);
		} catch(Exception e) {
			Log.e("AddTandDDB", "addAll:" + e.toString(),e);
		}finally {
			helper.close();
			Log.v("TandDdatabase", "Succeeded in close the database.");
		}
	}
}
