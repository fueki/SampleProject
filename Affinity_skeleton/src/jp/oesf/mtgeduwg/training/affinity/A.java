package jp.oesf.mtgeduwg.training.affinity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class A extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        
        ((TextView)findViewById(R.id.time_text)).setText(Long.toString(System.currentTimeMillis()));
    }
    

 
}