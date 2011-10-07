package jp.oesf.httpsample.helper;

import jp.oesf.httpsample.constant.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonHelper {
	
	public static String makeTestJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = "test.echo";

		// method
		json.put(Constants.Request.METHOD, method);
		
		json.put(Constants.Request.PARAMS, paramsCategoryListJsonString());

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}

	public static String makeDecoListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Constants.Request.METHOD_DECOS_GETLIST;

		// method
		json.put(Constants.Request.METHOD, method);
		
		json.put(Constants.Request.PARAMS, paramsCategoryListJsonString());

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}
	
	public static String makeCategoryListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Constants.Request.METHOD_CATEGORY_GETLIST;

		// method
		json.put(Constants.Request.METHOD, method);
		
		json.put(Constants.Request.PARAMS, paramsCategoryListJsonString());

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}
	
	
	public static JSONObject paramsCategoryListJsonString() throws JSONException {

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Constants.Request.PARAMS_LIMIT, 20);
		jsonParams.put(Constants.Request.PARAMS_OFFSET, 0);

		return jsonParams;
	}
}
