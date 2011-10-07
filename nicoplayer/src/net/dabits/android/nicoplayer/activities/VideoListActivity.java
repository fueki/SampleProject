package net.dabits.android.nicoplayer.activities;

import java.util.HashMap;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.R;
import net.dabits.android.nicoplayer.procs.VideoListProc;
import net.dabits.android.nicoplayer.serializables.VideoItems;

public class VideoListActivity  extends BaseActivity {
	
	private SimpleAdapter videoItemAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list);
        
        Intent intent = getIntent();
        VideoItems videoItems = (VideoItems)intent.getSerializableExtra("VideoItems");
        

    	VideoListProc videoListProc = new VideoListProc(this);
    	videoListProc.setVideoItems(videoItems.get());
    	videoListProc.draw();
    	
    	videoItemAdapter = videoListProc.getVideoItemAdapter();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	HashMap<String, String> videoItem = (HashMap<String, String>) videoItemAdapter.getItem(position);
    	
		Intent intent = new Intent(this, PreloadActivity.class);
		intent.putExtra("url", videoItem.get("url"));
		startActivityForResult(intent, Config.requestCode.Preload);
    }
}
