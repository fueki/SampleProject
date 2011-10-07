package net.dabits.android.nicoplayer.activities;

import net.dabits.android.nicoplayer.Config;
import net.dabits.android.nicoplayer.R;
import net.dabits.android.nicoplayer.tasks.PreloadTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PreloadActivity extends Activity {
	
	String vid;
	String cookie;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        
        vid = url.replaceAll(Config.nicovideo.PLAY_BASE_URL, "");
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	
    	//動画の変換済みを確認
    	
    	
    	//コメント取得
    	PreloadTask preloadTask = new PreloadTask(this);
    	preloadTask.setVid(vid);
    	preloadTask.execute();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	finish();
    }
}