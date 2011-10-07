package jp.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class SampleCheckBox extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClickCheckBox(View view){
    	TextView textView = (TextView)findViewById(R.id.textView1);
    	CheckBox checkebox = (CheckBox)findViewById(R.id.checkBox1);
    	
    	if(checkebox.isChecked() == true){
    		Log.v("onClickCheckBox","きてます");
    		textView.setText("チェックされた是");
    	} else {
    		Log.v("onClickCheckBox","きてます");
    		textView.setText("チェックされねぇ");
    	}
    }
}