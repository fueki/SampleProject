package jp.oesf.httpsample.helper;

import java.util.ArrayList;

import jp.oesf.httpsample.bean.DecoBean;
import jp.oesf.httpsample.constant.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonHelper {

	public static String makeTestJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = "test.echo";

		// method
		json.put(Constants.Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Constants.Request.PARAMS_LIMIT, 20);
		jsonParams.put(Constants.Request.PARAMS_OFFSET, 0);
		json.put(Constants.Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}

	public static String makeDecoListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Constants.Request.METHOD_DECOS_GETLIST;

		// method
		json.put(Constants.Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Constants.Request.PARAMS_TYPE, "freeword");
		jsonParams.put(Constants.Request.PARAMS_LIMIT, 20);
		jsonParams.put(Constants.Request.PARAMS_OFFSET, 0);
		json.put(Constants.Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}

	public static String makeCategoryListJsonString() throws JSONException {
		JSONObject json = new JSONObject();
		String method = Constants.Request.METHOD_CATEGORY_GETLIST;
		// method
		json.put(Constants.Request.METHOD, method);

		// params
		JSONObject jsonParams = new JSONObject();
		jsonParams.put(Constants.Request.PARAMS_LIMIT, 20);
		jsonParams.put(Constants.Request.PARAMS_OFFSET, 0);
		json.put(Constants.Request.PARAMS, jsonParams);

		String text = json.toString();
		Log.v("JSON", text);
		return text;
	}

	public static ArrayList<DecoBean> decodeDecos(String jsonStr) throws JSONException {
		ArrayList<DecoBean> result = new ArrayList<DecoBean>();
		// toTagList
		JSONObject jsonResponse = new JSONObject(jsonStr);

		if(!checkStatus(jsonResponse)){
			return null;
		}
		
		JSONArray decos = jsonResponse.getJSONArray("decos");
		for(int i = 0; i < decos.length(); i++){
			JSONObject deco = decos.getJSONObject(i);
			long id = deco.getLong("id");
			String file_name = deco.getString("file_name");
			String kind = deco.getString("kind");
			String url = deco.getString("url");
			result.add(new DecoBean(String.valueOf(id), url, file_name, kind));
		}
		
		return result;
	}


	public static boolean checkStatus(JSONObject jsonResponse) throws JSONException {
		String stat = jsonResponse.getString(Constants.Responce.STAT);
		if (stat.equals(Constants.Responce.STAT_OK)) {
			return true;
		} else {
//			JsonErrorBean bean = new JsonErrorBean(stat, jsonResponse.getString(Response.ERROR_NO),
//					jsonResponse.getString(Response.ERROR_MESSAGE));
//			throw new JsonStatErrorException("stat is Failed!", bean);
		}
		return false;
	}

}
