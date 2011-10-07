package jp.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameSQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final String VANGUARDGAME_DATABASE = "VANGUARDGAME_DATABASE";
	
    public static final String PLAYERPROFILE_TABLE   = "PLAYERPROFILE_TABLE";
    public static final String MASSESINFO_TABLE      = "MASSESINFO_TABLE";
    public static final String EVENTCONTENTS_TABLE   = "EVENTCONTENTS_TABLE";
    
	public GameSQLiteOpenHelper(Context context) {
		super(context,VANGUARDGAME_DATABASE,null,1);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE " + PLAYERPROFILE_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                                                              ",Players_Name TEXT not null" +
                                                              ",Masses_Id INTEGER not null" +
                                                              ",Point INTEGER not null" +
                                                              ",Remaining_Action_Times INTEGER not null);");
            
            database.execSQL("CREATE TABLE " + MASSESINFO_TABLE + "(_id INTEGER PRIMARY KEY" +
            												  ",Event_Name TEXT not null" +
            												  ",Event_Content_Id INTEGER not null);");
            
            database.execSQL("CREATE TABLE " + EVENTCONTENTS_TABLE + "(_id INTEGER PRIMARY KEY" +
            												  ",Event_Content TEXT not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}
