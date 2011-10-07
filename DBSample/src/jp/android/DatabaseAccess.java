package jp.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DatabaseAccess extends Activity {
	private static final String TAG ="DatabaseAccess";
	
    public static final String PLAYERPROFILE_TABLE   = "PLAYERPROFILE_TABLE";
    public static final String MASSESINFO_TABLE      = "MASSESINFO_TABLE";
    public static final String EVENTCONTENTS_TABLE   = "EVENTCONTENTS_TABLE";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public boolean SelectTable(ArrayList ary){
		return ChangeDatabase(ary,PLAYERPROFILE_TABLE);
    }
    
    public boolean UpdateTable(ArrayList ary){
		return ChangeDatabase(ary,PLAYERPROFILE_TABLE);
    }
    
    public boolean DeleteTable(ArrayList ary){
		return ChangeDatabase(ary,PLAYERPROFILE_TABLE);
    }
    
    public void onClickNew(View v){
    	GameSQLiteOpenHelper databaseOpenHelper = new GameSQLiteOpenHelper(this); 
        SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();

        // updateデータの設定
        ContentValues values = new ContentValues();
        values.put("Players_Name", "");
        values.put("Masses_Id", "");
        values.put("Point", "");

            // データを更新する
            long result = database.update(GameSQLiteOpenHelper.PLAYERPROFILE_TABLE, values, "_id=" + 1,
                            null);

            databaseOpenHelper.close();
            if (result != -1) {
                    Toast.makeText(this, "更新完了", Toast.LENGTH_LONG).show();
                    finish();
            }
    
    }
    
    
    
    
    public boolean ChangeDatabase(ArrayList ary,String dbname){
    	
        GameSQLiteOpenHelper databaseOpenHelper = new GameSQLiteOpenHelper(this);
        
        // 読込専用のSQLiteDatabaseオブジェクトを取得する
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
    	
		return false;
    }
}