package jp.oesf.mtgeduwg.training.profilingtest;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PhotoDBHelper extends SQLiteOpenHelper {

	public PhotoDBHelper(Context context) {
		super(context, "photo.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db
		.execSQL("CREATE TABLE IF NOT EXISTS PHOTOS (ID integer PRIMARY KEY AUTOINCREMENT , TITLE text , UPDATETIME integer , BITMAP blob);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	public long insertBitMap(String title , byte[] bitmapBytes , long updatetime){
		PhotoInfo photoInfo = getPhotoInfo(title);
		if(photoInfo == null){
			
		}else if(photoInfo.updatetime < updatetime){
			delete(photoInfo.id);
		}else{
			return -1;
		}
		ContentValues contentValues = new ContentValues();
		contentValues.put("TITLE",title);
		contentValues.put("BITMAP", bitmapBytes);
		contentValues.put("UPDATETIME", updatetime);
		return insert("PHOTOS", contentValues);
	}
	
	public void delete(long id) {
		SQLiteDatabase db = getWritableDatabase();

		db.beginTransaction();
		try {
			
			db.delete("PHOTOS", "ID=?", new String[]{String.valueOf(id)});
			db.setTransactionSuccessful();

		} finally {
			db.endTransaction();
			db.close();
		}
	}


	
	public long insert(String table, ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();

		db.beginTransaction();
		long rowID = 0;
		try {
			rowID = db.insert(table, null, values);
			db.setTransactionSuccessful();

		} finally {
			db.endTransaction();
			db.close();
			values = null;
		}
		return rowID;
	}

	public List<PhotoInfo> getPhotoInfos(){
		Cursor cursor = null;
		SQLiteDatabase readableDatabase = null;
		try {
			readableDatabase = getReadableDatabase();
			cursor = readableDatabase.rawQuery(
					"SELECT ID , TITLE , UPDATETIME FROM PHOTOS ",
					null);
			List<PhotoInfo> results = new ArrayList<PhotoInfo>();
			while(cursor.moveToNext()){
				results.add(new PhotoInfo(cursor.getLong(0), cursor.getString(1),cursor.getLong(2)));
			}
			return results;
		} finally {
			try{
				if(cursor!=null){cursor.close();}
			}catch (Exception e) {
			}
			if(readableDatabase!=null){readableDatabase.close();}
		}
	}
	
	public PhotoInfo getPhotoInfo(String title){
		Cursor cursor = null;
		SQLiteDatabase readableDatabase = null;
		try {
			readableDatabase = getReadableDatabase();
			cursor = readableDatabase.rawQuery(
					"SELECT ID , TITLE ,UPDATETIME FROM PHOTOS WHERE TITLE=?",
					new String[]{title});
			if (!cursor.moveToFirst()) {
				return null;
			}
			return new PhotoInfo(cursor.getLong(0), cursor.getString(1),cursor.getLong(2));
		} finally {
			try{
				if(cursor!=null){cursor.close();}
			}catch (Exception e) {
			}
			if(readableDatabase!=null){readableDatabase.close();}
		}
	}
	
	public Bitmap getPhotoBitmap(long id){
		
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inDither = true;
		
		Cursor cursor = null;
		SQLiteDatabase readableDatabase = null;
		try {
			readableDatabase = getReadableDatabase();
			cursor = readableDatabase.rawQuery("SELECT BITMAP FROM PHOTOS WHERE ID=?",
					new String[] { String.valueOf(id) });
			if (!cursor.moveToFirst()) {
				return null;
			}
			byte[] blob = cursor.getBlob(0);
//			return BitmapFactory.decodeByteArray(blob, 0, blob.length , options);
			return BitmapFactory.decodeByteArray(blob, 0, blob.length );
		} finally {
			try{
				if(cursor!=null){cursor.close();}
			}catch (Exception e) {
			}
			if(readableDatabase!=null){readableDatabase.close();}
		}

		
	}

	public static class PhotoInfo {
		
		public final long id;
		public final String title;
		public final long updatetime;
		public PhotoInfo(long id, String title , long updatetime) {
			super();
			this.id = id;
			this.title = title;
			this.updatetime = updatetime;
		}
	}

}
