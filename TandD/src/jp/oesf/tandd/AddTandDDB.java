package jp.oesf.tandd;

import java.util.List;

import jp.oesf.tandd.model.FeedEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AddTandDDB {
	Context context;
	private TandDSQLiteOpenHelper helper = new TandDSQLiteOpenHelper(context);
	private SQLiteDatabase database = null;

	// DBに入れるもの
	public ContentValues values = new ContentValues();

	public AddTandDDB(Context context) {
		this.context = context;
	}

	public void addAll(List<FeedEntity> list)// XMLから取得したデータ
	{
		FeedEntity entity;

		try {
			database = helper.getWritableDatabase();
			Log.v("TandGdatabase", "Succeeded in open the database.");
			
			for (int i = 0; i < list.size(); i++) {
				entity = list.get(i);
				values.put("title", entity.getTitle());
				values.put("link", entity.getLink());
				values.put("pubDate", entity.getPubDate());
				values.put("category", entity.getCategory());

				database.insert(TandDSQLiteOpenHelper.RSS_TABLE, null, values);
			}
		} catch(Exception e) {
			Log.e("AddTandDDB", "addAll:" + e.toString(),e);
		}finally {
			database.close();
			Log.v("TandDdatabase", "Succeeded in open the database.");
		}
	}
}
