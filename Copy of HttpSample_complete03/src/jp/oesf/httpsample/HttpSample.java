package jp.oesf.httpsample;

import org.json.JSONException;

import jp.oesf.httpsample.constant.Constants;
import jp.oesf.httpsample.helper.CloudAccessManager;
import jp.oesf.httpsample.helper.JsonHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HttpSample extends Activity {
	private static final String TAG = "HttpSample";
	
	private TextView text;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		text = (TextView)findViewById(R.id.text_content);
	}

	public void onClickTestButton(View v) {
		Intent intent = new Intent(this, ResponseResultActivity.class);
		intent.putExtra(Constants.IntentParam.METHOD, Constants.Request.METHOD_TEST_ECHO);
		startActivity(intent);
//		String resultJson = CloudAccessManager.testEcho(null);
//		text.setText(resultJson);
	}
	
	public void onClickGetDecoButton(View v) throws JSONException {
		String resultJson = CloudAccessManager.getApiJsonString(JsonHelper.makeDecoListJsonString());
		text.setText(resultJson);
	}
	
	public void onClickGetCategoryButton(View v) throws JSONException {
		String resultJson = CloudAccessManager.getApiJsonString(JsonHelper.makeCategoryListJsonString());
		text.setText(resultJson);
	}

}