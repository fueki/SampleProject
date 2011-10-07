package jp.oesf.httpsample;

import jp.oesf.httpsample.helper.CloudAccessManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResponseResultActivity extends Activity {
	private static final String TAG = "HttpSample";
	// 接続先URL
	public static final String URL = "http://batezero.appspot.com/_api/";
//	private static final String URL = "http://[PCのIPアドレス]:8080/oesf.html";	//ネットワーク接続不可
	
	private TextView text;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		text = (TextView)findViewById(R.id.text_content);
		
//		String method = getIntent().getStringExtra("method");
//		if(method.equals(object))
	}

	public void onClickTestButton(View v) {
		String resultJson = CloudAccessManager.testEcho(null);
		text.setText(resultJson);
	}
	public void onClickGetDecoButton(View v) {
		String resultJson = CloudAccessManager.getDecos("search");
		text.setText(resultJson);
	}

}