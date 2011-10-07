package jp.oesf.activitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NextActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView textView = (TextView)findViewById(R.id.text_title);
        textView.setText(message);
    }
    
    public void onClickFinishButton(View v){
    	finish();
    }

}