package jp.oesf.httpsample;

import jp.oesf.httpsample.constant.Constants;
import jp.oesf.httpsample.helper.CloudAccessManager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ResponseResultActivity extends Activity {
	private static final String TAG = "ResponseResultActivity";
	
	private TextView text;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		text = (TextView)findViewById(R.id.text_content);
		
		String method = getIntent().getStringExtra(Constants.IntentParam.METHOD);
		if(method.equals(Constants.Request.METHOD_TEST_ECHO)){
			testEcho();
		}
	}

	public void testEcho() {
		String resultJson = CloudAccessManager.testEcho(null);
		text.setText(resultJson);
	}
}