/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader;

import jp.oesf.mtgeduwg.training.rssreader.helper.DatabaseOpenHelper;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * 一覧画面のActivityクラス。
 */
public class RssListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
    }
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        Log.v("RssListActivity", "Succeeded in open the database.");
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = databaseOpenHelper.getReadableDatabase();
            cursor = database.query("RSS_FEED", null, null, null, null, null, null);
            startManagingCursor(cursor);
            Log.v("RssListActivity", "Got cursor.");
            SimpleCursorAdapter rssFeedCursorAdapter = new SimpleCursorAdapter(this,
                    R.layout.list_row,
                    cursor,
                    new String[] {"TITLE"},
                    new int[] {R.id.feed_title});
            setListAdapter(rssFeedCursorAdapter);
        } finally {
            if (database != null) {
                database.close();
                Log.v("RssListActivity", "Succeeded in close the database.");
            }
        }
    }
}