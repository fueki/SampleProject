/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Databaseアクセスのヘルパークラス。
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    /** データベースファイル名 */
    private static final String DATABASE_NAME = "JoJoDataBase";
    /** データベースのバージョン */
    private static final int DATABASE_VERSION = 1;
    /** データベーステーブル名 */
    public static final String ARAKI_TABLE      = "ARAKI_TABLE";
    public static final String JONATHAN_TABLE   = "JONATHAN_TABLE";
    public static final String SPEEDWAGON_TABLE = "SPEEDWAGON_TABLE";
    public static final String STROHEIM_TABLE   = "STROHEIM_TABLE";
    public static final String JOSEF_TABLE      = "JOSEF_TABLE";
    public static final String JOUTAROU_TABLE   = "JOUTAROU_TABLE";
    public static final String KAKYUUIN_TABLE   = "KAKYUUIN_TABLE";
    public static final String POLNAREFF_TABLE  = "POLNAREFF_TABLE";
    public static final String DIO_TABLE        = "DIO_TABLE";
    public static final String KIRA_TABLE       = "KIRA_TABLE";
    public static final String JOUSUKE_TABLE    = "JOUSUKE_TABLE";
    public static final String OKUYASU_TABLE    = "OKUYASU_TABLE";
    public static final String ROHAN_TABLE      = "ROHAN_TABLE";
    public static final String GIORNO_TABLE     = "GIORNO_TABLE";
    public static final String BUCHARATHI_TABLE = "BUCHARATHI_TABLE";
    public static final String MISTA_TABLE      = "MISTA_TABLE";
    public static final String PROSCIUTTO_TABLE = "PROSCIUTTO_TABLE";
    public static final String JORIN_TABLE      = "JORIN_TABLE";
    public static final String FF_TABLE         = "FF_TABLE";
    public static final String ANNASUI_TABLE = "ANNASUI_TABLE";

    
    
    /** テーブル作成SQL */
    private static final String CREATE_JOJODB_ARAKI_TABLE = "CREATE TABLE ARAKI_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_JONATHAN_TABLE = "CREATE TABLE JONATHAN_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_SPEEDWAGON_TABLE = "CREATE TABLE SPEEDWAGON_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_STROHEIM_TABLE = "CREATE TABLE STROHEIM_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_JOSEF_TABLE = "CREATE TABLE JOSEF_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_JOUTAROU_TABLE = "CREATE TABLE JOUTAROU_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_KAKYUUIN_TABLE = "CREATE TABLE KAKYUUIN_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_POLNAREFF_TABLE = "CREATE TABLE POLNAREFF_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_DIO_TABLE = "CREATE TABLE DIO_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_KIRA_TABLE = "CREATE TABLE KIRA_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_JOUSUKE_TABLE = "CREATE TABLE JOUSUKE_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_OKUYASU_TABLE = "CREATE TABLE OKUYASU_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_ROHAN_TABLE = "CREATE TABLE ROHAN_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_GIORNO_TABLE = "CREATE TABLE GIORNO_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_BUCHARATHI_TABLE = "CREATE TABLE BUCHARATHI_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_MISTA_TABLE = "CREATE TABLE MISTA_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_PROSCIUTTO_TABLE = "CREATE TABLE PROSCIUTTO_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_JORIN_TABLE = "CREATE TABLE JORIN_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_FF_TABLE = "CREATE TABLE FF_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    private static final String CREATE_JOJODB_ANNASUI_TABLE = "CREATE TABLE ANNASUI_TABLE("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "WORDS TEXT);";
    
    /** RSSフィードテーブル削除SQL */
    private static final String DROP_RSS_FEED_TABLE = "DROP TABLE IF EXISTS RSS_FEED;";
    /**
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("DatabaseOpenHelper", "ここまできてる１");
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
    	database.execSQL(CREATE_JOJODB_ARAKI_TABLE);
    	database.execSQL(CREATE_JOJODB_JONATHAN_TABLE);
    	database.execSQL(CREATE_JOJODB_SPEEDWAGON_TABLE);
    	database.execSQL(CREATE_JOJODB_STROHEIM_TABLE);
    	database.execSQL(CREATE_JOJODB_JOSEF_TABLE);
    	database.execSQL(CREATE_JOJODB_JOUTAROU_TABLE);
    	database.execSQL(CREATE_JOJODB_KAKYUUIN_TABLE);
    	database.execSQL(CREATE_JOJODB_POLNAREFF_TABLE);
    	database.execSQL(CREATE_JOJODB_DIO_TABLE);
        database.execSQL(CREATE_JOJODB_KIRA_TABLE);
        database.execSQL(CREATE_JOJODB_JOUSUKE_TABLE);
        database.execSQL(CREATE_JOJODB_OKUYASU_TABLE);
        database.execSQL(CREATE_JOJODB_ROHAN_TABLE);
        database.execSQL(CREATE_JOJODB_GIORNO_TABLE);
        database.execSQL(CREATE_JOJODB_BUCHARATHI_TABLE);
        database.execSQL(CREATE_JOJODB_MISTA_TABLE);
        database.execSQL(CREATE_JOJODB_PROSCIUTTO_TABLE);
        database.execSQL(CREATE_JOJODB_JORIN_TABLE);
        database.execSQL(CREATE_JOJODB_FF_TABLE);
        database.execSQL(CREATE_JOJODB_ANNASUI_TABLE);
        Log.v("DatabaseOpenHelper", "作成完了");
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_RSS_FEED_TABLE);
        onCreate(database);
        Log.v("DatabaseOpenHelper", "Succeeded in update the tables.");
    }
}
