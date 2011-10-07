package net.dabits.android.nicoplayer.activities;

import java.util.ArrayList;
import java.util.HashMap;

import net.dabits.android.nicoplayer.R;
import net.dabits.android.nicoplayer.serializables.NicoThread;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayActivity extends Activity {
	
	ArrayList<HashMap<String, String>> comments;
	HashMap<String, String> thread;
	String vid;
	String videoUrl;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        Intent intent = getIntent();
        vid = intent.getStringExtra("vid");
        //サンプルとして、適当なh264動画をダウンロードして、適当なサーバにアップロード
        videoUrl = "http://192.168.x.x/nicodev/" + vid + ".mp4";
        NicoThread nt = (NicoThread)intent.getSerializableExtra("NicoThread");
        comments = nt.getComments();
        thread = nt.getThread();
    	
    	VideoView videoView = (VideoView)findViewById(R.id.NicoStage);
    	videoView.requestFocus();
    	videoView.setMediaController(new MediaController(this));
    	
    	videoView.setVideoURI(Uri.parse(videoUrl));
    	videoView.start();
    	
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    }
}