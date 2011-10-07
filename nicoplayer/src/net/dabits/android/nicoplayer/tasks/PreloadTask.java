package net.dabits.android.nicoplayer.tasks;

import java.util.HashMap;

import org.apache.http.impl.cookie.BasicClientCookie;

import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.activities.PlayActivity;
import net.dabits.android.nicoplayer.procs.CommentProc;
import net.dabits.android.nicoplayer.procs.FlashApiProc;
import net.dabits.android.nicoplayer.serializables.NicoThread;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;


public class PreloadTask extends AsyncTask <String, Integer, Integer> {
	
	private Activity activity;
	private CommentProc commentProc;
	private FlashApiProc flashApiProc;
	private String vid;
	
	public PreloadTask(Activity activity){
		this.activity = activity;
	}
	
	@Override
	protected Integer doInBackground(String... args){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
		BasicClientCookie cookie = new BasicClientCookie(
				Config.nicovideo.LOGIN_API_COOKIE_NAME,
				sp.getString("cookie", "")
		);
		cookie.setDomain(Config.nicovideo.LOGIN_API_COOKIE_DOMAIN);
		cookie.setPath(Config.nicovideo.LOGIN_API_COOKIE_PATH);
		
		flashApiProc = new FlashApiProc();
		flashApiProc.setCookie(cookie);
		if(!flashApiProc.getFlv(vid)){
			
		}
		
		HashMap<String, String> flvInfo = flashApiProc.getResult();
		
		commentProc = new CommentProc();
		commentProc.parse(flvInfo.get("ms"), flvInfo.get("thread_id"));
		
		return null;
	}
	
    @Override
    protected void onPostExecute(Integer result) {
    	NicoThread nt = new NicoThread();
    	nt.setComments(commentProc.getComments());
    	nt.setThread(commentProc.getThread());
    	
		Intent intent = new Intent(activity, PlayActivity.class);
		intent.putExtra("NicoThread", nt);
		intent.putExtra("vid", vid);
		activity.startActivityForResult(intent, Config.requestCode.Play);
    }

	public void setVid(String vid) {
		this.vid = vid;
	}
}