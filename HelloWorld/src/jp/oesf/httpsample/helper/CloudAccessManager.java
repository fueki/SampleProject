package jp.oesf.httpsample.helper;

import org.json.JSONException;

import android.util.Log;

public class CloudAccessManager {

	private static final String TAG = "CloudAccessManager";

	public static String testEcho(String type) {

		String requestJsonString;
		try {
			requestJsonString = JsonHelper.makeTestJsonString();
			Log.v(TAG, requestJsonString);

			String responceString;
			try {
				responceString = HttpHelper.getResponseContent(requestJsonString);
				Log.v(TAG, "response: " + responceString);
				return responceString;
			} catch (Exception e) {
				Log.e(TAG, "testEcho", e);
			}

		} catch (JSONException e1) {
			Log.e(TAG, "testEcho", e1);
		}
		return null;
	}

	public static String getDecos(String type) {

		String requestJsonString;
		try {
			requestJsonString = JsonHelper.makeDecoListJsonString();
			Log.v(TAG, requestJsonString);

			String responceString;
			try {
				responceString = HttpHelper.getResponseContent(requestJsonString);
				Log.v(TAG, "response: " + responceString);
				return responceString;
			} catch (Exception e) {
				Log.e(TAG, "getDecos", e);
			}

		} catch (JSONException e1) {
			Log.e(TAG, "getDecos", e1);
		}
		return null;
	}

	public static String getCategoryList(String type) {

		String requestJsonString;
		try {
			requestJsonString = JsonHelper.makeCategoryListJsonString();
			Log.v(TAG, requestJsonString);

			String responceString;
			try {
				responceString = HttpHelper.getResponseContent(requestJsonString);
				Log.v(TAG, "response: " + responceString);
				return responceString;
			} catch (Exception e) {
				Log.e(TAG, "getCategoryList", e);
			}

		} catch (JSONException e1) {
			Log.e(TAG, "getCategoryList", e1);
		}
		return null;
	}

}
