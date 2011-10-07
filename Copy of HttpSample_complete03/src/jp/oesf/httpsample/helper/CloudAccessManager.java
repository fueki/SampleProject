package jp.oesf.httpsample.helper;

import android.util.Log;

public class CloudAccessManager {

	private static final String TAG = "CloudAccessManager";

	public static String getApiJsonString(String type) {

		String requestJsonString = type;
		Log.v(TAG, requestJsonString);

		String responceString;
		try {
			responceString = HttpHelper.getResponseContent(requestJsonString);
			Log.v(TAG, "response: " + responceString);
			return responceString;
		} catch (Exception e) {
			Log.e(TAG, type, e);
		}
		return null;
	}

}
