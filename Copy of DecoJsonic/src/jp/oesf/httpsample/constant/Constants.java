package jp.oesf.httpsample.constant;

public class Constants {
	// 接続先URL
	public static final String URL = "http://batezero.appspot.com/_api/";
//	public static final String URL = "http://192.168.100.131:8080/_api/";
	public static final String BR = System.getProperty("line.separator");
	public static final String HOST = "http://batezero.appspot.com/";
	
	public static class IntentParam{
		public static final String METHOD = Request.METHOD;
	}
	
	public static class Request{
		public static final String METHOD = "method";
		public static String METHOD_TEST_ECHO = "test.echo";
		public static String METHOD_DECOS_GETLIST = "search.getDecos";
		public static String METHOD_CATEGORY_GETLIST = "search.getCategoryList";
		
		public static final String PARAMS = "params";
		public static final String PARAMS_TYPE = "type";
		public static final String PARAMS_LIMIT = "limit";
		public static final String PARAMS_OFFSET = "offset";

	}
	
	public static class Responce{
		public static final String STAT = "stat";
		public static final String STAT_OK = "OK";
		public static final String STAT_NG = "NG";
		
	}
}
