package jp.oesf.httpsample.constant;

public class Constants {
	// 接続先URL
	public static final String URL = "http://batezero.appspot.com/_api/";
//	public static final String URL = "http://192.168.1.100:8080/_api/";
//	private static final String URL = "http://[PCのIPアドレス]:8080/oesf.html";	//ネットワーク接続不可
	public static class IntentParam{
		public static final String METHOD = Request.METHOD;
	}
	
	public static class Request {
		public static String METHOD = "method";
		public static String METHOD_TEST_ECHO = "test.echo";
		public static String METHOD_DECOS_GETLIST = "search.getDecos";
		public static String METHOD_CATEGORY_GETLIST = "search.getCategoryList";
		
		public static final String PARAMS = "params";
		public static final String PARAMS_TYPE = "type";
		public static final String PARAMS_LIMIT = "limit";
		public static final String PARAMS_OFFSET = "offset";

	}

}
