package net.dabits.android.nicoplayer.tasks;

import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.activities.VideoListActivity;
import net.dabits.android.nicoplayer.procs.RssProc;
import net.dabits.android.nicoplayer.serializables.VideoItems;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;


public class FirstVideoListTask extends AsyncTask <String, Integer, Integer> {
	
	private Activity activity;
	private RssProc rssProc;
	
	public FirstVideoListTask(Activity activity){
		this.activity = activity;
	}
	
	@Override
	protected Integer doInBackground(String... args){
		rssProc = new RssProc();
		rssProc.parse("http://www.nicovideo.jp/ranking/fav/daily/all?rss=2.0");
		return null;
	}
	
    @Override
    protected void onPostExecute(Integer result) {
    	VideoItems videoItems = new VideoItems();
    	videoItems.set(rssProc.getVideoItems());
    	
		Intent intent = new Intent(activity, VideoListActivity.class);
		intent.putExtra("VideoItems", videoItems);
		activity.startActivityForResult(intent, Config.requestCode.VideoList);
    }
}