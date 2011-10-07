package jp.oesf.mtgeduwg.training.sendbootcomleted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Main extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.send_broadcast_button).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("a.b.c.d.E");
    	sendBroadcast(intent);
	}
}