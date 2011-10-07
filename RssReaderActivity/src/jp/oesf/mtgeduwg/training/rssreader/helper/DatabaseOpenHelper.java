/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Databaseアクセスのヘルパークラス。
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    /** データベースファイル名 */
    private static final String DATABASE_NAME = "data";
    /** データベースのバージョン */
    private static final int DATABASE_VERSION = 1;
    /** RSSフィードテーブル作成SQL */
    private static final String CREATE_RSS_FEED_TABLE = "CREATE TABLE RSS_FEED("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "SENDER_NAME TEXT,"
            + "URL TEXT,"
            + "TITLE TEXT,"
            + "PUBLISH_DATE TEXT,"
            + "DESCRIPTION TEXT,"
            + "LINK TEXT,"
            + "GUID TEXT);";
    /** RSSフィードテーブル削除SQL */
    private static final String DROP_RSS_FEED_TABLE = "DROP TABLE IF EXISTS RSS_FEED;";
    /**
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_RSS_FEED_TABLE);
        Log.v("DatabaseOpenHelper", "Succeeded in create the tables.");
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_RSS_FEED_TABLE);
        onCreate(database);
        Log.v("DatabaseOpenHelper", "Succeeded in update the tables.");
    }
}
