package jp.oesf.mtgeduwg.training.testcontentprovider;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class Main extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
    }

    public void onClickGetList(View view){
    	// Uriオブジェクトを生成する
    	Uri uri = Uri.parse("content://jp.oesf.mtgeduwg.training.rssreader/RSS_FEED");
    	// getContentResolver()メソッドを用いてContentResolverオブジェクトを操作する
    	Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    	
    	// ここからは描画のコードであり、コンテントプロバイダとは無関係
    	SimpleCursorAdapter rssFeedCursorAdapter = new SimpleCursorAdapter(this,
                 R.layout.list_row,
                 cursor,
                 new String[] {"TITLE"},
                 new int[] {R.id.feed_title});
         setListAdapter(rssFeedCursorAdapter);
    }
}