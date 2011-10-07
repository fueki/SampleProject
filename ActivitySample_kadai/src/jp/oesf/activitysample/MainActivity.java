package jp.oesf.activitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final int NEXTACTIVITY = 123;
	private static final int NEXTACTIVITY2 = 456;
	private TextView textFromActivity;
	private TextView textResultCode;
	private TextView textRequestCode;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textFromActivity = (TextView)findViewById(R.id.text_from_activity);
        textResultCode= (TextView)findViewById(R.id.text_resultcode);
        textRequestCode= (TextView)findViewById(R.id.text_requestcode);
    }
    
    public void onClickNextButton(View v){
    	Log.e("ぼたん", "onClickNextButton");
    	EditText editMessage = (EditText)findViewById(R.id.edit_message);
    	Intent intent = new Intent(this,NextActivity.class);
    	intent.putExtra("message", editMessage.getText().toString());
    	startActivityForResult(intent, NEXTACTIVITY);
    }

    public void onClickNextButton2(View v){
    	Log.e("ぼたん", "onClickNextButton２");
    	EditText editMessage = (EditText)findViewById(R.id.edit_message);
    	Intent intent = new Intent(this,NextActivity2.class);
    	intent.putExtra("message", editMessage.getText().toString());
    	startActivityForResult(intent, NEXTACTIVITY2);
    }

    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.v("MainActivity", "request code=" + requestCode);
		Log.v("MainActivity", "result code=" + resultCode);
		
		if(requestCode == NEXTACTIVITY){
			textFromActivity.setText("From " + NextActivity.class.getSimpleName());
		}else{
			//textFromActivity.setText("From " + NextActivity2.class.getSimpleName());
		}
		textResultCode.setText("Result :" + resultCode);
		textRequestCode.setText("Request:" + requestCode);
		
	}
}