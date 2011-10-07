package jp.oesf.tandd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * RSSデータを取得し詳細画面に表示する
 */
public class Detail extends Activity {
	
	private static final String TAG = "Detail";
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
    }
    /**
     * DBからRSSデータを取得しTextViewに表示する
     * @exception　DBアクセス時に発生
     */
    protected void onResume(){
    	super.onResume();
    	TandDSQLiteOpenHelper databaseOpenHelper = new TandDSQLiteOpenHelper(this);
    	
    	try{
    		Bundle extras = getIntent().getExtras();
    		long id = extras.getLong("id");
        
        	SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        	
        	Cursor cursor = database.query(TandDSQLiteOpenHelper.RSS_TABLE, null, "_id=" + id, null,null, null, null);
        	if(cursor != null){
        		startManagingCursor(cursor);
        		cursor.moveToFirst();
        		
        		//DBから取得した値をTextViewに表示
        		((TextView)findViewById(R.id.detail_title)).setText(cursor.getString(cursor.getColumnIndex("title")));
        		((TextView)findViewById(R.id.detail)).setText(cursor.getString(cursor.getColumnIndex("description")));
        		((TextView)findViewById(R.id.detail_link)).setText(cursor.getString(cursor.getColumnIndex("link")));
        		((TextView)findViewById(R.id.detail_date)).setText(cursor.getString(cursor.getColumnIndex("pubDate")));
        	}
    	}catch (NullPointerException e){
    		MissDialog(null);
        }catch (SQLException e){
        	Log.e(TAG, "Error:", e);
        }catch (Exception e){
        	Log.e(TAG, "Error:", e);
        }finally{
        	databaseOpenHelper.close();
            Log.v(TAG, "Succeeded in close the database.");
        }
    }
    
   public void MissDialog (View view){
       new AlertDialog.Builder(Detail.this)
       .setTitle("データ取得失敗")
       .setPositiveButton(
         "Yes", 
         new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {  
           }
         })
       .show();
   }
}
