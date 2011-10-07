package net.dabits.android.nicoplayer.procs;

import java.net.URLDecoder;
import java.util.HashMap;
import org.apache.http.cookie.Cookie;
import net.dabits.android.nicoplayer.Config;

public class FlashApiProc {
	
	protected HttpProc httpProc;
	protected String result;
	
	public FlashApiProc(){
		httpProc = new HttpProc();
	}
	
	public void setCookie(Cookie cookie){
		httpProc.setCookie(cookie);
	}

	public boolean getFlv(String vid) {
		if(httpProc.get(Config.nicovideo.FLASH_API_URL + vid)){
			result = httpProc.getString();
			if(result.length() > 0){
				return true;
			}
		}
		return false;
	}

	public HashMap<String, String> getResult() {
		HashMap<String, String> res = new HashMap<String, String>();
		String[] tmp;
		String key;
		String val;
		
		String[] results = result.split("&");
		for(int i = 0 ; i < results.length ; i++){
			tmp = results[i].split("=");
			key = URLDecoder.decode(tmp[0]);
			val = URLDecoder.decode(tmp[1]);
			res.put(key, val);
		}
		
		return res;
	}

}
