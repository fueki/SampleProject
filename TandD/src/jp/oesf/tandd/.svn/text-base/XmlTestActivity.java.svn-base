package jp.oesf.tandd;

import java.util.ArrayList;

import jp.oesf.tandd.model.FeedEntity;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class XmlTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmltest);
	}

	public void onClickParseButton(View v) {
		Analyze_XML analyze_XML = new Analyze_XML();
		
		ArrayList<FeedEntity> list = analyze_XML.parseSax(this, null);
		for(FeedEntity m:list){
			System.out.println(m);
		}

			AddTandDDB addTandDDB = new AddTandDDB(this);
			addTandDDB.addAll(list);
	}
	
	public void onClickAccessButton(View v) {
		AccessOesf ao = new AccessOesf();
		
		System.out.println(ao.conectOesf(this));
	}

	
	public void onClickConectButton(View v) {
		AccessOesf ao = new AccessOesf();
		String xml = ao.conectOesf(this);
		
		Analyze_XML analyze_XML = new Analyze_XML();
		ArrayList<FeedEntity> rssList = analyze_XML.parseSax(this, xml);
		for(FeedEntity m:rssList){
			System.out.println(m);
		}
		TandDDB db = new TandDDB(this);
		db.addAll(rssList);

	}
	
	
	public void onClickSearchButton(View v) {
		TandDSQLiteOpenHelper databaseOpenHelper = new TandDSQLiteOpenHelper(this);
		SQLiteDatabase database = null;
		try {
			database = databaseOpenHelper.getReadableDatabase();
		Log.v("TandGdatabase", "Succeeded in open the database.");
		
		Cursor cursor = database.query(databaseOpenHelper.RSS_TABLE, null, null, null, null, null, null);
		if(cursor != null) {
			Log.v("DatabaseSample", "データ件数：" + cursor.getCount() + "件");
		} 
		}finally {
			if (database != null) {
				database.close();
				Log.v("Database", "Succeeded in close the database.");
			}
		}
	}
}
