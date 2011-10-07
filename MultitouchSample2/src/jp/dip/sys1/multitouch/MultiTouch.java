package jp.dip.sys1.multitouch;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @author yagi
 * 
 */
public class MultiTouch extends Activity {
	private final static String TAG = MultiTouch.class.getSimpleName();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

}