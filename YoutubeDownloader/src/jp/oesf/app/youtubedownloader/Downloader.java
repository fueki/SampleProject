package jp.oesf.app.youtubedownloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * このプログラムの本体です。 Searchのテキストフィールドを表示します。
 */
public class Downloader extends Activity implements OnClickListener {
	/**
	 * 画面を生成します。
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View searchButton = findViewById(R.id.Button01);
		searchButton.setOnClickListener(this);
	}

	/**
	 * 検索ボタンを押下した処理を行います。
	 * 
	 * @param button
	 *            押されたボタンのウィジェット
	 */
	@Override
	public void onClick(View button) {
		switch (button.getId()) {
		case R.id.Button01:
			Intent intent = new Intent(getApplicationContext(),
					SearchList.class);
			EditText searchText = (EditText) findViewById(R.id.EditText01);
			String searchQuery = searchText.getText().toString();
			intent.putExtra("key1", searchQuery);

			startActivity(intent);

			break;

		default:
			break;
		}
	}
}