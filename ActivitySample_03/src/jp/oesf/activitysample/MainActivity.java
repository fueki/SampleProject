package jp.oesf.activitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickNextButton(View v){
    	EditText editMessage = (EditText)findViewById(R.id.edit_message);
    	Intent intent = new Intent(this,NextActivity.class);
    	intent.putExtra("message", editMessage.getText().toString());
    	startActivity(intent);
    }
}