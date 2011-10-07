package jp.oesf.httpsample.helper;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonHelper {
	public static class Request {
		public static String METHOD = "method";
		public static String METHOD_DECOS_GETLIST = "search.getDecos";
		public static String METHOD_CATEGORY_GETLIST = "search.getCategoryList";
		
		public static final String PARAMS = "params";
		public static final String PARAMS_TYPE = "type";
		public static final String PARAMS_LIMIT = "limit";
		public static final String PARAMS_OFFSET = "offset";

	}
	
	public static String makeTestJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = "test.echo";

		// method
		json.put(Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Request.PARAMS_LIMIT, 20);
		jsonParams.put(Request.PARAMS_OFFSET, 0);
		json.put(Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}

	public static String makeDecoListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Request.METHOD_DECOS_GETLIST;

		// method
		json.put(Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Request.PARAMS_LIMIT, 20);
		jsonParams.put(Request.PARAMS_OFFSET, 0);
		json.put(Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}
	
	public static String makeCategoryListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Request.METHOD_CATEGORY_GETLIST;

		// method
		json.put(Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Request.PARAMS_LIMIT, 20);
		jsonParams.put(Request.PARAMS_OFFSET, 0);
		json.put(Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}
}
