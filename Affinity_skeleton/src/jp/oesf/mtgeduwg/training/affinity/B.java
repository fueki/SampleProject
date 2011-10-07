package jp.oesf.mtgeduwg.training.affinity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class B extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b);
		
		((TextView)findViewById(R.id.time_text)).setText(Long.toString(System.currentTimeMillis()));
	}
	

}
