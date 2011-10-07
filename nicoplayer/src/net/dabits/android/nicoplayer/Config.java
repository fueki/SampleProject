package net.dabits.android.nicoplayer;

public class Config {
	
	public static final class nicovideo {
		public static final String LOGIN_API_URL = "https://secure.nicovideo.jp/secure/login?site=niconico";
		public static final String LOGIN_API_FORM_EMAIL = "mail";
		public static final String LOGIN_API_FORM_PASS = "password";
		public static final String LOGIN_API_COOKIE_NAME = "user_session";
		public static final String LOGIN_API_COOKIE_DOMAIN = ".nicovideo.jp";
		public static final String LOGIN_API_COOKIE_PATH = "/";
		public static final String PLAY_BASE_URL = "http://www.nicovideo.jp/watch/";
		public static final String FLASH_API_URL = "http://www.nicovideo.jp/api/getflv/";
	}
	
    public static final class requestCode {
    	public static final int VideoList = 0;
    	public static final int ACCOUNT = 1;
		public static final int Preload	= 2;
		public static final int Play	= 3;
    }
	
    public static final class login {
    	public static final int FAILED = 0;
    	public static final int NOCONFIG = 1;
    	public static final int SUCCESS = 2;
    }
}
