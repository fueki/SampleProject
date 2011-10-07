package jp.oesf.app.youtubedownloader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Video再生用アクティビティ。
 */
public class VideoActivity extends Activity {
	/** Video用View */
	private VideoView videoView;

	/**
	 * 画面を生成します。
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.video);
		String path = this.getIntent().getStringExtra("key1");
		videoView = (VideoView) findViewById(R.id.VideoView01);
		videoView.setVideoPath(path);
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
	}

	/**
	 * 起動後に動画を再生します。
	 */
	@Override
	protected void onResume() {
		super.onResume();
		videoView.seekTo(0);
		videoView.start();
	}
}
