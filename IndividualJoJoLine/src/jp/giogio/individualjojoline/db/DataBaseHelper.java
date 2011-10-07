package jp.giogio.individualjojoline.db;

import java.io.File;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
import android.content.Context;  
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteDatabase.CursorFactory;  
import android.database.sqlite.SQLiteException;  
import android.database.sqlite.SQLiteOpenHelper;  
  
public class DataBaseHelper extends SQLiteOpenHelper {  
  
    //The Android のデフォルトでのデータベースパス  
    private static String DB_PATH = "/data/data/jp.giogio.individualjojoline/databases/";  
   
    private static String DB_NAME = "JoJoDataBase";  
    private static String DB_NAME_ASSET = "JoJoDataBase";  
   
    private SQLiteDatabase mDataBase;   
   
    private final Context mContext;  
      
      
    public DataBaseHelper(Context context) {  
        super(context, DB_NAME, null, 1);    
        this.mContext = context;  
    }  
  
    /** 
     * asset に格納したデータベースをコピーするための空のデータベースを作成する 
     *  
     **/  
    public void createEmptyDataBase() throws IOException{  
        boolean dbExist = checkDataBaseExists();  
  
        if(dbExist){  
            // すでにデータベースは作成されている  
        }else{  
            // このメソッドを呼ぶことで、空のデータベースが  
            // アプリのデフォルトシステムパスに作られる  
            this.getReadableDatabase();  
   
            try {  
                // asset に格納したデータベースをコピーする  
                copyDataBaseFromAsset();   
            } catch (IOException e) {  
                throw new Error("Error copying database");  
            }  
        }  
    }   
      
    /** 
     * 再コピーを防止するために、すでにデータベースがあるかどうか判定する 
     *  
     * @return 存在している場合 {@code true} 
     */  
    private boolean checkDataBaseExists() {  
        SQLiteDatabase checkDb = null;  
   
        try{  
            String dbPath = DB_PATH + DB_NAME;  
            checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);  
        }catch(SQLiteException e){  
            // データベースはまだ存在していない  
        }  
   
        if(checkDb != null){  
            checkDb.close();  
        }  
        return checkDb != null ? true : false;  
    }      
   
    /** 
     * asset に格納したデーだベースをデフォルトの 
     * データベースパスに作成したからのデータベースにコピーする 
     * */  
    private void copyDataBaseFromAsset() throws IOException{  
   
        // asset 内のデータベースファイルにアクセス  
        InputStream mInput = mContext.getAssets().open(DB_NAME_ASSET);  
   
        // デフォルトのデータベースパスに作成した空のDB  
        String outFileName = DB_PATH + DB_NAME;  
   
        OutputStream mOutput = new FileOutputStream(outFileName);  
  
        // コピー  
        byte[] buffer = new byte[1024];  
        int size;  
        while ((size = mInput.read(buffer)) > 0){  
            mOutput.write(buffer, 0, size);  
        }  
   
        //Close the streams  
        mOutput.flush();  
        mOutput.close();  
        mInput.close();  

//        File file = new File(mInput.toString());
//        file.delete();
    }  
   
    public SQLiteDatabase openDataBase() throws SQLException{  
        //Open the database  
        String myPath = DB_PATH + DB_NAME;  
        mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);  
        return mDataBase;  
    }  
      
    @Override  
    public void onCreate(SQLiteDatabase arg0) {  
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
    }  
  
    @Override  
    public synchronized void close() {  
        if(mDataBase != null)  
            mDataBase.close();  
      
        super.close();  
    }  
}
