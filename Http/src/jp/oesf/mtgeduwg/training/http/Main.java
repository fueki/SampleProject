package jp.oesf.mtgeduwg.training.http;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {
	private static final String URL = "http://twitter.com/statuses/user_timeline/GOOGLE.json";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}



}