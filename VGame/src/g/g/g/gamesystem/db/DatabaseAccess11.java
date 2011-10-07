package g.g.g.gamesystem.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class DatabaseAccess11 extends Activity {
	private static final String TAG ="DatabaseAccess";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        
        GameSQLiteOpenHelper databaseOpenHelper = new GameSQLiteOpenHelper(this);
        SQLiteDatabase database = null;

        try {
        	// 読込専用のSQLiteDatabaseオブジェクトを取得する
            database = databaseOpenHelper.getReadableDatabase();
            Log.v(TAG, "Succeeded in open the database.");

         } catch (Exception e) {
            Log.e(TAG, "Error:", e);
         } finally {
        	 if (database != null) {
        		 // データベースから切断する
                 database.close();
                 Log.v(TAG, "Succeeded in close the database.");
        	 }
         }
    }
}