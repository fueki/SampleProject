package jp.oesf.httpsample;

import jp.oesf.httpsample.helper.CloudAccessManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HttpSample extends Activity {
	private static final String TAG = "HttpSample";
	// 接続先URL
	public static final String URL = "http://batezero.appspot.com/_api/";
//	private static final String URL = "http://[PCのIPアドレス]:8080/oesf.html";	//ネットワーク接続不可
	
	private TextView text;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		text = (TextView)findViewById(R.id.text_content);
	}

	public void onClickTestButton(View v) {
//		Intent intent = new Intent(this, ResponseResultActivity.class);
//		startActivity(intent);
		String resultJson = CloudAccessManager.testEcho(null);
		text.setText(resultJson);
	}
	public void onClickGetDecoButton(View v) {
		String resultJson = CloudAccessManager.getDecos("search");
		text.setText(resultJson);
	}
	public void onClickGetCategoryButton(View v) {
		String resultJson = CloudAccessManager.getCategoryList("search");
		text.setText(resultJson);
	}

}