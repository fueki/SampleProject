package com.tappli.android.listviewtest;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	/** ボタン */
	private Button button = null;
	/** リストビュー */
	private ListView listView = null;
	/** アダプタ */
	private MyArrayAdapter adapter = null;
	
	/** 画像のID */
	private static final int[] bmpIds = {
		R.drawable.a, R.drawable.b, R.drawable.c
	};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		//アダプタ作成
		adapter = new MyArrayAdapter(this);
		
		listView =  (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);
		//リストが空のときに表示されるViewを指定
		listView.setEmptyView(findViewById(R.id.empty));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			//適当に追加するアイテムを作成
			ListViewItem item = new ListViewItem();
			Date d = new Date();
			item.image = getResources().getDrawable(bmpIds[d.getSeconds() % 3]);
			item.text = d.toLocaleString();
			//追加
			adapter.add(item);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
		//viewは使いまわされる。
		//positionは違うのに、viewのインスタンスは同じということもあるので注意
		ListViewItem item = adapter.getItem(position);
		if (item != null) {
			Toast.makeText(this, item.text, Toast.LENGTH_SHORT).show();
		}
	}
}