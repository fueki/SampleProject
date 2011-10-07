/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * RssReader起動時のメニュー画面のActivityクラス。
 */
public class RssReaderActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View listButton = findViewById(R.id.list_button);
        listButton.setOnClickListener(this);
    }
    public void onClick(View view) {
        Log.v("RssReaderActivity", "Clicked");

        if (view.getId() == R.id.list_button) {
            startActivity(new Intent(this, RssListActivity.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInfalter = getMenuInflater();
        menuInfalter.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.v("RssReaderActivity", "onOptionsItemSelected start");
    	if (item.getItemId() == R.id.main_menu_add) {
//            new RssFeedRegister(this).registration("http://www.oesf.jp/modules/news/index.php?page=rss");
            new AlertDialog.Builder(this).setTitle(R.string.ok_dialog_label).show();
        	Intent intent = new Intent(this, RegisterService.class);
        	startService(intent);
            Log.v("RssReaderActivity", "onOptionsItemSelected end");
            return true;
        }
        return false;
    }
}