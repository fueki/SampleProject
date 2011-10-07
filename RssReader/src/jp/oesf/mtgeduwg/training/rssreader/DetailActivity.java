/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader;

import jp.oesf.mtgeduwg.training.rssreader.helper.DatabaseOpenHelper;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * 詳細画面のActivityクラス。
 */
public class DetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
    }
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            Bundle extras = getIntent().getExtras();
            long id = extras.getLong("id");
            Log.v("DetailActivity", "id = " + id);
            database = databaseOpenHelper.getReadableDatabase();
            cursor = database.query("RSS_FEED", null, "_id=" + id, null, null, null, null);
            startManagingCursor(cursor);
            if (!cursor.moveToFirst()) {
                return;
            }
            ((TextView) findViewById(R.id.title)).setText(cursor.getString(cursor.getColumnIndex("TITLE")));
            ((TextView) findViewById(R.id.publish_date)).setText(cursor.getString(cursor.getColumnIndex("PUBLISH_DATE")));
            ((TextView) findViewById(R.id.sender_name)).setText(cursor.getString(cursor.getColumnIndex("SENDER_NAME")));
            ((TextView) findViewById(R.id.description)).setText(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }
}
