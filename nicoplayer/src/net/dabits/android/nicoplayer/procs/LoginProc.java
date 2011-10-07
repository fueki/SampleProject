package net.dabits.android.nicoplayer.procs;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;


import net.dabits.android.nicoplayer.Config;

public class LoginProc {
	
	protected HttpProc httpProc;
	protected Cookie cookie;
	
	public LoginProc(){
		httpProc = new HttpProc();
	}

	public boolean login(String email, String pass) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Config.nicovideo.LOGIN_API_FORM_EMAIL, email));
		params.add(new BasicNameValuePair(Config.nicovideo.LOGIN_API_FORM_PASS, pass));
		
		if(httpProc.post(Config.nicovideo.LOGIN_API_URL, params)){
			cookie = parseCookies();
			if(cookie != null){
				return true;
			}
		}
		return false;
	}
	
	private Cookie parseCookies(){
		Cookie cookie = null;
		List<Cookie> cookies = httpProc.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			if(cookies.get(i).getName().equalsIgnoreCase(Config.nicovideo.LOGIN_API_COOKIE_NAME)){
				cookie = cookies.get(i);
			}
        }
		return cookie;
	}

	public Cookie getCookie() {
		return cookie;
	}

}
