package jp.oesf.httpsample;

import java.util.ArrayList;

import jp.oesf.httpsample.bean.DecoBean;
import jp.oesf.httpsample.constant.Constants;
import jp.oesf.httpsample.helper.CloudAccessManager;
import jp.oesf.httpsample.helper.JsonHelper;
import jp.oesf.httpsample.util.HttpUtil;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
	}

	public void onClickGetDecoButton(View v) {
		Intent intent = new Intent(this, ResponseListActivity.class);
		startActivity(intent);
//		String url = null;
//		String resultJson = CloudAccessManager.getDecos("search");
//		try {
//			ArrayList<DecoBean> decos = JsonHelper.decodeDecos(resultJson);
//			
//			StringBuilder builder = new StringBuilder();
//			for(DecoBean bean:decos){
//				builder.append(bean.toString());
//				builder.append(Constants.BR);
//				url = bean.url;
//				
//				
//			}
//			
//			text.setText(builder.toString());
//			Log.v("", "url:" + url);
//			Bitmap b = HttpUtil.getImage(Constants.HOST + url);
//			ImageView image = (ImageView)findViewById(R.id.imageView1);
//			image.setImageBitmap(b);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			Log.e("HttpSample", "onClickGetDecoButton", e);
//		}
	}
	public void onClickGetCategoryButton(View v) {
//		String resultJson = CloudAccessManager.getCategoryList("search");
//		text.setText(resultJson);
		
		Intent intent = new Intent(this, ResponseGridActivity.class);
		startActivity(intent);
	}

}